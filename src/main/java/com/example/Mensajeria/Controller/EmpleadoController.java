package com.example.Mensajeria.Controller;


import com.example.Mensajeria.Service.EmpleadoService;
import com.example.Mensajeria.dto.EmpleadoDTO;
import com.example.Mensajeria.model.Empleado;
import io.swagger.annotations.ApiOperation;
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
        this.empleadoService= empleadoService;
    }


    @GetMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorCedula(@PathVariable long cedula) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorCedula(cedula);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody Empleado empleado) {
        EmpleadoDTO empleadoCreado = empleadoService.crear(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }
    @ApiOperation(value = "Crear empleados", notes= "Crea  una lista de empleados por defecto para probar la api.", response = EmpleadoDTO.class)
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleados() {
        this.empleadoService.crearEmpleados();
        return new ResponseEntity("Se crearon las empleados por defecto.", HttpStatus.CREATED);
    }
    @ApiOperation(value = "Obtener empleados", notes= "Muestra todos los empleados registrados en la base de datos", response = EmpleadoDTO.class)
    @GetMapping("/empleados")
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @PutMapping("/empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable int cedula, @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(cedula,empleadoDTO);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("empleado/{cedula}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("cedula") int cedula) {
        this.empleadoService.eliminarEmpleadoPorCedula(cedula);
            return ResponseEntity.noContent().build();
    }

}

