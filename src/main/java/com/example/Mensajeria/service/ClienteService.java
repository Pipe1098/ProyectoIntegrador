package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.ClienteDTO;

import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;

import com.example.Mensajeria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ClienteDTO crear(ClienteDTO clientedto) {
        if (validarCliente(clientedto)) {
            Cliente cliente1 = new Cliente(clientedto.getNombre(), clientedto.getApellido(), clientedto.getCelular(),
                    clientedto.getCorreo(), clientedto.getDireccion(), clientedto.getCiudad(), clientedto.getCedula());
            this.clienteRepository.save(cliente1);
            return clientedto;
        } else {
            throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos");
        }
    }

    public boolean validarCliente(ClienteDTO cliente) {
        if (cliente.getCedula() == null || !cliente.getCedula().matches("^\\d{10}$")) {
            throw new ApiRequestException("Cedula:" + cliente.getCedula() + " no permitida");
        }

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            throw new ApiRequestException("El nombre o el apellido están vacíos o son nulos");
        }
        if (clienteRepository.findByCedula(cliente.getCedula()).isPresent()){
            throw new ApiRequestException("El cliente con cedula:"+cliente.getCedula()+" ya esta registrado");
        }

        return true;
    }

    public List<ClienteDTO> crearClientes() {
        this.clienteRepository.save(new Cliente("Carlos", "Perez", "3001458964", "Carlos@hotmail.com", "CR 50-30", "Medellin", "4558589409"));
        this.clienteRepository.save(new Cliente("Andres", "Montoya", "3014589442", "example@hotmail.com", "CR 80-20", "Pereira", "1234567895"));
        return clienteRepository.findAll().
                stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getCelular(),
                        cliente.getCorreo(),
                        cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion()))
                .collect(Collectors.toList());
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().
                map(cliente -> new ClienteDTO(
                        cliente.getNombre(), cliente.getApellido(),
                        cliente.getCelular(), cliente.getCorreo(),
                        cliente.getCedula(), cliente.getCiudad(), cliente.getDireccion())).collect(Collectors.toList());
    }

    public ClienteDTO obtenerClientePorCedula(String cedula) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getCedula().equalsIgnoreCase(cedula))
                .findFirst();


        if (!clienteEncontrado.isPresent()) {
            // Manejo del caso en que no se encuentra el cliente
            throw new ApiRequestException("El cliente con la cedula:"+cedula+" no se encuentra registrado.");
        } else {
            ClienteDTO clienteDTO = new ClienteDTO(clienteEncontrado.get().getNombre(), clienteEncontrado.get().getApellido(), clienteEncontrado.get().getCelular(),
                    clienteEncontrado.get().getCorreo(), clienteEncontrado.get().getCedula(), clienteEncontrado.get().getCiudad(), clienteEncontrado.get().getDireccion());
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
            throw new ApiRequestException("El cliente con cedula " + cedula + " no se encuentra registrado.");
        }
    }

    public String eliminarClientePorCedula(String cedula) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCedula(cedula);

        if (!clienteExistente.isPresent()) {
            throw new ApiRequestException("No se encontró ningún cliente con la cédula = " + cedula);
        }
        clienteRepository.deleteById(clienteExistente.get().getId());
        return "Cliente eliminado correctamente.";
    }
}


