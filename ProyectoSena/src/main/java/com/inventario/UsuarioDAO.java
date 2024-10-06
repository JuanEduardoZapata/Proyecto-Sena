package com.inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para crear un nuevo usuario
    public boolean createUser(String username, String password) {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;  // Si se insertó al menos una fila, el usuario fue creado

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si hay error, devuelve falso
    }

    // Método para iniciar sesión
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();  // Si hay algún resultado, el login es correcto

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si hay error o no se encontró al usuario, devuelve falso
    }
}
