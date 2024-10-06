package com.inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VentaDAO {
    public void venderProducto(List<Integer> productoIds, List<Integer> cantidades) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO ventas (producto_id, cantidad, total) VALUES (?, ?, ?)";

            for (int i = 0; i < productoIds.size(); i++) {
                PreparedStatement stmt = conn.prepareStatement(query);
                int productoId = productoIds.get(i);
                int cantidad = cantidades.get(i);
                double total = calcularTotal(productoId, cantidad);

                stmt.setInt(1, productoId);
                stmt.setInt(2, cantidad);
                stmt.setDouble(3, total);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calcularTotal(int productoId, int cantidad) {
        // Este método debe calcular el total basado en el precio del producto y la cantidad
        // Por simplicidad, este ejemplo asume que obtienes el precio desde la base de datos
        double precio = obtenerPrecioProducto(productoId);
        return precio * cantidad;
    }

    private double obtenerPrecioProducto(int productoId) {
        // Aquí debería ir la lógica para obtener el precio del producto desde la base de datos
        return 10.0; // Por simplicidad, se devuelve un precio fijo
    }
}
