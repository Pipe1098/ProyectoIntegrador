package com.example.Mensajeria.controller;

import com.example.Mensajeria.configurer.EnvioMapper;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.ActualizarEstadoRequest;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.service.EnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping("/envios")
    public List<EnvioDTO> findAll() {
        return envioService.findAll();
    }

    @PostMapping("/envio/numGuia")
    public EnvioDTO findByNumeroGuia(@RequestBody Map<String, String> request) {
        String numeroGuia = request.get("numeroGuia");
        Envio envio = envioService.findById(numeroGuia);
        return EnvioMapper.INSTANCE.envioToEnvioDTO(envio);
    }


    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody EnvioDTO envioDTO) {
        return envioService.generate(envioDTO);
    }

    @PutMapping("/envio/{id}")
    public EnvioDTO update(@PathVariable String id, @RequestBody EnvioDTO envioDTO) {
        return envioService.update(id, envioDTO);
    }
    @PutMapping("/envio/estado")
    public ResponseEntity<EnvioService.ActualizarEstadoResponse> actualizarEstado(
            @RequestBody ActualizarEstadoRequest request) {
        EnvioService.ActualizarEstadoResponse resultado = envioService.actualizarEstado(
                request.getNumGuia(),
                request.getCedulaEmpleado(),
                request.getEstado());
        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/envios/filtrar")
    public ResponseEntity<List<EnvioDTO>> filtrarEnviosPorEstadoYEmpleado(
            @RequestParam(name = "estado") EnvioService.EstadoEnvio estado,
            @RequestParam(name = "cedulaEmpleado") String cedulaEmpleado) {
        List<EnvioDTO> envios = envioService.filtrar(estado, cedulaEmpleado);
        return ResponseEntity.ok(envios);
    }

    @DeleteMapping("/envio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        envioService.deleteById(id);
    }
}
