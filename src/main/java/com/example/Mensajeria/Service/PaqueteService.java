package com.example.Mensajeria.Service;

import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.repository.PaqueteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final ModelMapper modelMapper;

    public PaqueteService(PaqueteRepository paqueteRepository, ModelMapper modelMapper) {
        this.paqueteRepository = paqueteRepository;
        this.modelMapper = modelMapper;
    }

    public List<PaqueteDTO> findAll() {
        List<Paquete> paquetes = paqueteRepository.findAll();
        return paquetes.stream()
                .map(paquete -> modelMapper.map(paquete, PaqueteDTO.class))
                .collect(Collectors.toList());
    }

    public PaqueteDTO findById(String id) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        return modelMapper.map(paquete, PaqueteDTO.class);
    }

    public PaqueteDTO save(PaqueteDTO paqueteDTO) {
        Paquete paquete = modelMapper.map(paqueteDTO, Paquete.class);
        paquete = paqueteRepository.save(paquete);
        return modelMapper.map(paquete, PaqueteDTO.class);
    }

    public PaqueteDTO update(String id, PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        paquete.setTipoPaquete(paqueteDTO.getTipoPaquete());
        paquete.setValorDeclarado(paqueteDTO.getValorDeclarado());
        paquete.setPeso(paqueteDTO.getPeso());
        paquete = paqueteRepository.save(paquete);
        return modelMapper.map(paquete, PaqueteDTO.class);
    }

    public void deleteById(String id) {
        if (!paqueteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paquete no encontrado con ID: " + id);
        }
        paqueteRepository.deleteById(id);
    }
}

