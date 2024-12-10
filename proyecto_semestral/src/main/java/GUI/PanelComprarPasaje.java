package GUI;

import LOGICA.Administrador;
import LOGICA.Recorrido;
import LOGICA.Asientos;
import LOGICA.TipoAsiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelComprarPasaje extends JPanel {
    private Administrador administrador;
    private JComboBox<String> comboRecorridos; // Para seleccionar un recorrido
    private JLabel lblPrecio; // Para mostrar el precio del recorrido seleccionado
    private JTextArea areaAsientos; // Para mostrar los asientos disponibles
    private JTextField txtNumeroAsiento; // Para ingresar el número de asiento a comprar
    private JButton btnComprarAsiento; // Botón para comprar asiento

    public PanelComprarPasaje(Administrador administrador) {
        this.administrador = administrador;
        this.setLayout(new BorderLayout(10, 10));

        // Panel superior para selección de recorrido
        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblSeleccionar = new JLabel("Seleccione un recorrido:");
        comboRecorridos = new JComboBox<>();
        lblPrecio = new JLabel("Precio: ---");
        JButton btnActualizar = new JButton("Actualizar recorridos");

        panelSuperior.add(lblSeleccionar);
        panelSuperior.add(comboRecorridos);
        panelSuperior.add(lblPrecio);
        panelSuperior.add(btnActualizar);

        // Panel central para mostrar asientos
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        JLabel lblAsientos = new JLabel("Asientos disponibles:");
        areaAsientos = new JTextArea(10, 30);
        areaAsientos.setEditable(false);
        JScrollPane scrollAsientos = new JScrollPane(areaAsientos);

        panelCentral.add(lblAsientos, BorderLayout.NORTH);
        panelCentral.add(scrollAsientos, BorderLayout.CENTER);

        // Panel inferior para compra de asientos
        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblNumeroAsiento = new JLabel("Número de asiento:");
        txtNumeroAsiento = new JTextField();
        btnComprarAsiento = new JButton("Comprar Asiento");

        panelInferior.add(lblNumeroAsiento);
        panelInferior.add(txtNumeroAsiento);
        panelInferior.add(new JLabel()); // Espaciador
        panelInferior.add(btnComprarAsiento);

        // Añadir paneles al layout principal
        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        // Listener para actualizar los recorridos
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRecorridos();
            }
        });

        // Listener para mostrar asientos disponibles y precio al seleccionar un recorrido
        comboRecorridos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboRecorridos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);
                    lblPrecio.setText("Precio: " + recorrido.getPrecioBase() + " CLP");
                    mostrarAsientosDisponibles(recorrido);
                }
            }
        });

        // Listener para comprar un asiento
        btnComprarAsiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedIndex = comboRecorridos.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);
                        int numeroAsiento = Integer.parseInt(txtNumeroAsiento.getText().trim());
                        int precio = recorrido.comprarAsiento(numeroAsiento);

                        if (precio != -1) {
                            JOptionPane.showMessageDialog(PanelComprarPasaje.this,
                                    "Asiento comprado exitosamente. Precio total: " + precio + " CLP");
                            mostrarAsientosDisponibles(recorrido); // Actualizar los asientos
                        } else {
                            JOptionPane.showMessageDialog(PanelComprarPasaje.this,
                                    "El asiento ya está ocupado o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelComprarPasaje.this,
                            "Ingrese un número de asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Actualiza los recorridos disponibles en el JComboBox.
     */
    public void actualizarRecorridos() {
        comboRecorridos.removeAllItems(); // Limpiar la lista actual
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();

        for (Recorrido recorrido : recorridos) {
            comboRecorridos.addItem(recorrido.getOrigen() + " -> " + recorrido.getDestino());
        }

        // Mostrar detalles del primer recorrido si existen recorridos
        if (!recorridos.isEmpty()) {
            comboRecorridos.setSelectedIndex(0);
            Recorrido primerRecorrido = recorridos.get(0);
            lblPrecio.setText("Precio: " + primerRecorrido.getPrecioBase() + " CLP");
            mostrarAsientosDisponibles(primerRecorrido);
        } else {
            lblPrecio.setText("Precio: ---");
            areaAsientos.setText("");
        }
    }

    /**
     * Muestra los asientos disponibles de un recorrido en el área de texto.
     *
     * @param recorrido El recorrido seleccionado.
     */
    private void mostrarAsientosDisponibles(Recorrido recorrido) {
        StringBuilder builder = new StringBuilder();
        ArrayList<Asientos> asientos = recorrido.getBus().getAsientosArray();

        for (Asientos asiento : asientos) {
            builder.append("Asiento ").append(asiento.getNumero())
                    .append(" (").append(asiento.getTipo()).append(")")
                    .append(asiento.isOcupado() ? " - Ocupado\n" : " - Disponible\n");
        }

        areaAsientos.setText(builder.toString());
    }
}