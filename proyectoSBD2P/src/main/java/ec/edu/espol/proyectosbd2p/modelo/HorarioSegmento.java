/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p.modelo;

/**
 *
 * @author zahid
 */
public class HorarioSegmento {
    private String idHorarioSegmento;
    private String idSegmento;
    private String horario;

    public HorarioSegmento(String idHorarioSegmento, String idSegmento, String horario) {
        this.idHorarioSegmento = idHorarioSegmento;
        this.idSegmento = idSegmento;
        this.horario = horario;
    }

    public String getIdHorarioSegmento() {
        return idHorarioSegmento;
    }

    public void setIdHorarioSegmento(String idHorarioSegmento) {
        this.idHorarioSegmento = idHorarioSegmento;
    }

    public String getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(String idSegmento) {
        this.idSegmento = idSegmento;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
}
