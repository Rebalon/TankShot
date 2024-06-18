package Mechanic.Move;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Mechanic.UnmoveObject.Unmove;
import UI.Direction;

public class UserTank extends Move {
    private Point position;
    private JLabel TankImage = new JLabel();

    private int direction = Direction.EAST;
    private int Health = 1;
    private boolean isDestroy = false;

    public UserTank(Point inputPos) {
        this.position = inputPos;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

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
    public void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank) {
        for (Unmove p : obstacle) {
            if (newPos.x == p.getPos().x && newPos.y == p.getPos().y) {
                this.setPosition(previous);
                draw();
                break;
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
}
