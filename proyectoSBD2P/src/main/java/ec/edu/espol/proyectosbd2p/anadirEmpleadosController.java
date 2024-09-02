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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class anadirEmpleadosController implements Initializable {

    @FXML
    private TextField tfanadirEmpleado;
    @FXML
    private TextField tfSueldoBase;
    @FXML
    private TextField tfanadirNombre;
    @FXML
    private TextField tfanadirApellido;
    @FXML
    private TextField tfanadirPuesto;
    @FXML
    private TextField tfanadirContrasena;
    @FXML
    private TextField tfanadirDireccion;
    @FXML
    private ComboBox<String> cbDepartamento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar ComboBox con opciones de departamentos
        cbDepartamento.getItems().addAll("Creativo", "Producción", "Finanzas");
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
    private void guardarCambios(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfanadirEmpleado.getText().trim().isEmpty() ||
            tfSueldoBase.getText().trim().isEmpty() ||
            tfanadirNombre.getText().trim().isEmpty() ||
            tfanadirApellido.getText().trim().isEmpty() ||
            tfanadirPuesto.getText().trim().isEmpty() ||
            tfanadirContrasena.getText().trim().isEmpty() ||
            tfanadirDireccion.getText().trim().isEmpty() ||
            cbDepartamento.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Llamar al procedimiento almacenado `crear_Empleado`
            String sql = "{CALL crear_Empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, tfanadirEmpleado.getText());
            cstmt.setInt(2, Integer.parseInt(tfSueldoBase.getText()));
            cstmt.setString(3, tfanadirNombre.getText());
            cstmt.setString(4, tfanadirApellido.getText());
            cstmt.setString(5, tfanadirPuesto.getText());
            cstmt.setString(6, tfanadirContrasena.getText());
            cstmt.setString(7, tfanadirDireccion.getText());

            // Asignar el departamento seleccionado
            String departamentoSeleccionado = cbDepartamento.getValue();
            switch (departamentoSeleccionado) {
                case "Creativo":
                    cstmt.setString(8, "DC001");
                    cstmt.setString(9, null);
                    cstmt.setString(10, null);
                    cstmt.setString(11, "DC001");
                    cstmt.setString(12, null);
                    cstmt.setString(13, null);
                    break;
                case "Producción":
                    cstmt.setString(8, null);
                    cstmt.setString(9, "DP001");
                    cstmt.setString(10, null);
                    cstmt.setString(11, null);
                    cstmt.setString(12, "DP001");
                    cstmt.setString(13, null);
                    break;
                case "Finanzas":
                    cstmt.setString(8, null);
                    cstmt.setString(9, null);
                    cstmt.setString(10, "DF001");
                    cstmt.setString(11, null);
                    cstmt.setString(12, null);
                    cstmt.setString(13, "DF001");
                    break;
            }

            cstmt.executeUpdate();

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Empleado creado correctamente.");

            // Redirigir a la ventana de usuarios
            App.setRoot("usuarios");

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
