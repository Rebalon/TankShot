package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HeartUp extends Unmove {
    private Point pos = new Point();
    private JLabel Heart = new JLabel();
    private int Health = 1;
    private boolean isDestroy = false;

    public HeartUp(Point position) {
        pos = position;
    }

    @Override
    public JLabel drawObject() {
        if (Health > 0) {
            Heart.setIcon(new ImageIcon(getClass().getResource("/Image/HeartImageIncrease.png")));
            Heart.setBounds(pos.x, pos.y, 40, 40);
            Heart.setVisible(true);

        } else {
            this.isDestroy = true;
            Heart.setIcon(null);
            Heart.setVisible(false);
        }
        return Heart;
    }

    @Override
    public void isDamage() {
        this.Health--;
        drawObject();
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    public JLabel getJLabel() {
        return this.Heart;
    }
}
