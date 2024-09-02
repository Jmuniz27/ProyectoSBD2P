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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jmuni
 */
public class anadirAnuncioCanalController implements Initializable {

    @FXML
    private TextField tfanadirWebTitulo;
    @FXML
    private TextField tfanadirWebPresupuesto;
    @FXML
    private TextField tfanadirTamano;
    @FXML
    private TextField tfanadirWebComision;
    @FXML
    private TextField tfanadirWebRuc;
    @FXML
    private DatePicker dtanadirWebFechaInicio;
    @FXML
    private DatePicker dtanadirWebFechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
    }

    @FXML
    private void anadirAnuncioCanal(ActionEvent event) {
    }
    
}
