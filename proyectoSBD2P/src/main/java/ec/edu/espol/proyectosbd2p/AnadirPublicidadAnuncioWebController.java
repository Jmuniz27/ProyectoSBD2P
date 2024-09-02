package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AnadirPublicidadAnuncioWebController implements Initializable {

    @FXML
    private TextField tfRuc;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfPresupuesto;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private TextField tfTamanoBanner;
    @FXML
    private TextField tfComision;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No se requiere inicialización especial en este caso
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista anterior
        try {
            App.setRoot("publicidadAnuncioWeb");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guardarAnuncioWeb(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfRuc.getText().trim().isEmpty() ||
            tfTitulo.getText().trim().isEmpty() ||
            tfPresupuesto.getText().trim().isEmpty() ||
            tfDescripcion.getText().trim().isEmpty() ||
            dpFechaInicio.getValue() == null ||
            dpFechaFin.getValue() == null ||
            tfTamanoBanner.getText().trim().isEmpty() ||
            tfComision.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado `crear_PublicidadAnuncioWeb`
            String sql = "{CALL crear_PublicidadAnuncioWeb(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tfRuc.getText());
            cstmt.setString(2, tfTitulo.getText());
            cstmt.setInt(3, Integer.parseInt(tfPresupuesto.getText()));
            cstmt.setString(4, tfDescripcion.getText());
            cstmt.setDate(5, java.sql.Date.valueOf(dpFechaInicio.getValue()));
            cstmt.setDate(6, java.sql.Date.valueOf(dpFechaFin.getValue()));
            cstmt.setString(7, tfTamanoBanner.getText());
            cstmt.setString(8, "DEP001"); // ID fijo para el departamento creativo
            cstmt.setBigDecimal(9, new java.math.BigDecimal(tfComision.getText()));

            cstmt.executeUpdate();

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Anuncio web creado correctamente.");

            // Redirigir a la ventana de anuncios web
            App.setRoot("publicidadAnuncioWeb");

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
