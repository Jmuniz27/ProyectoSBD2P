/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.RolPago;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class VerIndividualRPController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnRegresar;
    @FXML
    private GridPane gridInfo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    
    private RolPago rp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rp = GestionRolPagoController.rolPagoEscogido;
        
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }    

    private void updateGrid() {
        gridInfo.getChildren().clear();
        gridInfo.setVgap(60); 
        gridInfo.setHgap(60);
        gridInfo.setAlignment(Pos.CENTER);
        VBox vbId = plantilla("ID Pago",rp.getIdPago());
        gridInfo.add(vbId, 0, 0);
        VBox vbIdEmp = plantilla("ID Empleado",rp.getIdEmpleado());
        gridInfo.add(vbIdEmp, 1, 0);
        VBox vbPago = plantilla("Pago Neto",rp.getPagoNeto()+"");
        gridInfo.add(vbPago, 2, 0);
        
     }
    
    private VBox plantilla(String campo,String texto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaVer.fxml"));
            VBox vbInfo = loader.load();
            Text campo1 = (Text) vbInfo.lookup("#campo");
            Text texto1 = (Text) vbInfo.lookup("#texto");
            campo1.setText(campo);
            texto1.setText(texto);

            return vbInfo;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try{
            App.setRoot("gestionRolPago");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista de edición
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarRP.fxml"));
            VBox root = loader.load();

            // Obtener el controlador de la vista de edición
            EditarRPController controller = loader.getController();
            // Pasar el cliente actual al controlador de la vista de edición
            controller.setRolPago(rp);

            // Crear un nuevo Stage para la ventana emergente
            Stage stage = new Stage();
            stage.setTitle("Editar Rol de Pago");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana anterior hasta que se cierre esta
            stage.showAndWait(); // Mostrar la ventana y esperar a que se cierre

            // Actualizar la vista principal después de la edición
            updateGrid();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de edición.");
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }
    
}
