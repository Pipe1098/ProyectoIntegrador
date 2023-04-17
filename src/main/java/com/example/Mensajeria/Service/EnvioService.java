package com.example.Mensajeria.Service;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.repository.EnvioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;
   // private final ModelMapper modelMapper;
@Autowired
    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;

    }

 /*   public List<EnvioDTO> findAll() {
        List<Envio> envios = envioRepository.findAll();
        return envios.stream()
                .map(envio -> modelMapper.map(envio, EnvioDTO.class))
                .collect(Collectors.toList());
    }

    public EnvioDTO findById(String id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Envío no encontrado con ID: " + id));
        return modelMapper.map(envio, EnvioDTO.class);
    }

    public EnvioDTO save(EnvioDTO envioDTO) {
        Envio envio = modelMapper.map(envioDTO, Envio.class);
        envio = envioRepository.save(envio);
        return modelMapper.map(envio, EnvioDTO.class);
    }

    public EnvioDTO update(String id, EnvioDTO envioDTO) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Envío no encontrado con ID: " + id));
        envio.setCiudadOrigen(envioDTO.getCiudadOrigen());
        envio.setDirDestino(envioDTO.getDirDestino());
        envio.setHoraEntrega(envioDTO.getHoraEntrega());
        envio.setPaquete(modelMapper.map(envioDTO.getPaquete(), Paquete.class));
        envio = envioRepository.save(envio);
        return modelMapper.map(envio, EnvioDTO.class);
    }

    public void deleteById(String id) {
        if (!envioRepository.existsById(id)) {
            throw new EntityNotFoundException("Envío no encontrado con ID: " + id);
        }
        envioRepository.deleteById(id);
    }*/
}


