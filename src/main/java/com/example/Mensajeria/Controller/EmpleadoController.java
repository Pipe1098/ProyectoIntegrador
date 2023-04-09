package com.example.Mensajeria.Controller;

import com.example.Mensajeria.Service.EmpleadoService;
import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

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

    @PutMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long cedula, @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizar(cedula,empleadoDTO);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("empleado/{cedula}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long cedula) {
        empleadoService.eliminarEmpleadoPorCedula(cedula);
            return ResponseEntity.noContent().build();
    }

}

