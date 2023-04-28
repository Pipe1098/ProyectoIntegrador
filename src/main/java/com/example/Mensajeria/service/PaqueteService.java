package com.example.Mensajeria.service;


import com.example.Mensajeria.mappers.PaqueteMapper;
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


    public List<PaqueteDTO> obtenerPaquetes() {
        List<Paquete> paquetes = paqueteRepository.findAll();
        return paquetes.stream()
                .map(paquete -> PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete))
                .collect(Collectors.toList());
    }

    public PaqueteDTO encontrarPorId(Long id) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        PaqueteDTO paqueteDTO = PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);
        return paqueteDTO;
    }


    public PaqueteDTO crear(Paquete paquete) {
        paquete = paqueteRepository.save(paquete);
        PaqueteDTO paqueteDTO = PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);

        return paqueteDTO;
    }

    public PaqueteDTO actualizarPaquete(Long id, PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado con ID: " + id));
        paquete.setValorDeclarado(paqueteDTO.getValorDeclarado());
        paquete.setPeso(paqueteDTO.getPeso());
        paquete = paqueteRepository.save(paquete);
        return PaqueteMapper.INSTANCE.paqueteToPaqueteDTO(paquete);
    }

    public String eliminarPorId(Long id) {
        if (!paqueteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paquete no encontrado con ID: " + id);
        }
        String msg = "Paquete eliminado con exito";
        paqueteRepository.deleteById(id);
        return msg;

    }
}

