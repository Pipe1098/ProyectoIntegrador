package com.example.Mensajeria.service;


import com.example.Mensajeria.configurer.PaqueteMapper;
import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteService {

    private PaqueteRepository paqueteRepository;

    public PaqueteService() {
    }

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }


    public List<PaqueteDTO> findAll() {
        List<Paquete> paquetes = paqueteRepository.findAll();
        return paquetes.stream()
                .map(paquete -> PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete))
                .collect(Collectors.toList());
    }

    public PaqueteDTO findById(Long id) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        PaqueteDTO paqueteDTO = PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);
        return paqueteDTO;
    }
  *//*  Paquete paquete = new Paquete("123", "Tipo A", 1.5, 100.0);
    PaqueteDTO paqueteDTO = PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);*//*

    public PaqueteDTO crear(Paquete paquete) {
        paquete = paqueteRepository.save(paquete);
        //Paquete paquete = modelMapper.map(paqueteDTO, Paquete.class);
        PaqueteDTO paqueteDTO = PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);

        return paqueteDTO;
    }

    public PaqueteDTO update(Long id, PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        //paquete.setTipoPaquete(paqueteDTO.getTipoPaquete());
        paquete.setValorDeclarado(paqueteDTO.getValorDeclarado());
        paquete.setPeso(paqueteDTO.getPeso());
        paquete = paqueteRepository.save(paquete);
        return PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);
    }

    public void deleteById(Long id) {
        if (!paqueteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paquete no encontrado con ID: " + id);
        }
        paqueteRepository.deleteById(id);
    
}

