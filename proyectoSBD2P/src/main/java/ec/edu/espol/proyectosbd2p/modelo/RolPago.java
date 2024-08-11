/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class RolPago {
    private String idPago;
    private int pagoNeto;
    private String idEmpleado;
    private String idDepFinanzas;

    public RolPago(String idPago, int pagoNeto, String idEmpleado, String idDepFinanzas) {
        this.idPago = idPago;
        this.pagoNeto = pagoNeto;
        this.idEmpleado = idEmpleado;
        this.idDepFinanzas = idDepFinanzas;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public int getPagoNeto() {
        return pagoNeto;
    }

    public void setPagoNeto(int pagoNeto) {
        this.pagoNeto = pagoNeto;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdDepFinanzas() {
        return idDepFinanzas;
    }

    public void setIdDepFinanzas(String idDepFinanzas) {
        this.idDepFinanzas = idDepFinanzas;
    }
    
    

}
