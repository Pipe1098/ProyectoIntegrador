package com.example.usuarios.Model;

public class Cliente extends Usuario {

    public Cliente(String nombre, String apellido, int celular, String correo,
                   String direccion, String ciudad, String cedula) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
    }

}
