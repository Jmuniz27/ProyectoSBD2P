/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectosbd2p;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private String url;
    private String username;
    private String password;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void executeInsert(String query, String value1, String value2) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, value1);
            pstmt.setString(2, value2);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Filas insertadas: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la inserción.");
        }
    }

    public void executeSelect(String query) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                System.out.println("Nombre: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta.");
        }
    }
}