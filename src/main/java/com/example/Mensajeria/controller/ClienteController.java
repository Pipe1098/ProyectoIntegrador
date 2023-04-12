package com.example.Mensajeria.controller;

import com.example.Mensajeria.service.ClienteService;
import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("cliente/{cedula}")
    public ResponseEntity<ClienteDTO> getClienteByCedula(@PathVariable Long cedula) {
        Optional<Cliente> cliente = clienteService.getClienteByCedula(cedula);
        Cliente cliente1=cliente.get();
        if (cliente1 == null) {
            return ResponseEntity.notFound().build();
        }
        ClienteDTO clienteDTO = modelMapper.map(cliente1, ClienteDTO.class);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody Cliente cliente) {
       ClienteDTO clienteDTO = clienteService.crear(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @PutMapping("cliente/{cedula}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long cedula, @RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteService.getClienteByCedula(cedula);
        if (cliente.get() == null) {
            return ResponseEntity.notFound().build();
        }
        ClienteDTO updatedClienteDTO =clienteService.updateCliente(cedula,clienteDTO );
        return ResponseEntity.ok(updatedClienteDTO);
    }

    @DeleteMapping("cliente/{cedula}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long cedula) {
        Optional<Cliente> cliente = clienteService.getClienteByCedula(cedula);
        if (cliente.get() == null) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deleteCliente(cedula);
        return ResponseEntity.noContent().build();
    }
}

