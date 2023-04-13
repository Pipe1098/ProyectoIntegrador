package com.example.Mensajeria.model;
import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
   private String ciudad;
    @Column(nullable = false)
    private String direccionEnvio;

    // Constructor vacío requerido por JPA
    public Cliente() {}

    @Override
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, Long cedula, Long id, String direccionEnvio) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.id = id;
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getApellido() {
        return super.getApellido();
    }

    @Override
    public String getCelular() {
        return super.getCelular();
    }

    @Override
    public String getCorreo() {
        return super.getCorreo();
    }

    @Override
    public String getDireccion() {
        return super.getDireccion();
    }

    @Override
    public String getCiudad() {
        return super.getCiudad();
    }

    @Override
    public Long getCedula() {
        return super.getCedula();
    }
}
