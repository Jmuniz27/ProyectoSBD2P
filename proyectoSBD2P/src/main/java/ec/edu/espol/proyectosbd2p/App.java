package ec.edu.espol.proyectosbd2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("inicio"), 1280, 800);
        scene = new Scene(loadFXML("usuarios"), 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void mostrarAlerta(String titulo, String cabecera, String contenido, AlertType tipo) {
    Alert alert = new Alert(tipo);
    alert.setTitle(titulo);
    alert.setHeaderText(cabecera);
    alert.setContentText(contenido);
    
    alert.showAndWait();
}

}