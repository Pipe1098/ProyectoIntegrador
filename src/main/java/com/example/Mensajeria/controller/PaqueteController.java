package com.example.Mensajeria.controller;

import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PaqueteController {

    private PaqueteService paqueteService;

@Autowired
    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;

}

    @GetMapping("/paquetes")
    public List<PaqueteDTO> getAllPaquetes() {
        return paqueteService.findAll();
    }

    @GetMapping("/paquete/{id}")
    public PaqueteDTO getPaqueteById(@PathVariable Long id) {
        return paqueteService.findById(id);
    }

    @PostMapping("/paquete")
    @ResponseStatus(HttpStatus.CREATED)
    public PaqueteDTO createPaquete(@RequestBody Paquete paquete) {
        return paqueteService.crear(paquete);
    }

    @PutMapping("/paquete/{id}")
    public PaqueteDTO updatePaquete(@PathVariable Long id, @RequestBody PaqueteDTO paqueteDTO) {
        return paqueteService.update(id, paqueteDTO);
    }

    @DeleteMapping("/paquete/{id}")
    public void deletePaqueteById(@PathVariable Long id) {
        paqueteService.deleteById(id);
    }
}

