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
public class Segmento extends Proyecto{
    private String rating;
    private int duracion;
    private boolean estado;
    private String idDepProd;

    public Segmento(String idProyecto, String ruc, String numFactura, String rating, int duracion, boolean estado, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, String idDepProd, double comisionAEmpresa) {
        super(idProyecto, ruc, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, comisionAEmpresa);
        this.rating = rating;
        this.duracion = duracion;
        this.estado = estado;
        this.idDepProd = idDepProd;
    }


    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIdDepProd() {
        return idDepProd;
    }

    public void setIdDepProd(String idDepProd) {
        this.idDepProd = idDepProd;
    }
   
}
