package Mechanic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import UI.Direction;

public class UserTank {
    private Point position;

    public void setPosition(Point pos) {

        this.position = pos;
    }

    private int direction;

    public UserTank(Point inputPos, int d) {
        this.position = inputPos;
        this.direction = d;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    public void draw(Graphics g) {
        drawTank(g);
    }

    private void drawTank(Graphics g) {
        Image img = null;
        switch (direction) {
            case Direction.EAST:
                img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png").getImage();
                break;
            case Direction.WEST:
                img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankLeft.png").getImage();
                break;
            case Direction.SOUTH:
                img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankDown.png").getImage();
                break;
            case Direction.NORTH:
                img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankUp.png").getImage();
                break;
        }
        if (img != null) {
            g.drawImage(img, position.x, position.y, null);
        }
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
    }

    public Point getPosition() {
        return this.position;
    }
}
