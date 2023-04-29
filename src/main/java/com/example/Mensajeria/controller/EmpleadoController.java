package com.example.Mensajeria.controller;

import com.example.Mensajeria.exception.ApiRequestException;
import com.example.Mensajeria.service.EmpleadoService;
import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmpleadoController {

    private EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado creado con exito"),
            @ApiResponse(code = 400, message = "No se pudo crear el empleado con los datos ingresados"),
            @ApiResponse(code = 403, message = "Operacion prohibida"),
            @ApiResponse(code = 401, message = "No esta autorizado para realizar esta operación"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Obtener empleado por cedula", notes = "Muestra los datos del empleado por medio de su numero de cedula", response = EmpleadoDTO.class)
    @GetMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@ApiParam(value = "Digite la cedula del empleado que necesita obtener", required = true) @PathVariable String cedula) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorCedula(cedula);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            throw new ApiRequestException("Empleado con cedula: " + cedula + " no encontrado en el sistema");
        }
    }
    @ApiOperation(value = "Crear empleado", notes = "Permite registar un nuevo empleado en la base de datos", response = EmpleadoDTO.class)
    @PostMapping("/empleado")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody Empleado empleado) {
        EmpleadoDTO empleadoCreado = empleadoService.crear(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @ApiOperation(value = "Crear empleados", notes = "Crea  una lista de empleados por defecto para probar la api.", response = EmpleadoDTO.class)
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleados() {
        this.empleadoService.crearEmpleados();
        return new ResponseEntity("Se crearon las empleados por defecto.", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtener empleados", notes = "Muestra todos los empleados registrados en la base de datos.", response = EmpleadoDTO.class)
    @GetMapping("/empleados")
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @ApiOperation(value = "Actulizar empleado", notes = "Permite actualizar un empleado registrado en la base de datos, por medio de su cedula.", response = EmpleadoDTO.class)
    @PutMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@ApiParam(value = "Digite la cedula del empleado que necesita actualizar.", required = true) @PathVariable String cedula, @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(cedula, empleadoDTO);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "Eliminar empleado", notes = "Permite eliminar un empleado de la base de datos por medio de su cedula.", response = String.class)
    @DeleteMapping("empleado/{cedula}")
    public String eliminarEmpleado(@ApiParam(value = "Digite la cedula del empleado que necesita eliminar", required = true) @PathVariable("cedula") String cedula) {
        String msg = this.empleadoService.eliminarEmpleadoPorCedula(cedula);
        return msg;
    }

}

