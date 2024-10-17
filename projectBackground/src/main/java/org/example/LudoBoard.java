package org.example;

import javax.swing.*;
import java.awt.*;

public class LudoBoard extends JPanel {

    private Image backgroundImage;
    public LudoBoard() {
        // Create the JFrame for the Ludo board here in the constructor
        //backgroundImage = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\mergingLudo\\src\\main\\java\\img.png").getImage();

        JFrame jframe = new JFrame();
        jframe.setBounds(10, 10, 1000, 900);
        jframe.setLocationRelativeTo(null);
        jframe.setBackground(Color.white);
        jframe.setTitle("LUDO");
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\IdeaProjects\\mergingLudo\\src\\main\\java\\logo.png");
        jframe.setIconImage(icon);

        // Add the LudoBoard panel (this) to the JFrame
        jframe.add(this);

        // Create and set up the GameMoves component
        GameMoves gm = new GameMoves();
        gm.setFocusable(true);
        gm.addKeyListener(gm);
        gm.addMouseListener(gm);
        jframe.add(gm);

        // Make the JFrame visible
        jframe.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //if (backgroundImage != null) {
         //   g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
       // }
        // Your custom painting logic here (if needed)
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100); // Example of drawing on the Ludo board
    }
}
