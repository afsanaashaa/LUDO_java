package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrationForm extends JFrame implements ActionListener {
    // Components for the registration form
    private Container container;
    private JLabel userLabel, passwordLabel, confirmPasswordLabel, messageLabel, backgroundLabel, headlineLabel;
    private JTextField userTextField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, backButton;

    // Constructor to set up the registration form
    public RegistrationForm() {
        // Set frame properties
        setTitle("Registration Form");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        // Set the background image using the absolute path
        ImageIcon backgroundIcon = new ImageIcon("D:\\LUDO_java\\projectBackground\\src\\main\\java\\org\\example\\img.png");

        // Verify if the image was loaded successfully
        if (backgroundIcon.getIconWidth() > 0) {
            backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, 800, 600);
            container.add(backgroundLabel);
        } else {
            // If image not found, set a default background color
            container.setBackground(Color.LIGHT_GRAY);
            System.err.println("Background image not found. Using default background color.");
        }

        // Add headline label
        headlineLabel = new JLabel("REGISTRATION FORM");
        headlineLabel.setBounds(40, 30, 500, 30);
        headlineLabel.setForeground(Color.WHITE);
        headlineLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        container.add(headlineLabel);

        // Create a panel to hold the form components on top of the background
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

        // Confirm password label and field
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(20, 130, 150, 30);
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 130, 180, 30);
        panel.add(confirmPasswordField);

        // Message label to show errors or success messages
        messageLabel = new JLabel();
        messageLabel.setBounds(20, 230, 330, 30);
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(messageLabel);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(20, 180, 150, 40);
        customizeButton(registerButton, new Color(60, 179, 113), Color.WHITE);
        panel.add(registerButton);

        // Back button to return to the login form
        backButton = new JButton("Login");
        backButton.setBounds(200, 180, 150, 40);
        customizeButton(backButton, new Color(70, 130, 180), Color.WHITE);
        panel.add(backButton);

        // Adding action listeners
        registerButton.addActionListener(this);
        backButton.addActionListener(this);

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

    // Action handler
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = userTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                messageLabel.setText("Passwords do not match!");
            } else {
                // Save username and password to the database
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    int result = preparedStatement.executeUpdate();

                    if (result > 0) {
                        messageLabel.setForeground(new Color(34, 139, 34)); // Forest Green
                        messageLabel.setText("Registration successful!");
                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Registration failed!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    messageLabel.setText("Database error!");
                }
            }
        }

        // Back button action - return to login form
        if (e.getSource() == backButton) {
            new LoginForm();
            dispose(); // Close the registration form
        }
    }
}
