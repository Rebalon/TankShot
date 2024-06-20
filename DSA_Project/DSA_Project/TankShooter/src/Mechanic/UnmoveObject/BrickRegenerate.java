package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BrickRegenerate extends Unmove {
    private Point pos = new Point();
    private JLabel clock = new JLabel();
    private int Health = 1;
    private boolean isDestroy = false;

    public BrickRegenerate(Point position) {
        pos = position;
    }

    @Override
    public JLabel drawObject() {
        if (Health > 0) {
            clock.setIcon(new ImageIcon(getClass().getResource("/Image/StoneBrickRegen.png")));
            clock.setBounds(pos.x, pos.y, 80, 40);
            clock.setVisible(true);

        } else {
            this.isDestroy = true;
            clock.setIcon(null);
            clock.setVisible(false);
        }
        return clock;
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
        return this.clock;
    }
}