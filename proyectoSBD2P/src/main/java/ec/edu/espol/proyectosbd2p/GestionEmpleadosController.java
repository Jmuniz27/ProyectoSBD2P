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
import java.util.Set;
import java.util.HashSet;
/**
 * FXML Controller class
 *
 * @author isabella
 */
public class GestionEmpleadosController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button botonInicio;
    @FXML
    private VBox containerFAuto;
    @FXML
    private VBox filtroAuto;
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
    private TextField tbRuc;
    @FXML
    private TextField tbNombreEmpresa;
    @FXML
    private TextField tbDireccion;
    @FXML
    private TextField tbSitioWeb;
    @FXML
    private Button btnBuscar;
    @FXML
    private GridPane gridClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irInicio(ActionEvent event) {
    }


    @FXML
    private void buscarFiltros(ActionEvent event) {
        String ruc = tbRuc.getText();
        //se crean cuantos filtros necesarios para cada campo
        Set<Cliente> filtro1 = generarQuery("ruc",ruc);
        String nombreEmpresa = tbNombreEmpresa.getText();
        Set<Cliente> filtro2 = generarQuery("nombreEmpresa",nombreEmpresa);
        filtro1.addAll(filtro2);
        //falta crear filtro 3 y 4. la idea es q SIEMPRE en filtro 1 se use addAll con el resto de filtros
        filtro1.addAll(filtro3);
        filtro1.addAll(filtro4);
    }

    
    @FXML
    private void prevPag(ActionEvent event) {
    }

    @FXML
    private void nextPag(ActionEvent event) {
    }
    
    public Set<Cliente> generarQuery(String columna, String busqueda){
        Set<Cliente> clientes = new HashSet<>();
        try {
            // Obtener la conexión desde DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM cliente WHERE " + columna + "=" + busqueda;
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int ruc = resultSet.getInt("RUC");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    Cliente cliente = new Cliente();
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el query: " + e.getMessage());
        }
        return clientes;
    }
}
