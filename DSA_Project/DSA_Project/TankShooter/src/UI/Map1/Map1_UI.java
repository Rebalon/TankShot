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
    private Point userTank;
    private LinkedList<Shot> shots = new LinkedList<>();
    private LinkedList<Point> Obstacle = new LinkedList<>();
    private LinkedList<Point> canDamage = new LinkedList<>();
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
        this.userTank = new Point(10, 100);
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
            public void mouseClicked(MouseEvent e) {
                if (canFire()) {
                    mousePoint = e.getPoint();
                    Point tankPos = new Point(userTank1.getPosition());
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

        drawDamageObject(g);
        drawUserTank(g);
        if (!shots.isEmpty()) {
            drawShots(g);
        }
        direction = Direction.NO_DIRECTION;
    }

    private void drawMap(Graphics g) {
        // Drawing map for battle

        /*
         * Image background = new ImageIcon(
         * "DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\background.png")
         * .getImage();
         * Image water = new
         * ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\water.png")
         * .getImage();
         * Image steelblock = new ImageIcon(
         * "DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\steelBlock.png")
         * .getImage();
         * g.drawImage(background, 10, 100, null);
         * 
         * g.drawImage(water, 10, 220, null);
         * g.drawImage(water, 50, 220, null);
         * g.drawImage(water, 90, 220, null);
         * g.drawImage(water, 130, 220, null);
         * g.drawImage(water, 170, 220, null);
         * g.drawImage(water, 170, 180, null);
         * g.drawImage(water, 170, 140, null);
         */

        this.Obstacle.add(new Point(10, 220));
        this.Obstacle.add(new Point(50, 220));
        this.Obstacle.add(new Point(90, 220));
        this.Obstacle.add(new Point(130, 220));
        this.Obstacle.add(new Point(170, 220));
        this.Obstacle.add(new Point(170, 180));
        this.Obstacle.add(new Point(170, 140));

        /*
         * g.drawImage(water, 10, 620, null);
         * g.drawImage(water, 50, 620, null);
         * g.drawImage(water, 90, 620, null);
         * g.drawImage(water, 130, 620, null);
         * g.drawImage(water, 170, 620, null);
         * g.drawImage(water, 170, 660, null);
         * g.drawImage(water, 170, 700, null);
         */

        this.Obstacle.add(new Point(10, 620));
        this.Obstacle.add(new Point(50, 620));
        this.Obstacle.add(new Point(90, 620));
        this.Obstacle.add(new Point(130, 620));
        this.Obstacle.add(new Point(170, 620));
        this.Obstacle.add(new Point(170, 660));
        this.Obstacle.add(new Point(170, 700));
        /*
         * g.drawImage(water, 1130, 220, null);
         * g.drawImage(water, 1170, 220, null);
         * g.drawImage(water, 1210, 220, null);
         * g.drawImage(water, 1250, 220, null);
         * g.drawImage(water, 1290, 220, null);
         * g.drawImage(water, 1130, 180, null);
         * g.drawImage(water, 1130, 140, null);
         */

        this.Obstacle.add(new Point(1130, 220));
        this.Obstacle.add(new Point(1170, 220));
        this.Obstacle.add(new Point(1210, 220));
        this.Obstacle.add(new Point(1250, 220));
        this.Obstacle.add(new Point(1290, 220));
        this.Obstacle.add(new Point(1130, 180));
        this.Obstacle.add(new Point(1130, 140));

        /*
         * g.drawImage(water, 1130, 620, null);
         * g.drawImage(water, 1170, 620, null);
         * g.drawImage(water, 1210, 620, null);
         * g.drawImage(water, 1250, 620, null);
         * g.drawImage(water, 1290, 620, null);
         * g.drawImage(water, 1130, 660, null);
         * g.drawImage(water, 1130, 700, null);
         */

        this.Obstacle.add(new Point(1130, 620));
        this.Obstacle.add(new Point(1170, 620));
        this.Obstacle.add(new Point(1210, 620));
        this.Obstacle.add(new Point(1250, 620));
        this.Obstacle.add(new Point(1290, 620));
        this.Obstacle.add(new Point(1130, 660));
        this.Obstacle.add(new Point(1130, 700));

        for (int i = 0; i < 7; i++) {
            /* g.drawImage(water, 490 + (i * 40), 220, null); */
            this.Obstacle.add(new Point(490 + (i * 40), 220));
        }
        for (int i = 0; i < 7; i++) {
            /*
             * g.drawImage(steelblock, 10 + (i * 200), 380, null);
             * g.drawImage(steelblock, 10 + (i * 200), 420, null);
             * g.drawImage(steelblock, 50 + (i * 200), 380, null);
             * g.drawImage(steelblock, 50 + (i * 200), 420, null);
             */
            this.Obstacle.add(new Point(10 + (i * 200), 380));
            this.Obstacle.add(new Point(10 + (i * 200), 420));
            this.Obstacle.add(new Point(50 + (i * 200), 380));
            this.Obstacle.add(new Point(50 + (i * 200), 420));
        }

        for (int i = 0; i < 7; i++) {
            /* g.drawImage(water, 490 + (i * 40), 580, null); */
            this.Obstacle.add(new Point(490 + (i * 40), 580));
        }
    }

    private void check(Graphics g) {
        for (Point p : Obstacle) {
            g.drawRect(p.x, p.y, 40, 40);
        }
    }

    private void drawDamageObject(Graphics g) {
        // Drawing map for battle
        Image background = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\background.png")
                .getImage();
        g.drawImage(background, 10, 100, null);
        Image wooden = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\WoodenBoxFullHealth.png")
                .getImage();
        Image stone = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\StoneBrick.png")
                .getImage();
        Image Protect = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\heart.png")
                .getImage();

        /*
         * g.drawImage(stone, 10, 735, null);
         * g.drawImage(stone, 1270, 735, null);
         * g.drawImage(stone, 630, 735, null);
         */
        g.drawImage(Protect, 630, 735, null);
        this.canDamage.add(new Point(630, 735));
        g.drawImage(stone, 550, 735, null);
        g.drawImage(stone, 550, 695, null);
        g.drawImage(stone, 550, 655, null);

        this.canDamage.add(new Point(550, 735));
        this.canDamage.add(new Point(550, 695));
        this.canDamage.add(new Point(550, 655));

        g.drawImage(stone, 710, 735, null);
        g.drawImage(stone, 710, 695, null);
        g.drawImage(stone, 710, 655, null);

        this.canDamage.add(new Point(710, 735));
        this.canDamage.add(new Point(710, 695));
        this.canDamage.add(new Point(710, 655));

        g.drawImage(stone, 630, 655, null);
        g.drawImage(stone, 590, 655, null);
        g.drawImage(stone, 670, 655, null);

        this.canDamage.add(new Point(630, 655));
        this.canDamage.add(new Point(590, 655));
        this.canDamage.add(new Point(670, 655));

        for (int i = 0; i < 7; i++) {
            g.drawImage(wooden, 490 + (i * 40), 535, null);
            this.canDamage.add(new Point(490 + (i * 40), 535));
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 10 + (i * 40), 575, null);
            this.canDamage.add(new Point(10 + (i * 40), 575));
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 1110 + (i * 40), 575, null);
            this.canDamage.add(new Point(1110 + (i * 40), 575));
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 1110 + (i * 40), 260, null);
            this.canDamage.add(new Point(1110 + (i * 40), 260));
        }

        for (int i = 0; i < 5; i++) {
            g.drawImage(wooden, 10 + (i * 40), 260, null);
            this.canDamage.add(new Point(10 + (i * 40), 260));
        }

        for (int i = 0; i < 2; i++) {
            g.drawImage(wooden, 90 + (i * 200), 380, null);
            g.drawImage(wooden, 90 + (i * 200), 420, null);
            g.drawImage(wooden, 130 + (i * 200), 380, null);
            g.drawImage(wooden, 130 + (i * 200), 420, null);
            this.canDamage.add(new Point(90 + (i * 200), 380));
            this.canDamage.add(new Point(90 + (i * 200), 420));
            this.canDamage.add(new Point(130 + (i * 200), 380));
            this.canDamage.add(new Point(130 + (i * 200), 420));
        }

        for (int i = 0; i < 2; i++) {
            g.drawImage(wooden, 890 + (i * 200), 380, null);
            g.drawImage(wooden, 890 + (i * 200), 420, null);
            g.drawImage(wooden, 930 + (i * 200), 380, null);
            g.drawImage(wooden, 930 + (i * 200), 420, null);
            this.canDamage.add(new Point(890 + (i * 200), 380));
            this.canDamage.add(new Point(890 + (i * 200), 420));
            this.canDamage.add(new Point(930 + (i * 200), 380));
            this.canDamage.add(new Point(930 + (i * 200), 420));
        }

    }

    private void drawUserTank(Graphics g) {
        userTank1.setDirection(currentDirection);
        userTank1.draw(g);
        if (drawInitialTank == 0) {
            Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\tankRight.png").getImage();
            g.drawImage(img, userTank.x, userTank.y, null);
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
        userTank1.setDirection(currentDirection);
        Point previous = new Point(userTank1.getPosition());
        userTank1.move();
        Point pos = new Point(userTank1.getPosition());
        for (Point p : Obstacle) {
            if (pos.x == p.x && pos.y == p.y) {
                userTank1.setPosition(previous);
                break;
            }
        }
    }

    private Point calTankShotPos(Point tankPosition) {
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
                repaint();
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