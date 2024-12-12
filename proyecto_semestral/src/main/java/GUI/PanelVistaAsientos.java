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

        setLayout(new GridLayout(1, 1, 10, 10));
        ArrayList<Asientos> asientosPiso1 = new ArrayList<>();
        ArrayList<Asientos> asientosPiso2 = new ArrayList<>();

        for (Asientos asiento : asientos) {
            if (asiento.getNumero() <= 30) {
                asientosPiso1.add(asiento);
            } else {
                asientosPiso2.add(asiento);
            }
        }

        add(crearPanelDePiso(asientosPiso1, "Piso 1"));
        if (!asientosPiso2.isEmpty()) {
            add(crearPanelDePiso(asientosPiso2, "Piso 2"));
        }
    }

    private JPanel crearPanelDePiso(ArrayList<Asientos> asientos, String titulo) {
        JPanel panelPiso = new JPanel(new BorderLayout(10, 10));
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPiso.add(lblTitulo, BorderLayout.NORTH);

        JPanel gridAsientos = new JPanel(new GridLayout(4, 4, 5, 5));
        for (Asientos asiento : asientos) {
            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            if (asiento.isOcupado()) {
                btnAsiento.setBackground(Color.RED);
            } else {
                btnAsiento.setBackground(Color.GREEN);
            }
            btnAsiento.setOpaque(true);
            btnAsiento.setBorderPainted(false);
            btnAsiento.setEnabled(!asiento.isOcupado());

            btnAsiento.addActionListener(e -> {
                if (asiento.isOcupado()) {
                    // Mostrar mensaje de asiento ocupado
                    JOptionPane.showMessageDialog(
                            this,
                            "El asiento ya está ocupado. Por favor, seleccione otro.",
                            "Asiento Ocupado",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else if (onAsientoSeleccionado != null) {
                    // Disparar evento para notificar selección de asiento si está disponible
                    onAsientoSeleccionado.actionPerformed(new ActionEvent(asiento, ActionEvent.ACTION_PERFORMED, null));

                    // Verificar si el asiento ahora está ocupado tras la compra
                    if (asiento.isOcupado()) {
                        btnAsiento.setBackground(Color.RED);
                        btnAsiento.setEnabled(false);
                    }
                }
            });



            gridAsientos.add(btnAsiento);
        }

        panelPiso.add(gridAsientos, BorderLayout.CENTER);
        return panelPiso;
    }

    public void actualizarEstadoAsientos() {
        Component[] componentes = this.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                JPanel panelPiso = (JPanel) componente;
                for (Component boton : panelPiso.getComponents()) {
                    if (boton instanceof JButton) {
                        JButton btnAsiento = (JButton) boton;
                        int numeroAsiento = Integer.parseInt(btnAsiento.getText());
                        Asientos asiento = recorrido.obtenerAsiento(numeroAsiento);
                        if (asiento.isOcupado()) {
                            btnAsiento.setBackground(Color.RED);
                            btnAsiento.setEnabled(false);
                        } else {
                            btnAsiento.setBackground(Color.GREEN);
                            btnAsiento.setEnabled(true);
                        }
                    }
                }
            }
        }
    }
}
