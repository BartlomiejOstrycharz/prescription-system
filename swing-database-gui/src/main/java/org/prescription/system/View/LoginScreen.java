package org.prescription.system.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;


public class LoginScreen extends JFrame{

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Prescription System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font headerFont = new Font("Arial", Font.BOLD, 24);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        // Create components
        JLabel titleLabel = new JLabel("Login Form", SwingConstants.CENTER);
        titleLabel.setFont(headerFont);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        emailField = new JTextField();
        emailField.setFont(fieldFont);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Tworzenie klienta HTTP
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost("http://localhost:8080/login");

                try{
                    // Ustaw dane logowania w formie JSON
                    String json = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";
                    StringEntity entity = new StringEntity(json);
                    httpPost.setEntity(entity);
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");


                    // Wyślij zapytanie i odbierz odpowiedź
                    HttpResponse response = httpClient.execute(httpPost);


                    // Sprawdź status odpowiedzi
                    if(response.getStatusLine().getStatusCode() == 200){
                        JOptionPane.showMessageDialog(LoginScreen.this,"Zalogowano pomyslnie");
                    } else{
                        JOptionPane.showMessageDialog(LoginScreen.this,"Niepoprawne dane logowania");
                    }

                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginScreen.this, "Wystąpił błąd podczas komunikacji z serwerem.");
                }
            }
        });

        // Add an empty label for padding between login button and password field
        JLabel emptyLabel = new JLabel("");

        JLabel registerLabel = new JLabel("Don't have an account? Register here:");
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Add your registration logic here
                // For simplicity, let's just display a message for now
                JOptionPane.showMessageDialog(LoginScreen.this, "Redirect to registration form");
            }
        });

        // Create a main container with padding
        JPanel mainContainer = new JPanel(new GridLayout(8, 1));
        mainContainer.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Add components to the main container
        mainContainer.add(titleLabel);
        mainContainer.add(emailLabel);
        mainContainer.add(emailField);
        mainContainer.add(passwordLabel);
        mainContainer.add(passwordField);
        mainContainer.add(emptyLabel);// Empty label for padding
        mainContainer.add(loginButton);
        mainContainer.add(registerLabel);

        // Set layout manager for the frame
        setLayout(new BorderLayout());

        // Add the main container to the frame
        add(mainContainer, BorderLayout.CENTER);
    }
}
