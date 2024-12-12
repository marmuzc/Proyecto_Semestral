package GUI;

import LOGICA.Administrador;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class PanelCrearRecorrido extends JPanel {
    private final Administrador administrador;
    private final DefaultComboBoxModel<String> modeloCiudadesOrigen;
    private final DefaultComboBoxModel<String> modeloCiudadesDestino;

    public PanelCrearRecorrido(Runnable onRecorridoCreado) {
        this.administrador = Administrador.getInstance();
        this.setLayout(new GridLayout(9, 2, 10, 10));

        String[] ciudadesIniciales = {"<<Seleccione Ciudad>>", "Concepción", "Los Angeles", "Chillán", "Santiago", "Talca", "Rancagua"};
        modeloCiudadesOrigen = new DefaultComboBoxModel<>(ciudadesIniciales);
        modeloCiudadesDestino = new DefaultComboBoxModel<>(ciudadesIniciales);

        JLabel lblOrigen = new JLabel("Origen:");
        JComboBox<String> comboOrigen = new JComboBox<>(modeloCiudadesOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        JComboBox<String> comboDestino = new JComboBox<>(modeloCiudadesDestino);

        JButton btnAgregarCiudad = new JButton("Agregar Ciudad"); // Botón para añadir ciudades

        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();

        JLabel lblPisos = new JLabel("Número de pisos:");
        JComboBox<Integer> comboPisos = new JComboBox<>(new Integer[]{1, 2});

        JLabel lblFecha = new JLabel("Fecha (DD/MM):");
        JTextField txtFecha = new JTextField();

        JLabel lblHora = new JLabel("Hora (HH:mm):");
        JTextField txtHora = new JTextField();

        JButton btnCrearRecorrido = new JButton("Crear Recorrido");

        this.add(lblOrigen);
        this.add(comboOrigen);
        this.add(lblDestino);
        this.add(comboDestino);
        this.add(new JLabel("Agregar nueva ciudad:"));
        this.add(btnAgregarCiudad);
        this.add(lblPrecio);
        this.add(txtPrecio);
        this.add(lblPisos);
        this.add(comboPisos);
        this.add(lblFecha);
        this.add(txtFecha);
        this.add(lblHora);
        this.add(txtHora);
        this.add(new JLabel());
        this.add(btnCrearRecorrido);

        comboOrigen.addActionListener(e -> {
            String origen = (String) comboOrigen.getSelectedItem();
            String destino = (String) comboDestino.getSelectedItem();

            if (origen != null && origen.equals(destino) && !origen.equals("<<Seleccione Ciudad>>")) {
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "El origen y el destino no pueden ser la misma ciudad.", "Error", JOptionPane.ERROR_MESSAGE);
                comboOrigen.setSelectedIndex(0);
            }
        });

        comboDestino.addActionListener(e -> {
            String origen = (String) comboOrigen.getSelectedItem();
            String destino = (String) comboDestino.getSelectedItem();

            if (destino != null && destino.equals(origen) && !destino.equals("<<Seleccione Ciudad>>")) {
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "El origen y el destino no pueden ser la misma ciudad.", "Error", JOptionPane.ERROR_MESSAGE);
                comboDestino.setSelectedIndex(0);
            }
        });


        btnCrearRecorrido.addActionListener(e -> {
            try {
                String origen = (String) comboOrigen.getSelectedItem();
                String destino = (String) comboDestino.getSelectedItem();
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                int pisos = (int) comboPisos.getSelectedItem();
                String fecha = txtFecha.getText().trim();
                String hora = txtHora.getText().trim();

                if (origen.equals(destino) || origen.equals("<<Seleccione Ciudad>>") || destino.equals("<<Seleccione Ciudad>>")) {
                    JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "Seleccione ciudades válidas y diferentes.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!fecha.matches("\\d{2}/\\d{2}")) {
                    JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "La fecha debe tener el formato DD/MM.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!hora.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "La hora debe tener el formato HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                administrador.crearRecorrido(origen, destino, precio, pisos, fecha, hora);
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "Recorrido creado exitosamente.");

                onRecorridoCreado.run();

                comboOrigen.setSelectedIndex(0);
                comboDestino.setSelectedIndex(0);
                txtPrecio.setText("");
                comboPisos.setSelectedIndex(0);
                txtFecha.setText("");
                txtHora.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAgregarCiudad.addActionListener(e -> {
            String nuevaCiudad = JOptionPane.showInputDialog(PanelCrearRecorrido.this, "Ingrese el nombre de la nueva ciudad:");
            if (nuevaCiudad != null && !nuevaCiudad.trim().isEmpty() && !ciudadYaExiste(nuevaCiudad)) {
                modeloCiudadesOrigen.addElement(nuevaCiudad.trim());
                modeloCiudadesDestino.addElement(nuevaCiudad.trim());
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "Ciudad añadida exitosamente.");
            } else {
                JOptionPane.showMessageDialog(PanelCrearRecorrido.this, "La ciudad ya existe o es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Verifica si una ciudad ya existe en los modelos.
     *
     * @param ciudad La ciudad a verificar.
     * @return true si la ciudad ya existe, false de lo contrario.
     */
    private boolean ciudadYaExiste(String ciudad) {
        for (int i = 0; i < modeloCiudadesOrigen.getSize(); i++) {
            if (modeloCiudadesOrigen.getElementAt(i).equalsIgnoreCase(ciudad.trim())) {
                return true;
            }
        }
        return false;
    }
}
