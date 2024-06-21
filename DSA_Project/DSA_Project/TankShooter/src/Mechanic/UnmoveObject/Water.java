/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the water Block image.
*/
package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Water extends Unmove {
    private Point pos;
    private JLabel Water;

    public Water(Point point) {
        this.pos = point;
        this.Water = new JLabel();
    }

    @Override
    public JLabel drawObject() {
        Water.setIcon(new ImageIcon(getClass().getResource("/Image/water.png")));
        Water.setBounds(pos.x, pos.y, 40, 40);
        Water.setVisible(true);
        return Water;
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
        return Water;
    }
}
