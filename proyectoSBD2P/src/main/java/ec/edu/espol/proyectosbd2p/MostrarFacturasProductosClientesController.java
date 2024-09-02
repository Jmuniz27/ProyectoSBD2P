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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class MostrarFacturasProductosClientesController implements Initializable {


    @FXML
    private Text lblTítuloReporte;
    @FXML
    private TableView<ObservableList<String>> tablaDeReportes;
    public static String report;
    public static String title;
    @FXML
    private Button btnRegresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos(report, title);
        
    }    
   
    private void cargarDatos(String view, String title) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            try {
                lblTítuloReporte.setText(title);
                String query = "SELECT * FROM "+view;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j)));
                    tablaDeReportes.getColumns().add(column);
                }

                ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                tablaDeReportes.setItems(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void cambiarReporte(String reporte, String tit){
        report = reporte;
        title= tit;
    }

    @FXML
    private void regresarVentana(ActionEvent event) {
        try{
            App.setRoot("opcionesDeReportes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
