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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class InicioController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button consultarEmpleado;
    @FXML
    private Button consultarCliente;
    @FXML
    private ImageView imgEmpleado;
    @FXML
    private ImageView imgCliente;
    @FXML
    private ImageView imgProyecto;
    @FXML
    private Button consultarProyectos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickEnEmpleados(ActionEvent event) {
    }

    @FXML
    private void clickEnCLientes(ActionEvent event) {
    }

    @FXML
    private void clickEnProyectos(ActionEvent event) {
    }
    
}
