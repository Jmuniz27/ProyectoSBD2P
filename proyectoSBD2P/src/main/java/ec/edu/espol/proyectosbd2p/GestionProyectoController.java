/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import ec.edu.espol.proyectosbd2p.modelo.Proyecto;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioWeb;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class GestionProyectoController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button botonInicio;
    @FXML
    private VBox containerFAuto;
    @FXML
    private VBox filtroAuto;
    @FXML
    private TextField tbId;
    @FXML
    private ComboBox<String> cbTipoProyecto;
    @FXML
    private TextField tbTitulo;
    @FXML
    private TextField tbPresupuesto;
    @FXML
    private TextField tbComision;
    @FXML
    private TextField tbRUC;
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
    
    ArrayList<Proyecto> listaProyectos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipoProyecto.getItems().addAll("Segmento", "Producto en Tienda", "Anuncio Web", "Anuncio en Canal");
    }    

    @FXML
    private void irInicio(ActionEvent event) {
    }

    @FXML
    private void buscarFiltros(ActionEvent event) {
    }

    @FXML
    private void prevPag(ActionEvent event) {
    }

    @FXML
    private void nextPag(ActionEvent event) {
    }
    public Set<Empleado> generarQuery(String columna, String busqueda){
        Set<Proyecto> proyectos = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_web WHERE " + columna + "=" + busqueda;
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    String id_proyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String num_factura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    String tamano_banner = resultSet.getString("tamanoBanner");
                    String id_dep_creativo = resultSet.getString("id_dep_creativo");
                    Float comision_a_empresa = resultSet.getFloat("comision_a_empresa");
                    PublicidadAnuncioWeb publicidadWeb = new PublicidadAnuncioWeb(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, comision_a_empresa);
                    proyectos.add(publicidadWeb);
                }
                
                
                String sql = "SELECT * FROM cliente WHERE " + columna + "=" + busqueda;
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
