package com.example.Mensajeria.model;
import javax.persistence.*;

@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "id")
public class Empleado extends Usuario {

    @Column(nullable = false)
    private int antigueadadEnEmpresa;

    @Column(nullable = false)
    private String rh;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEmpleado tipoEmpleado;


    public Empleado() {}

    public Empleado(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, String cedula, int antigueadadEnEmpresa, String rh, TipoEmpleado tipoEmpleado) {
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

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
