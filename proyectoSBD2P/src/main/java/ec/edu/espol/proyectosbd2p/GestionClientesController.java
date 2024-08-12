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
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
/**
 * FXML Controller class
 *
 * @author isabella
 */
public class GestionClientesController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button botonInicio;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        String ruc = "'" + tbRuc.getText() + "'";
        Set<Cliente> filtro1 = generarQuery("RUC",ruc);
        
        String nombreEmpresa = "'" + tbNombreEmpresa.getText() + "'";
        Set<Cliente> filtro2 = generarQuery("nombre_empresa",nombreEmpresa);
        filtro1.addAll(filtro2);
        
        String dir = "'" + tbDireccion.getText() + "'";
        Set<Cliente> filtro3 = generarQuery("direccion",dir);
        filtro1.addAll(filtro3);
        
        String sitioWeb = "'" + tbSitioWeb.getText() + "'";
        Set<Cliente> filtro4 = generarQuery("sitio_web",sitioWeb);
        filtro1.addAll(filtro4);
        
        String descripcion = "'" + tbDescripcion.getText() + "'";
        Set<Cliente> filtro5 = generarQuery("decrip_empresa",descripcion);
        filtro1.addAll(filtro5);
        
        listaMostrada = new ArrayList<>(filtro1);
        System.out.println(listaMostrada);
    }
    public void llenarTodoGrid(){
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
                    String descripEmpresa = resultSet.getString("descrip_empresa");
                    String direccion = resultSet.getString("direccion");
                    String sitioWeb = resultSet.getString("sitio_web");
                    String idPersonaContacto = resultSet.getString("id_persona_contacto");
                    Cliente cliente = new Cliente(ruc,nombreEmpresa,descripEmpresa,direccion,sitioWeb,idPersonaContacto);
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return clientes;
    }
    
    public Set<Cliente> generarQueryTodo(){
        Set<Cliente> clientes = new HashSet<>();
        try{
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
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
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
                            App.setRoot("verIndividualCliente");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbCliente, j, i);
                    if(listaMostrada.indexOf(tempNode)+1<listaMostrada.size()){
                        tempNode = listaMostrada.get(listaMostrada.indexOf(tempNode)+1);
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
}

