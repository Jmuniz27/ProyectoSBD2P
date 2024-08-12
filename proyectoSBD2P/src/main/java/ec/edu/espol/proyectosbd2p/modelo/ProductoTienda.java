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
public class ProductoTienda extends Proyecto{
    private String categoria;
    private int precio;
    private String idDepProd;

    public ProductoTienda(String categoria, int precio, String idDepProd, String idProyecto, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, String ruc, String numFactura, double comisionAEmpresa) {
        super(idProyecto, titulo, presupuesto, descripcion, fechaInicio, fechaFin, ruc, numFactura, comisionAEmpresa);
        this.categoria = categoria;
        this.precio = precio;
        this.idDepProd = idDepProd;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getIdDepProd() {
        return idDepProd;
    }

    public void setIdDepProd(String idDepProd) {
        this.idDepProd = idDepProd;
    }
    
    
}
