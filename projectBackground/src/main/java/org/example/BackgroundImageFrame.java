package org.example;

import javax.swing.*;
import java.awt.*;

public class BackgroundImageFrame extends JFrame {
    public BackgroundImageFrame() {
        // Set the frame title
        setTitle("Frame with Background Image and Icon");

        // Set the frame icon
        setIconImage(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\projectBackground\\src\\img.png").getImage());

        // Create a JPanel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\projectBackground\\src\\img_1.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the layout and add the panel to the frame
        setLayout(new BorderLayout());
        add(panel);

        // Set frame size and default close operation
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BackgroundImageFrame frame = new BackgroundImageFrame();
            frame.setVisible(true);
        });
    }
}
