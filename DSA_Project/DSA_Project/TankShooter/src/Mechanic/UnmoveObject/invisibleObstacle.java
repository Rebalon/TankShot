package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.JLabel;

public class invisibleObstacle extends Unmove {
    private Point pos;
    private JLabel invisible;

    public invisibleObstacle(Point point) {
        this.pos = point;
        this.invisible = new JLabel();
    }

    @Override
    public JLabel drawObject() {
        invisible.setIcon(null);
        invisible.setBounds(pos.x, pos.y, 40, 40);
        invisible.setVisible(true);
        return invisible;
    }

    @Override
    public void isDamage() {
        return;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public boolean isDestroy() {
        return false;
    }

    @Override
    public JLabel getJLabel() {
        return invisible;
    }
}
