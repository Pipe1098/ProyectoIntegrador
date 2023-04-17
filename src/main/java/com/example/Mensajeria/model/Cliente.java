package com.example.Mensajeria.model;
import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/*
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
   private String apellido;

    @Column(nullable = false, unique = true)
   private Long cedula;

    @Column(nullable = false)
   private String celular;

    @Column(nullable = false, unique = true)
   private String correo;

    @Column(nullable = false)
   private String direccion;

    @Column(nullable = false)
   private String ciudad;*/


  
    public Cliente() {}

    @Override
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, Long cedula, Long id) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.id = id;

    }

    @Override
    public Long getId() {
        return id;
    }
}
