/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import static ec.edu.espol.proyectosbd2p.GestionEmpleadoController.empleadoEscogido;
import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import ec.edu.espol.proyectosbd2p.modelo.Segmento;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
 * @author isabella
 */
public class GestionSegmentoController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private VBox containerFAuto;
    @FXML
    private VBox filtroAuto;
    @FXML
    private TextField tbIdProyecto;
    @FXML
    private TextField tbRuc;
    @FXML
    private TextField tbTitulo;
    @FXML
    private TextField tbRating;
    @FXML
    private TextField tbPresupuesto;
    @FXML
    private TextField tbDuracion;
    @FXML
    private TextField tbComision;
    @FXML
    private TextField tbDescripcion;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;
    @FXML
    private VBox plantillaAutos;
    @FXML
    private ImageView planImg;
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
    
    private ArrayList<Segmento> listaMostrada;
    
    private int currentPage = 1;
    
    private Segmento currentNode;
    
    public static Segmento segmentoEscogido;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int totalPages;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        segmentoEscogido = null;
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        llenarTodoGrid();
        updateGrid();
        updatePagination();
    }    

    @FXML
    private void anadirSegmento(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) {
        try{
            App.setRoot("opcionesGeneral");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void buscarFiltros(ActionEvent event) {
        Set<Segmento> respuestas = generarQueryTodo();
        System.out.println(respuestas);
        boolean vacia = true;
        String idProyecto = "'" + tbIdProyecto.getText() + "'";
        if(!idProyecto.equals("''")){
            Set<Segmento> filtro1 = generarQuery("id_proyecto", idProyecto);
            if(!filtro1.isEmpty()){
                respuestas = interseccion(respuestas,filtro1);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String RUC = "'" + tbRuc.getText() + "'";
        if (!RUC.isEmpty()) {
            Set<Segmento> filtro2 = generarQuery("RUC", RUC);
            if(!filtro2.isEmpty()){
                respuestas = interseccion(respuestas,filtro2);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String titulo = "'" + tbTitulo.getText() + "'";
        if (!titulo.equals("''")) {
            Set<Segmento> filtro3 = generarQuery("titulo", titulo);
            if(!filtro3.isEmpty()){
               respuestas = interseccion(respuestas,filtro3);
               if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String rating = "'" + tbRating.getText() + "'";
        if (!rating.equals("''")) {
            Set<Segmento> filtro4 = generarQuery("rating", rating);
            if(!filtro4.isEmpty()){
                respuestas = interseccion(respuestas,filtro4);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String duracion = tbDuracion.getText();
        if (!duracion.equals("")) {
            Set<Segmento> filtro5 = generarQuery("duracion", duracion);
            if(!filtro5.isEmpty()){
                respuestas = interseccion(respuestas,filtro5);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String presupuesto =  tbPresupuesto.getText();
        if (!presupuesto.equals("")) {
            Set<Segmento> filtro6 = generarQuery("presupuesto", presupuesto);
            if(!filtro6.isEmpty()){
                respuestas = interseccion(respuestas,filtro6);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String comision = tbComision.getText();
        if (!comision.equals("''")) {
            Set<Segmento> filtro7 = generarQuery("comision_a_empresa", comision);
            if(!filtro7.isEmpty()){
               respuestas = interseccion(respuestas,filtro7);
               if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String descripcion = "'" + tbDescripcion.getText() + "'";
        if (!descripcion.equals("''")) {
            Set<Segmento> filtro8 = generarQuery("descripcion", descripcion);
            if(!filtro8.isEmpty()){
               respuestas = interseccion(respuestas,filtro8);
               if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (dpFechaInicio.getValue() != null) {
            String fechaInicio = "'" + dpFechaInicio.getValue().format(formatter) + "'";
            if (!fechaInicio.equals("''")) {
                Set<Segmento> filtro9 = generarQuery("fechaInicio", fechaInicio);
                if(!filtro9.isEmpty()){
                   respuestas = interseccion(respuestas,filtro9);
                   if(respuestas.isEmpty()){
                        vacia = true;
                    } else{
                    vacia = false;}
                }
            }
        }
        
        if (dpFechaFin.getValue() != null) {
            
            String fechaFin = "'" + dpFechaFin.getValue().format(formatter) + "'";
            if (!fechaFin.equals("''")) {
                Set<Segmento> filtro10 = generarQuery("fechaFin", fechaFin);
                if(!filtro10.isEmpty()){
                   respuestas = interseccion(respuestas,filtro10);
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
            App.mostrarAlerta("No existen segmentos", "Error", "No existen segmentos para su búsqueda. A continuación se muestran todos los segmentos.", Alert.AlertType.ERROR);
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
        
    }
    
    public Set<Segmento> interseccion(Set<Segmento> respuestas, Set<Segmento>  filtro){
        Set<Segmento> nuevo = new HashSet<>();
        for(Segmento s1: respuestas){
            for(Segmento s2: filtro){
                if(s1.getIdProyecto().equals(s2.getIdProyecto())){
                    nuevo.add(s1);
                }
            }
        }
        return nuevo;
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

    private void llenarTodoGrid() {
        Set<Segmento> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }

    private void updateGrid() {
        gridClientes.getChildren().clear();
        if (!listaMostrada.isEmpty()) {
            Segmento tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbSegmento = plantillaSegmento(tempNode);
                    Segmento seg = tempNode;
                    vbSegmento.setOnMouseClicked(event -> {
                        segmentoEscogido = seg;
                        try{
                            App.setRoot("verIndividualSegmento");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbSegmento, j, i);
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
    
    private VBox plantillaSegmento(Segmento seg) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaIndividual.fxml"));
            VBox vbSegmento = loader.load();
            ImageView planImg = (ImageView) vbSegmento.lookup("#planImg");
            Text planName = (Text) vbSegmento.lookup("#planName");
            Label planId = (Label) vbSegmento.lookup("#planId");
            
            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);
            
            planName.setText(seg.getTitulo());
            planId.setText(seg.getIdProyecto());

            return vbSegmento;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Set<Segmento> generarQueryTodo() {
        Set<Segmento> segmentos = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM segmento";
                ResultSet resultSet = statement.executeQuery(sql);

                segmentos = crearSegmento(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return segmentos;
    }
    private Set<Segmento> generarQuery(String columna, String busqueda) {
        Set<Segmento> segmentos = new HashSet<>();
        if (!busqueda.equals("")) {
            try {
                Connection connection = DatabaseConnection.getConnection();
                if (connection != null) {
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM segmento WHERE " + columna + "=" + busqueda;
                    ResultSet resultSet = statement.executeQuery(sql);
                    System.out.println(sql);
                    
                    segmentos = crearSegmento(resultSet);
                    System.out.println(segmentos);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar el query: " + e.getMessage());
            }
        }
        return segmentos;
    }

    private Set<Segmento> crearSegmento(ResultSet resultSet) throws SQLException {
        Set<Segmento> segmentos = new HashSet<>();
        while (resultSet.next()) {
            String id_proyecto = resultSet.getString("id_proyecto");
            String RUC = resultSet.getString("RUC");
            String num_factura = resultSet.getString("num_factura");
            String rating = resultSet.getString("rating");
            int duracion = resultSet.getInt("duracion");
            boolean estado = resultSet.getBoolean("estado");
            String titulo = resultSet.getString("titulo");
            int presupuesto = resultSet.getInt("presupuesto");
            String descripcion = resultSet.getString("descripcion");
            Date fecha_inicio = resultSet.getDate("fecha_inicio");
            Date fecha_Fin = resultSet.getDate("fecha_Fin");
            String id_dep_prod = resultSet.getString("id_dep_prod");
            Double comision_a_empresa = resultSet.getDouble("comision_a_empresa");
            Segmento segmento = new Segmento(id_proyecto, RUC, num_factura, rating, duracion, estado, titulo, presupuesto,descripcion,fecha_inicio,fecha_Fin,id_dep_prod, comision_a_empresa);
            segmentos.add(segmento);
        }
        return segmentos;
    }
    
    
}
