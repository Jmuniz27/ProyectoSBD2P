/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.sql.Date;
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

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarPCController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfDuracion;
    @FXML
    private TextField tfPresupuesto;
    @FXML
    private TextField tfDescri;
    @FXML
    private TextField tfComision;
    
    private PublicidadAnuncioCanal pc;
    @FXML
    private DatePicker dpFechaIni;
    @FXML
    private DatePicker dpFechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = GestionPublicidadCanalController.pcEscogido;
        tfTitulo.setText(pc.getTitulo());
        tfDuracion.setText(pc.getDuracion()+"");
        tfPresupuesto.setText(pc.getPresupuesto()+"");
        tfDescri.setText(pc.getDescripcion());
        tfComision.setText(pc.getComisionAEmpresa()+"");
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
            tfDuracion.getText().trim().isEmpty() ||
            tfPresupuesto.getText().trim().isEmpty() ||
            tfDescri.getText().trim().isEmpty()  ||
            tfComision.getText().trim().isEmpty()) {
            
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
            pc.setTitulo(tfTitulo.getText());
            pc.setDuracion(Integer.parseInt(tfDuracion.getText()));
            pc.setPresupuesto(Integer.parseInt(tfPresupuesto.getText()));
            pc.setDescripcion(tfDescri.getText());
            pc.setComisionAEmpresa(Double.parseDouble(tfComision.getText()));
            // Llamar al procedimiento almacenado
            try{
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_PublicidadAnuncioCanal(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setInt(1, pc.getIdProyecto());
                    cstmt.setString(2, pc.getRuc());
                    cstmt.setString(3, pc.getNumFactura());
                    cstmt.setString(4, pc.getTitulo());
                    cstmt.setInt(5, pc.getPresupuesto());
                    cstmt.setString(6, pc.getDescripcion());
                    cstmt.setDate(7, (Date) pc.getFechaInicio());
                    cstmt.setDate(8, (Date) pc.getFechaFin());
                    cstmt.setInt(9, pc.getDuracion());
                    cstmt.setString(10, pc.getId_dep_creativo());
                    cstmt.setDouble(11, pc.getComisionAEmpresa());

                    // Ejecutar el procedimiento
                    cstmt.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error al actualizar el empleado", e.getMessage());
            }

            // Cerrar la ventana después de guardar
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

    void setPublicidadCanal(PublicidadAnuncioCanal pc) {
        this.pc = pc;
    }
    
}
