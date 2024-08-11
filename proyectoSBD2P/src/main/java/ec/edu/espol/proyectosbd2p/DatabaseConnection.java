package ec.edu.espol.proyectosbd2p;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;


public class DatabaseConnection {
    static Connection connection = null;
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://proyectosbdgrupo8.mysql.database.azure.com:3306/wuanplus"; 
        String username = "main";
        String password = "proyectoSBD=Grupo8_";

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            if (connection != null) {
                System.out.println("Conectado a la base de datos en Azure con SSL");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
}

