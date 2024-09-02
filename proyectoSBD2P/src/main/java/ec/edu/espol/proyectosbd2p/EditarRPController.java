/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.ProductoTienda;
import ec.edu.espol.proyectosbd2p.modelo.RolPago;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarRPController implements Initializable {
    
    private RolPago rp;
    @FXML
    private TextField tfEmpleado;
    @FXML
    private TextField tfPago;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rp = GestionRolPagoController.rolPagoEscogido;
        tfPago.setText(rp.getPagoNeto()+"");
        tfEmpleado.setText(rp.getIdEmpleado());
    }    

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (tfPago.getText().trim().isEmpty() ||
            tfEmpleado.getText().trim().isEmpty()) {
            
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
            rp.setPagoNeto(Integer.parseInt(tfPago.getText()));
            rp.setIdEmpleado(tfEmpleado.getText());
            // Llamar al procedimiento almacenado
            try{
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_RolPago(?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setInt(1, rp.getIdPago());
                    cstmt.setInt(2, rp.getPagoNeto());
                    cstmt.setString(3, rp.getIdEmpleado());
                    cstmt.setString(4, rp.getIdDepFinanzas());

                    // Ejecutar el procedimiento
                    cstmt.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error al actualizar el empleado", e.getMessage());
            }

            // Cerrar la ventana después de guardar
            Stage stage = (Stage) tfPago.getScene().getWindow();
            stage.close();
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void setRolPago(RolPago rp) {
        this.rp = rp;
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfPago.getScene().getWindow();
        stage.close();
    }
    
}
