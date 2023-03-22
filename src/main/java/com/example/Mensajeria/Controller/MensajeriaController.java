package com.example.usuarios.Controller;

import com.example.usuarios.Service.MensajeriaService;
import com.example.usuarios.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MensajeriaController {

    private MensajeriaService mensajeriaService;

    @Autowired
    public MensajeriaController(MensajeriaService mensajeriaService) {
        this.mensajeriaService = mensajeriaService;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes() {
        return this.mensajeriaService.obtenerClientes();
    }

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        return this.mensajeriaService.obtenerEmpleados();
    }

    @GetMapping("/paquetes")
    public List<Paquete> obtenerPaquetes() {
        return this.mensajeriaService.obtenerPaquetes();
    }

    @GetMapping("/envios")
    public List<Envio> obtenerEnvios() {
        return this.mensajeriaService.obtenerEnvios();
    }

    @PostMapping("/cliente")
    public Usuario agregarCliente(@RequestBody Cliente cliente) {
        return this.mensajeriaService.crearCliente(cliente);
    }

    @PostMapping("/empleado")
    public Usuario agregarEmpleado(@RequestBody Empleado empleado) {
        return this.mensajeriaService.crearEmpleado(empleado);
    }
    @PostMapping("/paquete")
    public Paquete agregarPaquete(@RequestBody Paquete paquete) {
        return this.mensajeriaService.crearPaquete(paquete);
    }
    @PostMapping("/envio")
    public Envio agregarEnvio(@RequestBody Envio envio) {
        return this.mensajeriaService.crearEnvio(envio);
    }

    @GetMapping("/cliente/{cedula}")
    public Usuario obtenerClienteCedula(@PathVariable("cedula") String cedula) {
        return this.mensajeriaService.obtenerClienteCedula(cedula);
    }

    @GetMapping("/empleado/{cedula}")
    public Usuario obtenerEmpleadoCedula(@PathVariable("cedula") String cedula) {
        return this.mensajeriaService.obtenerEmpleadoCedula(cedula);
    }

    @GetMapping("/paquete/{idPaquete}")
    public Paquete obtenerPaqueteId(@PathVariable("idPaquete") String idPaquete) {
        return this.mensajeriaService.obtenerPaqueteId(idPaquete);
    }

    @GetMapping("/envios/")
    public List<Envio> obtenerEnviosGuia(@RequestParam("numeroGuia") String numeroGuia) {
        return this.mensajeriaService.obtenerEnviosGuia(numeroGuia);
    }

}




