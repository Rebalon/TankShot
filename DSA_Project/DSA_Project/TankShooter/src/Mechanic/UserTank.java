package Mechanic;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import UI.Direction;

public class UserTank {
    private Point position;
    private JLabel pos;

    public void setPosition(Point pos) {

        this.position = pos;
    }

    private int direction;

    public UserTank(Point inputPos, int d, JLabel jlabel2) {
        this.position = inputPos;
        this.direction = d;
        this.pos = jlabel2;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    public void draw() {
        drawTank();
    }

    private void drawTank() {
        /* Image img = null; */
        switch (direction) {
            case Direction.EAST:
                pos.setIcon(new ImageIcon(
                        getClass().getResource("/Image/tankRight.png")));
                /*
                 * img = new
                 * ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png")
                 * .getImage();
                 */
                break;
            case Direction.WEST:
                pos.setIcon(new ImageIcon(
                        getClass().getResource("/Image/tankLeft.png")));
                /*
                 * img = new
                 * ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankLeft.png").
                 * getImage();
                 */
                break;
            case Direction.SOUTH:
                pos.setIcon(new ImageIcon(
                        getClass().getResource("/Image/tankDown.png")));
                /*
                 * img = new
                 * ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankDown.png").
                 * getImage();
                 */
                break;
            case Direction.NORTH:
                pos.setIcon(new ImageIcon(
                        getClass().getResource("/Image/tankUp.png")));
                /*
                 * img = new
                 * ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankUp.png").
                 * getImage();
                 */
                break;
        }
        /*
         * if (img != null) {
         * g.drawImage(img, position.x, position.y, null);
         * }
         */
        pos.setBounds(position.x, position.y, 40, 40);
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

    public void isCollision(LinkedList<Point> obstacle, Point previous, Point newPos) {
        for (Point p : obstacle) {
            if (newPos.x == p.x && newPos.y == p.y) {
                this.setPosition(previous);
                break;
            }
        }
    }
}
