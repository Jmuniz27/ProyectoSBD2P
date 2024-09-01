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
 * @author creditos gonzalez
 */
public class anadirAnuncioWebController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfCategoria;
    @FXML
    private TextField tfanadirSitioWeb;
    @FXML
    private TextField tfanadirPrecio;
    @FXML
    private TextField tfanadirComision;
    @FXML
    private TextField tfanadirDescripcion;
    @FXML
    private TextField tfanadirRuc;
    @FXML
    private DatePicker dtanadirFechaInicio;
    @FXML
    private DatePicker dtanadirFechaFin;

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
    private void guardarCambios(ActionEvent event) {
    }
    
}
