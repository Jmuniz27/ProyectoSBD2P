/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import static ec.edu.espol.proyectosbd2p.GestionEmpleadoController.empleadoEscogido;
import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import ec.edu.espol.proyectosbd2p.modelo.ProductoTienda;
import ec.edu.espol.proyectosbd2p.modelo.Proyecto;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioWeb;
import ec.edu.espol.proyectosbd2p.modelo.Segmento;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML
    private DatePicker dpInicio;
    @FXML
    private DatePicker dpFin;
    
    private int currentPage = 1;
    
    private int totalPages;
    
    private Proyecto currentNode;
    
    public static Proyecto proyectoEscogido;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private ArrayList<Proyecto> listaMostrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipoProyecto.getItems().addAll("Segmento", "Producto en Tienda", "Anuncio Web", "Anuncio en Canal");
        proyectoEscogido = null;
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        llenarTodoGrid();
        updateGrid();
        updatePagination();
    }    

    @FXML
    private void irInicio(ActionEvent event) {
    }

    @FXML
    private void buscarFiltros(ActionEvent event) {
        String id_proyecto = "'"+ tbId.getText() + "'";
        Set<Proyecto> filtro1 = generarQuery("id_proyecto", id_proyecto);
        String titulo = tbTitulo.getText();
        Set<Proyecto> filtro2 = generarQuery("titulo", titulo);
        String presupuesto = tbPresupuesto.getText();
        Set<Proyecto> filtro3 = generarQuery("presupuesto",presupuesto);
        String comision_a_empresa = tbComision.getText();
        Set<Proyecto> filtro4 = generarQuery("comision_a_empresa", comision_a_empresa);
        String RUC = "'"+tbRUC.getText()+"'";
        Set<Proyecto> filtro5 = generarQuery("RUC", RUC);
        
        filtro1.addAll(filtro2);
        filtro1.addAll(filtro3);
        filtro1.addAll(filtro4);
        filtro1.addAll(filtro5);
        
        listaMostrada = new ArrayList<>(filtro1);
        System.out.println(listaMostrada);
        if(filtro1.isEmpty()){
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
    }

    @FXML
    private void prevPag(ActionEvent event) {
        if (currentPage > 1) {
            currentPage--;
            goToPage(currentPage);
        }
    }

    @FXML
    private void nextPag(ActionEvent event) {
        if (currentPage < totalPages) {
            currentPage++;
            goToPage(currentPage);
        }
    }
    public Set<Proyecto> generarQuery(String columna, String busqueda){
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
                    Proyecto publicidadWeb = new PublicidadAnuncioWeb(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa);
                    proyectos.add(publicidadWeb);
                }
                
                Statement statement1 = connection.createStatement();
                String sql1 = "SELECT * FROM segmento WHERE " + columna + "=" + busqueda;
                ResultSet resultSet1 = statement1.executeQuery(sql);
                
                while (resultSet1.next()) {
                    String id_proyecto = resultSet1.getString("id_proyecto");
                    String RUC = resultSet1.getString("RUC");
                    String num_factura = resultSet1.getString("num_factura");
                    String rating = resultSet1.getString("rating");
                    int duracion = resultSet1.getInt("duracion");
                    boolean estado = resultSet1.getBoolean("estado");
                    String titulo = resultSet1.getString("titulo");
                    int presupuesto = resultSet1.getInt("presupuesto");
                    String descripcion = resultSet1.getString("descripcion");
                    Date fechaInicio = resultSet1.getDate("fechaInicio");
                    Date fechaFin = resultSet1.getDate("fechaFin");
                    String id_dep_prod = resultSet1.getString("id_dep_prod");
                    Float comision_a_empresa = resultSet1.getFloat("comision_a_empresa");
                    Proyecto segmento = new Segmento(id_proyecto, RUC, num_factura, rating, duracion, estado, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_prod, comision_a_empresa);
                    proyectos.add(segmento);
                }
                
                Statement statement2 = connection.createStatement();
                String sql2 = "SELECT * FROM producto_tienda WHERE " + columna + "=" + busqueda;
                ResultSet resultSet2 = statement2.executeQuery(sql);
                
                while (resultSet2.next()) {
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
                    Proyecto producto_tienda = new ProductoTienda(id_proyecto, RUC, num_factura, categoria, precio, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_prod, comision_a_empresa);
                    proyectos.add(producto_tienda);
                }
                
                Statement statement3 = connection.createStatement();
                String sql3 = "SELECT * FROM publicidad_anuncio_web WHERE " + columna + "=" + busqueda;
                ResultSet resultSet3 = statement3.executeQuery(sql);
                
                while (resultSet3.next()) {
                    String id_proyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String num_factura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion = resultSet.getInt("duracion");
                    String id_dep_creativo = resultSet.getString("id_dep_creativo");
                    Float comision_a_empresa = resultSet.getFloat("comision_a_empresa");
                    Proyecto publicidadCanal = new PublicidadAnuncioCanal(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion, id_dep_creativo, comision_a_empresa);
                    proyectos.add(publicidadCanal);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return proyectos;
    }
    
    private Set<Proyecto> generarQueryTodo() {
        Set<Proyecto> proyectos = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_web";
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
                    Proyecto publicidadWeb = new PublicidadAnuncioWeb(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa);
                    proyectos.add(publicidadWeb);
                }
                
                Statement statement1 = connection.createStatement();
                String sql1 = "SELECT * FROM segmento";
                ResultSet resultSet1 = statement1.executeQuery(sql);
                
                while (resultSet1.next()) {
                    String id_proyecto = resultSet1.getString("id_proyecto");
                    String RUC = resultSet1.getString("RUC");
                    String num_factura = resultSet1.getString("num_factura");
                    String rating = resultSet1.getString("rating");
                    int duracion = resultSet1.getInt("duracion");
                    boolean estado = resultSet1.getBoolean("estado");
                    String titulo = resultSet1.getString("titulo");
                    int presupuesto = resultSet1.getInt("presupuesto");
                    String descripcion = resultSet1.getString("descripcion");
                    Date fechaInicio = resultSet1.getDate("fechaInicio");
                    Date fechaFin = resultSet1.getDate("fechaFin");
                    String id_dep_prod = resultSet1.getString("id_dep_prod");
                    Float comision_a_empresa = resultSet1.getFloat("comision_a_empresa");
                    Proyecto segmento = new Segmento(id_proyecto, RUC, num_factura, rating, duracion, estado, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_prod, comision_a_empresa);
                    proyectos.add(segmento);
                }
                
                Statement statement2 = connection.createStatement();
                String sql2 = "SELECT * FROM producto_tienda";
                ResultSet resultSet2 = statement2.executeQuery(sql);
                
                while (resultSet2.next()) {
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
                    Proyecto producto_tienda = new ProductoTienda(id_proyecto, RUC, num_factura, categoria, precio, titulo, presupuesto, descripcion, fechaInicio, fechaFin, id_dep_prod, comision_a_empresa);
                    proyectos.add(producto_tienda);
                }
                Statement statement3 = connection.createStatement();
                String sql3 = "SELECT * FROM publicidad_anuncio_web";
                ResultSet resultSet3 = statement3.executeQuery(sql);
                
                while (resultSet3.next()) {
                    String id_proyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String num_factura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion = resultSet.getInt("duracion");
                    String id_dep_creativo = resultSet.getString("id_dep_creativo");
                    Float comision_a_empresa = resultSet.getFloat("comision_a_empresa");
                    Proyecto publicidadCanal = new PublicidadAnuncioCanal(id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion, id_dep_creativo, comision_a_empresa);
                    proyectos.add(publicidadCanal);
                }
                
                
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return proyectos;
    }

    private void goToPage(int pageIndex) {
        System.out.println("goToPage " + pageIndex);
        currentPage = pageIndex;

        // Calcular el nodo inicial para la nueva página
        currentNode = listaMostrada.get(0);
        for (int i = 0; i < (currentPage - 1) * ITEMS_PER_PAGE && currentNode != null; i++) {
            currentNode = listaMostrada.get(i);
        }
        updateGrid();
        updatePagination();
    }

    private void updateGrid() {
        gridClientes.getChildren().clear();
        if (!listaMostrada.isEmpty()) {
            Proyecto tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbProyecto = plantillaProyecto(tempNode);
                    Proyecto pro = tempNode;
                    vbProyecto.setOnMouseClicked(event -> {
                        proyectoEscogido = pro;
                        try{
                            App.setRoot("verIndividualProyecto");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbProyecto, j, i);
                    if(listaMostrada.indexOf(tempNode)+1<listaMostrada.size()){
                        tempNode = listaMostrada.get(listaMostrada.indexOf(tempNode)+1);
                    } else{
                        tempNode = null;
                    }
                }
            }
        } else {
            System.out.println("La lista de clientes está vacía.");
        }
        
    }

    private void updatePagination() {
        pagesInd.getChildren().clear();
        totalPages = (int) Math.ceil((double) listaMostrada.size() / ITEMS_PER_PAGE);
        for (int i = 1; i <= totalPages; i++) {
            Button pageButton = new Button(String.valueOf(i));
            pageButton.setCursor(javafx.scene.Cursor.HAND);
            pageButton.setStyle("-fx-background-radius: 40;");
            pageButton.setStyle("-fx-border-color:  #DADADA;");
            pageButton.setStyle("-fx-font-family: 'Arial Black';");
            pageButton.setStyle("-fx-background-color: #FFFFFF;");
            if (i == currentPage) {
                pageButton.setCursor(javafx.scene.Cursor.DEFAULT);
                pageButton.setStyle("-fx-background-radius: 40;");
                pageButton.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 20, 0.6, 0, 0);"); 
                pageButton.setStyle("-fx-background-color:#e0252d;"); // Estilo para la página activa
                pageButton.setTextFill(javafx.scene.paint.Color.WHITE);
            }
            final int pageIndex = i;
            pageButton.setOnAction(event -> goToPage(pageIndex));
            pagesInd.getChildren().add(pageButton);
        }
    }

    private void llenarTodoGrid() {
        Set<Proyecto> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }

    private VBox plantillaProyecto(Proyecto pro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaIndividual.fxml"));
            VBox vbProyecto = loader.load();
            ImageView planImg = (ImageView) vbProyecto.lookup("#planImg");
            Text planName = (Text) vbProyecto.lookup("#planName");
            Label planId = (Label) vbProyecto.lookup("#planId");
            
            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);
            
            planName.setText(pro.getTitulo());
            planId.setText(pro.getIdProyecto());

            return vbProyecto;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
