/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class DepartamentoCreativo extends Departamento {
    private String idDirector;

    public DepartamentoCreativo(String idDirector, String idDepartamento, String descripcion, String nombre, String email) {
        super(idDepartamento, descripcion, nombre, email);
        this.idDirector = idDirector;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }
    
}
