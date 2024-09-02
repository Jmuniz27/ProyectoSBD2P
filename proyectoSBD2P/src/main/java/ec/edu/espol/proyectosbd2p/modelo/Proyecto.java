/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;
import java.util.Date;
/**
 *
 * @author zahid
 */
public class Proyecto {
    private int idProyecto;
    private String titulo;
    private int presupuesto;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private String ruc;
    private String numFactura;
    private double comisionAEmpresa;

    public Proyecto(int idProyecto, String ruc, String numFactura, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, double comisionAEmpresa) {
        this.idProyecto = idProyecto;
        this.ruc = ruc;
        this.numFactura = numFactura;
        this.titulo = titulo;
        this.presupuesto = presupuesto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.comisionAEmpresa = comisionAEmpresa;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public double getComisionAEmpresa() {
        return comisionAEmpresa;
    }

    public void setComisionAEmpresa(double comisionAEmpresa) {
        this.comisionAEmpresa = comisionAEmpresa;
    }
    
    
}
