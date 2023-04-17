package com.example.Mensajeria.controller;

import com.example.Mensajeria.service.ClienteService;
import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long cedula) {
        ClienteDTO cliente = clienteService.getClienteByCedula(cedula);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody Cliente cliente) {
        ClienteDTO clienteDTO = clienteService.addCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @PostMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> addClientes() {
        List<ClienteDTO> clientes = clienteService.crearClientes();
        return ResponseEntity.status(HttpStatus.CREATED).body(clientes);
    }

    @PutMapping("cliente/{cedula}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long cedula, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO cliente = clienteService.updateCliente(cedula, clienteDTO);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cliente/{cedula}")
    public String deleteClienteById(@PathVariable Long cedula) {
        clienteService.deleteById(cedula);
        return "Cliente con cedula: " + cedula + " eliminado exitosamente";
    }
}

