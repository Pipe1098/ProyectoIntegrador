package com.example.Mensajeria.dto;

import java.time.LocalDateTime;

public class EnvioDTO {
    private String numeroGuia;
    private ClienteDTO cliente;
    private String ciudadOrigen;
    private String dirDestino;
    private LocalDateTime horaEntrega;
    private String estadoEnvio;
    private double valorEnvio;
    private PaqueteDTO paquete;

    public EnvioDTO(String numeroGuia, ClienteDTO cliente, String ciudadOrigen, String dirDestino, LocalDateTime horaEntrega, String estadoEnvio, double valorEnvio, PaqueteDTO paquete) {
        this.numeroGuia = numeroGuia;
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.dirDestino = dirDestino;
        this.horaEntrega = horaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public LocalDateTime getHoraEntrega() {
        return horaEntrega;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public double getValorEnvio() {
        return valorEnvio;
    }

    public PaqueteDTO getPaquete() {
        return paquete;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public void setHoraEntrega(LocalDateTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public void setValorEnvio(double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public void setPaquete(PaqueteDTO paquete) {
        this.paquete = paquete;
    }
}

