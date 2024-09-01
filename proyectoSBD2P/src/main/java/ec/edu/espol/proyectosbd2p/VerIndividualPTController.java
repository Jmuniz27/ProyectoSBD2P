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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }
    
}
