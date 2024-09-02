/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.ProductoTienda;
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
public class EditarPTController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfCategoria;
    @FXML
    private TextField tfPresupuesto;
    @FXML
    private TextField tfDescri;
    @FXML
    private TextField tfComision;
    @FXML
    private TextField tfPrecio;
    
    private ProductoTienda pt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pt = GestionProductoTiendaController.ptEscogido;
        tfTitulo.setText(pt.getTitulo());
        tfCategoria.setText(pt.getCategoria()+"");
        tfPresupuesto.setText(pt.getPresupuesto()+"");
        tfDescri.setText(pt.getDescripcion());
        tfComision.setText(pt.getComisionAEmpresa()+"");
        tfPrecio.setText(""+pt.getPrecio());
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
            tfCategoria.getText().trim().isEmpty() ||
            tfPresupuesto.getText().trim().isEmpty() ||
            tfDescri.getText().trim().isEmpty()  ||
            tfComision.getText().trim().isEmpty() || tfPrecio.getText().trim().isEmpty()) {
            
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
            pt.setTitulo(tfTitulo.getText());
            pt.setCategoria(tfCategoria.getText());
            pt.setPresupuesto(Integer.parseInt(tfPresupuesto.getText()));
            pt.setDescripcion(tfDescri.getText());
            pt.setComisionAEmpresa(Double.parseDouble(tfComision.getText()));
            pt.setPrecio(Integer.parseInt(tfPrecio.getText()));
            // Llamar al procedimiento almacenado
            try{
                Connection conn = DatabaseConnection.getConnection();
                String sql = "{CALL actualizar_ProductoTienda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cstmt = conn.prepareCall(sql)) {
                    cstmt.setInt(1, pt.getIdProyecto());
                    cstmt.setString(2, pt.getRuc());
                    cstmt.setString(3, pt.getNumFactura());
                    cstmt.setString(4, pt.getCategoria());
                    cstmt.setInt(5, pt.getPrecio());
                    cstmt.setString(6, pt.getTitulo());
                    cstmt.setInt(7, pt.getPresupuesto());
                    cstmt.setString(8, pt.getDescripcion());
                    cstmt.setDate(9, (Date) pt.getFechaInicio());
                    cstmt.setDate(10, (Date) pt.getFechaFin());
                    cstmt.setString(11, pt.getIdDepProd());
                    cstmt.setDouble(12, pt.getComisionAEmpresa());

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

    void setProductoTienda(ProductoTienda pt) {
        this.pt = pt;
    }
    
}
