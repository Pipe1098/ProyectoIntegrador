package com.example.usuarios;


import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.repository.ClienteRepository;
import com.example.Mensajeria.repository.EmpleadoRepository;
import com.example.Mensajeria.repository.EnvioRepository;
import com.example.Mensajeria.repository.PaqueteRepository;
import com.example.Mensajeria.service.EnvioService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnvioServiceTest {


    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void crearEnvioTest() {
        // Arrange
        setUp();


        // Act
        Cliente cliente = new Cliente("John", "Doe", "123456789", "johndoe@example.com", "Calle 123", "Bogotá", "1234567890");
        Empleado empleado = new Empleado("Jane", "Doe", "987654321", "janedoe@example.com", "Carrera 456", "Medellín", "1987654321", 2, "O-", "REPARTIDOR");
        Paquete paquete = new Paquete("liviano", 5.0, 100000.0);
        EnvioDTO enviodto = new EnvioDTO(cliente.getCedula(), empleado.getCedula(), "Bogotá", "Medellín", "Carrera 789", "Juan Pérez", "321654987", paquete.getValorDeclarado(), paquete.getPeso());
        Envio envio = new Envio("abcd369852", cliente, "1234567890", "med", "cali", "cr30", "luis",
                "3002569874", LocalDateTime.now(), EnvioService.EstadoEnvio.RECIBIDO, 2000.0, 5.2, 100.0, paquete);
        when(clienteRepository.findByCedula(cliente.getCedula())).thenReturn(Optional.of(cliente));
        when(empleadoRepository.findByCedula(empleado.getCedula())).thenReturn(Optional.of(empleado));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(paqueteRepository.save(paquete)).thenReturn(paquete);
        when(envioRepository.save(envio)).thenReturn(envio);

        // Act
        String creado = envioService.generate(enviodto);
        String[] substring = creado.split(",");

        // Assert
        assertNotNull(creado);
        assertTrue("coincide", substring[1].contains(envio.getEstadoEnvio().toString()));
        // assertTrue("coincide",substring[0].contains(envio.getNumeroGuia()));
    }

    @org.junit.jupiter.api.Test
    void calcularValorEnvioTest() {
        // Arrange
        Paquete paquete = new Paquete("LIVIANO", 1.0, 50000.0);

        // Act1
        double valorEnvio = this.envioService.ValorEnvio(paquete.getTipoPaquete());

        // Assert
        assertEquals(30000.0, valorEnvio, 0.0);
    }

    @org.junit.jupiter.api.Test
    void generarNumGuia() {
        // Arrange
        setUp();
        EnvioService envioService = mock(EnvioService.class);
        when(envioService.generarNumGuia()).thenReturn("abcd369852");

        // Act
        String numGuia = envioService.generarNumGuia();

        // Assert
        assertEquals("abcd369852", numGuia);
    }

}
