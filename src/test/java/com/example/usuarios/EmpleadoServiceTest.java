package com.example.usuarios;

import com.example.Mensajeria.dto.EmpleadoDTO;;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.TipoEmpleado;
import com.example.Mensajeria.repository.EmpleadoRepository;
import com.example.Mensajeria.service.EmpleadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmpleadoServiceTest {
    private EmpleadoRepository empleadoRepository;
    private EmpleadoService empleadoService;


    @BeforeEach
    public void setUp() {
        this.empleadoRepository = mock(EmpleadoRepository.class);
        this.empleadoService = new EmpleadoService(empleadoRepository);
    }

    @org.junit.jupiter.api.Test
    public void testValidarEmpleadoCedula() {
        //Arrange
        setUp();

        // Act
        Empleado empleado = new Empleado("Juan", "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "123",2,"o+",TipoEmpleado.REPARTIDOR);
        //Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            empleadoService.validarEmpleado(empleado);
        });
    }

    @org.junit.jupiter.api.Test()
    public void testValidarEmpleadoNombreNull() {
        //Arrange
        setUp();
        // Act
        Empleado empleado = new Empleado(null, "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1236547852",2,"o+",TipoEmpleado.REPARTIDOR);
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            empleadoService.validarEmpleado(empleado);
        });
    }

    @org.junit.jupiter.api.Test
    public void testValidarEmpleadoNombreVacio() {
        // Arrange
        setUp();
        // Act
        Empleado empleado = new Empleado("", "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1236547852",2,"o+",TipoEmpleado.REPARTIDOR);
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            empleadoService.validarEmpleado(empleado);
        });
    }


    @org.junit.jupiter.api.Test
    public void testValidarEmpleadoApellidoNull() {
        // Arrange
        EmpleadoService empleadoService = new EmpleadoService();
        // Act
        Empleado empleado = new Empleado("Pepe", null ,"3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1236547852",2,"o+",TipoEmpleado.REPARTIDOR);
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            empleadoService.validarEmpleado(empleado);
        });

    }
    @org.junit.jupiter.api.Test
    public void testValidarEmpleadoCorrecto() {
        // Arrange
        setUp();
        Empleado empleado = new Empleado("Pepe", "Gomez" ,"3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1236547852",2,"o+",TipoEmpleado.REPARTIDOR);
        // Act & Assert
        assertTrue(empleadoService.validarEmpleado(empleado));

    }

    @org.junit.jupiter.api.Test
    public void testCrearEmpleado() {
        //Arrange
        setUp();
        Empleado empleado = new Empleado("Pepe", "Lopez","3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1236547852",2,"o+", TipoEmpleado.REPARTIDOR);
        EmpleadoDTO empleadoDTO = new EmpleadoDTO( empleado.getCedula(),
                empleado.getApellido(),
                empleado.getNombre(),
                empleado.getCorreo(),
                empleado.getCelular(),
                empleado.getTipoEmpleado(),
                empleado.getAntigueadadEnEmpresa()
        );

        // Act
        EmpleadoDTO empleadoCreado = empleadoService.crear(empleado);
        when(empleadoRepository.findById(123L)).thenReturn(Optional.of(empleado));

        // Assert
        Optional<Empleado> empleadoEncontrado = empleadoRepository.findById(123L);
        assertEquals(empleadoDTO.getCedula(), empleadoCreado.getCedula());
        assertTrue(empleadoEncontrado.isPresent());
        assertEquals(empleado, empleadoEncontrado.get());
    }
}

