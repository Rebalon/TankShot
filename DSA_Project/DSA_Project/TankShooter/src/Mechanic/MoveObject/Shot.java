/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the bullet image and manage the bullet movement as well as whether the bullet hit an objects.
*/
package Mechanic.MoveObject;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Mechanic.UnmoveObject.BrickRegenerate;
import Mechanic.UnmoveObject.BulletIncrease;
import Mechanic.UnmoveObject.Clock;
import Mechanic.UnmoveObject.HeartUp;
import Mechanic.UnmoveObject.Unmove;
import Mechanic.UnmoveObject.Water;
import UI.Direction;

public class Shot extends Move {
    private Point pos;
    private int SPEED = 10;
    private JLabel Bullet;
    private int direction = Direction.NO_DIRECTION;
    private boolean isDamage = false;
    private LinkedList<Unmove> Obstacle = new LinkedList<>();
    private int isitself = 0;

    public Shot(Point position, int direc) {
        this.pos = position;
        this.direction = direc;
        Bullet = new JLabel();
        Bullet.setIcon(new ImageIcon(getClass().getResource("/Image/bullet.png")));
        switch (direction) {
            case Direction.EAST:
                pos = new Point(pos.x + 30, pos.y);
                break;
            case Direction.WEST:
                pos = new Point(pos.x - 30, pos.y);
                break;
            case Direction.SOUTH:
                pos = new Point(pos.x, pos.y + 25);
                break;
            case Direction.NORTH:
                pos = new Point(pos.x, pos.y - 25);
                break;
        }
        // Center the icon inside the label
        Bullet.setHorizontalAlignment(SwingConstants.CENTER);
        Bullet.setVerticalAlignment(SwingConstants.CENTER);
        // Bullet.setBorder(new LineBorder(Color.red, 5));
        Bullet.setVisible(true);
    }

    @Override
    public JLabel draw() {
        return Bullet;
    }

    @Override
    public void move() {
        // Move bullet in the set direction
        switch (direction) {
            case Direction.EAST:
                pos.translate(SPEED, 0);
                break;
            case Direction.WEST:
                pos.translate(-SPEED, 0);
                break;
            case Direction.SOUTH:
                pos.translate(0, SPEED);
                break;
            case Direction.NORTH:
                pos.translate(0, -SPEED);
                break;
        }
        Bullet.setBounds(pos.x, pos.y, 40, 40);
    }

    public boolean isBulletAtTarget(LinkedList<Unmove> obstacle, Point nextPos, LinkedList<Move> tank,
            LinkedList<Point> boss, Boss currentBoss) {
        if (boss != null && currentBoss != null) {
            Iterator<Point> iterator = boss.iterator();
            while (iterator.hasNext()) {
                Point object = iterator.next();
                if (nextPos.equals(object)) {
                    isDamage = true;
                    currentBoss.isDamage();
                    break;
                }
            }
        }
        Iterator<Move> iterator1 = tank.iterator();
        while (iterator1.hasNext()) {
            Move object = iterator1.next();
            if (nextPos.x == object.getPos().x && nextPos.y == object.getPos().y) {
                isDamage = true;
                object.isDamage();
                break;
            }
        }
        Iterator<Unmove> iterator = obstacle.iterator();
        while (iterator.hasNext()) {
            Unmove object = iterator.next();
            // System.out.println(object.getPos());
            if (object instanceof Water || object instanceof Clock || object instanceof HeartUp
                    || object instanceof BulletIncrease || object instanceof BrickRegenerate) {
                continue;
            } else if (nextPos.x == object.getPos().x && nextPos.y == object.getPos().y) {
                // if (object.isDestroy()) {
                // continue;
                // }
                isDamage = true;
                object.isDamage();
                break;
            }
        }
        return isDamage;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setObstacle(LinkedList<Unmove> obstacle, LinkedList<Move> tank, LinkedList<Point> boss,
            Boss currentBoss) {
        Obstacle = obstacle;
        Point newPos = null;
        switch (direction) {
            case Direction.EAST:
                newPos = new Point(pos.x - 30 + 40 - SPEED, pos.y);
                break;
            case Direction.WEST:
                newPos = new Point(pos.x + 30 - 40 + SPEED, pos.y);
                break;
            case Direction.SOUTH:
                newPos = new Point(pos.x, pos.y - 25 + 40 - SPEED);
                break;
            case Direction.NORTH:
                newPos = new Point(pos.x, pos.y + 25 - 40 + SPEED);
                break;
        }

        if (isBulletAtTarget(obstacle, newPos, tank, boss, currentBoss)) {
            // Bullet.setVisible(!isDamage);

        }
    }

    @Override
    public JLabel getImage() {
        return Bullet;
    }

    public void setImage(JLabel image) {
        this.Bullet = image;
    }

    public boolean getisDamage() {
        return isDamage;
    }

    @Override
    public void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank,
            LinkedList<Point> boss) {
        return;
    }

    @Override
    public void isDamage() {
        return;
    }

    @Override
    public boolean isDestroy() {
        return false;
    }

    public void setSPEED(int sPEED) {
        SPEED = sPEED;
    }
}
