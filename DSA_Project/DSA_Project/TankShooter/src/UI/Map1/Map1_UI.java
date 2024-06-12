package UI.Map1;

import java.awt.Cursor;
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
import Mechanic.UserTank;
import UI.Direction;

public class Map1_UI extends JFrame implements Runnable {
    private final int boxWeight = 40;
    private final int boxHeight = 40;
    private final int gridWeight = 70;
    private final int gridHeight = 37;
    private Point userTank;
    private LinkedList<Shot> shots = new LinkedList<>();
    private LinkedList<Shot> Obstacle = new LinkedList<>();
    private int direction = Direction.NO_DIRECTION;
    private int currentDirection = Direction.NO_DIRECTION;
    private UserTank userTank1;
    private Point mousePoint;
    private int normalBulletCount = 12;
    private int drawInitialTank = 0;

    public Map1_UI() {
        initial();
    }

    private void initial() {
        this.setName("TankShooter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.userTank = new Point(20, 30);
        this.userTank1 = new UserTank(userTank, direction);
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
                    Point tankPos = new Point(userTank);
                    tankPos = calTankShotPos(tankPos);
                    Shot s = new Shot(mousePoint, tankPos);
                    shots.add(s);
                    normalBulletCount--;
                }
            }
        });
    }

    private void draw(Graphics g) {
        drawMap(g);
        drawUserTank(g);
        if (!shots.isEmpty()) {
            drawShots(g);
        }
        direction = Direction.NO_DIRECTION;
    }

    private void drawMap(Graphics g) {
        // Drawing map for battle
        Image wooden = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\WoodenBoxFullHealth.png")
                .getImage();
        Image stone = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\StoneBrick.png")
                .getImage();
        Image background = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\background.png")
                .getImage();
        Image water = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\water.png")
                .getImage();
        Image steelblock = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\steelBlock.png")
                .getImage();
        g.drawImage(background, 10, 100, null);
        Image Protect = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\heart.png")
                .getImage();

        /*
         * g.drawImage(stone, 10, 735, null);
         * g.drawImage(stone, 1270, 735, null);
         * g.drawImage(stone, 630, 735, null);
         */
        g.drawImage(Protect, 630, 735, null);

        g.drawImage(stone, 550, 735, null);
        g.drawImage(stone, 550, 695, null);
        g.drawImage(stone, 550, 655, null);

        g.drawImage(stone, 710, 735, null);
        g.drawImage(stone, 710, 695, null);
        g.drawImage(stone, 710, 655, null);

        g.drawImage(stone, 630, 655, null);
        g.drawImage(stone, 590, 655, null);
        g.drawImage(stone, 670, 655, null);

        /*
         * g.drawImage(wooden, 10, 100, null);
         * g.drawImage(wooden, 1270, 100, null);
         * g.drawImage(wooden, 630, 735, null);
         */

        g.drawImage(water, 10, 220, null);
        g.drawImage(water, 50, 220, null);
        g.drawImage(water, 90, 220, null);
        g.drawImage(water, 130, 220, null);
        g.drawImage(water, 170, 220, null);
        g.drawImage(water, 170, 180, null);
        g.drawImage(water, 170, 140, null);

        g.drawImage(water, 10, 615, null);
        g.drawImage(water, 50, 615, null);
        g.drawImage(water, 90, 615, null);
        g.drawImage(water, 130, 615, null);
        g.drawImage(water, 170, 615, null);
        g.drawImage(water, 170, 655, null);
        g.drawImage(water, 170, 695, null);

        g.drawImage(water, 1270, 220, null);
        g.drawImage(water, 1230, 220, null);
        g.drawImage(water, 1190, 220, null);
        g.drawImage(water, 1150, 220, null);
        g.drawImage(water, 1110, 220, null);
        g.drawImage(water, 1110, 180, null);
        g.drawImage(water, 1110, 140, null);

        g.drawImage(water, 1270, 615, null);
        g.drawImage(water, 1230, 615, null);
        g.drawImage(water, 1190, 615, null);
        g.drawImage(water, 1150, 615, null);
        g.drawImage(water, 1110, 615, null);
        g.drawImage(water, 1110, 655, null);
        g.drawImage(water, 1110, 695, null);
        for (int i = 0; i < 7; i++) {
            g.drawImage(water, 510 + (i * 40), 220, null);
        }
        for (int i = 0; i < 7; i++) {
            g.drawImage(steelblock, 20 + (i * 200), 380, null);
            g.drawImage(steelblock, 20 + (i * 200), 420, null);
            g.drawImage(steelblock, 60 + (i * 200), 380, null);
            g.drawImage(steelblock, 60 + (i * 200), 420, null);
        }

        for (int i = 0; i < 7; i++) {
            g.drawImage(water, 510 + (i * 40), 575, null);
        }

        for (int i = 0; i < 7; i++) {
            g.drawImage(wooden, 510 + (i * 40), 535, null);
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 10 + (i * 40), 575, null);
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 1110 + (i * 40), 575, null);
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 1110 + (i * 40), 260, null);
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 10 + (i * 40), 260, null);
        }

        for (int i = 0; i < 2; i++) {
            g.drawImage(wooden, 100 + (i * 200), 380, null);
            g.drawImage(wooden, 100 + (i * 200), 420, null);
            g.drawImage(wooden, 140 + (i * 200), 380, null);
            g.drawImage(wooden, 140 + (i * 200), 420, null);
        }

        for (int i = 0; i < 2; i++) {
            g.drawImage(wooden, 900 + (i * 200), 380, null);
            g.drawImage(wooden, 900 + (i * 200), 420, null);
            g.drawImage(wooden, 940 + (i * 200), 380, null);
            g.drawImage(wooden, 940 + (i * 200), 420, null);
        }

    }

    private void drawUserTank(Graphics g) {
        userTank1.setDirection(currentDirection);
        userTank1.draw(g);
        if (drawInitialTank == 0) {
            Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png").getImage();
            g.drawImage(img, userTank.x * 20, userTank.y * 20, null);
        }
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

    private void userTankMove() {
        userTank1.move();
        if (userTank1.getPosition().x < 0 || userTank1.getPosition().x >= gridWeight
                || userTank1.getPosition().y < 0 || userTank1.getPosition().y >= gridHeight) {
            direction = Direction.NO_DIRECTION;
        }
    }

    private Point calTankShotPos(Point tankPosition) {
        tankPosition.x *= 20;
        tankPosition.y *= 20;
        if (drawInitialTank == 0) {
            tankPosition.x += 35;
            tankPosition.y += 5;
        }
        switch (currentDirection) {
            case Direction.EAST:
                tankPosition.x += 35;
                tankPosition.y += 5;
                break;
            case Direction.WEST:
                tankPosition.y += 10;
                tankPosition.x -= 20;
                break;
            case Direction.SOUTH:
                tankPosition.x += 15;
                tankPosition.y += 40;
                break;
            case Direction.NORTH:
                tankPosition.x += 10;
                tankPosition.y -= 20;
                break;
        }
        return tankPosition;
    }

    private boolean canFire() {
        return normalBulletCount > 0;
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
                drawInitialTank++;
                currentDirection = direction;
                userTankMove();
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Map1_UI map1 = new Map1_UI();
        new Thread(map1).start();
    }
}