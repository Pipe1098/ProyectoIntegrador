package com.example.Mensajeria.service;

import com.example.Mensajeria.dto.ClienteDTO;
import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteService() {
    }

    public ClienteDTO crear(Cliente cliente) {
        if (validarCliente(cliente)) {
            ClienteDTO clienteDTO = new ClienteDTO(cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(),cliente.getCelular());
            this.clienteRepository.save(cliente);
            return clienteDTO;
        } else {
            throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos");
        }
    }

    public boolean validarCliente(Cliente cliente) {
        if (cliente.getCedula() == null) {
            // La cédula no es numérica o es nula
            return false;
        }

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            return false;
        }

        return true;
    }

        public List<ClienteDTO> crearClientes() {
        this.clienteRepository.save(new Cliente("Carlos", "Perez","3001458964", "Carlos@hotmail.com","CR 50-30","Medellin",123465L,"CRA 20 70"));
        this.clienteRepository.save(new Cliente("Andres", "Montoya","3014589442", "example@hotmail.com","CR 80-20","Pereira",456789L,"CRA 62 43"));
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

    public List<Cliente> getAllClientes() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteByCedula(Long cedula) {
        return clienteRepository.getByCedula(cedula);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setCedula(clienteDTO.getCedula());
            cliente.setCelular(clienteDTO.getCelular());
            cliente.setCorreo(clienteDTO.getCorreoElectronico());
            cliente = clienteRepository.save(cliente);
            return modelMapper.map(cliente, ClienteDTO.class);
        } else {
            throw new ApiRequestException("El cliente con id " + id + " no existe.");
        }
    }


    public String deleteCliente(Long cedula) {
        Optional<Cliente> cliente = clienteRepository.findByCedula(cedula);
        if (cliente.isPresent()) {
            clienteRepository.deleteByCedula(cedula);
        } else {
            throw new ApiRequestException("No se encontró ningun empleado con la cédula: " + cedula);
        }
        return "Eliminado";
    }

}
