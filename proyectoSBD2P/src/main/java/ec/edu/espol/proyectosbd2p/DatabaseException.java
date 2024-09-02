package ec.edu.espol.proyectosbd2p;

import java.sql.SQLException;

public class DatabaseException extends SQLException {
    public DatabaseException(String message, int errorCode) {
        super(message, null, errorCode);
    }
}