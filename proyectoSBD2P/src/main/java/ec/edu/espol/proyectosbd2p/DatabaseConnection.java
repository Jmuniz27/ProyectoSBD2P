package ec.edu.espol.proyectosbd2p;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;


public class DatabaseConnection {
    public static void main(String[] args) {
        // Cambia los siguientes valores según tu configuración de MySQL
        String jdbcURL = "jdbc:mysql://localhost:3306/nombre_bd"; // Cambia 'localhost' si tu servidor está en otro lugar
        String username = "tu_usuario";  // Cambia por tu nombre de usuario de MySQL
        String password = "tu_contraseña"; // Cambia por tu contraseña de MySQL

        Connection connection = null;

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection(jdbcURL, username, password);

            if (connection != null) {
                System.out.println("Conectado a la base de datos");
                
                // Crear una declaración de SQL
                Statement statement = connection.createStatement();
                // Ejecutar una consulta
                String sql = "SELECT * FROM tu_tabla"; // Cambia 'tu_tabla' por el nombre de tu tabla
                ResultSet resultSet = statement.executeQuery(sql);

                // Procesar el resultado
                while (resultSet.next()) {
                    System.out.println("Columna1: " + resultSet.getString("columna1")); // Cambia 'columna1' por el nombre real de tu columna
                    System.out.println("Columna2: " + resultSet.getString("columna2")); // Cambia 'columna2' por el nombre real de tu columna
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        } finally {
            try {
                // Cerrar la conexión si no es nula
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

