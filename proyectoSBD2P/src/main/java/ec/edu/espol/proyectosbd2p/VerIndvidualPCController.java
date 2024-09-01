/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;
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
public class VerIndvidualPCController implements Initializable {

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
    
    private PublicidadAnuncioCanal pc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = GestionPublicidadCanalController.pcEscogido;
        
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        
    }
    
    private void updateGrid() {
        gridInfo.getChildren().clear();
        gridInfo.setVgap(60); 
        gridInfo.setHgap(60);
        gridInfo.setAlignment(Pos.CENTER);
        VBox vbIdp = plantilla("ID Proyecto",pc.getIdProyecto());
        gridInfo.add(vbIdp, 0, 0);
        VBox vbRuc = plantilla("RUC del Cliente",pc.getRuc());
        gridInfo.add(vbRuc, 1, 0);
        VBox vbFac = plantilla("Número de Factura",pc.getNumFactura());
        gridInfo.add(vbFac, 2, 0);
        VBox vbTit = plantilla("Título",pc.getTitulo());
        gridInfo.add(vbTit, 3, 0);
        VBox vbPres = plantilla("Presupuesto",pc.getPresupuesto()+"");
        gridInfo.add(vbPres, 4, 0);
        VBox vbDescrip = plantilla("Descripción",pc.getDescripcion());
        gridInfo.add(vbDescrip, 0, 1);
        VBox vbFechaIni = plantilla("Fecha de Inicio",pc.getFechaInicio()+"");
        gridInfo.add(vbFechaIni, 1, 1);
        VBox vbFechaFin = plantilla("Fecha de Fin",pc.getFechaFin()+"");
        gridInfo.add(vbFechaFin, 2, 1);
        VBox vbDuracion = plantilla("Duración",pc.getDuracion()+"");
        gridInfo.add(vbDuracion, 3, 1);
        VBox vbComision = plantilla("Comisión",pc.getComisionAEmpresa()+"");
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
            App.setRoot("gestionPublicidadCanal");
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
