/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author isabella
 */
public class GestionClientesController implements Initializable {

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
    @FXML
    private TextField tbDireccion;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;
    @FXML
    private VBox filtroAuto;
    @FXML
    private TextField tbRuc;
    @FXML
    private TextField tbNombreEmpresa;
    @FXML
    private TextField tbSitioWeb;
    @FXML
    private TextField tbDescripcion;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int currentPage = 1;
    
    private int totalPages;
    
    private ArrayList<Cliente> listaMostrada;
    
    public static Cliente clienteEscogido;
    
    private Cliente currentNode;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button botonInicio1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteEscogido = null;
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        try {
            llenarTodoGrid();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1044)
                DatabaseConnection.alertaAccesoDenegado();
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
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
    private void buscarFiltros(ActionEvent event) throws SQLException {
        Set<Cliente> respuestas = generarQueryTodo();
        System.out.println(respuestas);
        boolean vacia = true;
        
        
        String ruc = "'" + tbRuc.getText() + "'";
        if(!ruc.equals("''")){
            Set<Cliente> filtro1 = generarQuery("RUC",ruc);
            if(!filtro1.isEmpty()){
                respuestas = interseccion(respuestas,filtro1);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String nombreEmpresa = "'" + tbNombreEmpresa.getText() + "'";
        if(!nombreEmpresa.equals("''")){
            Set<Cliente> filtro2 = generarQuery("nombre_empresa",nombreEmpresa);
            if(!filtro2.isEmpty()){
                respuestas = interseccion(respuestas,filtro2);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String dir = "'" + tbDireccion.getText() + "'";
        if(!dir.equals("''")){
            Set<Cliente> filtro3 = generarQuery("direccion",dir);
            if(!filtro3.isEmpty()){
                respuestas = interseccion(respuestas,filtro3);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String sitioWeb = "'" + tbSitioWeb.getText() + "'";
        if(!sitioWeb.equals("''")){
            Set<Cliente> filtro4 = generarQuery("sitio_web",sitioWeb);
            if(!filtro4.isEmpty()){
                respuestas = interseccion(respuestas,filtro4);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        
        String descripcion = "'" + tbDescripcion.getText() + "'";
        if(!descripcion.equals("''")){
            Set<Cliente> filtro5 = generarQuery("decrip_empresa",descripcion);
            if(!filtro5.isEmpty()){
                respuestas = interseccion(respuestas,filtro5);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        listaMostrada = new ArrayList<>(respuestas);
        System.out.println(listaMostrada);
        if(respuestas.isEmpty()){
            App.mostrarAlerta("No existen clientes", "Error", "No existen clientes para su búsqueda. A continuación se muestran todos los clientes.", Alert.AlertType.ERROR);
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
    }
    
    public Set<Cliente> interseccion(Set<Cliente> respuestas, Set<Cliente>  filtro){
        Set<Cliente> nuevo = new HashSet<>();
        for(Cliente c1: respuestas){
            for(Cliente c2: filtro){
                if(c1.getRuc().equals(c2.getRuc())){
                    nuevo.add(c1);
                }
            }
        }
        return nuevo;
    }
    
    public void llenarTodoGrid() throws SQLException{
        Set<Cliente> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }
    public Set<Cliente> generarQuery(String columna, String busqueda){
        Set<Cliente> clientes = new HashSet<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM cliente WHERE " + columna + "=" + busqueda;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String ruc = resultSet.getString("RUC");
                    String nombreEmpresa = resultSet.getString("nombre_empresa");
                    String descripEmpresa = resultSet.getString("decrip_empresa");
                    String direccion = resultSet.getString("direccion");
                    String sitioWeb = resultSet.getString("sitio_web");
                    String idPersonaContacto = resultSet.getString("id_persona_contacto");
                    Cliente cliente = new Cliente(ruc,nombreEmpresa,descripEmpresa,direccion,sitioWeb,idPersonaContacto);
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            if(e.getErrorCode()==1044)
                DatabaseConnection.alertaAccesoDenegado();
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return clientes;
    }
    
    public Set<Cliente> generarQueryTodo() throws SQLException{
        Set<Cliente> clientes = new HashSet<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM cliente";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String ruc = resultSet.getString("RUC");
                String nombreEmpresa = resultSet.getString("nombre_empresa");
                String descripEmpresa = resultSet.getString("decrip_empresa");
                String direccion = resultSet.getString("direccion");
                String sitioWeb = resultSet.getString("sitio_web");
                String idPersonaContacto = resultSet.getString("id_persona_contacto");
                Cliente cliente = new Cliente(ruc,nombreEmpresa,descripEmpresa,direccion,sitioWeb,idPersonaContacto);
                clientes.add(cliente);
            }
            }
        return clientes;
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
            Cliente tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbCliente = plantillaCliente(tempNode);
                    Cliente c = tempNode;
                    vbCliente.setOnMouseClicked(event -> {
                        clienteEscogido = c;
                        try{
                            App.setRoot("verIndividualClientes");
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
    
    private VBox plantillaCliente(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaIndividual.fxml"));
            VBox vbCliente = loader.load();
            ImageView planImg = (ImageView) vbCliente.lookup("#planImg");
            Text planName = (Text) vbCliente.lookup("#planName");
            Label planId = (Label) vbCliente.lookup("#planId");
            
            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);
            
            planName.setText(cliente.getNombreEmpresa());
            planId.setText(cliente.getRuc());

            return vbCliente;
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
        // Cargar el archivo FXML de la vista de edición
        FXMLLoader loader = new FXMLLoader(getClass().getResource("anadirClientes.fxml"));
        VBox root = loader.load();

        // Pasar el cliente actual al controlador de la vista de edición

        // Crear un nuevo Stage para la ventana emergente
        Stage stage = new Stage();
        stage.setTitle("Añadir Cliente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana anterior hasta que se cierre esta
        stage.showAndWait(); // Mostrar la ventana y esperar a que se cierre
        App.setRoot("usuarios");

        // Actualizar la vista principal después de la edición
        updateGrid();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

