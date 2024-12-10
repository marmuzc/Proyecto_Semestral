package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelCrearRecorrido extends JPanel {
    private final Administrador administrador;

    public PanelCrearRecorrido(Runnable onRecorridoCreado) {
        this.administrador = Administrador.getInstance(); // Obtiene la instancia única del Singleton
        this.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas

        // Lista de ciudades
        String[] ciudades = {"<<Seleccione Ciudad>>", "Concepción", "Los Angeles", "Chillán", "Santiago", "Talca", "Rancagua"};

        // Crear componentes
        JLabel lblOrigen = new JLabel("Origen:");
        JComboBox<String> comboOrigen = new JComboBox<>(ciudades);

        JLabel lblDestino = new JLabel("Destino:");
        JComboBox<String> comboDestino = new JComboBox<>(ciudades);

        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();

        JLabel lblPisos = new JLabel("Número de pisos:");
        JComboBox<Integer> comboPisos = new JComboBox<>(new Integer[]{1, 2});

        JButton btnCrearRecorrido = new JButton("Crear Recorrido");

        // Añadir componentes al panel
        this.add(lblOrigen);
        this.add(comboOrigen);
        this.add(lblDestino);
        this.add(comboDestino);
        this.add(lblPrecio);
        this.add(txtPrecio);
        this.add(lblPisos);
        this.add(comboPisos);
        this.add(new JLabel()); // Espaciador
        this.add(btnCrearRecorrido);

        // Configurar lógica del botón
        btnCrearRecorrido.addActionListener(e -> {
            try {
                String origen = (String) comboOrigen.getSelectedItem();
                String destino = (String) comboDestino.getSelectedItem();
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                int pisos = (int) comboPisos.getSelectedItem();
                LocalDateTime fechaHora = LocalDateTime.now();

                if (origen.equals(destino) || origen.equals("<<Seleccione Ciudad>>") || destino.equals("<<Seleccione Ciudad>>")) {
                    JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "Seleccione ciudades válidas y diferentes.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                administrador.crearRecorrido(origen, destino, precio, pisos, fechaHora.format(DateTimeFormatter.ofPattern("dd/MM")), fechaHora.format(DateTimeFormatter.ofPattern("HH:mm")));
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "Recorrido creado exitosamente.");

                // Ejecutar callback para actualizar el otro panel
                onRecorridoCreado.run();

                // Limpiar campos
                comboOrigen.setSelectedIndex(0);
                comboDestino.setSelectedIndex(0);
                txtPrecio.setText("");
                comboPisos.setSelectedIndex(0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
