package ec.edu.espol.proyectosbd2p;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class anadirClientesController implements Initializable {

    @FXML
    private TextField tfanadirNombreEmpresa;
    @FXML
    private TextField tfanadirDescrip;
    @FXML
    private TextField tfanadirDireccion;
    @FXML
    private TextField tfanadirSitioWeb;
    @FXML
    private TextField tfanadirPersonaContacto;

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
