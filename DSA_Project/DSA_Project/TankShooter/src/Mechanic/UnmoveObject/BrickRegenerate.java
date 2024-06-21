/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the BrickRegenerate Block image.
*/
package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BrickRegenerate extends Unmove {
    private Point pos = new Point();
    private JLabel Brick = new JLabel();
    private int Health = 1;
    private boolean isDestroy = false;

    public BrickRegenerate(Point position) {
        pos = position;
    }

    @Override
    public JLabel drawObject() {
        if (Health > 0) {
            Brick.setIcon(new ImageIcon(getClass().getResource("/Image/StoneBrickRegen.png")));
            Brick.setBounds(pos.x, pos.y, 80, 40);
            Brick.setVisible(true);

        } else {
            this.isDestroy = true;
            Brick.setIcon(null);
            Brick.setVisible(false);
        }
        return Brick;
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
        return this.Brick;
    }
}
