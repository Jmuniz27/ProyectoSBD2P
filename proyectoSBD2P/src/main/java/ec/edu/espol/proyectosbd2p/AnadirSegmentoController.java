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

public class AnadirSegmentoController implements Initializable {

    @FXML
    private TextField tfanadirSegmentoTitulo;
    @FXML
    private TextField tfanadirSegmentoPresupuesto;
    @FXML
    private TextField tfanadirSegmentoRating;
    @FXML
    private TextField tfanadirWebComision;
    @FXML
    private TextField tfanadirSegmentoDescripcion;
    @FXML
    private TextField tfanadirSegmentoRuc;
    @FXML
    private DatePicker dtanadirSegmentoFechaInicio;
    @FXML
    private DatePicker dtanadirSegmentoFechaFin;
    @FXML
    private TextField tfanadirSegmentoDuracion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista anterior
        try {
            App.setRoot("segmentos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirSegmento(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfanadirSegmentoTitulo.getText().trim().isEmpty() ||
            tfanadirSegmentoPresupuesto.getText().trim().isEmpty() ||
            tfanadirSegmentoRating.getText().trim().isEmpty() ||
            tfanadirWebComision.getText().trim().isEmpty() ||
            tfanadirSegmentoDescripcion.getText().trim().isEmpty() ||
            tfanadirSegmentoRuc.getText().trim().isEmpty() ||
            dtanadirSegmentoFechaInicio.getValue() == null ||
            dtanadirSegmentoFechaFin.getValue() == null ||
            tfanadirSegmentoDuracion.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado `crear_Segmento`
            String sql = "{CALL crear_Segmento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tfanadirSegmentoRuc.getText());
            cstmt.setString(2, tfanadirSegmentoRating.getText());
            cstmt.setInt(3, Integer.parseInt(tfanadirSegmentoDuracion.getText()));
            cstmt.setBoolean(4, true); // Estado (puedes cambiar este valor según sea necesario)
            cstmt.setString(5, tfanadirSegmentoTitulo.getText());
            cstmt.setInt(6, Integer.parseInt(tfanadirSegmentoPresupuesto.getText()));
            cstmt.setString(7, tfanadirSegmentoDescripcion.getText());
            cstmt.setDate(8, java.sql.Date.valueOf(dtanadirSegmentoFechaInicio.getValue()));
            cstmt.setDate(9, java.sql.Date.valueOf(dtanadirSegmentoFechaFin.getValue()));
            cstmt.setString(10, "DP001"); // ID fijo para departamento de producción
            cstmt.setBigDecimal(11, new java.math.BigDecimal(tfanadirWebComision.getText()));

            cstmt.executeUpdate();

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Segmento creado correctamente.");

            // Redirigir a la ventana de segmentos
            App.setRoot("segmentos");

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
