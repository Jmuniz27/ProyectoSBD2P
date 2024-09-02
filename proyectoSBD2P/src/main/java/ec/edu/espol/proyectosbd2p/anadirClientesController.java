package ec.edu.espol.proyectosbd2p;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import ec.edu.espol.proyectosbd2p.modelo.PersonaContacto;
import java.sql.CallableStatement;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;

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
    @FXML
    private TextField tfApellido;

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
            tfInfoContactoTelefono.getText().trim().isEmpty()||tfApellido.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        // Confirmación antes de guardar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar cambios");
        confirmacion.setHeaderText("¿Seguro que desea guardar los cambios?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            
            // Llamar al procedimiento almacenado
            try {
                Connection conn = DatabaseConnection.getConnection();
                // Verificar si la cédula ya existe
                String sqlCheck = "SELECT COUNT(*) FROM persona_contacto WHERE cedula = ?";
                PreparedStatement pstmt = conn.prepareStatement(sqlCheck);
                pstmt.setString(1, tfCedulaContacto.getText());
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count == 0) {
                    // Si la cédula no existe, inserta un nuevo registro en persona_contacto
                    String sql = "{CALL crear_PersonaContacto(?, ?, ?, ?, ?)}";
                    CallableStatement cstmt1 = conn.prepareCall(sql);
                    cstmt1.setString(1, tfCedulaContacto.getText());
                    cstmt1.setString(2, tfanadirPersonaContacto.getText());
                    cstmt1.setString(3, tfApellido.getText());
                    cstmt1.setString(4, tfInfoContactoEmail.getText());
                    cstmt1.setString(5, tfInfoContactoTelefono.getText());
                    cstmt1.execute();
                }
                Cliente cliente = new Cliente(tfAnadirRuc.getText(),tfanadirNombreEmpresa.getText(), tfanadirDescrip.getText(),tfanadirDireccion.getText(),tfanadirSitioWeb.getText(),tfCedulaContacto.getText());

                String sql = "{CALL crear_Cliente(?, ?, ?, ?, ?, ?)}";
                CallableStatement cstmt = conn.prepareCall(sql);
                cstmt.setString(1, cliente.getRuc());
                cstmt.setString(2, cliente.getNombreEmpresa());
                cstmt.setString(3, cliente.getDescripEmpresa());
                cstmt.setString(4, cliente.getDireccion());
                cstmt.setString(5, cliente.getSitioWeb());
                cstmt.setString(6, cliente.getIdPersonaContacto());
                boolean hadResults = cstmt.execute();
                if (!hadResults) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
                }
            } catch (SQLException e) { 
                DatabaseConnection.handleSQLException(e);
            }

            // Cerrar la ventana después de guardar
            Stage stage = (Stage) tfCedulaContacto.getScene().getWindow();
            stage.close();
        }
    }
            
        

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
