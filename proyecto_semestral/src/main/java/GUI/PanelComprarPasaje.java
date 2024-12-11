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
        areaAsientos = new JTextArea();
        areaAsientos.setEditable(false); // No debe ser editable por el usuario
        JScrollPane scrollPane = new JScrollPane(areaAsientos);
        panelCentral.add(lblAsientos, BorderLayout.NORTH);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Botón para mostrar los asientos en un nuevo panel
        JButton btnVerAsientos = new JButton("Ver Asientos en Detalle");
        panelCentral.add(btnVerAsientos, BorderLayout.SOUTH);

        // Listener para mostrar la ventana de asientos
        btnVerAsientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboRecorridos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);

                    ArrayList<Asientos> asientos = recorrido.getBus().getAsientosArray();
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

                    // Crear y mostrar la ventana de asientos
                    JFrame ventanaAsientos = new JFrame("Asientos por piso");
                    ventanaAsientos.setLayout(new BorderLayout());

                    JPanel contenedorPisos = new JPanel(new GridLayout(2, 1, 10, 10));
                    contenedorPisos.add(crearPanelDePiso(asientosPiso1, "Piso 1"));
                    contenedorPisos.add(crearPanelDePiso(asientosPiso2, "Piso 2"));

                    ventanaAsientos.add(contenedorPisos, BorderLayout.CENTER);
                    ventanaAsientos.setSize(800, 600);
                    ventanaAsientos.setLocationRelativeTo(null);
                    ventanaAsientos.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(PanelComprarPasaje.this,
                            "Seleccione un recorrido primero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblNumeroAsiento = new JLabel("Número de asiento:");
        txtNumeroAsiento = new JTextField();
        btnComprarAsiento = new JButton("Comprar Asiento");

        panelInferior.add(lblNumeroAsiento);
        panelInferior.add(txtNumeroAsiento);
        panelInferior.add(new JLabel()); // Espaciador
        panelInferior.add(btnComprarAsiento);

        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRecorridos();
            }
        });

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

        btnComprarAsiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedIndex = comboRecorridos.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        Recorrido recorrido = administrador.getRecorridos().get(selectedIndex);
                        int numeroAsiento = Integer.parseInt(txtNumeroAsiento.getText().trim());

                        // Crear un nuevo JFrame para mostrar los botones Sí y No
                        JFrame confirmFrame = new JFrame("Confirmar Compra");
                        confirmFrame.setSize(400, 200);
                        confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        confirmFrame.setLayout(new BorderLayout());

                        // Mensaje de confirmación
                        JLabel lblMensaje = new JLabel(String.format(
                                "<html>¿Confirma la compra del asiento %d?<br>Recorrido: %s -> %s<br>" +
                                        "Fecha: %s<br>Hora: %s<br>Precio: %d CLP</html>",
                                numeroAsiento,
                                recorrido.getOrigen(),
                                recorrido.getDestino(),
                                recorrido.getFecha(),
                                recorrido.getHora(),
                                recorrido.getPrecioBase()
                        ));
                        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

                        // Panel de botones
                        JPanel buttonPanel = new JPanel();
                        JButton btnSi = new JButton("Sí");
                        JButton btnNo = new JButton("No");

                        // Listener para el botón Sí
                        btnSi.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int precio = recorrido.comprarAsiento(numeroAsiento);
                                if (precio != -1) {
                                    String mensajeExito = String.format(
                                            "Asiento comprado exitosamente.\n" +
                                                    "Recorrido: %s -> %s\n" +
                                                    "Fecha: %s\nHora: %s\n" +
                                                    "Precio total: %d CLP",
                                            recorrido.getOrigen(),
                                            recorrido.getDestino(),
                                            recorrido.getFecha(),
                                            recorrido.getHora(),
                                            precio
                                    );
                                    JOptionPane.showMessageDialog(confirmFrame, mensajeExito);
                                    mostrarAsientosDisponibles(recorrido); // Actualizar los asientos
                                } else {
                                    JOptionPane.showMessageDialog(confirmFrame,
                                            "El asiento ya está ocupado o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                confirmFrame.dispose(); // Cerrar el cuadro de confirmación
                            }
                        });

                        // Listener para el botón No
                        btnNo.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mostrarAsientosDisponibles(recorrido); // Actualizar los asientos
                                confirmFrame.dispose(); // Cerrar el cuadro de confirmación
                            }
                        });

                        // Agregar botones al panel
                        buttonPanel.add(btnSi);
                        buttonPanel.add(btnNo);

                        // Agregar componentes al JFrame
                        confirmFrame.add(lblMensaje, BorderLayout.CENTER);
                        confirmFrame.add(buttonPanel, BorderLayout.SOUTH);

                        // Mostrar el JFrame
                        confirmFrame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelComprarPasaje.this,
                            "Ingrese un número de asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JPanel crearPanelDePiso(ArrayList<Asientos> asientos, String titulo) {
        JPanel panelPiso = new JPanel(new BorderLayout(10, 10));
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPiso.add(lblTitulo, BorderLayout.NORTH);

        // Crear un panel con GridLayout para los asientos
        JPanel gridAsientos = new JPanel(new GridLayout(5, 5, 5, 5)); // 5x5 como ejemplo
        for (Asientos asiento : asientos) {
            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            btnAsiento.setBackground(asiento.isOcupado() ? Color.RED : Color.GREEN);
            btnAsiento.setOpaque(true);
            btnAsiento.setBorderPainted(false);
            btnAsiento.setEnabled(true);
            gridAsientos.add(btnAsiento);
        }

        panelPiso.add(gridAsientos, BorderLayout.CENTER);
        return panelPiso;
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
