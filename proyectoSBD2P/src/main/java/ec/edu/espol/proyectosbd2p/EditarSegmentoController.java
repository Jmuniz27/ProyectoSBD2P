/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Segmento;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class EditarSegmentoController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfRating;
    @FXML
    private TextField tfDuracion;
    @FXML
    private TextField tfPresupuesto;
    @FXML
    private TextField tfDescri;
    @FXML
    private TextField tfComision;
    
    private Segmento seg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seg = GestionSegmentoController.segmentoEscogido;
        tfTitulo.setText(seg.getTitulo());
        tfDuracion.setText(seg.getDuracion()+"");
        tfPresupuesto.setText(seg.getPresupuesto()+"");
        tfDescri.setText(seg.getDescripcion());
        tfComision.setText(seg.getComisionAEmpresa()+"");
        tfRating.setText(seg.getRating());
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
            tfComision.getText().trim().isEmpty() || tfRating.getText().trim().isEmpty()) {
            
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
            seg.setTitulo(tfTitulo.getText());
            seg.setDuracion(Integer.parseInt(tfDuracion.getText()));
            seg.setPresupuesto(Integer.parseInt(tfPresupuesto.getText()));
            seg.setDescripcion(tfDescri.getText());
            seg.setComisionAEmpresa(Double.parseDouble(tfComision.getText()));
            seg.setRating(tfRating.getText());
            // Llamar al procedimiento almacenado
            try{
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_segmento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setInt(1, seg.getIdProyecto());
                    cstmt.setString(2, seg.getRuc());
                    cstmt.setString(3, seg.getNumFactura());
                    cstmt.setString(4, seg.getRating());
                    cstmt.setInt(5, seg.getDuracion());
                    cstmt.setBoolean(6, seg.isEstado());
                    cstmt.setString(7, seg.getTitulo());
                    cstmt.setInt(8, seg.getPresupuesto());
                    cstmt.setString(9 , seg.getDescripcion());
                    cstmt.setDate(10, (Date) seg.getFechaInicio());
                    cstmt.setDate(11, (Date) seg.getFechaFin());
                    cstmt.setString(12, seg.getIdDepProd());
                    cstmt.setDouble(13, seg.getComisionAEmpresa());

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

    void setSegmento(Segmento seg) {
        this.seg = seg;
    }
    
}
