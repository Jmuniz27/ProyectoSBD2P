/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioWeb;
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

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarPWController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfPresupuesto;
    @FXML
    private TextField tfDescri;
    @FXML
    private DatePicker dpFechaIni;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private TextField tfComision;
    @FXML
    private TextField tfTamano;
    
    private PublicidadAnuncioWeb pw;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pw = GestionPublicidadWebController.pwEscogido;
        tfTitulo.setText(pw.getTitulo());
        tfTamano.setText(pw.getTamanoBanner()+"");
        tfPresupuesto.setText(pw.getPresupuesto()+"");
        tfDescri.setText(pw.getDescripcion());
        tfComision.setText(pw.getComisionAEmpresa()+"");
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
            tfTamano.getText().trim().isEmpty() ||
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
            pw.setTitulo(tfTitulo.getText());
            pw.setTamanoBanner(tfTamano.getText());
            pw.setPresupuesto(Integer.parseInt(tfPresupuesto.getText()));
            pw.setDescripcion(tfDescri.getText());
            pw.setComisionAEmpresa(Double.parseDouble(tfComision.getText()));
            // Llamar al procedimiento almacenado
            try{
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_PublicidadAnuncioWeb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setInt(1, pw.getIdProyecto());
                    cstmt.setString(2, pw.getRuc());
                    cstmt.setString(3, pw.getNumFactura());
                    cstmt.setString(4, pw.getTitulo());
                    cstmt.setInt(5, pw.getPresupuesto());
                    cstmt.setString(6, pw.getDescripcion());
                    cstmt.setDate(7, (Date) pw.getFechaInicio());
                    cstmt.setDate(8, (Date) pw.getFechaFin());
                    cstmt.setString(9, pw.getTamanoBanner());
                    cstmt.setString(10, pw.getId_dep_creativo());
                    cstmt.setDouble(11, pw.getComisionAEmpresa());

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

    void setPublicidadWeb(PublicidadAnuncioWeb pw) {
        this.pw = pw;
    }
    
}
