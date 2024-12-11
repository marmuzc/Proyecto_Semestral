package GUI;

import LOGICA.Asientos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAsientos extends JFrame {
    public PanelAsientos(ArrayList<Asientos> asientos, String piso) {
        setTitle("Asientos - " + piso);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Distribución de asientos - " + piso, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelAsientos = new JPanel();
        panelAsientos.setLayout(new GridLayout(0, 4, 10, 10)); // 4 columnas por fila
        JScrollPane scrollPane = new JScrollPane(panelAsientos);
        add(scrollPane, BorderLayout.CENTER);

        for (Asientos asiento : asientos) {
            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            btnAsiento.setBackground(asiento.isOcupado() ? Color.RED : Color.GREEN);
            btnAsiento.setToolTipText("Asiento " + asiento.getNumero() + " (" + asiento.getTipo() + ")");

            btnAsiento.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (asiento.isOcupado()) {
                        // Liberar asiento
                        asiento.setOcupado(false);
                        btnAsiento.setBackground(Color.GREEN);
                        JOptionPane.showMessageDialog(PanelAsientos.this,
                                "El asiento " + asiento.getNumero() + " ha sido liberado.");
                    } else {
                        // Ocupar asiento
                        asiento.setOcupado(true);
                        btnAsiento.setBackground(Color.RED);
                        JOptionPane.showMessageDialog(PanelAsientos.this,
                                "El asiento " + asiento.getNumero() + " ha sido comprado.");
                    }
                }
            });

            panelAsientos.add(btnAsiento);
        }

        setVisible(true);
    }
}
