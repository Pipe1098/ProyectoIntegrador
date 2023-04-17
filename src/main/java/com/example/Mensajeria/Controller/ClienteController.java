package com.example.Mensajeria.Controller;

import com.example.Mensajeria.Service.ClienteService;
import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.model.Cliente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente creado con exito"),
            @ApiResponse(code = 404, message = "No se pudo crear el cliente con los datos ingresados"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })

    @ApiOperation(value = "Crear un cliente", notes= "Crea un nuevo cliente en la base de datos con la información proporcionada en el cuerpo de la solicitud.", response = ClienteDTO.class)
    @PostMapping("/cliente")
    public ClienteDTO crear(@RequestBody Cliente cliente) {
        return this.clienteService.crear(cliente);
    }

    @ApiOperation(value = "Crear clientes", notes= "Crea  una lista de clientes por defecto para probar la api.", response = ClienteDTO.class)
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearClientes() {
        this.clienteService.crearClientes();
        return new ResponseEntity("Se crearon las clientes por defecto.", HttpStatus.CREATED);
    }
    @ApiOperation(value = "Obtener clientes", notes= "Muestra todos los clientes registrados en la base de datos", response = ClienteDTO.class)
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> clientes = this.clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/cliente/{cedula}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable int cedula, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(cedula,clienteDTO);
        if (clienteActualizado!= null) {
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("cliente/{cedula}")
    public ResponseEntity<ClienteDTO> obtenerClientePorCedula(@PathVariable long cedula) {
       ClienteDTO clienteEncontrado= clienteService.obtenerClientePorCedula(cedula);
        if (clienteEncontrado!= null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("cliente/{cedula}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("cedula") int cedula) {
        this.clienteService.eliminarClientePorCedula(cedula);
        return ResponseEntity.noContent().build();
    }

}
