package com.example.Mensajeria.dto;


public class EmpleadoDTO {

    private int cedula;
    private String apellido;
    private String nombre;
    private String correoElectronico;
    private String celular;
    private String tipoEmpleado;
    private int antiguedadEnEmpresa;

    public EmpleadoDTO(int cedula, String apellido, String nombre, String correoElectronico, String celular, String tipoEmpleado, int antiguedadEnEmpresa) {
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

    public int getCedula() {
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
    /*    public enum String{

            REPARTIDOR(1),
            CONDUCTOR(2),
            RECEPCIONISTA(3);

            private int valor;

            TipoEmpleado(int valor) {
                this.valor = valor;
            }

            public int getValor() {
                return valor;
            }
        }*/
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