package com.example.Mensajeria.dto;

public class ClienteDTO {

    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String cedula;
    private String ciudad;
    private String direccion;

    public ClienteDTO(String nombre, String apellido, String celular, String correo, String cedula, String ciudad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.cedula = cedula;
        this.ciudad = ciudad;
        this.direccion = direccion;
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

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }
}

