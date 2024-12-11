package GUI;

import LOGICA.Asientos;
import LOGICA.Recorrido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelVistaAsientos extends JPanel {
    private Recorrido recorrido;
    private ArrayList<Asientos> asientos;
    private ActionListener onAsientoSeleccionado;

    public PanelVistaAsientos(Recorrido recorrido, ActionListener onAsientoSeleccionado) {
        this.recorrido = recorrido;
        this.asientos = recorrido.getBus().getAsientosArray();
        this.onAsientoSeleccionado = onAsientoSeleccionado;

        setLayout(new GridLayout(2, 1, 10, 10));
        ArrayList<Asientos> asientosPiso1 = new ArrayList<>();
        ArrayList<Asientos> asientosPiso2 = new ArrayList<>();

        int totalAsientos = asientos.size();
        int asientosPorPiso = totalAsientos / 2;

        int contador = 1;
        for (Asientos asiento : asientos) {
            if (contador <= asientosPorPiso) {
                asientosPiso1.add(asiento);
            } else {
                asientosPiso2.add(asiento);
            }
            contador++;
        }

        add(crearPanelDePiso(asientosPiso1, "Piso 1"));
        add(crearPanelDePiso(asientosPiso2, "Piso 2"));
    }

    private JPanel crearPanelDePiso(ArrayList<Asientos> asientos, String titulo) {
        JPanel panelPiso = new JPanel(new BorderLayout(10, 10));
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPiso.add(lblTitulo, BorderLayout.NORTH);

        JPanel gridAsientos = new JPanel(new GridLayout(5, 5, 5, 5)); // Ajusta el tamaño de la cuadrícula según sea necesario
        for (Asientos asiento : asientos) {
            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            if (asiento.isOcupado()){
                btnAsiento.setBackground(Color.RED);
            } else {
                btnAsiento.setBackground(Color.GREEN);
            }
            btnAsiento.setOpaque(true);
            btnAsiento.setBorderPainted(false);
            btnAsiento.setEnabled(!asiento.isOcupado());

            btnAsiento.addActionListener(e -> {
                if (onAsientoSeleccionado != null) {
                    ActionEvent event = new ActionEvent(asiento, ActionEvent.ACTION_PERFORMED, null);
                    onAsientoSeleccionado.actionPerformed(event);
                }
            });

            gridAsientos.add(btnAsiento);
        }

        panelPiso.add(gridAsientos, BorderLayout.CENTER);
        return panelPiso;
    }

    public void actualizarEstadoAsientos() {
        this.removeAll();
        ArrayList<Asientos> asientosPiso1 = new ArrayList<>();
        ArrayList<Asientos> asientosPiso2 = new ArrayList<>();

        int totalAsientos = asientos.size();
        int asientosPorPiso = totalAsientos / 2;

        int contador = 1;
        for (Asientos asiento : asientos) {
            if (contador <= asientosPorPiso) {
                asientosPiso1.add(asiento);
            } else {
                asientosPiso2.add(asiento);
            }
            contador++;
        }

        add(crearPanelDePiso(asientosPiso1, "Piso 1"));
        add(crearPanelDePiso(asientosPiso2, "Piso 2"));
        revalidate();
        repaint();
    }

}
