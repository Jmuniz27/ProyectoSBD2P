/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ec.edu.espol.proyectosbd2p.modelo.Proyecto;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class VerPublicidadAnuncioCanalController implements Initializable {

    @FXML
    private Button botonInicio;
    @FXML
    private Label txtIDProyecto;
    @FXML
    private Label txtDescripcion;
    @FXML
    private Label txtTitulo;
    @FXML
    private Label txtFechaInicio;
    @FXML
    private Label txtRUC;
    @FXML
    private Label txtNumFactura;
    @FXML
    private Label txtDuracion;
    @FXML
    private Label txtPresupuesto;
    @FXML
    private Label txtFechaFin;
    @FXML
    private Label txtComision;
    @FXML
    private Button btnEditarAnuncioCanal;
    @FXML
    private Button btnEliminarAnuncioCanal;
    Proyecto clienteEscogido = GestionPublicidadCanalController.pcEscogido;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtIDProyecto.setText(clienteEscogido.getIdProyecto());
        txtDescripcion.setText(clienteEscogido.getDescripcion());
        txtTitulo.setText(clienteEscogido.getTitulo());
        txtFechaInicio.setText(clienteEscogido.getFechaInicio().toString());
        txtRUC.setText(clienteEscogido.getRuc());
        txtNumFactura.setText(clienteEscogido.getNumFactura());
        int dura = ((PublicidadAnuncioCanal) clienteEscogido).getDuracion();
        txtDuracion.setText(dura + "");
        txtPresupuesto.setText(clienteEscogido.getPresupuesto()+"");
        txtFechaFin.setText(clienteEscogido.getFechaFin().toString());
        txtComision.setText(clienteEscogido.getComisionAEmpresa()+"");
    }    

    @FXML
    private void irInicio(ActionEvent event) {
    }

    @FXML
    private void editarAnuncioCanal(ActionEvent event) {
    }

    @FXML
    private void eliminarAnuncioCanal(ActionEvent event) {
    }
    
}
