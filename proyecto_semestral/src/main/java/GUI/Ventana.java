package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {
    private final String PASSWORD = "admin123"; // Contraseña predefinida
    private boolean accesoConcedido = false; // Para recordar si ya se verificó la contraseña

    public Ventana() {
        // Usar el método getInstance del Singleton
        Administrador administrador = Administrador.getInstance();

        // Crear los paneles
        PanelComprarPasaje panelComprar = new PanelComprarPasaje(administrador);

        // Crear el panel de "Crear Recorrido" como un panel independiente
        PanelCrearRecorrido panelCrear = new PanelCrearRecorrido(() -> {
            panelComprar.actualizarRecorridos(); // Callback para actualizar recorridos
        });

        // Crear el contenedor de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Comprar Pasaje", panelComprar);
        tabbedPane.addTab("Crear Recorrido", new JPanel()); // Panel vacío temporalmente

        // Listener para controlar el cambio de pestañas
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();

            // Si selecciona la pestaña "Crear Recorrido"
            if (selectedIndex == 1 && !accesoConcedido) {
                // Mostrar diálogo de contraseña
                JPasswordField passwordField = new JPasswordField();
                int result = JOptionPane.showConfirmDialog(this, passwordField,
                        "Admin, Ingrese la contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String inputPassword = new String(passwordField.getPassword());
                    if (inputPassword.equals(PASSWORD)) {
                        accesoConcedido = true;
                        tabbedPane.setComponentAt(1, panelCrear); // Reemplazar el panel vacío con `PanelCrearRecorrido`
                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                        tabbedPane.setSelectedIndex(0); // Volver a la pestaña anterior
                    }
                } else {
                    tabbedPane.setSelectedIndex(0); // Volver a la pestaña anterior si se cancela
                }
            }
        });

        // Configurar la ventana principal
        this.setTitle("Gestión de Recorridos");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
