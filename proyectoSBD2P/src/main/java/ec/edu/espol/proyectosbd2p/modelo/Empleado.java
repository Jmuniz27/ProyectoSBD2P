/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class Empleado {
    private String idEmpleado;
    private int sueldoBase;
    private String nombre;
    private String apellido;
    private String puesto;
    private String contrasena;
    private String direccion;
    private String idSupervisor;
    private String idDepCreativo;
    private String idDepProd;
    private String idDepFinanzas;
    private String id_dir_dep_creativo;
    private String id_dir_dep_prod;
    private String id_dir_dep_finanzas;

    public Empleado(String idEmpleado, int sueldoBase, String nombre, String apellido, String puesto, String contrasena, String direccion, String idDepCreativo, String idDepProd, String idDepFinanzas, String id_dir_dep_creativo, String id_dir_dep_prod, String id_dir_dep_finanzas) {
        this.idEmpleado = idEmpleado;
        this.sueldoBase = sueldoBase;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.idDepCreativo = idDepCreativo;
        this.idDepProd = idDepProd;
        this.idDepFinanzas = idDepFinanzas;
        this.id_dir_dep_creativo = id_dir_dep_creativo;
        this.id_dir_dep_prod = id_dir_dep_prod;
        this.id_dir_dep_finanzas = id_dir_dep_finanzas;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(int sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(String idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getIdDepCreativo() {
        return idDepCreativo;
    }

    public void setIdDepCreativo(String idDepCreativo) {
        this.idDepCreativo = idDepCreativo;
    }

    public String getIdDepProd() {
        return idDepProd;
    }

    public void setIdDepProd(String idDepProd) {
        this.idDepProd = idDepProd;
    }

    public String getIdDepFinanzas() {
        return idDepFinanzas;
    }

    public void setIdDepFinanzas(String idDepFinanzas) {
        this.idDepFinanzas = idDepFinanzas;
    }
    
    
    
}
