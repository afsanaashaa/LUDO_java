package org.example;

import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LudoInterface extends JFrame {

    private JProgressBar progressBar;

    public LudoInterface() {
        // Create the main panel to hold everything
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\mergingLudo\\src\\main\\java\\background1.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Create a sub-panel for alignment
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        subPanel.setOpaque(false);
        subPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subPanel.setBorder(BorderFactory.createEmptyBorder(100, 150, 50, 50)); // Adjust the position (Move down)

        // Create and customize the progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0); // Set initial progress value to 0
        progressBar.setStringPainted(true); // Display the progress percentage
        progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Title label for "LUDO"
        JLabel ludoLabel = new JLabel("LUDO");
        ludoLabel.setForeground(Color.RED); // Change color to red
        ludoLabel.setFont(new Font("Verdana", Font.BOLD, 48)); // Change font to Verdana
        ludoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Title label for "MULTIPLAYER"
        JLabel multiplayerLabel = new JLabel("MULTIPLAYER");
        multiplayerLabel.setForeground(Color.YELLOW); // Change color to yellow
        multiplayerLabel.setFont(new Font("Verdana", Font.BOLD, 36)); // Change font to Verdana
        multiplayerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Buttons for game selection
        JButton classicLudoButton = new JButton("CLASSIC LUDO");
        JButton snakeLadderButton = new JButton("SNAKE LADDER LUDO");

        // Customize buttons appearance
        classicLudoButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        snakeLadderButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Change button sizes, font sizes, and colors
        Dimension buttonSize = new Dimension(350, 60); // Increase the size of the buttons
        Font buttonFont = new Font("Verdana", Font.BOLD, 24); // Increase font size to 24

        classicLudoButton.setPreferredSize(buttonSize);
        classicLudoButton.setMaximumSize(buttonSize);
        classicLudoButton.setFont(buttonFont);
        classicLudoButton.setBackground(Color.BLUE);
        classicLudoButton.setForeground(Color.WHITE);
        classicLudoButton.setFocusable(false);

        snakeLadderButton.setPreferredSize(buttonSize);
        snakeLadderButton.setMaximumSize(buttonSize);
        snakeLadderButton.setFont(buttonFont);
        snakeLadderButton.setBackground(Color.GREEN);
        snakeLadderButton.setForeground(Color.WHITE);
        snakeLadderButton.setFocusable(false);

        // Add action listeners to buttons
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Activate the progress bar when a button is clicked
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(10); // Simulate a task by sleeping for a short time
                            progressBar.setValue(i); // Update the progress bar
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Close the current window (LudoInterface)
                        LudoInterface.this.dispose();

                        // Check which button was clicked
                        if (e.getSource() == classicLudoButton) {
                            // Instantiate the Classic Ludo game logic here
                            new LudoBoard();  // Assuming LudoBoard is a Swing-based class
                        } else if (e.getSource() == snakeLadderButton) {
                            // Launch the JavaFX DiceRoll class properly
                            new Thread(() -> {
                                Application.launch(org.example.DiceRoll.class);
                            }).start();
                        }
                    }


                };
                worker.execute();
            }
        };

        classicLudoButton.addActionListener(buttonListener);
        snakeLadderButton.addActionListener(buttonListener);

        // Add components to the sub-panel with adjusted spacing
        subPanel.add(progressBar); // Add the progress bar at the top
        subPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Increase space after the progress bar
        subPanel.add(ludoLabel);
        subPanel.add(multiplayerLabel);
        subPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Increase space between labels and buttons
        subPanel.add(classicLudoButton);
        subPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Increase space between buttons
        subPanel.add(snakeLadderButton);

        // Add the sub-panel to the main panel
        mainPanel.add(subPanel);

        // Add the main panel to this frame's content pane
        this.getContentPane().add(mainPanel);

        // Set the icon of the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\IdeaProjects\\mergingLudo\\src\\main\\java\\logo.png");
        this.setIconImage(icon);

        // Set up frame properties with increased size
        this.setTitle("Ludo Game Interface");
        this.setSize(1200, 1000); // Increase the frame size
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
