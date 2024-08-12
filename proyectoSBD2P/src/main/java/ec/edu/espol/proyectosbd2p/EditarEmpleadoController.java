package ec.edu.espol.proyectosbd2p;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import ec.edu.espol.proyectosbd2p.DatabaseConnection;

public class EditarEmpleadoController {

    @FXML
    private TextField txtIDEmpleado;
    @FXML
    private TextField txtSueldoBase;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtPuesto;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtIDSupervisor;
    @FXML
    private ChoiceBox<String> choiceBoxDepartamento;

    private Empleado empleado;

    @FXML
    private void initialize() {
        // Inicializar el ChoiceBox con opciones de departamento
        choiceBoxDepartamento.getItems().addAll("Creativo", "Producción", "Finanzas");
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        cargarDatosEmpleado();
    }

    private void cargarDatosEmpleado() {
        // Cargar los datos del empleado en los campos de texto y choicebox
        if (empleado != null) {
            txtIDEmpleado.setText(empleado.getIdEmpleado());
            txtSueldoBase.setText(String.valueOf(empleado.getSueldoBase()));
            txtNombre.setText(empleado.getNombre());
            txtApellido.setText(empleado.getApellido());
            txtPuesto.setText(empleado.getPuesto());
            txtContrasena.setText(empleado.getContrasena());
            txtDireccion.setText(empleado.getDireccion());
            txtIDSupervisor.setText(empleado.getIdSupervisor());

            // Seleccionar el departamento correspondiente en el ChoiceBox
            if (empleado.getIdDepCreativo() != null) {
                choiceBoxDepartamento.setValue("Creativo");
            } else if (empleado.getIdDepProd() != null) {
                choiceBoxDepartamento.setValue("Producción");
            } else if (empleado.getIdDepFinanzas() != null) {
                choiceBoxDepartamento.setValue("Finanzas");
            }
        }
    }

    @FXML
    private void guardarCambios() {
        // Verificación de campos vacíos
        if (txtSueldoBase.getText().trim().isEmpty() ||
            txtNombre.getText().trim().isEmpty() ||
            txtApellido.getText().trim().isEmpty() ||
            txtPuesto.getText().trim().isEmpty() ||
            txtContrasena.getText().trim().isEmpty() ||
            txtDireccion.getText().trim().isEmpty() ||
            txtIDSupervisor.getText().trim().isEmpty() ||
            choiceBoxDepartamento.getValue() == null) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Actualizar los datos del empleado
        empleado.setSueldoBase(Integer.parseInt(txtSueldoBase.getText()));
        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        empleado.setPuesto(txtPuesto.getText());
        empleado.setContrasena(txtContrasena.getText());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setIdSupervisor(txtIDSupervisor.getText());

        // Asignar el departamento correspondiente
        String departamentoSeleccionado = choiceBoxDepartamento.getValue();
        empleado.setIdDepCreativo(null);
        empleado.setIdDepProd(null);
        empleado.setIdDepFinanzas(null);
        switch (departamentoSeleccionado) {
            case "Creativo":
                empleado.setIdDepCreativo("dep_creativo");
                break;
            case "Producción":
                empleado.setIdDepProd("dep_producción");
                break;
            case "Finanzas":
                empleado.setIdDepFinanzas("dep_finanzas");
                break;
        }

        // Guardar los cambios en la base de datos
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE empleado SET sueldoBase = ?, nombre = ?, apellido = ?, puesto = ?, contrasenia = ?, direccion = ?, idSupervisor = ?, id_dep_creativo = ?, id_dep_prod = ?, id_dep_finanzas = ? WHERE id_empleado = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empleado.getSueldoBase());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getApellido());
            pstmt.setString(4, empleado.getPuesto());
            pstmt.setString(5, empleado.getContrasena());
            pstmt.setString(6, empleado.getDireccion());
            pstmt.setString(7, empleado.getIdSupervisor());
            pstmt.setString(8, empleado.getIdDepCreativo());
            pstmt.setString(9, empleado.getIdDepProd());
            pstmt.setString(10, empleado.getIdDepFinanzas());
            pstmt.setString(11, empleado.getIdEmpleado());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar los datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}