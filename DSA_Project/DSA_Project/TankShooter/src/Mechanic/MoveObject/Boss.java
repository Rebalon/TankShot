/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the boss image and manage the boss health.
*/
package Mechanic.MoveObject;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Mechanic.UnmoveObject.Unmove;

public class Boss extends Move {
    private Point position;
    private LinkedList<Point> pos = new LinkedList<>();
    private JLabel tankImage;
    private int health = 100;

    public void setHealth(int health) {
        this.health = health;
    }

    private int isitself = 0;
    private boolean isDestroy = false;

    public Boss(Point inputPos) {
        this.position = inputPos;
        pos.add(inputPos);
        this.tankImage = new JLabel();
        draw(); // Initial draw call to set up the JLabel

    }

    @Override
    public JLabel draw() {
        if (health > 0) {
            tankImage.setIcon(new ImageIcon(getClass().getResource("/Image/boss.png")));
            tankImage.setBounds(position.x, position.y, 700, 280);
            tankImage.setVisible(true);
        } else {
            tankImage.setIcon(null);
            tankImage.setBounds(position.x, position.y, 700, 320);
            tankImage.setVisible(false);
            this.isDestroy = true;
        }
        return tankImage;
    }

    @Override
    public void move() {
    }

    @Override
    public void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank,
            LinkedList<Point> boss) {
    }

    public void setPosition(Point pos) {
        this.position = pos;
    }

    public void isDamage() {
        health--;
        draw();
    }

    @Override
    public Point getPos() {
        return this.position;
    }

    public Point getPosition() {
        return position;
    }

    public JLabel getTankImage() {
        return tankImage;
    }

    public int getHealth() {
        return health;
    }

    public int getIsitself() {
        return isitself;
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    public JLabel getImage() {
        return tankImage;
    }
}