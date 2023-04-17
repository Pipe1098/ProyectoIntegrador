package com.example.Mensajeria.model;
import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    private int antigueadadEnEmpresa;

    @Column(nullable = false)
    private String rh;

    @Column(nullable = false)
    private String tipoEmpleado;

    // Constructor vacío requerido por JPA
    public Empleado() {}

    @Override
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    // Constructor con parámetros
    public Empleado(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, Long cedula, int antigueadadEnEmpresa, String rh, String tipoEmpleado) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.antigueadadEnEmpresa = antigueadadEnEmpresa;
        this.rh = rh;
        this.tipoEmpleado = tipoEmpleado;


    }

    public int getAntigueadadEnEmpresa() {
        return antigueadadEnEmpresa;
    }

    public void setAntigueadadEnEmpresa(int antigueadadEnEmpresa) {
        this.antigueadadEnEmpresa = antigueadadEnEmpresa;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
