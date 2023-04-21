package com.example.Mensajeria.service;

import com.example.Mensajeria.configurer.EnvioMapper;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.repository.ClienteRepository;
import com.example.Mensajeria.repository.EmpleadoRepository;
import com.example.Mensajeria.repository.EnvioRepository;
import com.example.Mensajeria.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    private EnvioRepository envioRepository;
    private ClienteRepository clienteRepository;
    private PaqueteRepository paqueteRepository;
    private EmpleadoRepository empleadoRepository;

    public EnvioService() {
    }

    @Autowired
    public EnvioService(EnvioRepository envioRepository, ClienteRepository clienteRepository, PaqueteRepository paqueteRepository, EmpleadoRepository empleadoRepository) {
        this.envioRepository = envioRepository;
        this.clienteRepository = clienteRepository;
        this.paqueteRepository = paqueteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public String generarNumGuia() {
        UUID uuid = UUID.randomUUID();
        String codigo = uuid.toString().replace("-", "").substring(0, 10);
        return codigo;
    }

    public LocalDateTime generarFechaAleatoria() {

        // Generar un año aleatorio en el rango especificado
        int anio = Year.now().getValue();

        // Generar un mes aleatorio en el año aleatorio generado
        int mesAleatorio = ThreadLocalRandom.current().nextInt(1, 13);

        // Generar un día aleatorio en el mes aleatorio y año aleatorio generados
        int diaAleatorio = ThreadLocalRandom.current().nextInt(1, LocalDateTime.of(anio, mesAleatorio, 1, 0, 0, 0).toLocalDate().lengthOfMonth() + 1);

        // Generar una hora aleatoria en el día aleatorio generado
        int horaAleatoria = ThreadLocalRandom.current().nextInt(0, 24);

        // Crear el LocalDateTime a partir de los valores aleatorios generados
        LocalDateTime fechaAleatoria = LocalDateTime.of(anio, mesAleatorio, diaAleatorio, horaAleatoria, 0);

        // Retornar el LocalDateTime aleatorio generado
        return fechaAleatoria;
    }


    public enum EstadoEnvio {
        RECIBIDO,
        EN_RUTA,
        ENTREGADO
    }


    public String generate(EnvioDTO envioDTO) {

        validarCliente(envioDTO.getCedula());
        if (envioDTO.getCiudadDestino() == null ||
                envioDTO.getCiudadOrigen() == null || envioDTO.getDirDestino() == null
                || envioDTO.getNombreReceptor() == null || envioDTO.getCelReceptor() == null
                || envioDTO.getValorDeclarado() == 0.0 || envioDTO.getPeso() == 0.0) {
            throw new ApiRequestException("Revisar, hace falta llenar algunos de los campos o peso y valor son igual a 0");
        }
        Cliente newCliente = validarCliente(envioDTO.getCedula());
        String tipoPaquete = asignarTipo(envioDTO.getPeso());
        String codigo = generarNumGuia();
        Double valorEnvio = ValorEnvio(tipoPaquete);
        LocalDateTime fecha = generarFechaAleatoria();

        Paquete paquete = new Paquete(tipoPaquete, envioDTO.getPeso(), envioDTO.getValorDeclarado());
        Envio newEnvio = new Envio(codigo, newCliente, envioDTO.getCedula(),envioDTO.getCiudadOrigen(), envioDTO.getCiudadDestino(),
                envioDTO.getDirDestino(), envioDTO.getNombreReceptor(), envioDTO.getCelReceptor(),
                fecha, EstadoEnvio.RECIBIDO,envioDTO.getValorDeclarado(),envioDTO.getPeso(), valorEnvio, paquete);

        paqueteRepository.save(paquete);
        envioRepository.save(newEnvio);

        return newEnvio.toString();
    }

    private Double ValorEnvio(String tipoPaquete) {
        if (tipoPaquete.contains("LIVIANO")) {
            return 30000.0;
        } else if (tipoPaquete.contains("Mediano")) {
            return 40000.0;
        } else {
            return 50000.0;
        }
    }

    public List<Envio> findAll() {
        List<Envio> envios = envioRepository.findAll();
        return envios;
    }

    public Envio findById(String id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Envío no encontrado con ID: " + id));
        return envio;
    }


    private Cliente validarCliente(String cedula) {
        if (cedula == null || !cedula.matches("^\\d{10}$")) {
            throw new ApiRequestException("Cedula:" + cedula + " no permitida");
        }
        Optional<Cliente> cliente = clienteRepository.findByCedula(cedula);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ApiRequestException("El cliente con cedula " + cedula + " debe de estar registrado para poder enviar un paquete");
    }

    private Empleado validarEmpleado(String cedula) {
        if (cedula == null || !cedula.matches("^\\d{10}$")) {
            throw new ApiRequestException("Cedula:" + cedula + " no permitida");
        }
        Optional<Empleado> empleado = empleadoRepository.findByCedula(cedula);
        if (empleado.isPresent()) {
            return empleado.get();
        }
        throw new ApiRequestException("El empleado con cedula " + cedula + " debe de estar registrado para poder enviar un paquete");
    }

    public EnvioDTO update(String id, EnvioDTO envioDTO) {
        if (empleadoRepository.findByCedula(envioDTO.getCedulaEmpleado()).isPresent()) {
            Envio envio = envioRepository.findById(id)
                    .orElseThrow(() -> new ApiRequestException("El numero de guia no se encuentra registrado"));
            envio.setCiudadOrigen(envioDTO.getCiudadOrigen());
            envio.setCedulaEmpleado(envioDTO.getCedulaEmpleado());
            envio.setClientep(envioDTO.getCedula());
            envio.setCiudadDestino(envioDTO.getCiudadDestino());
            envio.setDirDestino(envioDTO.getDirDestino());
            envio.setNombreReceptor(envioDTO.getNombreReceptor());
            envio.setCelReceptor(envioDTO.getCelReceptor());
            //envio.setHoraEntrega(envioDTO.getHoraEntrega());
            String tipoP=asignarTipo(envioDTO.getPeso());
            Paquete paq = new Paquete(tipoP, envioDTO.getPeso(), envioDTO.getValorDeclarado());
            envio.setValorDeclarado(envioDTO.getValorDeclarado());
            envio.setPaquete(paq);
            paqueteRepository.save(paq);
            envio.setValorEnvio(ValorEnvio(tipoP));
            envio.setPeso(envioDTO.getPeso());
            envio = envioRepository.save(envio);
            Optional<Envio> envio1 = envioRepository.findById(envio.getNumeroGuia());
            return EnvioMapper.INSTANCE.envioToEnvioDTO(envio1.get());
        } else {
            throw new ApiRequestException("El empleado con cedula: " + envioDTO.getCedula() + " no trabaja en nuestra compania");
        }
    }

    public class ActualizarEstadoResponse {
        private String numeroGuia;
        private EstadoEnvio estado;

        public ActualizarEstadoResponse(String numeroGuia, EstadoEnvio estado) {
            this.numeroGuia = numeroGuia;
            this.estado = estado;
        }

        public String getNumeroGuia() {
            return numeroGuia;
        }

        public void setNumeroGuia(String numeroGuia) {
            this.numeroGuia = numeroGuia;
        }

        public EstadoEnvio getEstado() {
            return estado;
        }

        public void setEstado(EstadoEnvio estado) {
            this.estado = estado;
        }
    }


    public ActualizarEstadoResponse actualizarEstado(String numGuia, String cedulaEmpleado, String estado) {
        String estadoUpper=estado.toUpperCase();
        Empleado empleado = empleadoRepository.findByCedula(cedulaEmpleado)
                .orElseThrow(() -> new ApiRequestException("El empleado con cedula " + cedulaEmpleado + " no existe en nuestra compañia"));

        if (!esEmpleadoValido(empleado)) {
            throw new ApiRequestException("Este empleado no puede realizar el cambio solicitado");
        }

        Envio envio = envioRepository.findById(numGuia)
                .orElseThrow(() -> new ApiRequestException("El numero guia no se encuentra registrado"));

        if (!esEstadoValido(envio.getEstadoEnvio(), EstadoEnvio.valueOf(estadoUpper))) {
            throw new ApiRequestException("El cambio de estado no cumple con las validaciones");
        }
        envio.setEstadoEnvio( EstadoEnvio.valueOf(estadoUpper));
        envioRepository.save(envio);

        return new ActualizarEstadoResponse(numGuia,  EstadoEnvio.valueOf(estadoUpper));
    }

    public List<Envio> filtrar(EstadoEnvio estado, String cedulaEmpleado) {
        Optional<Empleado> empleado = this.empleadoRepository.findByCedula(cedulaEmpleado);
        if (empleado.isPresent()) {
            List<Envio> envios = this.envioRepository.findByEstadoEnvio(estado);
            List<EnvioDTO> enviosDTO = envios.stream()
                    .map(envio -> EnvioMapper.INSTANCE.envioToEnvioDTO(envio))
                    .collect(Collectors.toList());
            return envios;
        }
        throw new ApiRequestException("El empleado con cedula " + cedulaEmpleado + " no existe en nuestra compañía");
    }


    private boolean esEmpleadoValido(Empleado empleado) {
        String tipo = empleado.getTipoEmpleado();
        return tipo.equalsIgnoreCase("REPARTIDOR") || tipo.equals("COORDINADOR");
    }

    private boolean esEstadoValido(EstadoEnvio estadoActual, EstadoEnvio estadoNuevo) {
        switch (estadoActual) {
            case RECIBIDO:
                return estadoNuevo == EstadoEnvio.EN_RUTA;
            case EN_RUTA:
            case ENTREGADO:
                return estadoNuevo == EstadoEnvio.ENTREGADO;
            default:
                return false;
        }
    }

    private String asignarTipo(double peso) {
        if (peso > 0.0 && peso <= 2.0) {
            return "LIVIANO";
        } else if (peso > 2.0 && peso <= 5.0) {
            return "MEDIANO";
        } else {
            return "GRANDE";
        }
    }

    public String deleteById(String id) {
        Optional<Envio> envioExistente = envioRepository.findById(id);
        if (!envioExistente.isPresent()) {
            throw new ApiRequestException("Envío no encontrado con ID: " + id);
        }
        envioRepository.deleteById(envioExistente.get().getNumeroGuia());
        return "Envio eliminado exitosamente";
    }
}


