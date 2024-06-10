package Mechanic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Shot {
    private Point mousePoint;
    private double posX;
    private double posY;
    private double dx;
    private double dy;
    private static final double SPEED = 20.0; // Speed factor
    private static final double THRESHOLD = 10.0; // Threshold distance to consider as hit

    public Shot(Point mousePos, Point tPoint) {
        this.mousePoint = mousePos;
        this.posX = tPoint.getX();
        this.posY = tPoint.getY();

        // Calculate the direction vector (dx, dy)

        double distance = Math.sqrt(Math.pow(mousePoint.getX() - posX, 2) +
                Math.pow(mousePoint.getY() - posY, 2));
        dx = ((mousePoint.getX() - posX) / distance) * SPEED;
        dy = ((mousePoint.getY() - posY) / distance) * SPEED;
    }

    public void draw(Graphics g) {
        drawBullet(g);
    }

    private void drawBullet(Graphics g) {
        Image img = new ImageIcon("src\\Image\\bullet.png").getImage();
        g.drawImage(img, (int) posX, (int) posY, null);
    }

    public void Movement() {
        if (!isBulletAtTarget()) {
            posX += dx;
            posY += dy;
        }
    }

    public boolean isBulletAtTarget() {
        double distance = Math
                .sqrt(Math.pow(mousePoint.getX() - posX, 2) + Math.pow(mousePoint.getY() - posY, 2));
        return distance < THRESHOLD;
    }

    public boolean isCollision() {
        double distance = Math.sqrt(Math.pow(mousePoint.getX() - posX, 2) + Math.pow(mousePoint.getY() - posY, 2));
        return true;
    }
}
