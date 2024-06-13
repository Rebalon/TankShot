package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Mechanic.Shot;

public class TankShoterCanvas extends JFrame implements Runnable {
    private final int boxWeight = 20;
    private final int boxHeight = 20;
    private final static int TankWeight = 20;
    private final static int TankHeight = 20;
    private final int gridWeight = 70;
    private final int gridHeight = 37;
    private int normalBulletCount = 12;
    private LinkedList<Point> tank;
    private Point item;
    private int direction = Direction.NO_DIRECTION;
    private int Currentdirection = Direction.NO_DIRECTION;
    private Point mousePoint;
    private LinkedList<Shot> shots = new LinkedList<>();
    private int drawTank = 0;

    public TankShoterCanvas() {
        initial();
    }

    private void initial() {
        this.setName("TankShoter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(gridWeight * boxWeight, gridHeight * boxHeight));
        this.tank = new LinkedList<>();
        this.item = new Point(10, 10);
        this.tank.add(new Point(10, 100));

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        direction = Direction.NORTH;
                        break;
                    case KeyEvent.VK_S:
                        direction = Direction.SOUTH;
                        break;
                    case KeyEvent.VK_A:
                        direction = Direction.WEST;
                        break;
                    case KeyEvent.VK_D:
                        direction = Direction.EAST;
                        break;
                }
            }
        });

        this.setFocusable(true);

        Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\target.png").getImage();
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(16, 16), "targetCursor");
        setCursor(customCursor);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });

        this.pack();
        this.setVisible(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (canFire()) {
                    mousePoint = e.getPoint();
                    Point tankPos = new Point(tank.peekFirst()); // Create a new Point object if not it will pass the
                                                                 // tank point into new class and modify it
                    tankPos = calTankShotPos(tankPos);
                    Shot s = new Shot(mousePoint, tankPos);
                    shots.add(s);
                    normalBulletCount--;
                }
            }
        });
    }

    private Point calTankShotPos(Point tankPosition) {
        if (drawTank == 0) {
            tankPosition.x *= TankWeight;
            tankPosition.y *= TankHeight;
            tankPosition.x += 50;
            tankPosition.y += 20;
        }
        switch (Currentdirection) {
            case Direction.EAST:
                tankPosition.x *= TankWeight;
                tankPosition.y *= TankHeight;
                tankPosition.x += 50;
                tankPosition.y += 20;
                break;
            case Direction.WEST:
                tankPosition.x *= TankWeight;
                tankPosition.y *= TankHeight;
                tankPosition.y += 20;
                break;
            case Direction.SOUTH:
                tankPosition.x *= TankWeight;
                tankPosition.y *= TankHeight;
                tankPosition.x += 20;
                tankPosition.y += 40;
                break;
            case Direction.NORTH:
                tankPosition.x *= TankWeight;
                tankPosition.y *= TankHeight;
                tankPosition.x += 20;
                break;
        }
        return tankPosition;
    }

    private void drawShots(Graphics g) {
        LinkedList<Shot> shotsToRemove = new LinkedList<>();
        for (Shot shot : shots) {
            shot.draw(g);
            shot.Movement();

            if (shot.isBulletAtTarget()) {
                shotsToRemove.add(shot);
            }

        }
        shots.removeAll(shotsToRemove);
    }

    private void draw(Graphics g) {
        drawGrid(g);
        drawTank(g);
        drawItem(g);
        if (!shots.isEmpty()) {
            drawShots(g);
        }
        direction = Direction.NO_DIRECTION;
    }

    private void drawGrid(Graphics g) {
        /*
         * g.drawRect(0, 0, gridWeight * boxWeight, gridHeight * boxHeight);
         * for (int i = boxWeight; i < gridWeight * boxWeight; i += boxWeight) {
         * g.drawLine(i, 0, i, gridHeight * boxHeight);
         * }
         * for (int i = boxHeight; i < gridHeight * boxHeight; i += boxHeight) {
         * g.drawLine(0, i, gridWeight * boxWeight, i);
         * }
         */
    }

    // draw enemy tank
    private void drawEnemy(Graphics g) {

    }

    private void drawTank(Graphics g) {
        for (Point p : tank) {
            switch (direction) {
                case Direction.EAST:
                    Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png")
                            .getImage();
                    g.drawImage(img, p.x, p.y, null);
                    Currentdirection = Direction.EAST;
                    drawTank++;
                    break;
                case Direction.WEST:
                    Image img3 = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankLeft.png")
                            .getImage();
                    g.drawImage(img3, p.x, p.y, null);
                    Currentdirection = Direction.WEST;
                    drawTank++;
                    break;
                case Direction.SOUTH:
                    Image img1 = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankDown.png")
                            .getImage();
                    g.drawImage(img1, p.x, p.y, null);
                    Currentdirection = Direction.SOUTH;
                    drawTank++;
                    break;
                case Direction.NORTH:
                    Image img2 = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankUp.png")
                            .getImage();
                    g.drawImage(img2, p.x, p.y, null);
                    Currentdirection = Direction.NORTH;
                    drawTank++;
                    break;
                case Direction.NO_DIRECTION:
                    switch (Currentdirection) {
                        case Direction.EAST:
                            Image imag = new ImageIcon(
                                    "DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png").getImage();
                            g.drawImage(imag, p.x, p.y, null);
                            Currentdirection = Direction.EAST;
                            break;
                        case Direction.WEST:
                            Image imag3 = new ImageIcon(
                                    "DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankLeft.png").getImage();
                            g.drawImage(imag3, p.x, p.y, null);
                            Currentdirection = Direction.WEST;
                            break;
                        case Direction.SOUTH:
                            Image imag1 = new ImageIcon(
                                    "DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankDown.png").getImage();
                            g.drawImage(imag1, p.x, p.y, null);
                            Currentdirection = Direction.SOUTH;
                            break;
                        case Direction.NORTH:
                            Image imag2 = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankUp.png")
                                    .getImage();
                            g.drawImage(imag2, p.x, p.y, null);
                            Currentdirection = Direction.NORTH;
                            break;
                    }
            }
            if (drawTank == 0) {
                Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png")
                        .getImage();
                g.drawImage(img, p.x * TankWeight, p.y * TankHeight, null);
            }
        }
    }

    private void drawItem(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(item.x * boxWeight, item.y * boxHeight, boxWeight, boxHeight);
    }

    private boolean canFire() {
        return normalBulletCount > 0;
    }

    private void move() {
        Point head = tank.peekFirst();
        Point newPoint = new Point(head); // Create a new Point object

        switch (direction) {
            case Direction.EAST:
                newPoint = new Point(head.x + 1, head.y);
                break;
            case Direction.WEST:
                newPoint = new Point(head.x - 1, head.y);
                break;
            case Direction.SOUTH:
                newPoint = new Point(head.x, head.y + 1);
                break;
            case Direction.NORTH:
                newPoint = new Point(head.x, head.y - 1);
                break;
        }

        if (newPoint.equals(item)) {
            tank.addFirst(newPoint);
            item = new Point((int) (Math.random() * gridWeight), (int) (Math.random() * gridHeight));
        } else if (newPoint.x < 0 || newPoint.x >= gridWeight || newPoint.y < 0 || newPoint.y >= gridHeight
                || tank.contains(newPoint)) {
            direction = Direction.NO_DIRECTION;
        } else {
            tank.removeLast();
            tank.addFirst(newPoint);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    @Override
    public void run() {
        while (true) {
            if (direction != Direction.NO_DIRECTION) {
                move();
                repaint();
            }
            if (!shots.isEmpty()) {
                repaint();
            } else
                repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TankShoterCanvas gameCanvas = new TankShoterCanvas();
        new Thread(gameCanvas).start();
    }
}
