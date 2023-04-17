package com.example.Mensajeria.Controller;

import com.example.Mensajeria.Service.PaqueteService;

import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PaqueteController {

    private  PaqueteService paqueteService;

@Autowired
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
    public PaqueteDTO createPaquete(@RequestBody Paquete paquete) {
        return paqueteService.save(paquete);
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

