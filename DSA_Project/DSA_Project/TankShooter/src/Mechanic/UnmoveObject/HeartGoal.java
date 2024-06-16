package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HeartGoal extends Unmove {
    private Point pos;
    private int Health = 1;
    private JLabel Heart;
    private boolean isDestroy = false;

    public HeartGoal(Point point) {
        this.pos = point;
        this.Heart = new JLabel();
    }

    @Override
    public JLabel drawObject() {
        if (Health == 1) {
            Heart.setIcon(new ImageIcon(getClass().getResource("/Image/heart.png")));
        } else {
            Heart.setIcon(null);
            Heart.setVisible(false);
            this.isDestroy = true;
            return Heart;
        }
        Heart.setBounds(pos.x, pos.y, 40, 40);
        Heart.setVisible(true);
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
        return Heart;
    }
}
