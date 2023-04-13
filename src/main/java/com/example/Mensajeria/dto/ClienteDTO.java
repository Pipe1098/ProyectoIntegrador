package com.example.Mensajeria.dto;

public class ClienteDTO {

    private  Long cedula;
    private  String apellido;
    private String nombre;
    private String correoElectronico;
    private String celular;
    public ClienteDTO() {
    }

    public ClienteDTO(Long cedula, String nombre, String apellido, String correoElectronico, String celular) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.cedula=cedula;
        this.celular = celular;
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

    public Long getCedula() {
        return cedula;
    }

    public String getCelular() {
        return celular;
    }
}
