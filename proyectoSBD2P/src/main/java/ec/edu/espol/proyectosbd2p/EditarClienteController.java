/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarClienteController implements Initializable {

    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfDescrip;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfSitioWeb;
    @FXML
    private TextField tfPersonaContacto;
    
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = GestionClientesController.clienteEscogido;
        tfNombreEmpresa.setText(cliente.getNombreEmpresa());
        tfDescrip.setText(cliente.getDescripEmpresa());
        tfDireccion.setText(cliente.getDireccion());
        tfSitioWeb.setText(cliente.getSitioWeb());
        tfPersonaContacto.setText(cliente.getIdPersonaContacto());
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
        try{
            App.setRoot("verIndividualClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
    if (tfNombreEmpresa.getText().trim().isEmpty() ||
            tfDescrip.getText().trim().isEmpty() ||
            tfDireccion.getText().trim().isEmpty() ||
            tfSitioWeb.getText().trim().isEmpty() ||
            tfPersonaContacto.getText().trim().isEmpty()) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Actualizar los datos del empleado
        cliente.setNombreEmpresa(tfNombreEmpresa.getText());
        cliente.setDescripEmpresa(tfDescrip.getText());
        cliente.setDireccion(tfDireccion.getText());
        cliente.setSitioWeb(tfSitioWeb.getText());
        cliente.setIdPersonaContacto(tfPersonaContacto.getText());

        

        // Guardar los cambios en la base de datos
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE cliente SET nombre_empresa = ?, decrip_empresa = ?, direccion = ?, sitio_web = ?, id_persona_contacto = ? WHERE RUC = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombreEmpresa());
            pstmt.setString(2, cliente.getDescripEmpresa());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setString(4, cliente.getSitioWeb());
            pstmt.setString(5, cliente.getIdPersonaContacto());
            pstmt.setString(6, cliente.getRuc());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar los datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        }
        try{
            App.setRoot("verIndividualClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
