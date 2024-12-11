package GUI;

import LOGICA.Administrador;
import LOGICA.Recorrido;
import LOGICA.Asientos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelComprarPasaje extends JPanel {
    private Administrador administrador;
    private JComboBox<String> comboRecorridos;
    private JLabel lblPrecio;
    private JTextArea areaAsientos;
    private JTextField txtNumeroAsiento;
    private JButton btnComprarAsiento;

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
        areaAsientos = new JTextArea();
        areaAsientos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaAsientos);
        panelCentral.add(lblAsientos, BorderLayout.NORTH);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Botón para mostrar los asientos en un nuevo panel
        JButton btnVerAsientos = new JButton("Ver Asientos en Detalle");
        panelCentral.add(btnVerAsientos, BorderLayout.SOUTH);

        // Listener para mostrar la ventana de asientos
        btnVerAsientos.addActionListener(e -> {
            int selectedIndex = comboRecorridos.getSelectedIndex();
            if (selectedIndex >= 0) {
                Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);

                JFrame ventanaAsientos = new JFrame("Asientos por piso");
                ventanaAsientos.setLayout(new BorderLayout());

                PanelVistaAsientos panelVistaAsientos = new PanelVistaAsientos(recorrido, event -> {
                    Asientos asientoSeleccionado = (Asientos) event.getSource();
                    int numeroAsiento = asientoSeleccionado.getNumero();
                    int precio = recorrido.comprarAsiento(numeroAsiento);
                    // Confirmación de compra del asiento
                    int confirm = JOptionPane.showConfirmDialog(ventanaAsientos,
                            "¿Desea comprar el asiento " + numeroAsiento + "?" +
                                    "Detalles:\n- Asiento: " + numeroAsiento +
                                    "\n- Recorrido: " + recorrido.getOrigen() + " -> " + recorrido.getDestino() +
                                    "\n- Fecha: " + recorrido.getFecha() + "\n- Hora: " + recorrido.getHora() +
                                    "\n- Precio: " + precio  + " CLP",
                            "Confirmar Compra", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                      //  int precio = recorrido.comprarAsiento(numeroAsiento);
                        if (precio != -1) {
                            mostrarAsientosDisponibles(recorrido); // Refrescar la vista
                            JOptionPane.showMessageDialog(ventanaAsientos,
                                    "Asiento comprado exitosamente.\n" +
                                            "Detalles:\n- Asiento: " + numeroAsiento +
                                            "\n- Recorrido: " + recorrido.getOrigen() + " -> " + recorrido.getDestino() +
                                            "\n- Fecha: " + recorrido.getFecha() + "\n- Hora: " + recorrido.getHora() +
                                            "\n- Precio: " + precio + " CLP",
                                    "Compra Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(ventanaAsientos,
                                    "El asiento ya está ocupado o no existe.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                    ventanaAsientos.add(panelVistaAsientos, BorderLayout.CENTER);
                ventanaAsientos.setSize(800, 600);
                ventanaAsientos.setLocationRelativeTo(null);
                ventanaAsientos.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un recorrido primero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblNumeroAsiento = new JLabel("Número de asiento:");
        txtNumeroAsiento = new JTextField();
        btnComprarAsiento = new JButton("Comprar Asiento");

        panelInferior.add(lblNumeroAsiento);
        panelInferior.add(txtNumeroAsiento);
        panelInferior.add(new JLabel());
        panelInferior.add(btnComprarAsiento);

        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> actualizarRecorridos());

        comboRecorridos.addActionListener(e -> {
            int selectedIndex = comboRecorridos.getSelectedIndex();
            if (selectedIndex >= 0) {
                Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);
                lblPrecio.setText("Precio: " + recorrido.getPrecioBase() + " CLP");
                mostrarAsientosDisponibles(recorrido);
            }
        });

        btnComprarAsiento.addActionListener(e -> {
            try {
                int selectedIndex = comboRecorridos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);
                    int numeroAsiento = Integer.parseInt(txtNumeroAsiento.getText().trim());
                    int precio = recorrido.comprarAsiento(numeroAsiento);
                    int confirm = JOptionPane.showConfirmDialog(this,"¿Desea comprar el asiento " + numeroAsiento + "?" +
                                    "Detalles:\n- Asiento: " + numeroAsiento +
                                    "\n- Recorrido: " + recorrido.getOrigen() + " -> " + recorrido.getDestino() +
                                    "\n- Fecha: " + recorrido.getFecha() + "\n- Hora: " + recorrido.getHora() +
                                    "\n- Precio: " + precio  + " CLP",
                            "Confirmar Compra", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (precio != -1) {
                            mostrarAsientosDisponibles(recorrido);
                            JOptionPane.showMessageDialog(this,
                                    "Asiento comprado exitosamente.\n" +
                                            "Detalles:\n- Asiento: " + numeroAsiento +
                                            "\n- Recorrido: " + recorrido.getOrigen() + " -> " + recorrido.getDestino() +
                                            "\n- Fecha: " + recorrido.getFecha() + "\n- Hora: " + recorrido.getHora() +
                                            "\n- Precio: " + precio + " CLP",
                                    "Compra Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE)
;                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "El asiento ya está ocupado o no existe.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese un número de asiento valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void actualizarRecorridos() {
        comboRecorridos.removeAllItems();
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();

        for (Recorrido recorrido : recorridos) {
            String infoRecorrido = String.format(
                    "%s -> %s (%s %s)",
                    recorrido.getOrigen(),
                    recorrido.getDestino(),
                    recorrido.getFecha(),
                    recorrido.getHora()
            );
            comboRecorridos.addItem(infoRecorrido);
        }

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

    private void mostrarAsientosDisponibles(Recorrido recorrido) {
        StringBuilder builder = new StringBuilder();
        ArrayList<Asientos> asientos = recorrido.getBus().getAsientosArray();

        builder.append("Piso 1:\n");
        for (Asientos asiento : asientos) {
            if (asiento.getNumero() <= 25) {
                builder.append("Asiento ").append(asiento.getNumero())
                        .append(" (").append(asiento.getTipo()).append(") ")
                        .append(asiento.isOcupado() ? " - Ocupado\n" : " - Disponible\n");
            }
        }

        builder.append("\nPiso 2:\n");
        for (Asientos asiento : asientos) {
            if (asiento.getNumero() > 25) {
                builder.append("Asiento ").append(asiento.getNumero())
                        .append(" (").append(asiento.getTipo()).append(") ")
                        .append(asiento.isOcupado() ? " - Ocupado\n" : " - Disponible\n");
            }
        }

        areaAsientos.setText(builder.toString());
    }
}