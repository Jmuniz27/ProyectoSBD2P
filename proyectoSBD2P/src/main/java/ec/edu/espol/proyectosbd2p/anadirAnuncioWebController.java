package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.stage.Stage;
import javafx.scene.Node;

public class anadirAnuncioWebController implements Initializable {

    private TextField tfanadirWebTitulo;
    private TextField tfanadirWebPresupuesto;
    private TextField tfanadirTamano;
    @FXML
    private TextField tfanadirWebComision;
    private TextField tfanadirWebRuc;
    private DatePicker dtanadirWebFechaInicio;
    private DatePicker dtanadirWebFechaFin;
    private TextField tfanadirDescrip;
    private TextField tfNumFactura;  // Campo para ingresar num_factura si se tiene, puede ser opcional
    @FXML
    private TextField tfanadirSegmentoTitulo;
    @FXML
    private TextField tfanadirSegmentoPresupuesto;
    @FXML
    private TextField tfanadirSegmentoRating;
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
        // Inicializaciones adicionales si son necesarias
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista de inicio
        try {
            App.setRoot("usuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirAnuncioWeb(ActionEvent event) {
        // Verificar que los campos obligatorios no estén vacíos
        if (tfanadirWebTitulo.getText().trim().isEmpty() ||
            tfanadirWebPresupuesto.getText().trim().isEmpty() ||
            tfanadirTamano.getText().trim().isEmpty() ||
            tfanadirWebComision.getText().trim().isEmpty() ||
            tfanadirWebRuc.getText().trim().isEmpty() ||
            dtanadirWebFechaInicio.getValue() == null ||
            dtanadirWebFechaFin.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            // Convertir el presupuesto a entero
            int presupuesto = Integer.parseInt(tfanadirWebPresupuesto.getText().trim());

            // Convertir la comisión a BigDecimal
            BigDecimal comision = new BigDecimal(tfanadirWebComision.getText().trim());

            // Generar IDs
            String idProyecto = generateIdProyecto(); // Generar el ID del proyecto
            String numFactura = tfNumFactura.getText().trim(); // Obtener el num_factura si se ingresó

            // Si no se proporciona un num_factura, se pasa NULL
            if (numFactura.isEmpty()) {
                numFactura = null;
            }

            // Conectar a la base de datos
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado
            String sql = "{CALL crear_PublicidadAnuncioWeb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);

            cstmt.setString(1, idProyecto);
            cstmt.setString(2, tfanadirWebRuc.getText());
            cstmt.setString(3, numFactura);
            cstmt.setString(4, tfanadirWebTitulo.getText());
            cstmt.setInt(5, presupuesto);
            cstmt.setString(6, tfanadirDescrip.getText());
            cstmt.setDate(7, java.sql.Date.valueOf(dtanadirWebFechaInicio.getValue()));
            cstmt.setDate(8, java.sql.Date.valueOf(dtanadirWebFechaFin.getValue()));
            cstmt.setString(9, tfanadirTamano.getText());
            cstmt.setString(10, "DC001");  // Asumiendo que id_dep_creativo es "DC001"
            cstmt.setBigDecimal(11, comision);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Anuncio Web creado correctamente.");

                App.setRoot("usuarios");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo crear el anuncio web. Ninguna fila fue afectada.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error de Formato", "Por favor ingrese un número válido.");
        } catch (SQLException e) {
            String detailedMessage = "Error de Base de Datos: " + e.getMessage();
            // Log the SQL state and error code for more detailed debugging
            detailedMessage += "\nSQL State: " + e.getSQLState();
            detailedMessage += "\nError Code: " + e.getErrorCode();
            showAlert(Alert.AlertType.ERROR, "Error", detailedMessage);
            e.printStackTrace();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error de E/S", "No se pudo redirigir a la ventana de usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Método para generar un ID único para el proyecto con un tamaño máximo de 10 caracteres
    private String generateIdProyecto() {
        String id = "P" + System.currentTimeMillis();
        if (id.length() > 10) {
            id = id.substring(0, 10);
        }
        return id;
    }

    // Método para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
