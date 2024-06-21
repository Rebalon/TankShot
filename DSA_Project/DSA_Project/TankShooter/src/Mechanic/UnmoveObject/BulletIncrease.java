/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the BulletIncrease Block image.
*/
package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BulletIncrease extends Unmove {
    private Point pos = new Point();
    private JLabel Bullet = new JLabel();
    private int Health = 1;
    private boolean isDestroy = false;

    public BulletIncrease(Point position) {
        pos = position;
    }

    @Override
    public JLabel drawObject() {
        if (Health > 0) {
            Bullet.setIcon(new ImageIcon(getClass().getResource("/Image/BulletIncrease.png")));
            Bullet.setBounds(pos.x, pos.y, 40, 40);
            Bullet.setVisible(true);

        } else {
            this.isDestroy = true;
            Bullet.setIcon(null);
            Bullet.setVisible(false);
        }
        return Bullet;
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
        return this.Bullet;
    }
}
