/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class AnadirEmpleadoController implements Initializable {

    @FXML
    private Button botonInicio;
    @FXML
    private TextField txtIDEmpleado;
    @FXML
    private TextField txtSueldoBase;
    @FXML
    private TextField txtPuesto;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtIDSupervisor;
    @FXML
    private Button btnAnadirCliente;
    @FXML
    private ComboBox<String> comboBoxDepartamento;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxDepartamento.getItems().addAll("Creativo", "Producción", "Finanzas");
    }    

    @FXML
    private void irInicio(ActionEvent event) {
        try {
            App.setRoot("inicio");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirCliente(ActionEvent event) {
        if (txtIDEmpleado.getText().trim().isEmpty() ||
            txtSueldoBase.getText().trim().isEmpty() ||
            txtNombre.getText().trim().isEmpty() ||
            txtApellido.getText().trim().isEmpty() ||
            txtPuesto.getText().trim().isEmpty() ||
            txtContrasena.getText().trim().isEmpty() ||
            txtDireccion.getText().trim().isEmpty() ||
            txtIDSupervisor.getText().trim().isEmpty() ||
            comboBoxDepartamento.getValue() == null) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Crear un nuevo empleado
        Empleado nuevoEmpleado = new Empleado(
            txtIDEmpleado.getText(),
            Integer.parseInt(txtSueldoBase.getText()),
            txtNombre.getText(),
            txtApellido.getText(),
            txtPuesto.getText(),
            txtContrasena.getText(),
            txtDireccion.getText(),
<<<<<<< HEAD
            null, null, null,null,null,null
=======
            txtIDSupervisor.getText(),
            null, null, null, null, null
>>>>>>> 01f4c4de3da24fb8a6232985d744e2c332974bd4
        );

        // Asignar el departamento correspondiente
        String departamentoSeleccionado = comboBoxDepartamento.getValue();
        switch (departamentoSeleccionado) {
            case "Creativo":
                nuevoEmpleado.setIdDepCreativo("DC001");
                break;
            case "Producción":
                nuevoEmpleado.setIdDepProd("DP001");
                break;
            case "Finanzas":
                nuevoEmpleado.setIdDepFinanzas("DF001");
                break;
        }

        // Insertar el nuevo empleado en la base de datos
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO empleado (id_empleado, sueldoBase, nombre, apellido, puesto, contrasenia, direccion, idSupervisor, id_dep_creativo, id_dep_prod, id_dep_finanzas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoEmpleado.getIdEmpleado());
            pstmt.setInt(2, nuevoEmpleado.getSueldoBase());
            pstmt.setString(3, nuevoEmpleado.getNombre());
            pstmt.setString(4, nuevoEmpleado.getApellido());
            pstmt.setString(5, nuevoEmpleado.getPuesto());
            pstmt.setString(6, nuevoEmpleado.getContrasena());
            pstmt.setString(7, nuevoEmpleado.getDireccion());
            pstmt.setString(8, nuevoEmpleado.getIdSupervisor());
            pstmt.setString(9, nuevoEmpleado.getIdDepCreativo());
            pstmt.setString(10, nuevoEmpleado.getIdDepProd());
            pstmt.setString(11, nuevoEmpleado.getIdDepFinanzas());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Empleado creado correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo crear el empleado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
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
