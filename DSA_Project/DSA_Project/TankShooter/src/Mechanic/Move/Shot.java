package Mechanic.Move;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Mechanic.UnmoveObject.Unmove;
import Mechanic.UnmoveObject.Water;
import UI.Direction;

public class Shot extends Move {
    private Point pos;
    private static final int SPEED = 10;
    private JLabel Bullet;
    private int direction = Direction.NO_DIRECTION;
    private Timer moveTimer;
    private boolean isDamage = false;
    private LinkedList<Unmove> Obstacle = new LinkedList<>();

    public Shot(Point position) {
        this.pos = position;
        Bullet = new JLabel();
        Bullet.setIcon(new ImageIcon(getClass().getResource("/Image/bullet.png")));
        Point newPos = new Point();
        switch (direction) {
            case Direction.EAST:
                newPos = new Point(pos.x + 40, pos.y + 30);
                break;
            case Direction.WEST:
                newPos = new Point(pos.x - 20, pos.y + 20);
                break;
            case Direction.SOUTH:
                newPos = new Point(pos.x + 20, pos.y + 60);
                break;
            case Direction.NORTH:
                newPos = new Point(pos.x + 20, pos.y - 20);
                break;
        }
        Bullet.setBounds(newPos.x, newPos.y, 40, 40);
        Bullet.setVisible(true);
    }

    @Override
    public JLabel draw() {
        // Update bullet's position in its label
        Point newPos = new Point();
        switch (direction) {
            case Direction.EAST:
                newPos = new Point(pos.x + 30, pos.y + 30);
                break;
            case Direction.WEST:
                newPos = new Point(pos.x - 20, pos.y + 30);
                break;
            case Direction.SOUTH:
                newPos = new Point(pos.x + 10, pos.y + 60);
                break;
            case Direction.NORTH:
                newPos = new Point(pos.x + 10, pos.y - 10);
                break;
        }
        Bullet.setBounds(newPos.x, newPos.y, 40, 40);
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
    }

    public boolean isBulletAtTarget(LinkedList<Unmove> obstacle, Point nextPos) {
        Iterator<Unmove> iterator = obstacle.iterator();
        while (iterator.hasNext()) {
            Unmove object = iterator.next();
            if (object instanceof Water) {
                continue;
            }
            if (nextPos.x == object.getPos().x && nextPos.y == object.getPos().y) {
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

    public void setObstacle(LinkedList<Unmove> obstacle) {
        Obstacle = obstacle;
        if (isBulletAtTarget(obstacle, pos)) {
            Bullet.setVisible(false);
        }
    }

    public JLabel getBullet() {
        return Bullet;
    }

    public boolean getisDamage() {
        return isDamage;
    }

}
