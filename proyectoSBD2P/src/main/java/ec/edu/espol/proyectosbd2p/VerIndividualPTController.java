/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.ProductoTienda;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class VerIndividualPTController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private HBox hbInfo1;
    @FXML
    private HBox hbInfo2;
    
    private ProductoTienda pt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pt = GestionProductoTiendaController.ptEscogido;
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }
    
    private void updateGrid() {
        hbInfo1.getChildren().clear();
        hbInfo2.getChildren().clear();
        VBox vbIdp = plantilla("ID Proyecto",pt.getIdProyecto());
        hbInfo1.getChildren().add(vbIdp);
        VBox vbRuc = plantilla("RUC del Cliente",pt.getRuc());
        hbInfo1.getChildren().add(vbRuc);
        VBox vbFac = plantilla("Número de Factura",pt.getNumFactura());
        hbInfo1.getChildren().add(vbFac);
        VBox vbTit = plantilla("Título",pt.getTitulo());
        hbInfo1.getChildren().add(vbTit);
        VBox vbPres = plantilla("Presupuesto",pt.getPresupuesto()+"");
        hbInfo1.getChildren().add(vbPres);
        VBox vbDescrip = plantilla("Descripción",pt.getDescripcion());
        hbInfo1.getChildren().add(vbDescrip);
        VBox vbFechaIni = plantilla("Fecha de Inicio",pt.getFechaInicio()+"");
        hbInfo2.getChildren().add(vbFechaIni);
        VBox vbFechaFin = plantilla("Fecha de Fin",pt.getFechaFin()+"");
        hbInfo2.getChildren().add(vbFechaFin);
        VBox vbCat = plantilla("Categoria",pt.getCategoria()+"");
        hbInfo2.getChildren().add(vbCat);
        VBox vbPrecio = plantilla("Precio",pt.getPrecio()+"");
        hbInfo2.getChildren().add(vbPrecio);
        VBox vbComision = plantilla("Comisión",pt.getComisionAEmpresa()+"");
        hbInfo2.getChildren().add(vbComision);
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
            App.setRoot("gestionProductoTienda");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista de edición
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarPT.fxml"));
            VBox root = loader.load();

            // Obtener el controlador de la vista de edición
            EditarPTController controller = loader.getController();
            // Pasar el cliente actual al controlador de la vista de edición
            controller.setProductoTienda(pt);

            // Crear un nuevo Stage para la ventana emergente
            Stage stage = new Stage();
            stage.setTitle("Editar Producto Tienda");
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
