package com.example.Mensajeria.controller;

import com.example.Mensajeria.mappers.EnvioMapper;
import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.service.ActualizarEstadoRequest;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.EstadoEnvio;
import com.example.Mensajeria.service.ActualizarEstadoResponse;
import com.example.Mensajeria.service.EnvioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EnvioController {

    private final EnvioService envioService;
@Autowired
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Envio creado con exito"),
            @ApiResponse(code = 400, message = "No se pudo crear el envio con los datos ingresados"),
            @ApiResponse(code = 404, message = "Envio no encontrado"),
            @ApiResponse(code = 403, message = "Operación prohibida"),
            @ApiResponse(code = 401, message = "No esta autorizado para realizar esta operación"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Obtener todos los envios", notes = "Permite obtener todos los envios de la base de datos.", response = Envio.class)
    @GetMapping("/envios")
    public List<Envio> obtenerEnvios() {
        return envioService.obtenerEnvios();
    }

    @ApiOperation(value = "Obtener envio por su numero de guia", notes = "Permite obtener un envio especifico de la base de datos por medio de su id (numGuia).", response = EnvioDTO.class)
    @PostMapping("/envio/numGuia")
    public EnvioDTO encontrarPorId(@RequestBody Map<String, String> request) {
        String numeroGuia = request.get("numeroGuia");
        Envio envio = envioService.encontrarEnvioPorId(numeroGuia);
        return EnvioMapper.INSTANCE.envioToEnvioDTO(envio);
    }

    @ApiOperation(value = "Crear envio", notes = "Permite crear un nuevo envio con los datos proporcionados en el cuerpo de la solicitud.", response = EnvioDTO.class)
    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public String crearEnvio(@RequestBody EnvioDTO envioDTO) {
        return envioService.generar(envioDTO);
    }

    @ApiOperation(value = "Actualizar envio", notes = "Permite actualizar un envio por medio de su id (numGuia).", response = EnvioDTO.class)
    @PutMapping("/envio/{id}")
    public EnvioDTO actualizarEnvio(@ApiParam(value = "Digite el numero de guia del envio que quiere actualizar.", required = true) @PathVariable String id, @RequestBody EnvioDTO envioDTO) {
        return envioService.actualizarEnvio(id, envioDTO);
    }

    @ApiOperation(value = "Actualizar estado", notes = "Permite actualizar de un estado a otro (entregado, en_ruta o recibido).", response = ActualizarEstadoResponse.class)
    @PutMapping("/envio/estado")
    public ResponseEntity<ActualizarEstadoResponse> actualizarEstado(
            @RequestBody ActualizarEstadoRequest request) {
                   ActualizarEstadoResponse resultado = envioService.actualizarEstado(
                request.getNumGuia(),
                request.getCedulaEmpleado(),
                request.getEstado());
        return ResponseEntity.ok(resultado);
    }

    @ApiOperation(value = "Filtar por estado", notes = "Permite obtener todos los envios de la base de datos que estan en determinado estado (entregado, en_ruta o recibido).", response = Envio.class)
    @GetMapping("/envios/filtro")
    public ResponseEntity<List<Envio>> filtrarPorEstado(
            @RequestParam(name = "estado") EstadoEnvio estado,
            @RequestParam(name = "cedulaEmpleado") String cedulaEmpleado) {
        List<Envio> envios = envioService.filtrar(estado, cedulaEmpleado);
        return ResponseEntity.ok(envios);
    }

    @ApiOperation(value = "Eliminar envio", notes = "Permite eliminar envios de la base de datos por medio de su numero de guia", response = String.class)
    @DeleteMapping("/envio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String eliminarPorId(@ApiParam(value = "Digite el numGuia del envio que desea eliminar", required = true) @PathVariable String id) {
        return envioService.eliminarPorId(id);
    }
}
