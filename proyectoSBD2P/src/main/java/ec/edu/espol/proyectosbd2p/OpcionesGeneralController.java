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
import javafx.scene.text.Text;

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
    @FXML
    private ImageView imgEmpleado211;
    private Text txtHeader;
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
            App.setRoot("usuarios");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEmpleado(ActionEvent event) {
        try{
            App.setRoot("gestionEmpleado");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickPubliCanal(ActionEvent event) {
        try{
            App.setRoot("gestionPublicidadCanal");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickPubliWeb(ActionEvent event) {
        try{
            App.setRoot("gestionPublicidadWeb");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickSegmento(ActionEvent event) {
        try{
            App.setRoot("gestionSegmento");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEnTienda(ActionEvent event) {
        try{
            App.setRoot("gestionProductoTienda");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickRolPago(ActionEvent event) {
        try{
            App.setRoot("gestionRolPago");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickReporte(ActionEvent event) {
        try{
            App.setRoot("opcionesDeReportes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
