/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class OpcionesGeneralController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgEmpleado;
    @FXML
    private Button btnEmpleado;
    @FXML
    private ImageView imgEmpleado1;
    @FXML
    private Button btnEmpleado3;
    @FXML
    private ImageView imgEmpleado2;
    @FXML
    private Button btnEmpleado4;
    @FXML
    private Button btnEmpleado31;
    @FXML
    private ImageView imgEmpleado11;
    @FXML
    private Button btnRegresar;
    @FXML
    private ImageView imgEmpleado3;
    @FXML
    private Button btnEmpleado1;
    @FXML
    private ImageView imgEmpleado21;
    @FXML
    private Button btnEmpleado41;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    private void clickCliente(ActionEvent event) {
        try{
            App.setRoot("gestionClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try{
            App.setRoot("usuariosController");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEmpleado(ActionEvent event) {
    }

    @FXML
    private void clickPubliCanal(ActionEvent event) {
    }

    @FXML
    private void clickPubliWeb(ActionEvent event) {
    }

    @FXML
    private void clickSegmento(ActionEvent event) {
    }

    @FXML
    private void clickEnTienda(ActionEvent event) {
    }

    @FXML
    private void clickRolPago(ActionEvent event) {
    }
    
}
