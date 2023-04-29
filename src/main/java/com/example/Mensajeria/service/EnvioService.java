package com.example.Mensajeria.service;

import com.example.Mensajeria.mappers.EnvioMapper;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.*;
import com.example.Mensajeria.repository.ClienteRepository;
import com.example.Mensajeria.repository.EmpleadoRepository;
import com.example.Mensajeria.repository.EnvioRepository;
import com.example.Mensajeria.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        int anio = Year.now().getValue();

        int mesAleatorio = ThreadLocalRandom.current().nextInt(1, 13);

        int diaAleatorio = ThreadLocalRandom.current().nextInt(1, LocalDateTime.of(anio, mesAleatorio, 1, 0, 0, 0).toLocalDate().lengthOfMonth() + 1);

        int horaAleatoria = ThreadLocalRandom.current().nextInt(0, 24);

        LocalDateTime fechaAleatoria = LocalDateTime.of(anio, mesAleatorio, diaAleatorio, horaAleatoria, 0);

        return fechaAleatoria;
    }

    public String generar(EnvioDTO envioDTO) {

        if (envioDTO.getCiudadDestino() == null ||
                envioDTO.getCiudadOrigen() == null || envioDTO.getDirDestino() == null
                || envioDTO.getNombreReceptor() == null || envioDTO.getCelReceptor() == null
                || envioDTO.getValorDeclarado() <= 0.0 || envioDTO.getPeso() <= 0.0) {
            throw new ApiRequestException("Revisar, hace falta llenar algunos de los campos o peso y valor son igual a 0");
        }
        Cliente newCliente = validarCliente(envioDTO.getCedula());
        Empleado newEmpleado = validarEmpleado(envioDTO.getCedulaEmpleado());
        TipoPaquete tipoPaquete = asignarTipo(envioDTO.getPeso());
        String codigo = generarNumGuia();
        Double valorEnvio = ValorEnvio(tipoPaquete);
        LocalDateTime fecha = generarFechaAleatoria();
        Paquete paquete = new Paquete(tipoPaquete, envioDTO.getPeso(), envioDTO.getValorDeclarado());

        Envio newEnvio = new Envio(codigo, newCliente, envioDTO.getCiudadOrigen(), envioDTO.getCiudadDestino(),
                envioDTO.getDirDestino(), envioDTO.getNombreReceptor(), envioDTO.getCelReceptor(),
                fecha, EstadoEnvio.RECIBIDO, envioDTO.getValorDeclarado(), envioDTO.getPeso(), valorEnvio, paquete, newEmpleado);

        paqueteRepository.save(paquete);
        envioRepository.save(newEnvio);

        return newEnvio.toString();
    }

    public Double ValorEnvio(TipoPaquete tipoPaquete) {
        if (tipoPaquete.equals(TipoPaquete.LIVIANO)) {
            return 30000.0;
        } else if (tipoPaquete.equals(TipoPaquete.MEDIANO)) {
            return 40000.0;
        } else {
            return 50000.0;
        }
    }

    public List<Envio> obtenerEnvios() {
        List<Envio> envios = envioRepository.findAll();
        return envios;
    }

    public Envio encontrarEnvioPorId(String id) {
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
            throw new ApiRequestException("Cedula de empleado:" + cedula + " no permitida");
        }
        Optional<Empleado> empleado = empleadoRepository.findByCedula(cedula);
        if (empleado.isPresent()) {
            return empleado.get();
        }
        throw new ApiRequestException("El empleado con cedula " + cedula + " debe de estar registrado para poder enviar un paquete");
    }

    public EnvioDTO actualizarEnvio(String id, EnvioDTO envioDTO) {
        if (empleadoRepository.findByCedula(envioDTO.getCedulaEmpleado()).isPresent()) {
            Envio envio = envioRepository.findById(id)
                    .orElseThrow(() -> new ApiRequestException("El numero de guia no se encuentra registrado"));
            envio.setCiudadOrigen(envioDTO.getCiudadOrigen());
            envio.setCiudadDestino(envioDTO.getCiudadDestino());
            envio.setDirDestino(envioDTO.getDirDestino());
            envio.setNombreReceptor(envioDTO.getNombreReceptor());
            envio.setCelReceptor(envioDTO.getCelReceptor());
            envio.setValorDeclarado(envioDTO.getValorDeclarado());
            envio.setPeso(envioDTO.getPeso());
            TipoPaquete tipoP = asignarTipo(envioDTO.getPeso());
            envio.setValorEnvio(ValorEnvio(tipoP));
            Paquete paqExixtente = envio.getPaquete();
            paqExixtente.setTipoPaquete(tipoP);
            paqExixtente.setPeso(envioDTO.getPeso());
            paqExixtente.setValorDeclarado(envioDTO.getValorDeclarado());

            envio = envioRepository.save(envio);

            Optional<Envio> envio1 = envioRepository.findById(envio.getNumeroGuia());
            EnvioDTO envioC = EnvioMapper.INSTANCE.envioToEnvioDTO(envio1.get());
            envioC.setCedula(envio1.get().getCliente().getCedula());
            envioC.setCedulaEmpleado(envio1.get().getEmpleado().getCedula());
            System.out.println(envioC.getCedula() + envioC.getCedulaEmpleado());
            return envioC;
        } else {
            throw new ApiRequestException("El empleado con cedula: " + envioDTO.getCedula() + " no trabaja en nuestra compania");
        }
    }

    public ActualizarEstadoResponse actualizarEstado(String numGuia, String cedulaEmpleado, String estado) {
        String estadoUpper = estado.toUpperCase();
        if(!estadoUpper.equals("RECIBIDO")&&!estadoUpper.equals("EN_RUTA")&&!estadoUpper.equals("ENTREGADO")){
            throw new ApiRequestException("El tipo de estado: "+estado+" no esta esta dentro de las 3 opciones.");
        }
        Empleado empleado = empleadoRepository.findByCedula(cedulaEmpleado)
                .orElseThrow(() -> new ApiRequestException("El empleado con cedula " + cedulaEmpleado + " no existe en nuestra compañia."));

        if (!esEmpleadoValido(empleado)) {
            throw new ApiRequestException("Este empleado no puede realizar el cambio solicitado.");
        }

        Envio envio = envioRepository.findById(numGuia)
                .orElseThrow(() -> new ApiRequestException("El numero guia no se encuentra registrado."));

        if (!esEstadoValido(envio.getEstadoEnvio(), EstadoEnvio.valueOf(estadoUpper))) {
            throw new ApiRequestException("El cambio de estado no cumple con las validaciones.");
        }
        envio.setEstadoEnvio(EstadoEnvio.valueOf(estadoUpper));
        envioRepository.save(envio);

        return new ActualizarEstadoResponse(numGuia, EstadoEnvio.valueOf(estadoUpper));
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
        throw new ApiRequestException("El empleado con cedula " + cedulaEmpleado + " no existe en nuestra compañía.");
    }


    private boolean esEmpleadoValido(Empleado empleado) {
        TipoEmpleado tipo = empleado.getTipoEmpleado();
        return tipo.equals(TipoEmpleado.REPARTIDOR) || tipo.equals(TipoEmpleado.COORDINADOR);
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

    private TipoPaquete asignarTipo(double peso) {
        if (peso > 0.0 && peso <= 2.0) {
            return TipoPaquete.LIVIANO;
        } else if (peso > 2.0 && peso <= 5.0) {
            return TipoPaquete.MEDIANO;
        } else {
            return TipoPaquete.GRANDE;
        }
    }

    public String eliminarPorId(String id) {
        Optional<Envio> envioExistente = envioRepository.findById(id);
        if (!envioExistente.isPresent()) {
            throw new ApiRequestException("Envío no encontrado con ID: " + id);
        }
        envioRepository.deleteById(envioExistente.get().getNumeroGuia());
        return "Envio eliminado exitosamente.";
    }
}


