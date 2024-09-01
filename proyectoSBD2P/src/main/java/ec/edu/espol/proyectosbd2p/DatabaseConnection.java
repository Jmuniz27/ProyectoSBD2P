package ec.edu.espol.proyectosbd2p;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DatabaseConnection {
    static Connection connection = null;
    public static void conectaBase(String username, String password) {
        String jdbcURL = "jdbc:mysql://proyectosbdgrupo8.mysql.database.azure.com:3306/wuanplus"; 

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            if (connection != null) {
                System.out.println("Conectado a la base de datos en Azure con SSL");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            if(e.getErrorCode()==1044)
                alertaAccesoDenegado();
        }
    }
    

    private static void alertaAccesoDenegado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Acceso Denegado");
        alert.setHeaderText("Acceso Denegado");
        alert.setContentText("Usted no cuenta con los permisos necesarios para entrar a esta sección.");
        
        alert.showAndWait();
    }


    public static Connection getConnection(){
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

