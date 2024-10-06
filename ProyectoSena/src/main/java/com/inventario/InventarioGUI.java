package com.inventario;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioGUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtNombreProducto;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JLabel lblImagen;

    public InventarioGUI() {
        // Configuración del JFrame
        setTitle("Sistema de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        // Etiquetas y campos de texto para login
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(77, 159, 80, 25);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(167, 159, 160, 25);
        getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(77, 189, 80, 25);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(167, 189, 160, 25);
        getContentPane().add(txtPassword);

        // Botón de iniciar sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(140, 225, 150, 25);
        getContentPane().add(btnLogin);

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
    }

    private void mostrarPanelProductos() {
        // Limpia la ventana actual
        getContentPane().removeAll();
        repaint();

        JLabel lblNombreProducto = new JLabel("Nombre:");
        lblNombreProducto.setBounds(10, 10, 80, 25);
        getContentPane().add(lblNombreProducto);

        txtNombreProducto = new JTextField();
        txtNombreProducto.setBounds(100, 10, 160, 25);
        getContentPane().add(txtNombreProducto);
        txtNombreProducto.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 40, 80, 25);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(100, 40, 160, 25);
        getContentPane().add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 70, 80, 25);
        getContentPane().add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 70, 160, 25);
        getContentPane().add(txtPrecio);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10, 100, 80, 25);
        getContentPane().add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(100, 100, 160, 25);
        getContentPane().add(txtCantidad);

        JLabel lblImagenProducto = new JLabel("Imagen:");
        lblImagenProducto.setBounds(10, 130, 80, 25);
        getContentPane().add(lblImagenProducto);

        lblImagen = new JLabel("");
        lblImagen.setBounds(100, 130, 160, 100);
        getContentPane().add(lblImagen);

        // Botón para agregar productos
        JButton btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.setBounds(10, 240, 150, 25);
        getContentPane().add(btnAgregarProducto);

        btnAgregarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
    }

    private void agregarProducto() {
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.agregarProducto(nombre, descripcion, precio, cantidad, "imagen.jpg"); // Por simplicidad, la imagen es fija

        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
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
