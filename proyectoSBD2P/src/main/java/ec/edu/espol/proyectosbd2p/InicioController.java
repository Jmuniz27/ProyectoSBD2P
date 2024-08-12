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
import javafx.scene.image.Image;
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
    private ImageView imgEmpleado;
    @FXML
    private ImageView imgCliente;
    @FXML
    private Button btnEmpleado;
    @FXML
    private Button btnCliente;
    @FXML
    private ImageView imgCliente1;
    @FXML
    private Button btnProyectos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }    


    @FXML
    private void clickEnProyectos(ActionEvent event) {
        try{
            App.setRoot("gestionClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEnEmpleado(ActionEvent event) {
        try{
            App.setRoot("gestionEmpleados");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEnCliente(ActionEvent event) {
        try{
            App.setRoot("gestionClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
