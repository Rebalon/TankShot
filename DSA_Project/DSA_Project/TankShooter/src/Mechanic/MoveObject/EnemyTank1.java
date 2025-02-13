/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the enemy tank image and manage the enemy tank health and movement.
*/
package Mechanic.MoveObject;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Mechanic.UnmoveObject.Unmove;
import UI.Direction;

public class EnemyTank1 extends Move {
    private Point position;
    private JLabel tankImage;
    private int health = 1;
    private int isitself = 0;
    private boolean isDestroy = false;

    public EnemyTank1(Point inputPos) {
        this.position = inputPos;
        this.tankImage = new JLabel();
        draw(); // Initial draw call to set up the JLabel
    }

    @Override
    public JLabel draw() {
        if (health == 1) {
            tankImage.setIcon(new ImageIcon(getClass().getResource("/Image/enemyTank_1.png")));
            tankImage.setBounds(position.x, position.y, 40, 40);
            tankImage.setVisible(true);
        } else {
            tankImage.setIcon(null);
            tankImage.setBounds(position.x, position.y, 40, 40);
            tankImage.setVisible(false);
            this.isDestroy = true;
        }
        return tankImage;
    }

    @Override
    public void move() {
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;

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

    @Override
    public void isDamage() {
        health--;
        draw();
    }

    @Override
    public Point getPos() {
        return this.position;
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