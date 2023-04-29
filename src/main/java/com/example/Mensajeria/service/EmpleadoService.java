package com.example.Mensajeria.service;


import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.TipoEmpleado;
import com.example.Mensajeria.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    public EmpleadoService() {
    }
@Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;

    }
    public EmpleadoDTO crear(Empleado empleado) {
        if (validarEmpleado(empleado)) {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(empleado.getCedula(), empleado.getApellido(), empleado.getNombre(), empleado.getCorreo(), empleado.getCelular(), empleado.getTipoEmpleado(), empleado.getAntigueadadEnEmpresa());
            this.empleadoRepository.save(empleado);
            return empleadoDTO;
        } else {
            throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos.");
        }
    }

    public boolean validarEmpleado(Empleado empleado) {
        String cedula = empleado.getCedula();
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();

        if (cedula == null || !cedula.matches("^\\d{10}$")) {
            throw new ApiRequestException("Cedula: " + cedula + " no permitida.");
        }

        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new ApiRequestException("El nombre o el apellido están vacíos o son nulos.");
        }

        if (empleadoRepository.existsByCedula(cedula)) {
            throw new ApiRequestException("El empleado con cedula: " + cedula + " ya esta registrado.");
        }

        return true;
    }

    public List<EmpleadoDTO> crearEmpleados() {
        if(!empleadoRepository.findByCedula("1234567852").isPresent()) {
            this.empleadoRepository.save(new Empleado("Carlos", "Perez", "3001458964", "Carl@hotmail.com", "CR 50-30", "Medellin", "1234567852", 1, "o+", TipoEmpleado.COORDINADOR));
            this.empleadoRepository.save(new Empleado("Juan", "Lopez", "3121858554", "Ju@hotmail.com", "CR 24-20", "Cali", "5671254789", 5, "A+", TipoEmpleado.REPARTIDOR));
            return empleadoRepository.findAll().
                    stream()
                    .map(empleado -> new EmpleadoDTO(
                            empleado.getCedula(),
                            empleado.getApellido(),
                            empleado.getNombre(),
                            empleado.getCorreo(),
                            empleado.getCelular(),
                            empleado.getTipoEmpleado(),
                            empleado.getAntigueadadEnEmpresa()
                    ))
                    .collect(Collectors.toList());
        }
        throw new ApiRequestException("Ya se han creado los empleados para probar la API.");
    }
    public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();

        for (Empleado empleado : empleados) {
            EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
            empleadosDTO.add(empleadoDTO);
        }

        return empleadosDTO;
    }

    public EmpleadoDTO obtenerEmpleadoPorCedula(String cedula) {
        Optional<Empleado> empleadoEncontrado = empleadoRepository.findAll()
                .stream()
                .filter(empleado -> empleado.getCedula().equalsIgnoreCase(cedula))
                .findFirst();
        if (!empleadoEncontrado.isPresent()) {
            throw new ApiRequestException("Empleado con cedula:" +cedula+" no encontrado en la base de datos.");
        } else {
            EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleadoEncontrado.get());
            return empleadoDTO;
        }
    }

    public EmpleadoDTO actualizarEmpleado( String cedula, EmpleadoDTO empleadoDtoActualizado) {

        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);
        if (!empleadoExistente.isPresent()) {
            throw new ApiRequestException("No se encontró ningún empleado con la cédula = "+cedula);
        }
        Empleado empleadoExistente1=empleadoExistente.get();

        empleadoExistente1.setApellido(empleadoDtoActualizado.getApellido());
        empleadoExistente1.setNombre(empleadoDtoActualizado.getNombre());
        empleadoExistente1.setCorreo(empleadoDtoActualizado.getCorreoElectronico());
        empleadoExistente1.setCelular(empleadoDtoActualizado.getCelular());
        empleadoExistente1.setTipoEmpleado(empleadoDtoActualizado.getTipoEmpleado());
        empleadoExistente1.setAntigueadadEnEmpresa(empleadoDtoActualizado.getAntiguedadEnEmpresa());

        empleadoRepository.save(empleadoExistente1);
        EmpleadoDTO empleadoDTO= convertirEmpleadoAEmpleadoDTO(empleadoExistente1);
        return empleadoDTO;
    }


    public String eliminarEmpleadoPorCedula(String cedula) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);

        if (!empleadoExistente.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula = "+cedula);
        }
        empleadoRepository.deleteById(empleadoExistente.get().getId());
        return "Empleado eliminado exitosamente.";
    }

    private EmpleadoDTO convertirEmpleadoAEmpleadoDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(
                empleado.getCedula(),
                empleado.getApellido(),
                empleado.getNombre(),
                empleado.getCorreo(),
                empleado.getCelular(),
                empleado.getTipoEmpleado(),
                empleado.getAntigueadadEnEmpresa()
        );
        return empleadoDTO;
    }

    private Empleado convertirEmpleadoDTOAEmpleado(EmpleadoDTO empleadoDTO) {

        Empleado empleado = new Empleado(
                empleadoDTO.getNombre(),
                empleadoDTO.getApellido(),
                empleadoDTO.getCelular(),
                empleadoDTO.getCorreoElectronico(),
                "empleadoDTO.getDireccion()",
                "empleadoDTO.getCiudad()",
                empleadoDTO.getCedula(),
                1,
                "empleadoDTO.getRh()",
                empleadoDTO.getTipoEmpleado()
        );
        return empleado;
    }
}

