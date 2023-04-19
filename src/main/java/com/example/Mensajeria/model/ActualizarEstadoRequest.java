package com.example.Mensajeria.model;

import com.example.Mensajeria.service.EnvioService;

public class ActualizarEstadoRequest {
    private String numGuia;
    private String cedulaEmpleado;
    private EnvioService.EstadoEnvio estado;

    public ActualizarEstadoRequest() {}

    public ActualizarEstadoRequest(String numGuia, String cedulaEmpleado, EnvioService.EstadoEnvio estado) {
        this.numGuia = numGuia;
        this.cedulaEmpleado = cedulaEmpleado;
        this.estado = estado;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public String getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(String cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public EnvioService.EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EnvioService.EstadoEnvio estado) {
        this.estado = estado;
    }
}

