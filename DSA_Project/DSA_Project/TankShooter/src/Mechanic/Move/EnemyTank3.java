package Mechanic.Move;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Mechanic.UnmoveObject.Unmove;
import UI.Direction;

public class EnemyTank3 extends Move {
    private Point position;
    private JLabel TankImage = new JLabel();
    private int direction = Direction.EAST;
    private int Health = 6;
    private boolean isDestroy = false;
    private int isitself = 0;

    public EnemyTank3(Point inputPos) {
        this.position = inputPos;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    public JLabel draw() {
        if (Health > 0) {
            TankImage.setIcon(new ImageIcon(getClass().getResource("/Image/enemyTank_3.png")));
            TankImage.setBounds(position.x, position.y, 40, 40);
            TankImage.setVisible(true);
        } else {
            TankImage.setIcon(null);
            TankImage.setBounds(position.x, position.y, 40, 40);
            TankImage.setVisible(false);
            this.isDestroy = true;
        }
        return TankImage;
    }

    @Override
    public void move() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
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
        draw();
    }

    @Override
    public void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank,
            LinkedList<Point> boss) {
        for (Point p : boss) {
            if (newPos.equals(p)) {
                this.setPosition(previous);
                draw();
                break;
            }
        }
        for (Unmove p : obstacle) {
            if (newPos.x == p.getPos().x && newPos.y == p.getPos().y) {
                this.setPosition(previous);
                draw();
                break;
            }
        }
        for (Move p : tank) {
            if (p.getPos().equals(this.getPos()) && isitself == 0) {
                isitself++;
                continue;
            }
            if (newPos.x == p.getPos().x && newPos.y == p.getPos().y) {
                this.setPosition(previous);
                draw();
                break;
            }
        }
        isitself = 0;
    }

    public void setPosition(Point pos) {
        this.position = pos;
    }

    public void isDamage() {
        Health--;
        draw();
    }

    @Override
    public Point getPos() {
        return this.position;
    }

    @Override
    public boolean isDestroy() {
        return this.isDestroy;
    }

    @Override
    public JLabel getImage() {
        return TankImage;
    }
}
