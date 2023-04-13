package com.example.Mensajeria.controller;

import com.example.Mensajeria.service.EmpleadoService;
import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/e")
public class EmpleadoController {


    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleados")
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/empleados/{cedula}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorCedula(@PathVariable Long cedula) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorCedula(cedula);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody Empleado empleado) {
        EmpleadoDTO empleadoCreado = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @PutMapping("/empleadoss/{cedula}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long cedula, @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizar(cedula,empleadoDTO);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/empleado/{id}")
    public void deleteEmpleadoById(@PathVariable Long id) {
        empleadoService.deleteById(id);
    }
    /*public ResponseEntity<EmpleadoDTO> eliminarEmpleado(@PathVariable Long cedula) {
        EmpleadoDTO empleado = empleadoService.eliminarEmpleadoPorCedula(cedula);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }*/
   // }

}

