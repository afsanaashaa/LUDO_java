package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame implements ActionListener {
    private Container container;
    private JLabel userLabel, passwordLabel, messageLabel, backgroundLabel, headlineLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public LoginForm() {
        // Set frame properties
        setTitle("Login Form");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        // Set the background image using the absolute path
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\loginForm\\src\\main\\java\\org\\example\\img.png");
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 600);
        container.add(backgroundLabel);

        // Headline
        headlineLabel = new JLabel("LOGIN TO PLAY LUDO");
        headlineLabel.setBounds(100, 30, 400, 30);
        headlineLabel.setForeground(Color.WHITE);
        headlineLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        container.add(headlineLabel);

        // Create a panel to hold the form components
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 100, 400, 300);
        panel.setOpaque(false);

        // Username label and field
        userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 30, 100, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(130, 30, 220, 30);
        panel.add(userTextField);

        // Password label and field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 80, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 80, 220, 30);
        panel.add(passwordField);

        // Message label to show errors or success messages
        messageLabel = new JLabel();
        messageLabel.setBounds(20, 180, 330, 30);
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(messageLabel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(20, 130, 150, 40);
        customizeButton(loginButton, new Color(70, 130, 180), Color.WHITE);
        panel.add(loginButton);

        // Back button to return to the main page
        backButton = new JButton("Back");
        backButton.setBounds(200, 130, 150, 40);
        customizeButton(backButton, new Color(60, 179, 113), Color.WHITE);
        panel.add(backButton);

        // Adding action listeners
        loginButton.addActionListener(this);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginRegistrationPage(); // Go back to the main page
                dispose(); // Close the login form
            }
        });

        // Add the panel on top of the background
        container.add(panel);
        container.setComponentZOrder(backgroundLabel, container.getComponentCount() - 1);
        container.setComponentZOrder(panel, 0);

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\IdeaProjects\\mergingLudo\\src\\main\\java\\logo.png");
        this.setIconImage(icon);

        // Set frame visibility
        setVisible(true);
    }

    // Method to customize button appearance
    private void customizeButton(JButton button, Color background, Color foreground) {
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    messageLabel.setForeground(new Color(34, 139, 34)); // Forest Green
                    messageLabel.setText("Login successful!");
                    // Proceed to the next page
                    LudoInterface ludoInterface = new LudoInterface();
                    ludoInterface.setVisible(true);
                    dispose();
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Invalid username or password!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setText("Database error!");
            }
        }
    }
}
