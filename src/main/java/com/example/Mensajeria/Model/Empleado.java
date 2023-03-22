package com.example.usuarios.Model;

public class Empleado extends Usuario {
    private int antigueadadEnEmpresa;
    private String rh;
    private String tipoEmpleado;

    public Empleado(String nombre, String apellido, int celular, String correo, String direccion, String ciudad, String cedula, int antigueadadEnEmpresa, String tipoEmpleado) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.antigueadadEnEmpresa = antigueadadEnEmpresa;
        this.tipoEmpleado = tipoEmpleado;
    }

}
