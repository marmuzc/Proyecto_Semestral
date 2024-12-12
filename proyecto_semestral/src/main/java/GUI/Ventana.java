package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private boolean accesoConcedido = false;

    public Ventana() {
        // Usar el metodo getInstance del Singleton
        Administrador administrador = Administrador.getInstance();

        // Crear los paneles
        PanelComprarPasaje panelComprar = new PanelComprarPasaje(administrador);

        // Crear el panel de "Crear Recorrido" como un panel independiente
        PanelCrearRecorrido panelCrear = new PanelCrearRecorrido(() -> {
            panelComprar.actualizarRecorridos(); // Callback para actualizar recorridos
        });

        // Panel de inicio de sesión
        PanelLogin panelLogin = new PanelLogin(() -> {
            accesoConcedido = true;
            cardLayout.show(mainPanel, "Crear Recorrido"); // Mostrar el panel "Crear Recorrido" después del inicio de sesión exitoso
        });

        // Configurar CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Añadir los paneles al mainPanel
        mainPanel.add(panelComprar, "Comprar Pasaje");
        mainPanel.add(panelLogin, "Login");
        mainPanel.add(panelCrear, "Crear Recorrido");

        // Crear el contenedor de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Comprar Pasaje", null);
        tabbedPane.addTab("Crear Recorrido", null);

        // Listener para controlar el cambio de pestañas
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();

            // Si selecciona la pestaña "Crear Recorrido"
            if (selectedIndex == 1) {
                if (accesoConcedido) {
                    cardLayout.show(mainPanel, "Crear Recorrido"); // Mostrar el panel "Crear Recorrido" directamente si ya se concedió el acceso
                } else {
                    cardLayout.show(mainPanel, "Login"); // Mostrar el panel de inicio de sesión si no se ha concedido el acceso
                }
            } else if (selectedIndex == 0) {
                cardLayout.show(mainPanel, "Comprar Pasaje"); // Cambiar al panel "Comprar Pasaje"
            }
        });

        // Configurar la ventana principal
        this.setTitle("Gestión de Recorridos");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.NORTH); // Colocar las pestañas en la parte superior
        this.add(mainPanel, BorderLayout.CENTER); // Colocar el mainPanel en el centro
        this.setVisible(true);
    }
}
