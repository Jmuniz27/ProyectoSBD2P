package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AnadirRolPagoController implements Initializable {

    @FXML
    private TextField tfIdPago;
    @FXML
    private TextField tfPagoNeto;
    @FXML
    private TextField tfIdEmpleado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Asignar automáticamente un ID de pago
        generarIdPago();
    }

    private void generarIdPago() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT CONCAT('RP', LPAD(IFNULL(MAX(CAST(SUBSTRING(id_pago, 3) AS UNSIGNED)), 0) + 1, 8, '0')) AS nuevo_id FROM rol_pago";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tfIdPago.setText(rs.getString("nuevo_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo generar el ID de pago automáticamente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista anterior
        try {
            App.setRoot("rolesPago");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guardarRolPago(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfPagoNeto.getText().trim().isEmpty() ||
            tfIdEmpleado.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado `crear_RolPago`
            String sql = "{CALL crear_RolPago(?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tfIdPago.getText());  // ID Pago ya generado automáticamente
            cstmt.setInt(2, Integer.parseInt(tfPagoNeto.getText()));
            cstmt.setString(3, tfIdEmpleado.getText());
            cstmt.setString(4, "DF001"); // ID fijo para el departamento de finanzas

            cstmt.executeUpdate();

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Rol de pago creado correctamente.");

            // Redirigir a la ventana de roles de pago
            App.setRoot("rolesPago");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Método para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}