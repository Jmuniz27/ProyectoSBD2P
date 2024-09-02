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

public class AnadirProductoTiendaController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfCategotia;
    @FXML
    private TextField tfanadirSitioWeb;
    @FXML
    private TextField tfanadirPrecio;
    @FXML
    private TextField tfanadirComision;
    @FXML
    private TextField tfanadirDescripcion;
    @FXML
    private TextField tfanadirRuc;
    @FXML
    private DatePicker dtanadirFechaInicio;
    @FXML
    private DatePicker dtanadirFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesario
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfTitulo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (tfTitulo.getText().trim().isEmpty() ||
            tfCategotia.getText().trim().isEmpty() ||
            tfanadirPrecio.getText().trim().isEmpty() ||
            tfanadirComision.getText().trim().isEmpty() ||
            tfanadirDescripcion.getText().trim().isEmpty() ||
            tfanadirRuc.getText().trim().isEmpty() ||
            dtanadirFechaInicio.getValue() == null ||
            dtanadirFechaFin.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Confirmación antes de insertar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Seguro que desea añadir este producto a la tienda?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Llamar al procedimiento almacenado
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL crear_ProductoTienda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    // Suponiendo que se genera automáticamente el ID del proyecto o lo obtienes de otro lado
                    String idProyecto = generarIdProyecto();
                    String numFactura = obtenerNumFactura(); // Implementa según tu lógica

                    cstmt.setString(1, idProyecto);
                    cstmt.setString(2, tfanadirRuc.getText());
                    cstmt.setString(3, numFactura);
                    cstmt.setString(4, tfCategotia.getText());
                    cstmt.setDouble(5, Double.parseDouble(tfanadirPrecio.getText()));
                    cstmt.setString(6, tfTitulo.getText());
                    cstmt.setInt(7, obtenerPresupuesto()); // Implementa según tu lógica para obtener el presupuesto
                    cstmt.setString(8, tfanadirDescripcion.getText());
                    cstmt.setDate(9, Date.valueOf(dtanadirFechaInicio.getValue()));
                    cstmt.setDate(10, Date.valueOf(dtanadirFechaFin.getValue()));
                    cstmt.setString(11, "DEP001"); // ID del departamento de producción (modifícalo según tu lógica)
                    cstmt.setDouble(12, Double.parseDouble(tfanadirComision.getText()));

                    // Ejecutar el procedimiento
                    cstmt.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto añadido correctamente.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error al añadir el producto", e.getMessage());
            }

            // Cerrar la ventana después de añadir
            Stage stage = (Stage) tfTitulo.getScene().getWindow();
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

    private int obtenerPresupuesto() {
        // Lógica para obtener el presupuesto (puedes ajustar esto según tus necesidades)
        return 10000; // Valor de ejemplo, ajusta según la lógica de tu sistema
    }
}
