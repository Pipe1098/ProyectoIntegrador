package com.example.Mensajeria.service;

import com.example.Mensajeria.model.EstadoEnvio;

public class ActualizarEstadoResponse {

        private String numeroGuia;
        private EstadoEnvio estado;

        public ActualizarEstadoResponse(String numeroGuia, EstadoEnvio estado) {
            this.numeroGuia = numeroGuia;
            this.estado = estado;
        }

        public String getNumeroGuia() {
            return numeroGuia;
        }

        public void setNumeroGuia(String numeroGuia) {
            this.numeroGuia = numeroGuia;
        }

        public EstadoEnvio getEstado() {
            return estado;
        }

        public void setEstado(EstadoEnvio estado) {
            this.estado = estado;
        }

}
