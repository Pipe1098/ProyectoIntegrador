package com.example.Mensajeria.controller;

import com.example.Mensajeria.service.EmpleadoService;
import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmpleadoController {


    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleados")
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Long cedula) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorCedula(cedula);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<EmpleadoDTO> addEmpleado(@RequestBody Empleado empleado) {
        EmpleadoDTO empleadoCreado = empleadoService.addEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }
    @PostMapping("/empleados")
    public ResponseEntity<List<EmpleadoDTO>> addEmpleados() {
        List<EmpleadoDTO> empleados= empleadoService.crearEmpleados();
        return ResponseEntity.status(HttpStatus.CREATED).body(empleados);
    }
    @PutMapping("/empleados/{cedula}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long cedula, @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizar(cedula,empleadoDTO);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/empleado/{id}")
    public String deleteEmpleadoById(@PathVariable Long cedula) {
        empleadoService.deleteById(cedula);
        return "Empleado con cedula: "+cedula+" eliminado exitosamente";
    }

}

