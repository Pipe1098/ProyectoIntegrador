package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
        Optional<Empleado> empleado = empleadoRepository.findByCedula(cedula);
        if (empleado == null) {
            return null;
        }
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado.get());
        return empleadoDTO;
    }

    public EmpleadoDTO guardarEmpleado(Empleado empleado) {
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
        empleadoRepository.save(empleado);
        return empleadoDTO;
    }

    public EmpleadoDTO actualizar(Long cedula, EmpleadoDTO empleadoDtoActualizado) {
        // Buscamos al empleado por su cédula
        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);
        if (empleadoExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula proporcionada");
        }
        // Actualizamos los datos del empleado existente con los datos del DTO actualizado
        Empleado empleadoExistente1=empleadoExistente.get();
        empleadoExistente1.setApellido(empleadoDtoActualizado.getApellido());
        empleadoExistente1.setNombre(empleadoDtoActualizado.getNombre());
        empleadoExistente1.setCorreo(empleadoDtoActualizado.getCorreoElectronico());
        empleadoExistente1.setCelular(empleadoDtoActualizado.getCelular());
        empleadoExistente1.setTipoEmpleado(empleadoDtoActualizado.getTipoEmpleado());
        empleadoExistente1.setAntigueadadEnEmpresa(empleadoDtoActualizado.getAntiguedadEnEmpresa());
        // Guardamos los cambios en la base de datos
        empleadoRepository.save(empleadoExistente1);
        // Retornamos el DTO actualizado
        return modelMapper.map(empleadoDtoActualizado, EmpleadoDTO.class);
    }


    public EmpleadoDTO eliminarEmpleadoPorCedula(Long cedula) {
        Optional<Empleado> empleado = empleadoRepository.findByCedula(cedula);
        if (empleado == null) {
            return null;
        }
        empleadoRepository.deleteByCedula(cedula);
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado.get());
        return empleadoDTO;
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
}

