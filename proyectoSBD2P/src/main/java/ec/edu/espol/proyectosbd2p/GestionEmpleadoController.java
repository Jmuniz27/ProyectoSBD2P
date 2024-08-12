/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import static ec.edu.espol.proyectosbd2p.GestionClientesController.clienteEscogido;
import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import ec.edu.espol.proyectosbd2p.modelo.Empleado;
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
 * @author creditos gonzalez
 */
public class GestionEmpleadoController implements Initializable {

    @FXML
    private ImageView imgLogo;
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
    
    private int currentPage = 1;
    
    private Empleado currentNode;
    
    public static Empleado empleadoEscogido;
    
    private static final int ITEMS_PER_PAGE = 4;
    
    private int totalPages;
    @FXML
    private Button botonInicio1;
    @FXML
    private Button btnEmpleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoEscogido = null;
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

    private void llenarTodoGrid() {
        Set<Empleado> set = generarQueryTodo();
        listaMostrada = new ArrayList<>(set);
    }

    private void updateGrid() {
        gridClientes.getChildren().clear();
        if (!listaMostrada.isEmpty()) {
            Empleado tempNode = listaMostrada.get(0);
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            for (int i = 0; i < startIndex && tempNode != null; i++) {
                tempNode = listaMostrada.get(i);
            }
            for (int i = 0; i < 2 && tempNode != null; i++) {
                for (int j = 0; j < 2 && tempNode != null; j++) {
                    VBox vbEmpleado = plantillaEmpleado(tempNode);
                    Empleado emple = tempNode;
                    vbEmpleado.setOnMouseClicked(event -> {
                        empleadoEscogido = emple;
                        try{
                            App.setRoot("verIndividualEmpleado");
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    });
                    gridClientes.add(vbEmpleado, j, i);
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

    private Set<Empleado> generarQueryTodo() {
        Set<Empleado> empleados = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM empleado";
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

    private VBox plantillaEmpleado(Empleado emp) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaIndividual.fxml"));
            VBox vbEmpleado = loader.load();
            ImageView planImg = (ImageView) vbEmpleado.lookup("#planImg");
            Text planName = (Text) vbEmpleado.lookup("#planName");
            Label planId = (Label) vbEmpleado.lookup("#planId");
            
            Image image = new Image("imagenes/user.png");
            planImg.setImage(image);
            
            planName.setText(emp.getNombre());
            planId.setText(emp.getIdEmpleado());

            return vbEmpleado;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void anadirEmpleado(ActionEvent event) {
        try{
            App.setRoot("anadirEmpleado");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
