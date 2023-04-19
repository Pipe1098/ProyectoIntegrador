package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.ClienteDTO;

import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;

import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    //private  ModelMapper modelMapper;
@Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO crear(Cliente cliente) {
        if (validarCliente(cliente)) {
            ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(), cliente.getApellido(), cliente.getCelular(), cliente.getCorreo(), cliente.getCedula());
            this.clienteRepository.save(cliente);
            return clienteDTO;
        } else {
            throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos");
        }
    }

    public boolean validarCliente(Cliente cliente) {
        if (cliente.getCedula()==null|| !cliente.getCedula().matches("^\\d{10}$")) {
            throw new ApiRequestException("Cedula: " + cliente.getCedula()+ "no permitida");
        }

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            throw new ApiRequestException("El nombre o el apellido están vacíos o son nulos");
        }

        return true;
    }

    public List<ClienteDTO> crearClientes() {
        this.clienteRepository.save(new Cliente("Carlos", "Perez","3001458964", "Carlos@hotmail.com","CR 50-30","Medellin","4558589409","CRA 20 70"));
        this.clienteRepository.save(new Cliente("Andres", "Montoya","3014589442", "example@hotmail.com","CR 80-20","Pereira","1234567895","CRA 62 43"));
        return clienteRepository.findAll().
                stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getCelular(),
                        cliente.getCorreo(),
                        cliente.getCedula()))
                .collect(Collectors.toList());
    }
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().
                map(cliente -> new ClienteDTO(
                        cliente.getNombre(), cliente.getApellido(),
                        cliente.getCelular(), cliente.getCorreo(),
                        cliente.getCedula())).collect(Collectors.toList());
    }

    public ClienteDTO obtenerClientePorCedula(String cedula) {
        Cliente clienteEncontrado = clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getCedula() == cedula)
                .findFirst()
                .orElse(null);

        if (clienteEncontrado == null) {
            // Manejo del caso en que no se encuentra el cliente
            return null;
        } else {
            ClienteDTO clienteDTO = new ClienteDTO(clienteEncontrado.getNombre(), clienteEncontrado.getApellido(), clienteEncontrado.getCelular(), clienteEncontrado.getCorreo(), clienteEncontrado.getCedula());
            return clienteDTO;
        }
    }

    public ClienteDTO actualizarCliente(String cedula, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCedula(cedula);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setCelular(clienteDTO.getCelular());
            cliente.setCorreo(clienteDTO.getCorreo());
            cliente = clienteRepository.save(cliente);
            return clienteDTO;
        } else {
            throw new ApiRequestException("El cliente con cedula " + cedula+ " no se encuentra registrado.");
        }
    }

    public void eliminarClientePorCedula(String cedula) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCedula(cedula);

        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún cliente con la cédula = "+cedula);
        }
        clienteRepository.deleteById(clienteExistente.get().getId());
    }
    }


