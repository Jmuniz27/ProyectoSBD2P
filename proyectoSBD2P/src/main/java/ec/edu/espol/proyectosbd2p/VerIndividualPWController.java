/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioWeb;
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
public class VerIndividualPWController implements Initializable {

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
    
    private PublicidadAnuncioWeb pw;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pw = GestionPublicidadWebController.pwEscogido;
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
    }    
    
    private void updateGrid() {
        gridInfo.getChildren().clear();
        gridInfo.setVgap(60); 
        gridInfo.setHgap(60);
        gridInfo.setAlignment(Pos.CENTER);
        VBox vbIdp = plantilla("ID Proyecto",pw.getIdProyecto());
        gridInfo.add(vbIdp, 0, 0);
        VBox vbRuc = plantilla("RUC del Cliente",pw.getRuc());
        gridInfo.add(vbRuc, 1, 0);
        VBox vbFac = plantilla("Número de Factura",pw.getNumFactura());
        gridInfo.add(vbFac, 2, 0);
        VBox vbTit = plantilla("Título",pw.getTitulo());
        gridInfo.add(vbTit, 3, 0);
        VBox vbPres = plantilla("Presupuesto",pw.getPresupuesto()+"");
        gridInfo.add(vbPres, 4, 0);
        VBox vbDescrip = plantilla("Descripción",pw.getDescripcion());
        gridInfo.add(vbDescrip, 0, 1);
        VBox vbFechaIni = plantilla("Fecha de Inicio",pw.getFechaInicio()+"");
        gridInfo.add(vbFechaIni, 1, 1);
        VBox vbFechaFin = plantilla("Fecha de Fin",pw.getFechaFin()+"");
        gridInfo.add(vbFechaFin, 2, 1);
        VBox vbTamano = plantilla("Tamaño del Banner",pw.getTamanoBanner()+"");
        gridInfo.add(vbTamano, 3, 1);
        VBox vbComision = plantilla("Comisión",pw.getComisionAEmpresa()+"");
        gridInfo.add(vbComision, 4, 1);
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
            App.setRoot("gestionPublicidadWeb");
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
