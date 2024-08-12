/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class AnadirClienteController implements Initializable {

    @FXML
    private Button botonInicio;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private Button btnAnadirCliente;
    @FXML
    private TextField txtRuc;
    @FXML
    private TextField tfDescrip;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfSitioWeb;
    @FXML
    private TextField tfPersonaContacto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No se inicializan valores aquí porque estamos creando un nuevo cliente
    }    

    @FXML
    private void irInicio(ActionEvent event) {
        try {
            App.setRoot("inicio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirCliente(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (txtRuc.getText().trim().isEmpty() ||
            txtNombreCliente.getText().trim().isEmpty() ||
            tfDescrip.getText().trim().isEmpty() ||
            tfDireccion.getText().trim().isEmpty() ||
            tfSitioWeb.getText().trim().isEmpty() ||
            tfPersonaContacto.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Crear un nuevo cliente
        Cliente nuevoCliente = new Cliente(
            txtRuc.getText(),
            txtNombreCliente.getText(),
            tfDescrip.getText(),
            tfDireccion.getText(),
            tfSitioWeb.getText(),
            tfPersonaContacto.getText()
        );

        // Insertar el nuevo cliente en la base de datos
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO cliente (RUC, nombre_empresa, decrip_empresa, direccion, sitio_web, id_persona_contacto) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoCliente.getRuc());
            pstmt.setString(2, nuevoCliente.getNombreEmpresa());
            pstmt.setString(3, nuevoCliente.getDescripEmpresa());
            pstmt.setString(4, nuevoCliente.getDireccion());
            pstmt.setString(5, nuevoCliente.getSitioWeb());
            pstmt.setString(6, nuevoCliente.getIdPersonaContacto());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Cliente creado correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo crear el cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        }

        // Volver a la vista de inicio después de añadir
        try {
            App.setRoot("inicio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
