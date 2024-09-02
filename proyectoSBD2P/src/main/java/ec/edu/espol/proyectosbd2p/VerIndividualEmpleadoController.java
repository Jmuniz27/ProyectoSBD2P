/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
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
public class VerIndividualEmpleadoController implements Initializable {

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
    
    private Empleado empleado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado = GestionEmpleadoController.empleadoEscogido;
        
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }    

    private void updateGrid() {
        gridInfo.getChildren().clear();
        gridInfo.setVgap(60); 
        gridInfo.setHgap(60);
        gridInfo.setAlignment(Pos.CENTER);
        VBox vbId = plantilla("ID Empleado",empleado.getIdEmpleado());
        gridInfo.add(vbId, 0, 0);
        VBox vbNom = plantilla("Nombre",empleado.getNombre());
        gridInfo.add(vbNom, 1, 0);
        VBox vbApe = plantilla("Apellido",empleado.getApellido());
        gridInfo.add(vbApe, 2, 0);
        VBox vbPues = plantilla("Puesto",empleado.getPuesto());
        gridInfo.add(vbPues, 3, 0);
        VBox vbSueldo = plantilla("Sueldo Base",empleado.getSueldoBase()+"");
        gridInfo.add(vbSueldo, 0, 1);
        VBox vbDir = plantilla("Dirección",empleado.getDireccion());
        gridInfo.add(vbDir, 1, 1);
        VBox vbCon = plantilla("Contraseña",empleado.getContrasena());
        gridInfo.add(vbCon, 2, 1);
        VBox vbDep = null;
        if(empleado.getIdDepCreativo()!=null){
            vbDep = plantilla("Departamento",empleado.getIdDepCreativo());
        } else if(empleado.getIdDepFinanzas()!=null){
            vbDep = plantilla("Departamento",empleado.getIdDepFinanzas());
        } else{
            vbDep = plantilla("Departamento",empleado.getIdDepProd());
        }
        gridInfo.add(vbDep, 3, 1);
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
            App.setRoot("gestionEmpleado");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista de edición
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarEmpleados.fxml"));
            VBox root = loader.load();

            // Obtener el controlador de la vista de edición
            EditarEmpleadosController controller = loader.getController();
            // Pasar el cliente actual al controlador de la vista de edición
            controller.setEmpleado(empleado);

            // Crear un nuevo Stage para la ventana emergente
            Stage stage = new Stage();
            stage.setTitle("Editar Empleado");
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

    @FXML
    private void eliminar(ActionEvent event) {
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
