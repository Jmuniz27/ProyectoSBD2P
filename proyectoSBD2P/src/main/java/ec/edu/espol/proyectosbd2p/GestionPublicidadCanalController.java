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
import ec.edu.espol.proyectosbd2p.modelo.PublicidadAnuncioCanal;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
public class GestionPublicidadCanalController implements Initializable {

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
    @FXML
    private TextField tbDescripcion;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int currentPage = 1;
    
    private int totalPages;
    
    private ArrayList<PublicidadAnuncioCanal> listaMostrada;
    
    public static PublicidadAnuncioCanal pcEscogido;
    
    private PublicidadAnuncioCanal currentNode;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button botonInicio1;
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
    @FXML
    private TextField tbRuc;
    @FXML
    private TextField tbComision;
    @FXML
    private TextField tbPresupuesto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pcEscogido = null;
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        llenarTodoGrid();
        updateGrid();
        updatePagination();
    }    

    @FXML
    private void irInicio(ActionEvent event) {
        try{
            App.setRoot("opcionesGeneral");
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void buscarFiltros(ActionEvent event) {
        Set<PublicidadAnuncioCanal> respuestas = generarQueryTodo();
        System.out.println(respuestas);
        boolean vacia = true;
        String id = "'" + tbID.getText() + "'";
        if(!id.equals("''")){
            Set<PublicidadAnuncioCanal> filtro1 = generarQuery("id_proyecto", id);
            if(!filtro1.isEmpty()){
                respuestas = interseccion(respuestas,filtro1);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String titulo = "'" + tbTitulo.getText() + "'";
        if(!titulo.equals("''")){
            Set<PublicidadAnuncioCanal> filtro2 = generarQuery("titulo", titulo);
            if(!filtro2.isEmpty()){
                respuestas = interseccion(respuestas,filtro2);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String presupuesto = tbPresupuesto.getText();
        if(presupuesto.equals("")){
            Set<PublicidadAnuncioCanal> filtro3 = generarQuery("presupuesto",presupuesto);
            if(!filtro3.isEmpty()){
                respuestas = interseccion(respuestas,filtro3);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String duracion = tbDura.getText();
        if(!duracion.equals("")){
            Set<PublicidadAnuncioCanal> filtro4 = generarQuery("duracion", duracion);
            if(!filtro4.isEmpty()){
                respuestas = interseccion(respuestas,filtro4);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        
        String RUC = "'" + tbRuc.getText() + "'";
        if (!RUC.equals("''")) {
            Set<PublicidadAnuncioCanal> filtro5 = generarQuery("RUC", RUC);
            if(!filtro5.isEmpty()){
                respuestas = interseccion(respuestas,filtro5);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String descripcion = "'" + tbDescripcion.getText() + "'";
        if (!descripcion.equals("''")) {
            Set<PublicidadAnuncioCanal> filtro6 = generarQuery("descripcion", descripcion);
            if(!filtro6.isEmpty()){
                respuestas = interseccion(respuestas,filtro6);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String comision =tbComision.getText();
        if (!comision.equals("")) {
            Set<PublicidadAnuncioCanal> filtro7 = generarQuery("comision", comision);
            if(!filtro7.isEmpty()){
                respuestas = interseccion(respuestas,filtro7);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if (DFechaInicio.getValue() != null) {
            String fechaInicio = "'" + DFechaInicio.getValue().format(formatter) + "'";
            if (!fechaInicio.equals("''")) {
                Set<PublicidadAnuncioCanal> filtro8 = generarQuery("fecha_inicio", fechaInicio);
                if(!filtro8.isEmpty()){
                   respuestas = interseccion(respuestas,filtro8);
                   if(respuestas.isEmpty()){
                        vacia = true;
                    } else{
                    vacia = false;}
                }
            }
        }
        
        if (DFechaFin.getValue() != null) {
            String fechaFin = "'" + DFechaFin.getValue().format(formatter) + "'";
            if (!fechaFin.equals("''")) {
                Set<PublicidadAnuncioCanal> filtro9 = generarQuery("fecha_Fin", fechaFin);
                if(!filtro9.isEmpty()){
                   respuestas = interseccion(respuestas,filtro9);
                   if(respuestas.isEmpty()){
                        vacia = true;
                    } else{
                    vacia = false;
                   }
                }
            }
        }
        
        
        listaMostrada = new ArrayList<>(respuestas);
        
        if (vacia) {
            App.mostrarAlerta("No existen publicidades tipo anuncio en canal", "Error", "No existen publicidades tipo anuncio en canal para su búsqueda. A continuación se muestran todos los publicidades tipo anuncio en canal.", Alert.AlertType.ERROR);
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
    }
    
    public Set<PublicidadAnuncioCanal> interseccion(Set<PublicidadAnuncioCanal> respuestas, Set<PublicidadAnuncioCanal>  filtro){
        Set<PublicidadAnuncioCanal> nuevo = new HashSet<>();
        for(PublicidadAnuncioCanal p1: respuestas){
            for(PublicidadAnuncioCanal p2: filtro){
                if(p1.getIdProyecto().equals(p2.getIdProyecto())){
                    nuevo.add(p1);
                }
            }
        }
        return nuevo;
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
                    VBox vbPublicidadAnuncioCanal = plantillaPublicidadCanal(tempNode);
                    PublicidadAnuncioCanal c = tempNode;
                    vbPublicidadAnuncioCanal.setOnMouseClicked(event -> {
                        pcEscogido = c;
                        try{
                            App.setRoot("verIndvidualPC");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbPublicidadAnuncioCanal, j, i);
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
    
    private VBox plantillaPublicidadCanal(PublicidadAnuncioCanal publicidad) {
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

