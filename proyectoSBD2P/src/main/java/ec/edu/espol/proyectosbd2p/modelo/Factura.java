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
public class Factura {
    private String numFactura;
    private String ruc;
    private int precioTotal;
    private Date fecha;
    private String idDepFinanzas;

    public Factura(String numFactura, String ruc, int precioTotal, Date fecha, String idDepFinanzas) {
        this.numFactura = numFactura;
        this.ruc = ruc;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.idDepFinanzas = idDepFinanzas;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdDepFinanzas() {
        return idDepFinanzas;
    }

    public void setIdDepFinanzas(String idDepFinanzas) {
        this.idDepFinanzas = idDepFinanzas;
    }
    
    
    
}
