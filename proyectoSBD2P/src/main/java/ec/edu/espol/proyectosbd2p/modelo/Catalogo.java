/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class Catalogo {
    private String idBono;
    private String idPago;

    public Catalogo(String idBono, String idPago) {
        this.idBono = idBono;
        this.idPago = idPago;
    }

    public String getIdBono() {
        return idBono;
    }

    public void setIdBono(String idBono) {
        this.idBono = idBono;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }
    
}
