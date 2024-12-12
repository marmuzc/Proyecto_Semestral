package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Ventana que representa la ventana principal de la aplicación de reserva de pasajes.
 */
public class Ventana extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private boolean accesoConcedido = false;

    /**
     * Constructor de la clase Ventana.
     * Inicializa los paneles y la lógica de la interfaz gráfica.
     */
    public Ventana() {
        Administrador administrador = Administrador.getInstance(); //administrador es singleton
        PanelComprarPasaje panelComprar = new PanelComprarPasaje(administrador);
        PanelCrearRecorrido panelCrear = new PanelCrearRecorrido(() -> {
            panelComprar.actualizarRecorridos(); //actualizar recorridos al crear uno nuevo
        });

        PanelLogin panelLogin = new PanelLogin(() -> {
            accesoConcedido = true;
            cardLayout.show(panelPrincipal, "Crear Recorrido");
        });

        cardLayout = new CardLayout(); //card layout para que se vea como una pagina real, sin tantos cambios bruscos
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.add(panelComprar, "Comprar Pasaje");
        panelPrincipal.add(panelLogin, "Login");
        panelPrincipal.add(panelCrear, "Crear Recorrido");

        JTabbedPane panelTabs = new JTabbedPane(); //tabs para cambiar entre comprar pasaje y crear recorrido
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
