package com.inventario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        VentaDAO ventaDAO = new VentaDAO();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Iniciar sesión\n2. Crear usuario");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            System.out.print("Usuario: ");
            String username = scanner.next();
            System.out.print("Contraseña: ");
            String password = scanner.next();

            if (usuarioDAO.login(username, password)) {
                System.out.println("Bienvenido!");
                // Aquí puedes agregar más opciones como agregar productos, modificar productos, vender, etc.
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } else if (opcion == 2) {
            System.out.print("Nuevo usuario: ");
            String newUsername = scanner.next();
            System.out.print("Nueva contraseña: ");
            String newPassword = scanner.next();
            usuarioDAO.createUser(newUsername, newPassword);
            System.out.println("Usuario creado!");
        }

        scanner.close();
    }
}