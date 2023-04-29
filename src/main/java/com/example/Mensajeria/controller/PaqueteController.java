package com.example.Mensajeria.controller;

import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.service.PaqueteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PaqueteController {

    private PaqueteService paqueteService;

    @Autowired
    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Paquete creado con exito"),
            @ApiResponse(code = 404, message = "Paquete no encontrado"),
            @ApiResponse(code = 400, message = " dato/s  mal ingresado/s"),
            @ApiResponse(code = 403, message = "Operacion prohibida"),
            @ApiResponse(code = 401, message = "No esta autorizado para realizar esta operación"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Obtener paquetes", notes = "Devuelve una lista de paquetes registrados en la base de datos.", response = PaqueteDTO.class)
    @GetMapping("/paquetes")
    public List<PaqueteDTO> obtenerPaquetes() {
        return paqueteService.obtenerPaquetes();
    }

    @ApiOperation(value = "Obtener paquete por Id", notes = "Devuelve los datos de un paquete especificando su Id.", response = PaqueteDTO.class)
    @GetMapping("/paquete/{id}")
    public PaqueteDTO obtenerPaquetePorId(@PathVariable Long id) {
        return paqueteService.encontrarPorId(id);
    }

    @ApiIgnore
    @ApiOperation(value = "Crear un paquete", notes = "Permite registrar un nuevo paquete en la base de datos.", response = PaqueteDTO.class)
    @PostMapping("/paquete")
    @ResponseStatus(HttpStatus.CREATED)
    public PaqueteDTO crearPaquete(@RequestBody Paquete paquete) {
        return paqueteService.crear(paquete);
    }

    @ApiIgnore
    @ApiOperation(value = "Actulizar paquete", notes = "Permite actualizar los datos de un paquete previamente creado.", response = PaqueteDTO.class)
    @PutMapping("/paquete/{id}")
    public PaqueteDTO actualizarPaquete(@PathVariable Long id, @RequestBody PaqueteDTO paqueteDTO) {
        return paqueteService.actualizarPaquete(id, paqueteDTO);
    }
    @ApiIgnore
    @ApiOperation(value = "Eliminar paquete", notes = "Elimina un paquete de la base de datos.", response = String.class)
    @DeleteMapping("/paquete/{id}")
    public String eliminarPaquetePorId(@PathVariable Long id) {
        return paqueteService.eliminarPorId(id);
    }
}

