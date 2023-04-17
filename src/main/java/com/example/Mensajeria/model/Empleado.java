package com.example.Mensajeria.model;
import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {

    @Column(nullable = false)
    private int antigueadadEnEmpresa;

    @Column(nullable = false)
    private String rh;

    @Column(nullable = false)
    private String tipoEmpleado;

    // Constructor vacío requerido por JPA
    public Empleado() {}


    // Constructor con parámetros
    public Empleado(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, int cedula, int antigueadadEnEmpresa, String rh, String tipoEmpleado) {
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

/*    public enum TipoEmpleado {

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

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
