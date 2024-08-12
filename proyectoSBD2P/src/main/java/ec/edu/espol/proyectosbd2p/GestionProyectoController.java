/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import ec.edu.espol.proyectosbd2p.modelo.ProductoTienda;
import ec.edu.espol.proyectosbd2p.modelo.Proyecto;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioWeb;
import ec.edu.espol.proyectosbd2p.modelo.Segmento;
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
                    PublicidadAnuncioWeb publicidadWeb = new PublicidadAnuncioWeb(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa);
                    proyectos.add(publicidadWeb);
                }
                
                
                String sql1 = "SELECT * FROM segmento WHERE " + columna + "=" + busqueda;
                ResultSet resultSet1 = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    String id_proyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String num_factura = resultSet.getString("num_factura");
                    String rating = resultSet.getString("rating");
                    int duracion = resultSet.getInt("duracion");
                    int estado = resultSet.getInt("estado");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    String id_dep_prod = resultSet.getString("id_dep_prod");
                    Float comision_a_empresa = resultSet.getFloat("comision_a_empresa");
                    Segmento segmento = new Segmento(id_proyecto, RUC, num_factura, rating, duracion, estado, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_prod, comision_a_empresa);
                    proyectos.add(segmento);
                }
                
                String sql2 = "SELECT * FROM producto_tienda WHERE " + columna + "=" + busqueda;
                ResultSet resultSet2 = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    String id_proyecto = resultSet2.getString("id_proyecto");
                    String RUC = resultSet2.getString("RUC");
                    String num_factura = resultSet2.getString("num_factura");
                    String categoria = resultSet2.getString("categoria");
                    int precio = resultSet2.getInt("precio");
                    String titulo = resultSet2.getString("titulo");
                    int presupuesto = resultSet2.getInt("presupuesto");
                    String descripcion = resultSet2.getString("descripcion");
                    Date fechaInicio = resultSet2.getDate("fechaInicio");
                    Date fechaFin = resultSet2.getDate("fechaFin");
                    String id_dep_prod = resultSet2.getString("id_dep_prod");
                    double comision_a_empresa = resultSet2.getDouble("comision_a_empresa");
                    ProductoTienda producto_tienda = new ProductoTienda(id_proyecto, RUC, num_factura, categoria, precio, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_prod, comision_a_empresa);

                    proyectos.add(producto_tienda);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return proyectos;
    }
}
