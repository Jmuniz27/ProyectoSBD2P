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

    public PublicidadAnuncioWeb(String idProyecto, String ruc, String numFactura, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, String tamanoBanner, String id_dep_creativo, double comisionAEmpresa) {
        super(idProyecto, ruc, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_creativo, comisionAEmpresa);
        this.tamanoBanner = tamanoBanner;
    }

    

    public String getTamanoBanner() {
        return tamanoBanner;
    }

    public void setTamanoBanner(String tamanoBanner) {
        this.tamanoBanner = tamanoBanner;
    }
    
}
