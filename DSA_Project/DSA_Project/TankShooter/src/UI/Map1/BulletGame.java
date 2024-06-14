package UI.Map1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BulletGame extends JFrame {
    private List<JLabel> bullets = new ArrayList<>();
    private int currentBullet = 0;
    private final int maxBullets = 12;
    private Timer bulletTimer;

    public BulletGame() {
        setTitle("Bullet Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create a pool of JLabels for bullets
        for (int i = 0; i < maxBullets; i++) {
            JLabel bullet = new JLabel(new ImageIcon(getClass().getResource("/Image/bullet.png")));
            bullet.setSize(40, 40);
            bullet.setVisible(false);
            bullets.add(bullet);
            add(bullet);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shootBullet(e.getX(), e.getY());
            }
        });

        setVisible(true);
    }

    private void shootBullet(int startX, int startY) {
        if (currentBullet >= maxBullets) {
            System.out.println("Out of bullets, reload needed!");
            reloadBullets();
            return;
        }

        JLabel bullet = bullets.get(currentBullet);
        bullet.setLocation(startX, startY);
        bullet.setVisible(true);
        currentBullet++;

        bulletTimer = new Timer(50, new ActionListener() {
            int x = startX;
            int y = startY;

            @Override
            public void actionPerformed(ActionEvent e) {
                x += 10; // Adjust this value for movement speed
                bullet.setLocation(x, y);

                if (x > getWidth()) {
                    bullet.setVisible(false);
                    ((Timer) e.getSource()).stop();
                }

                // Example of collision detection
                // Replace this with your actual collision logic
                if (collisionDetected(bullet)) {
                    bullet.setVisible(false);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        bulletTimer.start();
    }

    private boolean collisionDetected(JLabel bullet) {
        // Add logic to detect collision with targets
        // Return true if collision is detected
        return false;
    }

    private void reloadBullets() {
        currentBullet = 0;
        System.out.println("Bullets reloaded!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BulletGame());
    }
}
