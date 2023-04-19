package com.example.Mensajeria.dto;

public class ClienteDTO {

    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String cedula;

    public ClienteDTO(String nombre, String apellido, String celular, String correo, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.cedula = cedula;
    }

    public ClienteDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCedula() {
        return cedula;
    }

}

