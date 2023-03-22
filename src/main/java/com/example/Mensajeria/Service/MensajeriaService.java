package com.example.usuarios.Service;

import com.example.usuarios.Model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MensajeriaService {

    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Envio> envios;
    private List<Paquete> paquetes;

    public MensajeriaService(List<Cliente> clientes, List<Empleado> empleados) {
        this.clientes = clientes;
        this.empleados = empleados;

    }

    public List<Cliente> obtenerClientes(){
        return this.clientes;

    }
    public List<Empleado> obtenerEmpleados(){
        return this.empleados;

    }
    public List<Paquete> obtenerPaquetes() {
        return this.paquetes;
    }
    public List<Envio> obtenerEnvios() {
        return this.envios;
    }

    public Usuario crearCliente(Cliente cliente){
        this.clientes.add(cliente);
        return cliente;
    }
    public Usuario crearEmpleado(Empleado empleado){
        this.empleados.add(empleado);
        return empleado;
    }
    public Envio crearEnvio(Envio envio) {
        this.envios.add(envio);
        return envio;
    }
    public Paquete crearPaquete(Paquete paquete) {
        this.paquetes.add(paquete);
        return paquete;
    }
    public Usuario obtenerClienteCedula(String cedula){
        return this.clientes
                .stream()
                .filter(usuario -> usuario.getCedula().equals(cedula))
                .findFirst().get();
    }
    public Usuario obtenerEmpleadoCedula(String cedula){
        return this.empleados
                .stream()
                .filter(usuario -> usuario.getCedula().equals(cedula))
                .findFirst().get();
    }
    public Paquete obtenerPaqueteId(String idPaquete){
        return this.paquetes
                .stream()
                .filter(paquete -> paquete.getIdPaquete().equals(idPaquete))
                .findFirst().get();
    }

    public List<Envio> obtenerEnviosGuia(String numeroGuia) {
        return this.envios.stream()
                .filter(envio -> envio.getNumeroGuia()
                        .equals(numeroGuia)).collect(Collectors.toList());
    }


}
