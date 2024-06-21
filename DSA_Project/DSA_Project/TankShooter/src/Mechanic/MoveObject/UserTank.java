package Mechanic.MoveObject;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Mechanic.UnmoveObject.BrickRegenerate;
import Mechanic.UnmoveObject.BulletIncrease;
import Mechanic.UnmoveObject.Clock;
import Mechanic.UnmoveObject.HeartUp;
import Mechanic.UnmoveObject.Unmove;
import UI.Direction;

public class UserTank extends Move {
    private Point position;
    private JLabel TankImage = new JLabel();

    private int direction = Direction.EAST;
    private int Health = 1;

    private boolean isDestroy = false;
    private int currentNumOfBullet = 1;
    private final int Max_NumBullet = 3;
    private final int Max_Health = 3;

    public UserTank(Point inputPos) {
        this.position = inputPos;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    @Override
    public JLabel draw() {
        /* Image img = null; */
        if (Health > 0) {
            switch (direction) {
                case Direction.EAST:
                    TankImage.setIcon(new ImageIcon(getClass().getResource("/Image/tankRight.png")));
                    break;
                case Direction.WEST:
                    TankImage.setIcon(new ImageIcon(getClass().getResource("/Image/tankLeft.png")));
                    break;
                case Direction.SOUTH:
                    TankImage.setIcon(new ImageIcon(getClass().getResource("/Image/tankDown.png")));
                    break;
                case Direction.NORTH:
                    TankImage.setIcon(new ImageIcon(getClass().getResource("/Image/tankUp.png")));
                    break;
            }
            TankImage.setBounds(position.x, position.y, 40, 40);
            TankImage.setVisible(true);
        } else {
            this.isDestroy = true;
            TankImage.setVisible(false);
        }
        return TankImage;
    }

    @Override
    public void move() {
        switch (direction) {
            case Direction.EAST:
                position.translate(40, 0);
                break;
            case Direction.WEST:
                position.translate(-40, 0);
                break;
            case Direction.SOUTH:
                position.translate(0, 40);
                break;
            case Direction.NORTH:
                position.translate(0, -40);
                break;
        }
        /* System.out.println(position); */
        draw();
    }

    @Override
    public void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank,
            LinkedList<Point> boss) {
        for (Unmove p : obstacle) {
            if (newPos.x == p.getPos().x && newPos.y == p.getPos().y) {
                if (p instanceof HeartUp) {
                    this.Health++;
                    p.isDamage();
                    break;
                } else if (p instanceof BulletIncrease) {
                    this.currentNumOfBullet++;
                    p.isDamage();
                    break;
                } else if (p instanceof Clock) {
                    p.isDamage();
                    break;
                } else if (p instanceof BrickRegenerate) {
                    p.isDamage();
                    break;
                } else {
                    this.setPosition(previous);
                    draw();
                    break;
                }
            }
        }
        for (Move p : tank) {
            if (newPos.x == p.getPos().x && newPos.y == p.getPos().y) {
                this.setPosition(previous);
                draw();
                break;
            }
        }
    }

    public void setPosition(Point pos) {
        this.position = pos;
    }

    @Override
    public Point getPos() {
        return this.position;
    }

    @Override
    public void isDamage() {
        Health--;
        draw();
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    public JLabel getImage() {
        return TankImage;
    }

    public int getCurrentNumOfBullet() {
        return currentNumOfBullet;
    }

    public boolean isAtMaxHealth() {
        return Health >= Max_Health;
    }

    public boolean isMaxBullet() {
        return currentNumOfBullet >= Max_NumBullet;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int h) {
        this.Health = h;
    }
}
