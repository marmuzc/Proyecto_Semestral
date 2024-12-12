package GUI;
import LOGICA.Asientos;
import LOGICA.Recorrido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase PanelAsientos que representa el panel de selección de asientos.
 */

public class PanelAsientos extends JPanel {
    private Recorrido recorrido;
    private ArrayList<Asientos> asientos;
    private ActionListener onAsientoSeleccionado;

    /**
     * Constructor de la clase PanelAsientos.
     *
     * @param recorrido El recorrido asociado a los asientos.
     * @param onAsientoSeleccionado El ActionListener que se ejecutará cuando se seleccione un asiento.
     */
    public PanelAsientos(Recorrido recorrido, ActionListener onAsientoSeleccionado) {
        this.recorrido = recorrido;
        this.asientos = recorrido.getBus().getAsientosArray();
        this.onAsientoSeleccionado = onAsientoSeleccionado;

        setLayout(new GridLayout(1, 1, 10, 10));
        ArrayList<Asientos> asientosPiso1 = new ArrayList<>();
        ArrayList<Asientos> asientosPiso2 = new ArrayList<>();

        for (Asientos asiento : asientos) { //separar los asientos por piso
            if (asiento.getNumero() <= 30) {
                asientosPiso1.add(asiento);
            } else {
                asientosPiso2.add(asiento);
            }
        }

        add(crearPanelDePisos(asientosPiso1, "Piso 1"));
        if (!asientosPiso2.isEmpty()) {
            add(crearPanelDePisos(asientosPiso2, "Piso 2"));
        }
    }

    /**
     * Crea un panel para los asientos de un piso específico.
     *
     * @param asientos La lista de asientos del piso.
     * @param titulo El título del panel.
     * @return El panel creado.
     */
    private JPanel crearPanelDePisos(ArrayList<Asientos> asientos, String titulo) {
        JPanel panelPiso = new JPanel(new BorderLayout(10, 10));
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPiso.add(lblTitulo, BorderLayout.NORTH);

        JPanel gridAsientos = new JPanel(new GridLayout(4, 4, 5, 5));
        for (Asientos asiento : asientos) { //asignar los asientos al panel y los botones para los asientos
            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            if (asiento.isOcupado()) { //ocupado sera rojo y disponle verde
                btnAsiento.setBackground(Color.RED);
            } else {
                btnAsiento.setBackground(Color.GREEN);
            }
            btnAsiento.setOpaque(true);
            btnAsiento.setBorderPainted(false);

            btnAsiento.addActionListener(e -> {
                if (asiento.isOcupado()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "El asiento número " + asiento.getNumero() + " ya está ocupado. Por favor, seleccione otro asiento.",
                            "Asiento Ocupado",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

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


    /**
     * Actualiza el estado de los asientos en el panel.
     */
    public void actualizarEstadoAsientos() {
        this.removeAll();
        ArrayList<Asientos> asientosPiso1 = new ArrayList<>();
        ArrayList<Asientos> asientosPiso2 = new ArrayList<>();
        //actualizar estado de los asientos del asiento
        for (Asientos asiento : asientos) {
            if (asiento.getNumero() <= 30) {
                asientosPiso1.add(asiento);
            } else {
                asientosPiso2.add(asiento);
            }
        }

        add(crearPanelDePisos(asientosPiso1, "Piso 1"));
        if (!asientosPiso2.isEmpty()) {
            add(crearPanelDePisos(asientosPiso2, "Piso 2"));
        }
        revalidate();
        repaint();
    }
}
