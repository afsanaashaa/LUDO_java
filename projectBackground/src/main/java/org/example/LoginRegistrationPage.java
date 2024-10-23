package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegistrationPage extends JFrame implements ActionListener {
    private JButton loginButton, registerButton;
    private JLabel backgroundLabel;

    public LoginRegistrationPage() {
        // Set frame properties
        setTitle("Login or Register");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Container container = getContentPane();
        container.setLayout(null);

        // Set the background image using the absolute path
        ImageIcon backgroundIcon = new ImageIcon("D:\\LUDO_java\\projectBackground\\src\\main\\java\\org\\example\\img_4.png");
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 600);
        container.add(backgroundLabel);

        JLabel headlineLabel = new JLabel("LOGIN OR REGISTER TO PLAY LUDO", JLabel.CENTER);
        headlineLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // Set font and size
        headlineLabel.setForeground(Color.WHITE); // Set text color
        headlineLabel.setBounds(0, 50, 800, 50); // Set position and size of the headline
        container.add(headlineLabel);

        // Create a panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(250, 250, 300, 150); // Adjusted panel size to accommodate larger buttons
        panel.setOpaque(false);

        // Login button (increased height)
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 10, 200, 50); // Increased height to 50
        customizeButton(loginButton, new Color(70, 130, 180), Color.WHITE);
        panel.add(loginButton);

        // Register button (increased height)
        registerButton = new JButton("Register");
        registerButton.setBounds(50, 70, 200, 50); // Increased height to 50
        customizeButton(registerButton, new Color(60, 179, 113), Color.WHITE);
        panel.add(registerButton);

        // Adding action listeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Add panel on top of the background
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
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Increased font size for visibility
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // Action handler
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Open the login form
            new LoginForm();
            dispose();
        } else if (e.getSource() == registerButton) {
            // Open the registration form
            new RegistrationForm();
            dispose();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new LoginRegistrationPage();
    }
}
