package com.example.usuarios;

import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.repository.ClienteRepository;
import com.example.Mensajeria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteCedula() {
        //Arrange
        setUp();

        // Act
        Cliente cliente = new Cliente("Juan", "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234565");
        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());

        //Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.validarCliente(clienteDTO);
        });
    }

    @org.junit.jupiter.api.Test()
    public void testValidarClienteNombreNull() {
        //Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente(null, "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.validarCliente(clienteDTO);
        });
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteNombreVacio() {
        // Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente("", "Perez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.validarCliente(clienteDTO);
        });
    }


    @org.junit.jupiter.api.Test
    public void testValidarClienteApellidoNull() {
        // Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente("Juan", null, "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.validarCliente(clienteDTO);
        });
    }


    @org.junit.jupiter.api.Test
    public void testValidarClienteApellidoVacio() {
        // Arrange
        setUp();
        Cliente cliente = new Cliente("Pepe", "", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());
        // Assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.validarCliente(clienteDTO);
        });
    }


    @org.junit.jupiter.api.Test
    public void testValidarClienteCorrecto() {
        // Arrange
        setUp();
        Cliente cliente = new Cliente("Pepe", "Lopez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());
        // Act & Assert
        assertTrue(clienteService.validarCliente(clienteDTO));

    }

    @org.junit.jupiter.api.Test
    public void testCrearCliente() {
        //Arrange
        setUp();
        Cliente cliente = new Cliente("Pepe", "Lopez", "3002568974",
                "juan@example.com", "Calle 123", "Medellin", "1234985624");
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion());

        // Act
        ClienteDTO clienteCreado = clienteService.crear(clienteDTO);
        when(clienteRepository.findById(123L)).thenReturn(Optional.of(cliente));

        // Assert
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(123L);
        assertEquals(clienteDTO, clienteCreado);
        assertTrue(clienteEncontrado.isPresent());
        assertEquals(cliente, clienteEncontrado.get());
    }
}
