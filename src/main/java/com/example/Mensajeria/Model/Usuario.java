package com.example.usuarios.Model;

public abstract class Usuario {

    protected String nombre;
    protected String apellido;
    protected int celular;
    protected String correo;
    protected String direccion;
    protected String ciudad;
    protected String cedula;

    public Usuario(String nombre, String apellido, int celular, String correo, String direccion, String ciudad, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCedula() {
        return cedula;
    }
}
