package com.example.Mensajeria.Controller;

import com.example.Mensajeria.Service.EnvioService;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EnvioController {

  /*  private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping("/envios")
    public List<EnvioDTO> findAll() {
        return envioService.findAll();
    }

    @GetMapping("/envio/{id}")
    public EnvioDTO findById(@PathVariable String id) {
        return envioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnvioDTO create(@RequestBody EnvioDTO envioDTO) {
        return envioService.save(envioDTO);
    }

    @PutMapping("/envio/{id}")
    public EnvioDTO update(@PathVariable String id, @RequestBody EnvioDTO envioDTO) {
        return envioService.update(id, envioDTO);
    }

    @DeleteMapping("/envio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        envioService.deleteById(id);
    }*/
}
