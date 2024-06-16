package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WoodenBox extends Unmove {
    private boolean isDestroy = false;
    private int Health = 4;
    private Point Pos;
    private JLabel woddenbox;

    public WoodenBox(Point position) {
        this.Pos = position;
        this.woddenbox = new JLabel(); // Initialize JLabel
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    @Override
    public Point getPos() {
        return Pos;
    }

    public void setPos(Point pos) {
        Pos = pos;
    }

    @Override
    public JLabel drawObject() {
        if (Health > 2) {
            woddenbox.setIcon(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
        } else if (Health > 0) {
            woddenbox.setIcon(new ImageIcon(getClass().getResource("/Image/WoodenBoxHalf.png")));
        } else {
            woddenbox.setIcon(null);
            woddenbox.setVisible(false);
            this.isDestroy = true;
            return woddenbox;
        }
        woddenbox.setBounds(Pos.x, Pos.y, 40, 40);
        woddenbox.setVisible(true);
        return woddenbox;
    }

    public void takeDamage() {
        Health--;
    }

    @Override
    public void isDamage() {
        this.Health--;
        drawObject();
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    public JLabel getWoddenbox() {
        return woddenbox;
    }

    @Override
    public JLabel getJLabel() {
        return woddenbox;
    }
}
