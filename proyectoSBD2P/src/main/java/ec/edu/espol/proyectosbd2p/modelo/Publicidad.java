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
public class Publicidad extends Proyecto {
    private String id_dep_creativo;

    public Publicidad(String idProyecto, String ruc, String numFactura, String titulo, int presupuesto, String descripcion, Date fechaInicio, Date fechaFin, String id_dep_creativo, double comisionAEmpresa) {
        super(idProyecto, ruc, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, comisionAEmpresa);
        this.id_dep_creativo = id_dep_creativo;
    }

    public String getId_dep_creativo() {
        return id_dep_creativo;
    }

    public void setId_dep_creativo(String id_dep_creativo) {
        this.id_dep_creativo = id_dep_creativo;
    }

    
    
}
