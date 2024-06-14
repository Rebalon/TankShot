package Mechanic;

import java.awt.Point;

import javax.swing.JLabel;

public class Shot {
    private Point mousePoint;
    private double posX;
    private double posY;
    private double dx;
    private double dy;
    private static final double SPEED = 20.0;
    private static final double THRESHOLD = 10.0;
    private JLabel img;

    public Shot(Point mousePos, Point tPoint, JLabel i) {
        this.mousePoint = mousePos;
        this.posX = tPoint.getX();
        this.posY = tPoint.getY();
        this.img = i;
        double distance = Math.sqrt(Math.pow(mousePoint.getX() - posX, 2) + Math.pow(mousePoint.getY() - posY, 2));
        dx = ((mousePoint.getX() - posX) / distance) * SPEED;
        dy = ((mousePoint.getY() - posY) / distance) * SPEED;
    }

    public void draw() {
        img.setBounds((int) posX, (int) posY, 40, 40);
        if (isBulletAtTarget()) {
            img.setVisible(false);
        } else {
            img.setVisible(true);
        }
    }

    public void move() {
        if (!isBulletAtTarget()) {
            posX += dx;
            posY += dy;
        }
    }

    public boolean isBulletAtTarget() {
        double distance = Math.sqrt(Math.pow(mousePoint.getX() - posX, 2) + Math.pow(mousePoint.getY() - posY, 2));
        return distance < THRESHOLD;
    }

    public Point getMousePoint() {
        return mousePoint;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
}
