package org.prescription.system.View;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Prescription system");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        setLayout(new GridLayout(3,2));

        add(emailLabel);
        add(emailField);

        add(passwordLabel);
        add(passwordField);

        add(loginButton);
        add(registerButton);
    }

}
