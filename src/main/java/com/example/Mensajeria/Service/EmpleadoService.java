package com.example.Mensajeria.Service;

import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    private ModelMapper modelMapper;

    public EmpleadoService(EmpleadoRepository empleadoRepository, ModelMapper modelMapper) {
        this.empleadoRepository = empleadoRepository;
        this.modelMapper = modelMapper;
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

    public EmpleadoDTO obtenerEmpleadoPorCedula(Long cedula) {
        Empleado empleado = empleadoRepository.findByCedula(cedula);
        if (empleado == null) {
            return null;
        }
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
        return empleadoDTO;
    }

    public EmpleadoDTO guardarEmpleado(Empleado empleado) {
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
        empleadoRepository.save(empleado);
        return empleadoDTO;
    }

    public EmpleadoDTO actualizar(Long cedula, EmpleadoDTO empleadoDtoActualizado) {
        // Buscamos al empleado por su cédula
        Empleado empleadoExistente = empleadoRepository.findByCedula(cedula);
        if (empleadoExistente == null) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula proporcionada");
        }
        // Actualizamos los datos del empleado existente con los datos del DTO actualizado
        empleadoExistente.setApellido(empleadoDtoActualizado.getApellido());
        empleadoExistente.setNombre(empleadoDtoActualizado.getNombre());
        empleadoExistente.setCorreo(empleadoDtoActualizado.getCorreoElectronico());
        empleadoExistente.setCelular(empleadoDtoActualizado.getCelular());
        empleadoExistente.setTipoEmpleado(empleadoDtoActualizado.getTipoEmpleado());
        empleadoExistente.setAntigueadadEnEmpresa(empleadoDtoActualizado.getAntiguedadEnEmpresa());
        // Guardamos los cambios en la base de datos
        empleadoRepository.save(empleadoExistente);
        // Retornamos el DTO actualizado
        return modelMapper.map(empleadoExistente, EmpleadoDTO.class);
    }


    public void eliminarEmpleadoPorCedula(Long cedula) {
        empleadoRepository.deleteById(cedula);
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

