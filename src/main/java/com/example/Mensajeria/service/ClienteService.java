package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    private ClienteDTO convertirClienteAClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCorreo(),
                cliente.getCelular()
        );
        return clienteDTO;
    }

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = convertirClienteAClienteDTO(cliente);
            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }

    public ClienteDTO getClienteByCedula(Long cedula) {
        Optional<Cliente> cliente = clienteRepository.findByCedula(cedula);
        if (!cliente.isPresent()) {
            return null;
        }
        ClienteDTO clienteDTO = convertirClienteAClienteDTO(cliente.get());
        return clienteDTO;
    }

    public ClienteDTO addCliente(Cliente cliente) {
        if (validarCliente(cliente)) {
            ClienteDTO clienteDTO = convertirClienteAClienteDTO(cliente);
            clienteRepository.save(cliente);
            return clienteDTO;
        } else {
            throw new ApiRequestException("Los campos de cedula, nombre y apellidos son obligatorios");
        }
    }

    public boolean validarCliente(Cliente cliente) {

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty() || cliente.getCedula() == null) {
            // El nombre, apellido o cedula están vacíos o son nulos
            return false;
        }
        return true;
    }

    public List<ClienteDTO> crearClientes() {
        this.clienteRepository.save(new Cliente("Carlos", "Perez", "3001458964", "Carlos@hotmail.com", "CR 50-30", "Medellin", 123465L, 1l));
        this.clienteRepository.save(new Cliente("Andres", "Montoya", "3014589442", "example@hotmail.com", "CR 80-20", "Pereira", 456789L, 2l));
        return clienteRepository.findAll().
                stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getCedula(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getCorreo(),
                        cliente.getCelular()))
                .collect(Collectors.toList());
    }

    public ClienteDTO updateCliente(Long cedula, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCedula(cedula);
        if (optionalCliente.isPresent()) {
            Cliente cliente1 = optionalCliente.get();
            cliente1.setNombre(clienteDTO.getNombre());
            cliente1.setApellido(clienteDTO.getApellido());
            cliente1.setCedula(clienteDTO.getCedula());
            cliente1.setCelular(clienteDTO.getCelular());
            cliente1.setCorreo(clienteDTO.getCorreoElectronico());
            clienteRepository.save(cliente1);
            return modelMapper.map(clienteDTO, ClienteDTO.class);
        } else {
            throw new ApiRequestException("No se encontró ningún cliente registrado con la cédula: " + cedula);
        }
    }


    public void deleteById(Long cedula) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findByCedula(cedula);
        if (!clienteEncontrado.isPresent()) {
            throw new EntityNotFoundException("Cliente no encontrado con cedula: " + cedula);
        }
        clienteRepository.deleteById(clienteEncontrado.get().getId());
    }

}

