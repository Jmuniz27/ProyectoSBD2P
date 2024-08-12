/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class GestionEmpleadoController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button botonInicio;
    @FXML
    private VBox containerFAuto;
    @FXML
    private VBox filtroAuto;
    @FXML
    private TextField tbIdEmpleado;
    @FXML
    private TextField tbPuesto;
    @FXML
    private TextField tbNombre;
    @FXML
    private TextField tbApellido;
    @FXML
    private TextField tbSueldo;
    @FXML
    private TextField tbDireccion;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;
    @FXML
    private VBox plantillaAutos;
    @FXML
    private ImageView planImg;
    @FXML
    private Text plantVehName;
    @FXML
    private Label planVehAnio;
    @FXML
    private Text planVehKm;
    @FXML
    private Text planVehCiu;
    @FXML
    private Text planVehNego;
    @FXML
    private Text planVehPrecio;
    @FXML
    private HBox pestanias;
    @FXML
    private HBox pagesInd;
    @FXML
    private Label lblNumPagina;
    @FXML
    private Label lblNumPagina1;
    @FXML
    private Label lblNumPagina2;
    
    private ArrayList<Empleado> listaMostrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irInicio(ActionEvent event) {
    }

    @FXML
    private void buscarFiltros(ActionEvent event) {
        String id_empleado = "'"+ tbIdEmpleado.getText() + "'";
        Set<Empleado> filtro1 = generarQuery("id_empleado", id_empleado);
        String sueldoBase = tbSueldo.getText();
        Set<Empleado> filtro2 = generarQuery("sueldoBase", sueldoBase);
        String nombre = "'" + tbNombre.getText() +"'";
        Set<Empleado> filtro3 = generarQuery("nombre",nombre);
        String apellido = "'" + tbApellido.getText() + "'";
        Set<Empleado> filtro4 = generarQuery("apellido", apellido);
        String puesto = "'"+tbPuesto.getText()+"'";
        Set<Empleado> filtro5 = generarQuery("puesto", puesto);
        String direccion = "'" +tbDireccion.getText() + "'";
        Set<Empleado> filtro6 = generarQuery("direccion", direccion);
        filtro1.addAll(filtro2);
        filtro1.addAll(filtro3);
        filtro1.addAll(filtro4);
        filtro1.addAll(filtro5);
        filtro1.addAll(filtro6);
        listaMostrada = new ArrayList<>(filtro1);
    }

    @FXML
    private void prevPag(ActionEvent event) {
    }

    @FXML
    private void nextPag(ActionEvent event) {
    }
    
    public Set<Empleado> generarQuery(String columna, String busqueda){
        Set<Empleado> empleados = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM empleado WHERE " + columna + "=" + busqueda;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String id_empleado = resultSet.getString("id_empleado");
                    int sueldoBase = resultSet.getInt("sueldoBase");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String puesto = resultSet.getString("puesto");
                    String contrasenia = resultSet.getString("contrasenia");
                    String direccion = resultSet.getString("direccion");
                    String idSupervisor = resultSet.getString("idSupervisor");
                    String id_dep_creativo = resultSet.getString("id_dep_creativo");
                    String id_dep_prod = resultSet.getString("id_dep_prod");
                    String id_dep_finanzas = resultSet.getString("id_dep_finanzas");
                    Empleado empleado = new Empleado(id_empleado, sueldoBase, nombre, apellido, puesto, contrasenia, direccion, idSupervisor, id_dep_creativo,id_dep_prod,id_dep_finanzas);
                    empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return empleados;
    }
}
