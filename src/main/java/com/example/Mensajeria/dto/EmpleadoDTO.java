package com.example.Mensajeria.dto;


public class EmpleadoDTO {

    private String cedula;
    private String apellido;
    private String nombre;
    private String correoElectronico;
    private String celular;
    private String tipoEmpleado;
    private int antiguedadEnEmpresa;

    public EmpleadoDTO(String cedula, String apellido, String nombre, String correoElectronico, String celular, String tipoEmpleado, int antiguedadEnEmpresa) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.celular = celular;
        this.tipoEmpleado = tipoEmpleado;
        this.antiguedadEnEmpresa = antiguedadEnEmpresa;
    }

    public EmpleadoDTO() {
    }

    public String getCedula() {
        return cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getCelular() {
        return celular;
    }



    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public int getAntiguedadEnEmpresa() {
        return antiguedadEnEmpresa;
    }

}