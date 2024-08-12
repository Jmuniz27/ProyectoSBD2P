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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class AgregarProyectoController implements Initializable {

    @FXML
    private Label lblProyecto;
    @FXML
    private TextField txtIDProyecto;
    @FXML
    private Label lblRUC;
    @FXML
    private TextField txtRuc;
    @FXML
    private Label lblNumFact;
    @FXML
    private TextField txtNumFact;
    @FXML
    private Label lblDescripcion;
    @FXML
    private TextField txtTitulo;
    @FXML
    private Label lblPresupuesto;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label fechaInicio;
    @FXML
    private TextField txtPresupuesto;
    @FXML
    private Label lblFechaFin;
    @FXML
    private Label lblCreat;
    @FXML
    private TextField txtIDDepCreativo;
    @FXML
    private Label lblProd;
    @FXML
    private TextField txtIDDepProd;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtRating;
    @FXML
    private TextField txtComision;
    @FXML
    private DatePicker dtDateIn;
    @FXML
    private DatePicker dtDateFin;
    @FXML
    private TextField txtTamano;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtPrecio;

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
