package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private boolean accesoConcedido = false;

    public Ventana() {
        Administrador administrador = Administrador.getInstance();
        PanelComprarPasaje panelComprar = new PanelComprarPasaje(administrador);
        PanelCrearRecorrido panelCrear = new PanelCrearRecorrido(() -> {
            panelComprar.actualizarRecorridos();
        });

        PanelLogin panelLogin = new PanelLogin(() -> {
            accesoConcedido = true;
            cardLayout.show(mainPanel, "Crear Recorrido");
        });

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(panelComprar, "Comprar Pasaje");
        mainPanel.add(panelLogin, "Login");
        mainPanel.add(panelCrear, "Crear Recorrido");

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Comprar Pasaje", null);
        tabbedPane.addTab("Crear Recorrido", null);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();

            if (selectedIndex == 1) {
                if (accesoConcedido) {
                    cardLayout.show(mainPanel, "Crear Recorrido");
                } else {
                    cardLayout.show(mainPanel, "Login");
                }
            } else if (selectedIndex == 0) {
                cardLayout.show(mainPanel, "Comprar Pasaje");
            }
        });


        this.setTitle("Reserva de pasajes");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
