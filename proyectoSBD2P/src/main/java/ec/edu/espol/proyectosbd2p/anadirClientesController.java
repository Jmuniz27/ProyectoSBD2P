package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class anadirClientesController implements Initializable {

    @FXML
    private TextField tfanadirNombreEmpresa;
    @FXML
    private TextField tfanadirDescrip;
    @FXML
    private TextField tfanadirDireccion;
    @FXML
    private TextField tfanadirSitioWeb;
    @FXML
    private TextField tfanadirPersonaContacto;
    @FXML
    private TextField tfCedulaContacto;
    @FXML
    private TextField tfAnadirRuc;
    @FXML
    private TextField tfInfoContactoEmail;
    @FXML
    private TextField tfInfoContactoTelefono;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No se inicializan valores aquí porque estamos creando un nuevo cliente
    }

    @FXML
    private void regresarBtn(ActionEvent event) {
        // Volver a la vista de inicio
        try {
            App.setRoot("inicio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        // Validar que todos los campos estén llenos
        if (tfAnadirRuc.getText().trim().isEmpty() ||
            tfanadirNombreEmpresa.getText().trim().isEmpty() ||
            tfanadirDescrip.getText().trim().isEmpty() ||
            tfanadirDireccion.getText().trim().isEmpty() ||
            tfanadirSitioWeb.getText().trim().isEmpty() ||
            tfanadirPersonaContacto.getText().trim().isEmpty() ||
            tfCedulaContacto.getText().trim().isEmpty() ||
            tfInfoContactoEmail.getText().trim().isEmpty() ||
            tfInfoContactoTelefono.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Verificar si la cédula ya existe
            String sqlCheck = "SELECT COUNT(*) FROM persona_contacto WHERE cedula = ?";
            pstmt = conn.prepareStatement(sqlCheck);
            pstmt.setString(1, tfCedulaContacto.getText());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                // Si la cédula no existe, inserta un nuevo registro en persona_contacto
                String sqlPersonaContacto = "INSERT INTO persona_contacto (cedula, nombre, email, telefono) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sqlPersonaContacto);
                pstmt.setString(1, tfCedulaContacto.getText());
                pstmt.setString(2, tfanadirPersonaContacto.getText());
                pstmt.setString(3, tfInfoContactoEmail.getText());
                pstmt.setString(4, tfInfoContactoTelefono.getText());
                pstmt.executeUpdate();
            }

            // Crear un nuevo cliente con la cédula de la persona de contacto
            Cliente nuevoCliente = new Cliente(
                tfAnadirRuc.getText(),
                tfanadirNombreEmpresa.getText(),
                tfanadirDescrip.getText(),
                tfanadirDireccion.getText(),
                tfanadirSitioWeb.getText(),
                tfCedulaContacto.getText()  // Usamos la cédula como la referencia en cliente
            );

            // Insertar el nuevo cliente en la base de datos
            String sqlCliente = "INSERT INTO cliente (RUC, nombre_empresa, decrip_empresa, direccion, sitio_web, id_persona_contacto) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sqlCliente);
            pstmt.setString(1, nuevoCliente.getRuc());
            pstmt.setString(2, nuevoCliente.getNombreEmpresa());
            pstmt.setString(3, nuevoCliente.getDescripEmpresa());
            pstmt.setString(4, nuevoCliente.getDireccion());
            pstmt.setString(5, nuevoCliente.getSitioWeb());
            pstmt.setString(6, nuevoCliente.getIdPersonaContacto());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Mostrar alerta de confirmación
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Cliente y Persona de Contacto creados correctamente.");
                
                // Cerrar la ventana actual
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                App.setRoot("usuarios");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo crear el cliente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
