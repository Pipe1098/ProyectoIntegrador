package com.example.Mensajeria.controller;

import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.service.ClienteService;
import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.model.Cliente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
            @ApiResponse(code = 201, message = "Cliente creado con exito"),
            @ApiResponse(code = 404, message = "Cliente no encontrado"),
            @ApiResponse(code = 400, message = " Dato/s  mal ingresado/s"),
            @ApiResponse(code = 401, message = " No esta autorizado para realizar esta operación"),
            @ApiResponse(code = 403, message = " Operacion prohibida"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })

    @ApiOperation(value = "Crear un cliente", notes = "Crea un nuevo cliente en la base de datos con la información proporcionada en el cuerpo de la solicitud.", response = ClienteDTO.class)
    @PostMapping("/cliente")
    public ClienteDTO crear(@RequestBody ClienteDTO clientedto) {
        return this.clienteService.crear(clientedto);
    }

    @ApiOperation(value = "Crear clientes", notes = "Crea  una lista de clientes por defecto para probar la API.", response = Cliente.class)
    //@PreAuthorize("hasRole('READ')")
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearClientes() {
        this.clienteService.crearClientes();
        return new ResponseEntity("Se crearon las clientes por defecto.", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtener clientes", notes = "Muestra todos los clientes registrados en la base de datos.", response = ClienteDTO.class)
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> clientes = this.clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @ApiOperation(value = "Actualizar cliente", notes = "Permite actualizar un cliente registrado en la base de datos.", response = ClienteDTO.class)
    @PutMapping("/cliente/{cedula}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@ApiParam(value = "Digite la cedula del cliente que necesita actualizar.", required = true) @PathVariable String cedula, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(cedula, clienteDTO);
        if (clienteActualizado != null) {
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Obtener cliente por su cedula ", notes = "Permite obtener datos de un cliente registrado en la base de datos", response = ClienteDTO.class)
    @GetMapping("cliente/{cedula}")
    public ResponseEntity<ClienteDTO> obtenerClinetePorId(@ApiParam(value = "Digite la cedula del cliente del cual requiere obtener información", required = true) @PathVariable String cedula) {
        ClienteDTO clienteEncontrado = clienteService.obtenerClientePorCedula(cedula);
        if (clienteEncontrado != null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            throw new ApiRequestException("Cliente con cedula: " + cedula + " no encontrado en el sistema");
        }
    }

    @ApiOperation(value = "Eliminar cliente por su cedula", notes = "Permite eliminar un cliente registrado en la base de datos por medio del numero de cedula", response = String.class)
    @DeleteMapping("cliente/{cedula}")

    public String eliminarClientePorId(@ApiParam(value = "Digite la cedula del cliente que desea eliminar", required = true) @PathVariable("cedula") String cedula) {
        String msg = this.clienteService.eliminarClientePorCedula(cedula);
        return msg;
    }

}
