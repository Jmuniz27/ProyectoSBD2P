/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class VerIndividualClientesController implements Initializable {

    @FXML
    private Label txtRUC;
    @FXML
    private Label txtDireccion;
    @FXML
    private Label txtNombreCliente;
    @FXML
    private Label txtSitioWeb;
    @FXML
    private Label txtDescripcion;
    @FXML
    private Label txtIDPersonaContacto;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = GestionClientesController.clienteEscogido;
        txtRUC.setText(cliente.getRuc());
        txtDireccion.setText(cliente.getDireccion());
        txtNombreCliente.setText(cliente.getNombreEmpresa());
        txtSitioWeb.setText(cliente.getSitioWeb());
        txtDescripcion.setText(cliente.getDescripEmpresa());
        txtIDPersonaContacto.setText(cliente.getIdPersonaContacto());
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        
        
    }    

    @FXML
    private void regresar(ActionEvent event) {
        try{
            App.setRoot("gestionClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        try{
            App.setRoot("editarCliente");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        
    }
    
}
