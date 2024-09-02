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

import ec.edu.espol.proyectosbd2p.modelo.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> cbDepartamento;

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
        cbDepartamento.getItems().addAll("Todos","Creativo","Producción", "Finanzas");
        cbDepartamento.setValue("Todos");
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
        Set<Empleado> respuestas = generarQueryTodo();
        System.out.println(respuestas);
        boolean vacia = true;
        String idEmpleado = "'" + tbIdEmpleado.getText() + "'";
        if(!idEmpleado.equals("''")){
            Set<Empleado> filtro1 = generarQuery("id_empleado", idEmpleado);
            if(!filtro1.isEmpty()){
                respuestas = interseccion(respuestas,filtro1);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String departamentoSeleccionado = cbDepartamento.getValue();
        Set<Empleado> filtroDepartamento = generarQueryTodo();
        if (!departamentoSeleccionado.equals("Todos")) {
            String columnaDepartamento = "";
            switch (departamentoSeleccionado) {
                case "Creativo":
                    columnaDepartamento = "id_dep_creativo";
                    break;
                case "Producción":
                    columnaDepartamento = "id_dep_prod";
                    break;
                case "Finanzas":
                    columnaDepartamento = "id_dep_finanzas";
                    break;
            }
            filtroDepartamento = generarQueryDepartamento(columnaDepartamento);
            if(!filtroDepartamento.isEmpty()){
                respuestas = interseccion(respuestas,filtroDepartamento);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String sueldoBase = tbSueldo.getText();
        if (!sueldoBase.isEmpty()) {
            Set<Empleado> filtro2 = generarQuery("sueldoBase", sueldoBase);
            if(!filtro2.isEmpty()){
                respuestas = interseccion(respuestas,filtro2);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String nombre = "'" + tbNombre.getText() + "'";
        if (!nombre.equals("''")) {
            Set<Empleado> filtro3 = generarQuery("nombre", nombre);
            if(!filtro3.isEmpty()){
               respuestas = interseccion(respuestas,filtro3);
               if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String apellido = "'" + tbApellido.getText() + "'";
        if (!apellido.equals("''")) {
            Set<Empleado> filtro4 = generarQuery("apellido", apellido);
            if(!filtro4.isEmpty()){
                respuestas = interseccion(respuestas,filtro4);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String puesto = "'" + tbPuesto.getText() + "'";
        if (!puesto.equals("''")) {
            Set<Empleado> filtro5 = generarQuery("puesto", puesto);
            if(!filtro5.isEmpty()){
                respuestas = interseccion(respuestas,filtro5);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        String direccion = "'" + tbDireccion.getText() + "'";
        if (!direccion.equals("''")) {
            Set<Empleado> filtro6 = generarQuery("direccion", direccion);
            if(!filtro6.isEmpty()){
                respuestas = interseccion(respuestas,filtro6);
                if(respuestas.isEmpty()){
                    vacia = true;
                } else{
                vacia = false;}
            }
        }

        listaMostrada = new ArrayList<>(respuestas);
        
        if (vacia) {
            App.mostrarAlerta("No existen empleados", "Error", "No existen empleados para su búsqueda. A continuación se muestran todos los empleados.", Alert.AlertType.ERROR);
            llenarTodoGrid();
        }
        currentPage = 1;
        updateGrid();
        updatePagination();
    }
    
    public Set<Empleado> interseccion(Set<Empleado> respuestas, Set<Empleado>  filtro){
        Set<Empleado> nuevo = new HashSet<>();
        for(Empleado e1: respuestas){
            for(Empleado e2: filtro){
                if(e1.getIdEmpleado().equals(e2.getIdEmpleado())){
                    nuevo.add(e1);
                }
            }
        }
        return nuevo;
    }

    private Set<Empleado> generarQuery(String columna, String busqueda) {
        Set<Empleado> empleados = new HashSet<>();
        if (!busqueda.equals("")) {
            try {
                Connection connection = DatabaseConnection.getConnection();
                if (connection != null) {
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM empleado WHERE " + columna + "=" + busqueda;
                    ResultSet resultSet = statement.executeQuery(sql);
                    System.out.println(sql);
                    
                    empleados = crearEmpleado(resultSet);
                    System.out.println(empleados);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar el query: " + e.getMessage());
            }
        }
        return empleados;
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

                empleados = crearEmpleado(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return empleados;
    }
    
    public Set<Empleado> generarQueryDepartamento(String dep){
        Set<Empleado> empleados = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM empleado WHERE " + dep + " IS NOT NULL";
                ResultSet resultSet = statement.executeQuery(sql);
                empleados = crearEmpleado(resultSet);
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
        // Cargar el archivo FXML de la vista de edición
        FXMLLoader loader = new FXMLLoader(getClass().getResource("anadirEmpleados.fxml"));
        VBox root = loader.load();

        // Pasar el cliente actual al controlador de la vista de edición

        // Crear un nuevo Stage para la ventana emergente
        Stage stage = new Stage();
        stage.setTitle("Añadir Empleado");
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
    
    public Set<Empleado> crearEmpleado(ResultSet resultSet) throws SQLException{
        Set<Empleado> emps = new HashSet<>();
        while (resultSet.next()) {
            String id_empleado = resultSet.getString("id_empleado");
            int sueldoBase = resultSet.getInt("sueldoBase");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String puesto = resultSet.getString("puesto");
            String contrasenia = resultSet.getString("contrasena");
            String direccion = resultSet.getString("direccion");
            String id_dep_creativo = resultSet.getString("id_dep_creativo");
            String id_dep_prod = resultSet.getString("id_dep_prod");
            String id_dep_finanzas = resultSet.getString("id_dep_finanzas");
            String id_dir_dep_creativo = resultSet.getString("id_dir_dep_creativo");
            String id_dir_dep_prod = resultSet.getString("id_dir_dep_prod");
            String id_dir_dep_finanzas = resultSet.getString("id_dir_dep_finanzas");
            Empleado empleado = new Empleado(id_empleado, sueldoBase, nombre, apellido, puesto, contrasenia, direccion, id_dep_creativo,id_dep_prod,id_dep_finanzas,id_dir_dep_creativo,id_dir_dep_prod, id_dir_dep_finanzas);
            emps.add(empleado);
        }
        return emps;
    }
}
