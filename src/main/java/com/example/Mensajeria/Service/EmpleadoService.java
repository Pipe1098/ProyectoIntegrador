package com.example.Mensajeria.Service;


import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Empleado;
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
            throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos");
        }
    }

    private boolean validarEmpleado(Empleado empleado) {
        if (empleado.getCedula()==0) {
            // La cédula no es numérica o es nula
            return false;
        }

        if (empleado.getNombre() == null || empleado.getNombre().isEmpty() || empleado.getApellido() == null || empleado.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            return false;
        }

        return true;
    }
    public List<EmpleadoDTO> crearEmpleados() {
        this.empleadoRepository.save(new Empleado("Carlos", "Perez","3001458964", "Carl@hotmail.com","CR 50-30","Medellin",1234,1,"o+","conductor"));
        this.empleadoRepository.save(new Empleado("Juan", "Lopez","3121858554", "Ju@hotmail.com","CR 24-20","Cali",567,5,"A+","repartidor"));
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
    public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();

        for (Empleado empleado : empleados) {
            EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
            empleadosDTO.add(empleadoDTO);
        }

        return empleadosDTO;
    }

    public EmpleadoDTO obtenerEmpleadoPorCedula(long cedula) {
        Empleado empleadoEncontrado = empleadoRepository.findAll()
                .stream()
                .filter(empleado -> empleado.getCedula() == cedula)
                .findFirst()
                .orElse(null);

        if (empleadoEncontrado == null) {
            // Manejo del caso en que no se encuentra el empleado
            return null;
        } else {
            EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleadoEncontrado);
            return empleadoDTO;
        }
    }

    public EmpleadoDTO actualizarEmpleado(int cedula, EmpleadoDTO empleadoDtoActualizado) {
        // Buscamos al empleado por su cédula
        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);
        if (!empleadoExistente.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula = "+cedula);
        }
        Empleado empleadoExistente1=empleadoExistente.get();
        // Actualizamos los datos del empleado existente con los datos del DTO actualizado
        empleadoExistente1.setApellido(empleadoDtoActualizado.getApellido());
        empleadoExistente1.setNombre(empleadoDtoActualizado.getNombre());
        empleadoExistente1.setCorreo(empleadoDtoActualizado.getCorreoElectronico());
        empleadoExistente1.setCelular(empleadoDtoActualizado.getCelular());
        empleadoExistente1.setTipoEmpleado(empleadoDtoActualizado.getTipoEmpleado());
        empleadoExistente1.setAntigueadadEnEmpresa(empleadoDtoActualizado.getAntiguedadEnEmpresa());
        // Guardamos los cambios en la base de datos
        empleadoRepository.save(empleadoExistente1);
        // Retornamos el DTO actualizado
        return empleadoDtoActualizado;
    }


    public void eliminarEmpleadoPorCedula(int cedula) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);

        if (!empleadoExistente.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula = "+cedula);
        }
        empleadoRepository.deleteById(empleadoExistente.get().getId());
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
                "empleadoDTO.getCorreo()",
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

