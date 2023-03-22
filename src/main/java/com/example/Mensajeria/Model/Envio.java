package com.example.usuarios.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Envio {

    // Atributos de la clase
    private String numeroGuia;
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String dirDestino;
    private String nombreReceptor;
    private int celReceptor;
    private LocalDateTime horaEntrega;
    private EstadoEnvio estadoEnvio;
    private double valorEnvio;
    private Paquete paquete;

    // Constructor de la clase
    public Envio(Cliente cliente, String ciudadOrigen, String ciudadDestino,
                 String dirDestino, String nombreReceptor, int celReceptor,
                 LocalDateTime horaEntrega, EstadoEnvio estadoEnvio, double valorEnvio, Paquete paquete) {
        this.numeroGuia = generarNumGuia();
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreReceptor = nombreReceptor;
        this.celReceptor = celReceptor;
        this.horaEntrega = horaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }

    // Métodos de la clase

    public String generarNumGuia(){
        UUID uuid = UUID.randomUUID();
        String codigo = uuid.toString().replace("-", "").substring(0, 10);
        return codigo;
    }

    public String getNumeroGuia() {
        return this.numeroGuia;
    }


    public enum EstadoEnvio {

        RECIBIDO(1),
        EN_RUTA(2),
        ENTREGADO(3);

        private int valor;

        EstadoEnvio(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

}
