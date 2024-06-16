package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SteelBlock extends Unmove {
    private Point pos;
    private JLabel SteelBlock;

    public SteelBlock(Point point) {
        this.pos = point;
        this.SteelBlock = new JLabel();
    }

    @Override
    public JLabel drawObject() {
        SteelBlock.setIcon(new ImageIcon(getClass().getResource("/Image/steelBlock.png")));
        SteelBlock.setBounds(pos.x, pos.y, 40, 40);
        SteelBlock.setVisible(true);
        return SteelBlock;
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
        return SteelBlock;
    }
}
