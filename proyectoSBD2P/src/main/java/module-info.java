module ec.edu.espol.proyectosbd2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javafx.graphics;
    opens ec.edu.espol.proyectosbd2p to javafx.fxml;
    exports ec.edu.espol.proyectosbd2p;
}
