module ec.edu.espol.proyectosbd2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens ec.edu.espol.proyectosbd2p to javafx.fxml;
    exports ec.edu.espol.proyectosbd2p;
}
