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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class VerIndividualEmpleadoController implements Initializable {

    @FXML
    private Label txtIDEmpleado;
    @FXML
    private Label txtSueldoBase;
    @FXML
    private Label txtPuesto;
    @FXML
    private Label txtContrasena;
    @FXML
    private Label txtNombreEmpleado;
    @FXML
    private Label txtDireccion;
    @FXML
    private Label txtApellidoEmpleado;
    @FXML
    private Label txtIDSupervisor;
    
    private Empleado empleado;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label txtDepartamento;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado = GestionEmpleadoController.empleadoEscogido;
        txtIDEmpleado.setText(empleado.getIdEmpleado());
        txtDireccion.setText(empleado.getDireccion());
        txtSueldoBase.setText(empleado.getSueldoBase()+"");
        txtPuesto.setText(empleado.getPuesto());
        txtContrasena.setText(empleado.getContrasena());
        txtNombreEmpleado.setText(empleado.getNombre());
        txtApellidoEmpleado.setText(empleado.getApellido());
        txtIDSupervisor.setText(empleado.getIdSupervisor());
        if(empleado.getIdDepCreativo()!=null){
            txtDepartamento.setText("Creativo");
        } else if (empleado.getIdDepProd()!=null){
            txtDepartamento.setText("Productivo");
        } else{
            txtDepartamento.setText("Finanzas");
        }
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
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
        try{
            App.setRoot("gestionEmpleado");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }
    
}
