package com.example.Mensajeria.controller;

import com.example.Mensajeria.configurer.EnvioMapper;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.ActualizarEstadoRequest;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.service.EnvioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Obtener todos los envios", notes = "Permite obtener todos los envios de la base de datos.", response = Envio.class)
    @GetMapping("/envios")
    public List<Envio> findAll() {
        return envioService.findAll();
    }

    @ApiOperation(value = "Obtener envio por su numero de guia", notes = "Permite obtener un envio especifico de la base de datos por medio de su id (numGuia).", response = EnvioDTO.class)
    @PostMapping("/envio/numGuia")
    public EnvioDTO findById(@RequestBody Map<String, String> request) {
        String numeroGuia = request.get("numeroGuia");
        Envio envio = envioService.findById(numeroGuia);
        return EnvioMapper.INSTANCE.envioToEnvioDTO(envio);
    }

    @ApiOperation(value = "Crear envio", notes = "Permite crear un nuevo envio con los datos proporcionados en el cuerpo de la solicitud.", response = EnvioDTO.class)
    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody EnvioDTO envioDTO) {
        return envioService.generate(envioDTO);
    }

    @ApiOperation(value = "Actualizar envio", notes = "Permite actualizar un envio por medio de su id (numGuia).", response = EnvioDTO.class)
    @PutMapping("/envio/{id}")
    public EnvioDTO update(@ApiParam(value = "Digite el numero de guia del envio que quiere actualizar.", required = true) @PathVariable String id, @RequestBody EnvioDTO envioDTO) {
        return envioService.update(id, envioDTO);
    }

    @ApiOperation(value = "Actualizar estado", notes = "Permite actualizar de un estado a otro (entregado, en_ruta o recibido).", response = EnvioService.ActualizarEstadoResponse.class)
    @PutMapping("/envio/estado")
    public ResponseEntity<EnvioService.ActualizarEstadoResponse> UpdateState(
            @RequestBody ActualizarEstadoRequest request) {
        EnvioService.ActualizarEstadoResponse resultado = envioService.actualizarEstado(
                request.getNumGuia(),
                request.getCedulaEmpleado(),
                request.getEstado());
        return ResponseEntity.ok(resultado);
    }

    @ApiOperation(value = "Filtar por estado", notes = "Permite obtener todos los envios de la base de datos que estan en determinado estado (entregado, en_ruta o recibido).", response = Envio.class)
    @GetMapping("/envios/filtro")
    public ResponseEntity<List<Envio>> filterByState(
            @RequestParam(name = "estado") EnvioService.EstadoEnvio estado,
            @RequestParam(name = "cedulaEmpleado") String cedulaEmpleado) {
        List<Envio> envios = envioService.filtrar(estado, cedulaEmpleado);
        return ResponseEntity.ok(envios);
    }

    @ApiOperation(value = "Eliminar envio", notes = "Permite eliminar envios de la base de datos por medio de su numero de guia", response = String.class)
    @DeleteMapping("/envio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteById(@ApiParam(value = "Digite el numGuia del envio que desea eliminar", required = true) @PathVariable String id) {
        String msg = envioService.deleteById(id);
        return msg;
    }
}
