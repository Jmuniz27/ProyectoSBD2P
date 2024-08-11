/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author isabella
 */
public class Cliente {
    private String ruc;
    private String nombreEmpresa;
    private String descripEmpresa;
    private String direccion;
    private String sitioWeb;
    private String idPersonaContacto;

    public Cliente(String ruc, String nombreEmpresa, String descripEmpresa, String direccion, String sitioWeb, String idPersonaContacto) {
        this.ruc = ruc;
        this.nombreEmpresa = nombreEmpresa;
        this.descripEmpresa = descripEmpresa;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.idPersonaContacto = idPersonaContacto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripEmpresa() {
        return descripEmpresa;
    }

    public void setDescripEmpresa(String descripEmpresa) {
        this.descripEmpresa = descripEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getIdPersonaContacto() {
        return idPersonaContacto;
    }

    public void setIdPersonaContacto(String idPersonaContacto) {
        this.idPersonaContacto = idPersonaContacto;
    }
    
    
}
