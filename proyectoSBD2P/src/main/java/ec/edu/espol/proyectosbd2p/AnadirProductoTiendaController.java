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
    private TextField tfanadirPrecio;
    @FXML
    private TextField tfanadirTiendaTitulo;
    @FXML
    private TextField tfCategoria;
    @FXML
    private TextField tfanadirTiendaPresupuesto;
    @FXML
    private TextField tfanadirTiendaComision;
    @FXML
    private TextField tfanadirTiendaDescripcion;
    @FXML
    private TextField tfanadirTiendaRuc;
    @FXML
    private DatePicker dtanadirTiendaFechaInicio;
    @FXML
    private DatePicker dtanadirTiendaFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesario
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfanadirTiendaTitulo.getScene().getWindow();
        stage.close();
    }

    private void guardarCambios(ActionEvent event) {
        if (tfanadirTiendaTitulo.getText().trim().isEmpty() ||
            tfCategoria.getText().trim().isEmpty() ||
            tfanadirPrecio.getText().trim().isEmpty() ||
            tfanadirTiendaComision.getText().trim().isEmpty() ||
            tfanadirTiendaDescripcion.getText().trim().isEmpty() ||
            tfanadirTiendaRuc.getText().trim().isEmpty() ||
            dtanadirTiendaFechaInicio.getValue() == null ||
            dtanadirTiendaFechaFin.getValue() == null||
                tfanadirTiendaPresupuesto.getText().trim().isEmpty()) {

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
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL crear_ProductoTienda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement cstmt = conn.prepareCall(sql);
                cstmt.setString(1, tfanadirTiendaRuc.getText());
                cstmt.setString(2, tfCategoria.getText());
                cstmt.setDouble(3, Double.parseDouble(tfanadirPrecio.getText()));
                cstmt.setString(4, tfanadirTiendaTitulo.getText());
                cstmt.setInt(5, Integer.parseInt(tfanadirTiendaPresupuesto.getText()));
                cstmt.setString(6, tfanadirTiendaDescripcion.getText());
                cstmt.setDate(7, Date.valueOf(dtanadirTiendaFechaInicio.getValue()));
                cstmt.setDate(8, Date.valueOf(dtanadirTiendaFechaFin.getValue()));
                cstmt.setString(9, "DP001"); 
                cstmt.setDouble(10, Double.parseDouble(tfanadirTiendaComision.getText()));

                // Ejecutar el procedimiento
                cstmt.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto añadido correctamente.");
                
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error al añadir el producto", e.getMessage());
            }

            // Cerrar la ventana después de añadir
            Stage stage = (Stage) tfanadirTiendaTitulo.getScene().getWindow();
            stage.close();
        }
    }

    // Método para mostrar alertas
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
        return "FAC" + System.currentTimeMillis(); // Ejemplo simple, usa tu propia lógica
    }

    private int obtenerPresupuesto() {
        // Lógica para obtener el presupuesto (puedes ajustar esto según tus necesidades)
        return 10000; // Valor de ejemplo, ajusta según la lógica de tu sistema
    }

    @FXML
    private void anadirProductoTienda(ActionEvent event) {
        this.guardarCambios(event);
    }
}
