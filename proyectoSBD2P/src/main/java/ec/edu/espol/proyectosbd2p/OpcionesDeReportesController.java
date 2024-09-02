/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class OpcionesDeReportesController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgEmpleado;
    @FXML
    private Button btnFacturas;
    @FXML
    private ImageView imgEmpleado1;
    @FXML
    private Button btnProyectos;
    @FXML
    private ImageView imgEmpleado2;
    @FXML
    private Button btnCreativos1;
    @FXML
    private Button btnNomina;
    @FXML
    private ImageView imgEmpleado11;
    @FXML
    private Button btnCreativos2;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickReporteFacturas(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Facturas_Productos_Clientes");
            App.setRoot("mostrarFacturasProductosClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickReporteGeneralProyectos(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Proyectos_Departamento_Producción_Duracion");
            App.setRoot("mostrarFacturasProductosClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickReporteCreativoPresupuesto(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Reporte_Proyectos_Departamento_Creativo");
            App.setRoot("mostrarFacturasProductosClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickPagoNomina(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Pagos_Empleados_por_Departamento");
            App.setRoot("mostrarFacturasProductosClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickReporteCreativoComisiones(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Proyectos_Comisión_Alta");
            App.setRoot("mostrarFacturasProductosClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try{
            MostrarFacturasProductosClientesController.cambiarReporte("Proyectos_Comisión_Alta");
            App.setRoot("opcionesGeneral");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
