package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private boolean accesoConcedido = false;

    public Ventana() {
        Administrador administrador = Administrador.getInstance();
        PanelComprarPasaje panelComprar = new PanelComprarPasaje(administrador);
        PanelCrearRecorrido panelCrear = new PanelCrearRecorrido(() -> {
            panelComprar.actualizarRecorridos();
        });

        PanelLogin panelLogin = new PanelLogin(() -> {
            accesoConcedido = true;
            cardLayout.show(panelPrincipal, "Crear Recorrido");
        });

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.add(panelComprar, "Comprar Pasaje");
        panelPrincipal.add(panelLogin, "Login");
        panelPrincipal.add(panelCrear, "Crear Recorrido");

        JTabbedPane panelTabs = new JTabbedPane();
        panelTabs.addTab("Comprar Pasaje", null);
        panelTabs.addTab("Crear Recorrido", null);

        panelTabs.addChangeListener(e -> {
            int selectedIndex = panelTabs.getSelectedIndex();

            if (selectedIndex == 1) {
                if (accesoConcedido) {
                    cardLayout.show(panelPrincipal, "Crear Recorrido");
                } else {
                    cardLayout.show(panelPrincipal, "Login");
                }
            } else if (selectedIndex == 0) {
                cardLayout.show(panelPrincipal, "Comprar Pasaje");
            }
        });

        this.setTitle("Reserva de pasajes");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(panelTabs, BorderLayout.NORTH);
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
