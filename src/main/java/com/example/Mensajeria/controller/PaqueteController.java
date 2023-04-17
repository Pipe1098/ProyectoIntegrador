package com.example.Mensajeria.controller;

import com.example.Mensajeria.service.PaqueteService;
import com.example.Mensajeria.dto.PaqueteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @GetMapping("/paquetes")
    public List<PaqueteDTO> getAllPaquetes() {
        return paqueteService.findAll();
    }

    @GetMapping("/paquete/{id}")
    public PaqueteDTO getPaqueteById(@PathVariable String id) {
        return paqueteService.findById(id);
    }

    @PostMapping("/paquete")
    @ResponseStatus(HttpStatus.CREATED)
    public PaqueteDTO createPaquete(@RequestBody PaqueteDTO paqueteDTO) {
        return paqueteService.save(paqueteDTO);
    }

    @PutMapping("/paquete/{id}")
    public PaqueteDTO updatePaquete(@PathVariable String id, @RequestBody PaqueteDTO paqueteDTO) {
        return paqueteService.update(id, paqueteDTO);
    }

    @DeleteMapping("/paquete/{id}")
    public void deletePaqueteById(@PathVariable String id) {
        paqueteService.deleteById(id);
    }
}

