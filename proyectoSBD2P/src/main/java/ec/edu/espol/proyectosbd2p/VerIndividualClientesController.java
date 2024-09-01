/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class VerIndividualClientesController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private GridPane gridInfo;
    
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = GestionClientesController.clienteEscogido;
        
        updateGrid();
        
        Image img1 = new Image("/imagenes/logo.jpg");
        imgLogo.setImage(img1);
        
        
    }
    
    private void updateGrid() {
        gridInfo.getChildren().clear();
        gridInfo.setVgap(60); 
        gridInfo.setHgap(60);
        gridInfo.setAlignment(Pos.CENTER);
        VBox vbRuc = plantilla("RUC",cliente.getRuc());
        gridInfo.add(vbRuc, 0, 0);
        VBox vbNombre = plantilla("Nombre",cliente.getNombreEmpresa());
        gridInfo.add(vbNombre, 1, 0);
        VBox vbPersona = plantilla("ID Persona de Contacto",cliente.getIdPersonaContacto());
        gridInfo.add(vbPersona, 2, 0);
        VBox vbDir = plantilla("Dirección",cliente.getDireccion());
        gridInfo.add(vbDir, 0, 1);
        VBox vbSitio = plantilla("Sitio Web",cliente.getSitioWeb());
        gridInfo.add(vbSitio, 1, 1);
        VBox vbDescrip = plantilla("Descripción",cliente.getDescripEmpresa());
        gridInfo.add(vbDescrip, 2, 1);
        
     }
    
    private VBox plantilla(String campo,String texto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plantillaVer.fxml"));
            VBox vbInfo = loader.load();
            Text campo1 = (Text) vbInfo.lookup("#campo");
            Text texto1 = (Text) vbInfo.lookup("#texto");
            campo1.setText(campo);
            texto1.setText(texto);

            return vbInfo;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
        
    


    @FXML
    private void regresar(ActionEvent event) {
        try{
            App.setRoot("gestionClientes");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
    try {
        // Cargar el archivo FXML de la vista de edición
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarCliente.fxml"));
        VBox root = loader.load();

        // Obtener el controlador de la vista de edición
        EditarClienteController controller = loader.getController();
        // Pasar el cliente actual al controlador de la vista de edición
        controller.setCliente(cliente);

        // Crear un nuevo Stage para la ventana emergente
        Stage stage = new Stage();
        stage.setTitle("Editar Cliente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana anterior hasta que se cierre esta
        stage.showAndWait(); // Mostrar la ventana y esperar a que se cierre

        // Actualizar la vista principal después de la edición
        updateGrid();

    } catch (IOException e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de edición.");
    }
}

    @FXML
    private void eliminar(ActionEvent event) {
        // Confirmar la eliminación con el usuario
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Estás seguro de que deseas eliminar este cliente?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Proceder con la eliminación
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "DELETE FROM cliente WHERE RUC = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cliente.getRuc());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Cliente eliminado correctamente.");
                    // Navegar de regreso a la pantalla de gestión de clientes
                    App.setRoot("gestionClientes");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el cliente.");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
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
