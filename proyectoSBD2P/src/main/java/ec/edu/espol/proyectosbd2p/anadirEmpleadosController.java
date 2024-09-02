package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private TextField tfanadirDireccion;
    @FXML
    private TextField tfanadirNombre;
    @FXML
    private TextField tfanadirApellido;
    @FXML
    private TextField tfanadirPuesto;
    @FXML
    private TextField tfanadirContrasena;
    @FXML
    private ComboBox<String> cbDepartamento;
    @FXML
    private TextField tfanadirEmpleado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar el ComboBox con las opciones de departamentos
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
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Verificar si el empleado ya existe
            String sqlCheck = "SELECT COUNT(*) FROM empleado WHERE id_empleado = ?";
            pstmt = conn.prepareStatement(sqlCheck);
            pstmt.setString(1, tfanadirEmpleado.getText());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                // Si el empleado no existe, inserta un nuevo registro
                String sqlEmpleado = "INSERT INTO empleado (id_empleado, nombre, apellido, puesto, contrasena, direccion, id_dep_creativo, id_dep_prod, id_dep_finanzas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sqlEmpleado);
                pstmt.setString(1, tfanadirEmpleado.getText());
                pstmt.setString(2, tfanadirNombre.getText());
                pstmt.setString(3, tfanadirApellido.getText());
                pstmt.setString(4, tfanadirPuesto.getText());
                pstmt.setString(5, tfanadirContrasena.getText());
                pstmt.setString(6, tfanadirDireccion.getText());

                // Asignar el departamento correspondiente
                String departamentoSeleccionado = cbDepartamento.getValue();
                switch (departamentoSeleccionado) {
                    case "Creativo":
                        pstmt.setString(7, "DC001"); // id_dep_creativo
                        pstmt.setString(8, null);    // id_dep_prod
                        pstmt.setString(9, null);    // id_dep_finanzas
                        break;
                    case "Producción":
                        pstmt.setString(7, null);    // id_dep_creativo
                        pstmt.setString(8, "DP001"); // id_dep_prod
                        pstmt.setString(9, null);    // id_dep_finanzas
                        break;
                    case "Finanzas":
                        pstmt.setString(7, null);    // id_dep_creativo
                        pstmt.setString(8, null);    // id_dep_prod
                        pstmt.setString(9, "DF001"); // id_dep_finanzas
                        break;
                }
                pstmt.executeUpdate();
            } else {
                // Si el empleado ya existe, actualizar su información
                String sqlUpdate = "UPDATE empleado SET nombre = ?, apellido = ?, puesto = ?, contrasena = ?, direccion = ?, id_dep_creativo = ?, id_dep_prod = ?, id_dep_finanzas = ? WHERE id_empleado = ?";
                pstmt = conn.prepareStatement(sqlUpdate);
                pstmt.setString(1, tfanadirNombre.getText());
                pstmt.setString(2, tfanadirApellido.getText());
                pstmt.setString(3, tfanadirPuesto.getText());
                pstmt.setString(4, tfanadirContrasena.getText());
                pstmt.setString(5, tfanadirDireccion.getText());

                // Asignar el departamento correspondiente
                String departamentoSeleccionado = cbDepartamento.getValue();
                switch (departamentoSeleccionado) {
                    case "Creativo":
                        pstmt.setString(6, "DC001"); // id_dep_creativo
                        pstmt.setString(7, null);    // id_dep_prod
                        pstmt.setString(8, null);    // id_dep_finanzas
                        break;
                    case "Producción":
                        pstmt.setString(6, null);    // id_dep_creativo
                        pstmt.setString(7, "DP001"); // id_dep_prod
                        pstmt.setString(8, null);    // id_dep_finanzas
                        break;
                    case "Finanzas":
                        pstmt.setString(6, null);    // id_dep_creativo
                        pstmt.setString(7, null);    // id_dep_prod
                        pstmt.setString(8, "DF001"); // id_dep_finanzas
                        break;
                }
                pstmt.setString(9, tfanadirEmpleado.getText());
                pstmt.executeUpdate();
            }

            // Mostrar alerta de confirmación
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Empleado creado o actualizado correctamente.");

            // Redirigir a la ventana de usuarios
            App.setRoot("usuarios");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
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
