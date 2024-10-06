package com.inventario;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioGUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public InventarioGUI() {
        // Configuración del JFrame
        setTitle("Sistema de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        // Verificar la conexión a la base de datos al iniciar la interfaz
        verificarConexionBD();

        // Etiquetas y campos de texto para login
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(10, 10, 80, 25);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 10, 160, 25);
        getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(10, 40, 80, 25);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 40, 160, 25);
        getContentPane().add(txtPassword);

        // Botón de iniciar sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(10, 70, 150, 25);
        getContentPane().add(btnLogin);

        // Botón para crear nuevo usuario
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        btnCrearUsuario.setBounds(170, 70, 150, 25);
        getContentPane().add(btnCrearUsuario);

        // Acción del botón de login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (usuarioDAO.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido!");
                    mostrarPanelProductos();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });

        // Acción del botón para crear usuario
        btnCrearUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearNuevoUsuario();
            }
        });
    }

    // Método para verificar la conexión a la base de datos
    private void verificarConexionBD() {
        if (DatabaseConnection.checkConnection()) {
            JOptionPane.showMessageDialog(null, "Conexión a la base de datos exitosa.");
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para crear un nuevo usuario
    private void crearNuevoUsuario() {
        // Ventana emergente para ingresar nuevo usuario
        JTextField nuevoUsername = new JTextField();
        JPasswordField nuevoPassword = new JPasswordField();
        Object[] message = {
            "Nuevo Usuario:", nuevoUsername,
            "Nueva Contraseña:", nuevoPassword
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Crear nuevo usuario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = nuevoUsername.getText();
            String password = new String(nuevoPassword.getPassword());

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.createUser(username, password)) {
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el usuario.");
            }
        }
    }

    // Método que mostrará el panel de productos
    private void mostrarPanelProductos() {
        // Limpia la ventana actual
        getContentPane().removeAll();
        repaint();

        // Aquí puedes agregar la interfaz para gestionar productos
        JLabel lblProducto = new JLabel("Panel de Productos");
        lblProducto.setBounds(10, 10, 200, 25);
        getContentPane().add(lblProducto);

        // Más elementos de la interfaz aquí
    }

    public static void main(String[] args) {
        // Iniciar la interfaz gráfica
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InventarioGUI frame = new InventarioGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

