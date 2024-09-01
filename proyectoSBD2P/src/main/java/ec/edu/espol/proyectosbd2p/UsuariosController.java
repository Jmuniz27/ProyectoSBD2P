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
 * @author isabella
 */
public class UsuariosController implements Initializable {

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
    private Button btnEmpleado32;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection.closeConnection();
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }    

    @FXML
    private void clickDGeneral(ActionEvent event) {
        DatabaseConnection.conectaBase("director_general", "password123");
        try{
            App.setRoot("inicio");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickDCreativo(ActionEvent event) {
        DatabaseConnection.conectaBase("usuario_creativo", "password123");
    }

    @FXML
    private void clickDProduccion(ActionEvent event) {
        DatabaseConnection.conectaBase("usuario_produccion", "password123");

    }

    @FXML
    private void clickDFinanzas(ActionEvent event) {
        DatabaseConnection.conectaBase("usuario_finanzas", "password123");

    }

    @FXML
    private void clickCliente(ActionEvent event) {
        DatabaseConnection.conectaBase("cliente", "password123");

    }
    
}
