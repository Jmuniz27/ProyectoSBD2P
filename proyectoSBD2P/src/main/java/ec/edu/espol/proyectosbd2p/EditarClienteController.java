/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfNombreEmpresa.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (tfNombreEmpresa.getText().trim().isEmpty() ||
            tfDescrip.getText().trim().isEmpty() ||
            tfDireccion.getText().trim().isEmpty() ||
            tfSitioWeb.getText().trim().isEmpty()) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }
        // Confirmación antes de guardar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar cambios");
        confirmacion.setHeaderText("¿Seguro que desea guardar los cambios?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Actualizar los datos del cliente
            cliente.setNombreEmpresa(tfNombreEmpresa.getText());
            cliente.setDescripEmpresa(tfDescrip.getText());
            cliente.setDireccion(tfDireccion.getText());
            cliente.setSitioWeb(tfSitioWeb.getText());

            // Llamar al procedimiento almacenado
            try (Connection conn = DatabaseConnection.getConnection()) {
                System.out.println('1');
                String sql = "{CALL actualizar_Cliente(?, ?, ?, ?, ?, ?)}";
                System.out.println('2');
                CallableStatement cstmt = conn.prepareCall(sql);
                System.out.println('3');
                cstmt.setString(1, "'"+cliente.getRuc() +"'");
                System.out.println('4');
                cstmt.setString(2, "'"+cliente.getNombreEmpresa()+"'");
                cstmt.setString(3, "'"+cliente.getDescripEmpresa()+"'");
                cstmt.setString(4, "'"+cliente.getDireccion()+"'");
                cstmt.setString(5, "'"+cliente.getSitioWeb()+"'");
                cstmt.setString(6, "'"+cliente.getIdPersonaContacto()+"'");
                System.out.println('5');
                boolean hadResults = cstmt.execute();
                System.out.println('6');
                if (!hadResults) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar los datos.");
                }
            } catch (SQLException e) {
                showAlert(AlertType.ERROR, "Error al actualizar el cliente",e.getMessage());
            }

            // Cerrar la ventana después de guardar
            Stage stage = (Stage) tfNombreEmpresa.getScene().getWindow();
            stage.close();
        }
        
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
