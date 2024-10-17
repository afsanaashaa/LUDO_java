package org.example;

public class LoginRegistrationPage {

    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        javax.swing.SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
