package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final ModelMapper modelMapper;

    public EmpleadoService(EmpleadoRepository empleadoRepository, ModelMapper modelMapper) {
        this.empleadoRepository = empleadoRepository;
        this.modelMapper = modelMapper;
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
        if (!empleado.isPresent()) {
            return null;
        }
        EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado.get());
        return empleadoDTO;
    }

    public EmpleadoDTO addEmpleado(Empleado empleado) {
        if (validarEmpleado(empleado)) {
            EmpleadoDTO empleadoDTO = convertirEmpleadoAEmpleadoDTO(empleado);
            empleadoRepository.save(empleado);
            return empleadoDTO;
        } else {
            throw new ApiRequestException("Los campos de cedula, nombre y apellidos son obligatorios");
        }
    }

    public boolean validarEmpleado(Empleado empleado) {

        if (empleado.getNombre() == null || empleado.getNombre().isEmpty() || empleado.getApellido() == null || empleado.getApellido().isEmpty() || empleado.getCedula() == null) {
            // El nombre el apellido o cedula están vacíos o son nulos
            return false;
        }
        return true;
    }

    public List<EmpleadoDTO> crearEmpleados() {
        this.empleadoRepository.save(new Empleado("Pedro", "Lopez", "3001458964", "Carlos@hotmail.com", "CR 50-30", "Medellin", 6325L, 1, "AB", "Repartidor"));
        this.empleadoRepository.save(new Empleado("Sebastian", "Martinez", "3014589442", "example@hotmail.com", "CR 80-20", "Pereira", 5241L, 1, "o+", "Conductor"));
        return empleadoRepository.findAll().
                stream()
                .map(empleado -> new EmpleadoDTO(
                        empleado.getCedula(),
                        empleado.getApellido(),
                        empleado.getNombre(),
                        empleado.getCorreo(),
                        empleado.getCelular(),
                        empleado.getTipoEmpleado(),
                        empleado.getAntigueadadEnEmpresa()))
                .collect(Collectors.toList());
    }

    public EmpleadoDTO actualizar(Long cedula, EmpleadoDTO empleadoDtoActualizado) {
        // Buscamos al empleado por su cédula
        Optional<Empleado> empleadoExistente = empleadoRepository.findByCedula(cedula);
        if (empleadoExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún empleado registardao con la cédula: " + cedula);
        }
        // Actualizamos los datos del empleado existente con los datos del DTO actualizado
        Empleado empleadoExistente1 = empleadoExistente.get();
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

    public void deleteById(Long cedula) {
        Optional<Empleado> empleadoEncontrado = empleadoRepository.findByCedula(cedula);
        if (!empleadoEncontrado.isPresent()) {
            throw new EntityNotFoundException("Empleado no encontrado con cedula: " + cedula);
        }
        empleadoRepository.deleteById(empleadoEncontrado.get().getId());
    }

}

