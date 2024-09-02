/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarEmpleadosController implements Initializable {

    
    @FXML
    private TextField tfDireccion;
    
    private Empleado empleado;
    
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfContrasena;
    @FXML
    private TextField tfSueldo;
    @FXML
    private ComboBox<String> cbDepartamento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado = GestionEmpleadoController.empleadoEscogido;
        tfDireccion.setText(empleado.getDireccion());
        tfNombre.setText(empleado.getNombre());
        tfApellido.setText(empleado.getApellido());
        tfContrasena.setText(empleado.getContrasena());
        tfSueldo.setText(empleado.getSueldoBase()+"");
        cbDepartamento.getItems().add("Creativo");
        cbDepartamento.getItems().add("Finanzas");
        cbDepartamento.getItems().add("Producción");
    }    

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Cierra la ventana actual sin guardar cambios
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void guardarCambios(ActionEvent event) throws SQLException {
        if (tfDireccion.getText().trim().isEmpty() ||
            tfNombre.getText().trim().isEmpty() ||
            tfApellido.getText().trim().isEmpty() ||
            tfContrasena.getText().trim().isEmpty() ||
            tfSueldo.getText().trim().isEmpty() ||
            cbDepartamento.getValue()==null) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }
        // Confirmación antes de guardar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar cambios");
        confirmacion.setHeaderText("¿Seguro que desea guardar los cambios?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Actualizar los datos del cliente
            empleado.setNombre(tfNombre.getText());
            empleado.setApellido(tfApellido.getText());
            empleado.setDireccion(tfDireccion.getText());
            empleado.setContrasena(tfContrasena.getText());
            empleado.setSueldoBase(Integer.parseInt(tfSueldo.getText()));
            empleado.setIdDepCreativo(null);
            empleado.setIdDepFinanzas(null);
            empleado.setIdDepProd(null);
            if(cbDepartamento.getValue().equals("Creativo")){
                empleado.setIdDepCreativo("DC001");
            } else if(cbDepartamento.getValue().equals("Producción")){
                empleado.setIdDepProd("DP001");
            } else{
                empleado.setIdDepFinanzas("DF001");
            }

            // Configurar el departamento basado en la selección
            String idDepCreativo = cbDepartamento.getValue().equals("Creativo") ? "DC001" : null;
            String idDepProd = cbDepartamento.getValue().equals("Producción") ? "DP001" : null;
            String idDepFinanzas = cbDepartamento.getValue().equals("Finanzas") ? "DF001" : null;

            // Llamar al procedimiento almacenado
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_Empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setString(1, empleado.getIdEmpleado());
                    cstmt.setInt(2, empleado.getSueldoBase());
                    cstmt.setString(3, empleado.getNombre());
                    cstmt.setString(4, empleado.getApellido());
                    cstmt.setString(5, empleado.getPuesto());
                    cstmt.setString(6, empleado.getContrasena());
                    cstmt.setString(7, empleado.getDireccion());
                    cstmt.setString(8, idDepCreativo);
                    cstmt.setString(9, idDepProd);
                    cstmt.setString(10, idDepFinanzas);
                    cstmt.setString(11, empleado.getId_dir_dep_creativo());
                    cstmt.setString(12, empleado.getId_dir_dep_prod());
                    cstmt.setString(13, empleado.getId_dir_dep_finanzas());

                    // Ejecutar el procedimiento
                    try {
                        cstmt.executeUpdate();
                    } catch (SQLException ex) {
                        DatabaseConnection.handleSQLException(ex);
                        ex.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar los datos.");
                        return;
                    }
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
                }

            // Cerrar la ventana después de guardar
            Stage stage = (Stage) tfNombre.getScene().getWindow();
            stage.close();
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void setEmpleado(Empleado emp) {
        this.empleado = emp;
    }
    
}
