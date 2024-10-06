package com.inventario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/inventario";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para verificar la conexión a la base de datos
    public static boolean checkConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return true; // Conexión exitosa
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Conexión fallida
    }
}
