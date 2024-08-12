/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class Bono {
    private String idBono;
    private int cantidad;
    private String tipoBono;

    public Bono(String idBono, int cantidad, String tipoBono) {
        this.idBono = idBono;
        this.cantidad = cantidad;
        this.tipoBono = tipoBono;
    }

    public String getIdBono() {
        return idBono;
    }

    public void setIdBono(String idBono) {
        this.idBono = idBono;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoBono() {
        return tipoBono;
    }

    public void setTipoBono(String tipoBono) {
        this.tipoBono = tipoBono;
    }
    
}
