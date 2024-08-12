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
public class PublicidadAnuncioWeb extends Publicidad{
    private String tamanoBanner;

    public PublicidadAnuncioWeb(String tamanoBanner, String idProyecto, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, String ruc, String numFactura, double comisionAEmpresa) {
        super(idProyecto, titulo, presupuesto, descripcion, fechaInicio, fechaFin, ruc, numFactura, comisionAEmpresa);
        this.tamanoBanner = tamanoBanner;
    }

    public String getTamanoBanner() {
        return tamanoBanner;
    }

    public void setTamanoBanner(String tamanoBanner) {
        this.tamanoBanner = tamanoBanner;
    }
    
}
