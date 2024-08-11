module ec.edu.espol.proyectosbd2p {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectosbd2p to javafx.fxml;
    exports ec.edu.espol.proyectosbd2p;
}
