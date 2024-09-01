/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import ec.edu.espol.proyectosbd2p.modelo.Proyecto;
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
/**
 * FXML Controller class
 *
 * @author isabella
 */
public class publicidadCanalController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private VBox containerFAuto;
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
    private TextField tbDireccion;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;
    @FXML
    private VBox filtroAuto;
    private TextField tbid;
    private TextField tbNombreEmpresa;
    private TextField tbSitioWeb;
    private TextField tbDescripcion;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int currentPage = 1;
    
    private int totalPages;
    
    private ArrayList<PublicidadAnuncioCanal> listaMostrada;
    
    public static Proyecto clienteEscogido;
    
    private PublicidadAnuncioCanal currentNode;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button botonInicio1;
    @FXML
    private Slider precMinSlid;
    @FXML
    private Slider precMaxSlid;
    @FXML
    private DatePicker DFechaInicio;
    @FXML
    private DatePicker DFechaFin;
    @FXML
    private TextField tbID;
    @FXML
    private TextField tbTitulo;
    @FXML
    private TextField tbDura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteEscogido = null;
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        llenarTodoGrid();
        updateGrid();
        updatePagination();
    }    

    @FXML
    private void irInicio(ActionEvent event) {
        try{
            App.setRoot("inicio");
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void buscarFiltros(ActionEvent event) {
        String id = "'%" + tbID.getText() + "%'";
        Set<PublicidadAnuncioCanal> filtro1 = generarQuery("id_proyecto",id);
        
        String titulo = "'%" + tbTitulo.getText() + "%'";
        Set<PublicidadAnuncioCanal> filtro2 = generarQuery("titulo",titulo);
        if(!filtro2.isEmpty())
            filtro1.retainAll(filtro2);
        
        double minValPre = precMinSlid.getValue();        
        double maxValPre = precMaxSlid.getValue();
        Set<PublicidadAnuncioCanal> filtro4 = generarQuery("presupuesto",minValPre,maxValPre);
        if(!filtro4.isEmpty())
            filtro1.retainAll(filtro4);
        
        String duracion = tbDura.getText();
        Set<PublicidadAnuncioCanal> filtro5 = generarQueryDuracion("duracion",duracion);
        if(!filtro5.isEmpty())
            filtro1.retainAll(filtro5);
        
        listaMostrada = new ArrayList<>(filtro1);
        System.out.println(listaMostrada);
        if(filtro1.isEmpty()){
            App.mostrarAlerta("No existen PublicidadAnuncioCanal", "Error", "No existen PublicidadAnuncioCanal para su búsqueda. A continuación se muestran todos los clientes.", Alert.AlertType.ERROR);
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
    }
    private Set<PublicidadAnuncioCanal> generarQueryDuracion(String columna, String duracion) {
        Set<PublicidadAnuncioCanal> publucidadCana = new HashSet<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_canal where " + columna + "=" + duracion;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String idProyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String numFactura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion1 = resultSet.getInt("duracion");
                    String idDepCreativo = resultSet.getString("id_dep_creativo");
                    double comisionEmpresa = resultSet.getDouble("comision_a_empresa");
                    PublicidadAnuncioCanal publicidad = new PublicidadAnuncioCanal(idProyecto, RUC, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion1, idDepCreativo, comisionEmpresa);
                    publucidadCana.add(publicidad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return publucidadCana;
    }

    private Set<PublicidadAnuncioCanal> generarQuery(String columna, double minValPre, double maxValPre) {
        Set<PublicidadAnuncioCanal> publucidadCana = new HashSet<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_canal where " + columna + " BETWEEN " + minValPre + "and" + minValPre;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String idProyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String numFactura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion1 = resultSet.getInt("duracion");
                    String idDepCreativo = resultSet.getString("id_dep_creativo");
                    double comisionEmpresa = resultSet.getDouble("comision_a_empresa");
                    PublicidadAnuncioCanal publicidad = new PublicidadAnuncioCanal(idProyecto, RUC, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion1, idDepCreativo, comisionEmpresa);
                    publucidadCana.add(publicidad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return publucidadCana;
    }

    public void llenarTodoGrid(){
        Set<PublicidadAnuncioCanal> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }
    public Set<PublicidadAnuncioCanal> generarQuery(String columna, String busqueda){
        Set<PublicidadAnuncioCanal> publucidadCana = new HashSet<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_canal WHERE " + columna + "=" + busqueda;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String idProyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String numFactura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion1 = resultSet.getInt("duracion");
                    String idDepCreativo = resultSet.getString("id_dep_creativo");
                    double comisionEmpresa = resultSet.getDouble("comision_a_empresa");
                    PublicidadAnuncioCanal publicidad = new PublicidadAnuncioCanal(idProyecto, RUC, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion1, idDepCreativo, comisionEmpresa);
                    publucidadCana.add(publicidad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return publucidadCana;
    }
    
    public Set<PublicidadAnuncioCanal> generarQueryTodo(){
        Set<PublicidadAnuncioCanal> publucidadCana = new HashSet<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM publicidad_anuncio_canal";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String idProyecto = resultSet.getString("id_proyecto");
                    String RUC = resultSet.getString("RUC");
                    String numFactura = resultSet.getString("num_factura");
                    String titulo = resultSet.getString("titulo");
                    int presupuesto = resultSet.getInt("presupuesto");
                    String descripcion = resultSet.getString("descripcion");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    int duracion1 = resultSet.getInt("duracion");
                    String idDepCreativo = resultSet.getString("id_dep_creativo");
                    double comisionEmpresa = resultSet.getDouble("comision_a_empresa");
                    PublicidadAnuncioCanal publicidad = new PublicidadAnuncioCanal(idProyecto, RUC, numFactura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion1, idDepCreativo, comisionEmpresa);
                    publucidadCana.add(publicidad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return publucidadCana;
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
    private void updateGrid() {
        gridClientes.getChildren().clear();
        if (!listaMostrada.isEmpty()) {
            PublicidadAnuncioCanal tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbCliente = plantillaPublicidadCanal(tempNode);
                    Proyecto c = tempNode;
                    vbCliente.setOnMouseClicked(event -> {
                        clienteEscogido = c;
                        try{
                            App.setRoot("verPublicidadAnuncioCanal");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbCliente, j, i);
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
    
    private VBox plantillaPublicidadCanal(Proyecto publicidad) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaProyecto.fxml"));
            VBox vbPublicidad = loader.load();
            ImageView planImg = (ImageView) vbPublicidad.lookup("#planImg");
            Text nomPro = (Text) vbPublicidad.lookup("#planName");
            Label planIdPro = (Label) vbPublicidad.lookup("#planId");


            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);

            nomPro.setText(publicidad.getTitulo());
            planIdPro.setText(publicidad.getIdProyecto());

            return vbPublicidad;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //paginacion
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

    @FXML
    private void anadirCliente(ActionEvent event) {
        try{
            App.setRoot("anadirCliente");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

