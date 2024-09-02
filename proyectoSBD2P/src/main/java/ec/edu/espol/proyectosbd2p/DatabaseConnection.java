package ec.edu.espol.proyectosbd2p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class DatabaseConnection {

    static Connection connection = null;

    public static void conectaBase(String username, String password) throws DatabaseException {
        String jdbcURL = "jdbc:mysql://proyectosbdgrupo8.mysql.database.azure.com:3306/wuanplus"; 

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            if (connection != null) {
                System.out.println("Conectado a la base de datos en Azure con SSL");
            }
        } catch (SQLException e) {
            handleSQLException(e);
            throw new DatabaseException(e.getMessage(), e.getErrorCode());
        }
    }

    public static ResultSet executeQuery(String sql) throws DatabaseException {
        ResultSet resultSet = null;
        try {
            Statement statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            handleSQLException(e);
            throw new DatabaseException(e.getMessage(), e.getErrorCode());
        }
        return resultSet;
    }

    public static int executeUpdate(String sql) throws DatabaseException {
        int result = 0;
        try {
            Statement statement = getConnection().createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            handleSQLException(e);
            throw new DatabaseException(e.getMessage(), e.getErrorCode());
        }
        return result;
    }

    private static void handleSQLException(SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
        if (e.getErrorCode() == 1045 || e.getErrorCode() == 1142 || e.getErrorCode() == 1044) {
            alertaAccesoDenegado();
        } else {
            alertaErrorGeneral(e.getMessage());
        }
    }

    public static void alertaAccesoDenegado() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Acceso Denegado");
        alert.setHeaderText("Acceso Denegado");
        alert.setContentText("Usted no cuenta con los permisos necesarios para realizar esta operación.");
        alert.showAndWait();
    }

    public static void alertaErrorGeneral(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error SQL");
        alert.setHeaderText("Error en la base de datos");
        alert.setContentText("Ha ocurrido un error: " + mensaje);
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
