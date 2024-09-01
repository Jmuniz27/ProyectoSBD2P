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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }
    
}
