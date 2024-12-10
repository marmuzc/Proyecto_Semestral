package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
    private final String PASSWORD = "1234"; // Contraseña predefinida
    private Runnable onLoginSuccess;

    public PanelLogin(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
        this.setLayout(new GridLayout(3, 1, 10, 10)); // 3 filas, 1 columna

        JLabel lblPassword = new JLabel("Ingrese la contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Ingresar");

        this.add(lblPassword);
        this.add(txtPassword);
        this.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPassword = new String(txtPassword.getPassword());
                if (inputPassword.equals(PASSWORD)) {
                    // Contraseña correcta
                    JOptionPane.showMessageDialog(PanelLogin.this, "Acceso concedido.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    onLoginSuccess.run();
                } else {
                    // Contraseña incorrecta
                    JOptionPane.showMessageDialog(PanelLogin.this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}