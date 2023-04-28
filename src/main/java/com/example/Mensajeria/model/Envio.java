package com.example.Mensajeria.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Envio {

    @Id
    private String numeroGuia;

    @ManyToOne
    private Cliente cliente;

    private String ciudadOrigen;
    private String ciudadDestino;
    private String dirDestino;
    private String nombreReceptor;
    private String celReceptor;
    private LocalDateTime horaEntrega;
    private EstadoEnvio estadoEnvio;
    private double valorDeclarado;
    private double peso;
    private double valorEnvio;

    @OneToOne
    private Paquete paquete;
    @OneToOne
    private Empleado empleado;

public Envio(String codigo, Cliente newCliente, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreReceptor, String celReceptor, LocalDateTime fecha, EstadoEnvio estadoEnvio, double valorDeclarado, double peso, Double valorEnvio, Paquete paquete, Empleado empleado) {
        this.numeroGuia = codigo;
        this.cliente = newCliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreReceptor = nombreReceptor;
        this.celReceptor = celReceptor;
        this.horaEntrega = fecha;
        this.estadoEnvio = estadoEnvio;
        this.valorDeclarado = valorDeclarado;
        this.peso = peso;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
        this.empleado = empleado;
    }

    public Envio(String codigo, Cliente newCliente, String cedula, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreReceptor, String celReceptor, LocalDateTime fecha, EstadoEnvio estadoEnvio,
                 double valorDeclarado, double peso, Double valorEnvio, Paquete paquete) {
        this.cliente = newCliente;
        this.numeroGuia = codigo;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreReceptor = nombreReceptor;
        this.celReceptor = celReceptor;
        this.valorDeclarado = valorDeclarado;
        this.peso = peso;
        this.horaEntrega = fecha;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }
// Métodos de la clase

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente=cliente;
    }

    public void setClientep(String cedula) {
        this.cliente.setCedula(cedula);
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getCelReceptor() {
        return celReceptor;
    }

    public void setCelReceptor(String celReceptor) {
        this.celReceptor = celReceptor;
    }

    public LocalDateTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalDateTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public double getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public Paquete getPaquete() {
        return this.paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Envio{" +
                "numeroGuia='" + numeroGuia + '\'' +
                ", estadoEnvio=" + estadoEnvio +
                '}';
    }


}
