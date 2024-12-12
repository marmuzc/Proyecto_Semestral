package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
    private final String PASSWORD = "admin123";
    private Runnable onLoginSuccess;

    public PanelLogin(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblPassword = new JLabel("Admin, Ingrese la contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lblPassword, gbc);

        JPasswordField txtPassword = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        this.add(txtPassword, gbc);

        JButton btnLogin = new JButton("Ingresar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 10;
        this.add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPassword = new String(txtPassword.getPassword());
                if (inputPassword.equals(PASSWORD)) {
                    JOptionPane.showMessageDialog(PanelLogin.this, "Acceso concedido.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    onLoginSuccess.run();
                } else {
                    JOptionPane.showMessageDialog(PanelLogin.this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
