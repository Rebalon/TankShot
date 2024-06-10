package UI;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Example extends JPanel {
    private ImageIcon gifIcon;
    private Point p;
    private int TankWidth;
    private int TankHeight;

    public Example() {
        gifIcon = new ImageIcon("src\\Image\\shot.gif");
        p = new Point(0, 0); // Example starting position
        TankWidth = 50; // Example width
        TankHeight = 50; // Example height
        Timer timer = new Timer(100, e -> repaint()); // Adjust delay as needed
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gifIcon.paintIcon(this, g, p.x * TankWidth, p.y * TankHeight);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new Example());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
