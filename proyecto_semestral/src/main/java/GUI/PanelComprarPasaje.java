package GUI;

import LOGICA.Administrador;
import LOGICA.Recorrido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComprarPasaje extends JPanel {
    private Administrador administrador;
    private JComboBox<Recorrido> comboRecorridos;
    private JTextField txtAsiento;

    public PanelComprarPasaje(Administrador administrador) {
        this.administrador = administrador;
        this.setLayout(new GridLayout(4, 2, 10, 10)); // 4 filas, 2 columnas

        JLabel lblRecorridos = new JLabel("Seleccione Recorrido:");
        comboRecorridos = new JComboBox<>();

        JLabel lblAsiento = new JLabel("Número de Asiento:");
        txtAsiento = new JTextField();

        JButton btnComprar = new JButton("Comprar Pasaje");

        this.add(lblRecorridos);
        this.add(comboRecorridos);
        this.add(lblAsiento);
        this.add(txtAsiento);
        this.add(new JLabel()); // Espaciador
        this.add(btnComprar);

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Recorrido recorrido = (Recorrido) comboRecorridos.getSelectedItem();
                    int asiento = Integer.parseInt(txtAsiento.getText().trim());

                    int precio = recorrido.comprarAsiento(asiento);
                    if (precio == -1) {
                        JOptionPane.showMessageDialog(PanelComprarPasaje.this, "Asiento ya ocupado.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(PanelComprarPasaje.this, "Pasaje comprado. Precio: " + precio);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelComprarPasaje.this, "Ingrese un número de asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PanelComprarPasaje.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void actualizarRecorridos() {
        comboRecorridos.removeAllItems();
        for (Recorrido recorrido : administrador.getRecorridos()) {
            comboRecorridos.addItem(recorrido);
        }
    }
}
