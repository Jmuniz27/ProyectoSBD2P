/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.RolPago;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class GestionRolPagoController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnAnadir;
    @FXML
    private VBox containerFAuto;
    @FXML
    private VBox filtroAuto;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;
    @FXML
    private VBox plantillaAutos;
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
    private TextField tbIdRolPago;
    @FXML
    private TextField tbPrecioNeto;
    @FXML
    private TextField tbIdEmpleado;
    @FXML
    private Button btnRegresar;
    
    private ArrayList<RolPago> listaMostrada;
    
    private int currentPage = 1;
    
    private RolPago currentNode;
    
    public static RolPago rolPagoEscogido;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int totalPages;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rolPagoEscogido = null;
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
    private void anadirRolPago(ActionEvent event) {
    }

    @FXML
    private void buscarFiltros(ActionEvent event) {
        Set<RolPago> respuestas = generarQueryTodo();
        System.out.println(respuestas);
        boolean vacia = true;
        String idPago = "'" + tbIdRolPago.getText() + "'";
        if(!idPago.equals("''")){
            Set<RolPago> filtro1 = generarQuery("id_pago", idPago);
            if(!filtro1.isEmpty()){
                respuestas = interseccion(respuestas,filtro1);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String idEmpleado = "'" + tbIdEmpleado.getText() + "'";
        if(!idEmpleado.equals("''")){
            Set<RolPago> filtro2 = generarQuery("id_empleado", idEmpleado);
            if(!filtro2.isEmpty()){
                respuestas = interseccion(respuestas,filtro2);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        String precioNeto = tbPrecioNeto.getText();
        if(!precioNeto.equals("")){
            Set<RolPago> filtro3= generarQuery("precio_neto", precioNeto);
            if(!filtro3.isEmpty()){
                respuestas = interseccion(respuestas,filtro3);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }
        
        listaMostrada = new ArrayList<>(respuestas);
        
        if (vacia) {
            App.mostrarAlerta("No existen roles de pago", "Error", "No existen roles de pago para su búsqueda. A continuación se muestran todos los roles de pago.", Alert.AlertType.ERROR);
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
    
    public Set<RolPago> interseccion(Set<RolPago> respuestas, Set<RolPago>  filtro){
        Set<RolPago> nuevo = new HashSet<>();
        for(RolPago rp1: respuestas){
            for(RolPago rp2: filtro){
                if(rp1.getIdPago().equals(rp2.getIdPago())){
                    nuevo.add(rp1);
                }
            }
        }
        return nuevo;
    }

    private Set<RolPago> generarQuery(String columna, String busqueda) {
        Set<RolPago> rolPagos = new HashSet<>();
        if (!busqueda.equals("")) {
            try {
                Connection connection = DatabaseConnection.getConnection();
                if (connection != null) {
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM rol_pago WHERE " + columna + "=" + busqueda;
                    ResultSet resultSet = statement.executeQuery(sql);
                    System.out.println(sql);
                    
                    rolPagos = crearRolPago(resultSet);
                    System.out.println(rolPagos);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar el query: " + e.getMessage());
            }
        }
        return rolPagos;
    }

    private void llenarTodoGrid() {
        Set<RolPago> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }

    private void updateGrid() {
        gridClientes.getChildren().clear();
        if (!listaMostrada.isEmpty()) {
            RolPago tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbRolPago = plantillaRolPago(tempNode);
                    RolPago rol = tempNode;
                    vbRolPago.setOnMouseClicked(event -> {
                        rolPagoEscogido = rol;
                        try{
                            App.setRoot("verIndividualRP");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbRolPago, j, i);
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

    private Set<RolPago> generarQueryTodo() {
        Set<RolPago> rolesDePago = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM rol_pago";
                ResultSet resultSet = statement.executeQuery(sql);

                rolesDePago = crearRolPago(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return rolesDePago;
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

    private VBox plantillaRolPago(RolPago rp) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaIndividual.fxml"));
            VBox vbRolPago = loader.load();
            ImageView planImg = (ImageView) vbRolPago.lookup("#planImg");
            Text planName = (Text) vbRolPago.lookup("#planName");
            Label planId = (Label) vbRolPago.lookup("#planId");
            
            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);
            
            planName.setText(obtenerNombreEmpleado(rp.getIdEmpleado()));
            planId.setText(rp.getIdPago());

            return vbRolPago;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String obtenerNombreEmpleado(String idEmpleado) {
        String nombreEmpleado = "";
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT nombre FROM empleado WHERE id_empleado = '" + idEmpleado + "' LIMIT 1";
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    nombreEmpleado = resultSet.getString("nombre");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el nombre del empleado: " + e.getMessage());
        }
        return nombreEmpleado;
    }

    
    public Set<RolPago> crearRolPago(ResultSet resultSet) throws SQLException{
        Set<RolPago> rps = new HashSet<>();
        while (resultSet.next()) {
            String id_pago = resultSet.getString("id_pago");
            int pago_neto = resultSet.getInt("pago_neto");
            String id_empleado = resultSet.getString("id_empleado");
            String id_dep_finanzas = resultSet.getString("id_dep_finanzas");
            RolPago rolPago = new RolPago(id_pago, pago_neto, id_empleado, id_dep_finanzas);
            rps.add(rolPago);
        }
        return rps;
    }
    
}
