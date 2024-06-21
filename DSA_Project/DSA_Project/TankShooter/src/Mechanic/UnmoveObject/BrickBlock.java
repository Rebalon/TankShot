/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the Brick Block image and manage the block health.
*/
package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BrickBlock extends Unmove {
    private Point pos;
    private int Health = 10;
    private JLabel BrickBlock;
    private boolean isDestroy = false;

    public BrickBlock(Point point) {
        this.pos = point;
        this.BrickBlock = new JLabel();
    }

    @Override
    public JLabel drawObject() {

        if (Health > 5) {
            this.isDestroy = false;
            BrickBlock.setIcon(new ImageIcon(getClass().getResource("/Image/StoneBrick.png")));
        } else if (Health > 0) {
            this.isDestroy = false;
            BrickBlock.setIcon(new ImageIcon(getClass().getResource("/Image/halfStone.png")));
        } else {
            BrickBlock.setIcon(null);
            BrickBlock.setVisible(false);
            this.isDestroy = true;
            return BrickBlock;
        }
        BrickBlock.setBounds(pos.x, pos.y, 40, 40);
        BrickBlock.setVisible(true);
        return BrickBlock;
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

    public int getHealth() {
        return Health;
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    public JLabel getJLabel() {
        return BrickBlock;
    }

    public void setHealth(int health) {
        BrickBlock.setVisible(true);
        Health = health;
    }

    public void setDestroy(boolean isDestroy) {
        this.isDestroy = isDestroy;
    }

}
