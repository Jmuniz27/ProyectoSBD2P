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

public class AnadirProductoTiendaController implements Initializable {

    @FXML
    private TextField tfRuc;
    @FXML
    private TextField tfCategoria;
    @FXML
    private TextField tfPrecio;
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
    private TextField tfComision;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No se requiere inicialización especial en este caso
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista anterior
        try {
            App.setRoot("productosTienda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guardarProductoTienda(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfRuc.getText().trim().isEmpty() ||
            tfCategoria.getText().trim().isEmpty() ||
            tfPrecio.getText().trim().isEmpty() ||
            tfTitulo.getText().trim().isEmpty() ||
            tfPresupuesto.getText().trim().isEmpty() ||
            tfDescripcion.getText().trim().isEmpty() ||
            dpFechaInicio.getValue() == null ||
            dpFechaFin.getValue() == null ||
            tfComision.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado `crear_ProductoTienda`
            String sql = "{CALL crear_ProductoTienda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tfRuc.getText());
            cstmt.setString(2, tfCategoria.getText());
            cstmt.setBigDecimal(3, new java.math.BigDecimal(tfPrecio.getText()));
            cstmt.setString(4, tfTitulo.getText());
            cstmt.setInt(5, Integer.parseInt(tfPresupuesto.getText()));
            cstmt.setString(6, tfDescripcion.getText());
            cstmt.setDate(7, java.sql.Date.valueOf(dpFechaInicio.getValue()));
            cstmt.setDate(8, java.sql.Date.valueOf(dpFechaFin.getValue()));
            cstmt.setString(9, "DEP001"); // ID fijo para el departamento de producción
            cstmt.setBigDecimal(10, new java.math.BigDecimal(tfComision.getText()));

            cstmt.executeUpdate();

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto creado correctamente.");

            // Redirigir a la ventana de productos de tienda
            App.setRoot("productosTienda");

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
