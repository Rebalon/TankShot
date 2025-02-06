package UI.GamePage;

import javax.swing.*;

public class test extends JFrame {
    public test() {
        // Setting up the JFrame
        setTitle("Map 1 Defence");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use layout as needed

        // Add components to the panel (like a JLabel, JButton, etc.)
        JLabel label = new JLabel("Hello, world!");
        label.setBounds(50, 50, 100, 30); // Set position and size
        panel.add(label);

        // Add the panel to the JFrame
        add(panel);

        // Make the JFrame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the window
        new test();
    }
}
