package ec.edu.espol.proyectosbd2p;

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
        // Inicialización si es necesario
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfanadirSegmentoTitulo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void anadirSegmento(ActionEvent event) {
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

        // Confirmación antes de insertar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Seguro que desea añadir este segmento?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Llamar al procedimiento almacenado
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL crear_Segmento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    // Suponiendo que se genera automáticamente el ID del proyecto o lo obtienes de otro lado
                    String idProyecto = generarIdProyecto();
                    String numFactura = obtenerNumFactura(); // Implementa según tu lógica

                    cstmt.setString(1, idProyecto);
                    cstmt.setString(2, tfanadirSegmentoRuc.getText());
                    cstmt.setString(3, numFactura);
                    cstmt.setString(4, tfanadirSegmentoRating.getText());
                    cstmt.setInt(5, Integer.parseInt(tfanadirSegmentoDuracion.getText()));
                    cstmt.setBoolean(6, true); // Suponiendo que estado es 'true' por defecto
                    cstmt.setString(7, tfanadirSegmentoTitulo.getText());
                    cstmt.setInt(8, Integer.parseInt(tfanadirSegmentoPresupuesto.getText()));
                    cstmt.setString(9, tfanadirSegmentoDescripcion.getText());
                    cstmt.setDate(10, Date.valueOf(dtanadirSegmentoFechaInicio.getValue()));
                    cstmt.setDate(11, Date.valueOf(dtanadirSegmentoFechaFin.getValue()));
                    cstmt.setString(12, "DEP001"); // ID del departamento de producción (modifícalo según tu lógica)
                    cstmt.setDouble(13, Double.parseDouble(tfanadirWebComision.getText()));

                    // Ejecutar el procedimiento
                    cstmt.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Segmento añadido correctamente.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error al añadir el segmento", e.getMessage());
            }

            // Cerrar la ventana después de añadir
            Stage stage = (Stage) tfanadirSegmentoTitulo.getScene().getWindow();
            stage.close();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generarIdProyecto() {
        // Lógica para generar un ID de proyecto único
        return "PROJ" + System.currentTimeMillis(); // Ejemplo simple, usa tu propia lógica
    }

    private String obtenerNumFactura() {
        // Lógica para obtener el número de factura
        return "FAC" + System.currentTimeMillis(); // Ejemplo simple, usa tu propia lógica
    }


}
