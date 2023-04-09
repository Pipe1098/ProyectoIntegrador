package com.example.Mensajeria.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Envio {

    // Atributos de la clase
    @Id
    private String numeroGuia;

    @ManyToOne
    private Cliente cliente;

    private String ciudadOrigen;
    private String ciudadDestino;
    private String dirDestino;
    private String nombreReceptor;
    private int celReceptor;
    private LocalDateTime horaEntrega;
    private EstadoEnvio estadoEnvio;
    private double valorEnvio;

    @OneToOne
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

    public Envio() {}

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

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public int getCelReceptor() {
        return celReceptor;
    }

    public void setCelReceptor(int celReceptor) {
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
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
