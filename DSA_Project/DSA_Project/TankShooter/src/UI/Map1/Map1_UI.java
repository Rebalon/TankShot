package UI.Map1;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import Mechanic.Shot;
import Mechanic.UserTank;
import Mechanic.WoodenBox;
import UI.Direction;

public class Map1_UI extends JFrame implements Runnable {
    private Point userTank;
    private LinkedList<Point> Obstacle = new LinkedList<>();
    private LinkedList<Point> canDamage = new LinkedList<>();
    private int direction = Direction.NO_DIRECTION;
    private int currentDirection = Direction.NO_DIRECTION;
    private UserTank userTank1;
    private Point mousePoint;
    private int drawInitialTank = 0;
    private JLabel jlabel1 = new JLabel();
    private JLabel jlabel2 = new JLabel();
    private JLabel jlabel3 = new JLabel();
    private JLabel jlabel4 = new JLabel();
    private JLabel jlabel5 = new JLabel();
    private JLabel jlabel6 = new JLabel();
    private JLabel jlabel7 = new JLabel();
    private JLabel jlabel8 = new JLabel();
    private JLabel jlabel9 = new JLabel();
    private JLabel jlabel10 = new JLabel();
    private JLabel jlabel11 = new JLabel();
    private JLabel jlabel12 = new JLabel();
    private JLabel jlabel13 = new JLabel();
    private JLabel jlabel14 = new JLabel();
    private JLabel jlabel15 = new JLabel();
    private JLabel jlabel16 = new JLabel();
    private JLabel jlabel17 = new JLabel();
    private JLabel jlabel18 = new JLabel();
    private JLabel jlabel19 = new JLabel();
    private JLabel jlabel20 = new JLabel();
    private JLabel jlabel21 = new JLabel();
    private JLabel jlabel22 = new JLabel();
    private JLabel jlabel23 = new JLabel();
    private JLabel jlabel24 = new JLabel();
    private JLabel jlabel25 = new JLabel();
    private JLabel jlabel26 = new JLabel();
    private JLabel jlabel27 = new JLabel();
    private JLabel jlabel28 = new JLabel();
    private JLabel jlabel29 = new JLabel();
    private JLabel jlabel30 = new JLabel();
    private LinkedList<WoodenBox> Wooden = new LinkedList<>();
    private LinkedList<Shot> shots = new LinkedList<>();
    private LinkedList<JLabel> Shots = new LinkedList<>();
    private int currentBullet = 0;
    private int MaxBulletCount = 12;
    private Timer reloadTimer;

    public Map1_UI() {
        initial();
    }

    private void initial() {
        this.setName("TankShooter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.userTank = new Point(0, 70);
        this.userTank1 = new UserTank(userTank, direction, jlabel2);
        drawMap();
        drawDamageObject();

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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (canFire()) {
                    mousePoint = e.getPoint();
                    Point tankPos = new Point(userTank1.getPosition());
                    tankPos = calTankShotPos(tankPos);
                    Shot s = new Shot(mousePoint, tankPos, Shots.get(currentBullet));
                    Shots.get(currentBullet).setVisible(true);
                    shots.add(s);
                    currentBullet++;
                } else {
                    reloadBullet();
                }
            }
        });
    }

    private void draw(Graphics g) {
        drawUserTank();
        if (!shots.isEmpty()) {
            drawShots();
        }
        direction = Direction.NO_DIRECTION;
    }

    private void drawMap() {
        // Drawing map for battle

        this.Obstacle.add(new Point(0, 190));
        this.Obstacle.add(new Point(40, 190));
        this.Obstacle.add(new Point(80, 190));
        this.Obstacle.add(new Point(120, 190));
        this.Obstacle.add(new Point(160, 190));
        this.Obstacle.add(new Point(160, 150));
        this.Obstacle.add(new Point(160, 110));

        this.Obstacle.add(new Point(0, 590));
        this.Obstacle.add(new Point(40, 590));
        this.Obstacle.add(new Point(80, 590));
        this.Obstacle.add(new Point(120, 590));
        this.Obstacle.add(new Point(160, 590));
        this.Obstacle.add(new Point(160, 630));
        this.Obstacle.add(new Point(160, 670));

        this.Obstacle.add(new Point(1120, 190));
        this.Obstacle.add(new Point(1160, 190));
        this.Obstacle.add(new Point(1200, 190));
        this.Obstacle.add(new Point(1240, 190));
        this.Obstacle.add(new Point(1280, 190));
        this.Obstacle.add(new Point(1120, 150));
        this.Obstacle.add(new Point(1120, 110));

        this.Obstacle.add(new Point(1120, 590));
        this.Obstacle.add(new Point(1160, 590));
        this.Obstacle.add(new Point(1200, 590));
        this.Obstacle.add(new Point(1240, 590));
        this.Obstacle.add(new Point(1280, 590));
        this.Obstacle.add(new Point(1120, 630));
        this.Obstacle.add(new Point(1120, 670));

        for (int i = 0; i < 7; i++) {
            this.Obstacle.add(new Point(480 + (i * 40), 190));
        }
        for (int i = 0; i < 7; i++) {
            this.Obstacle.add(new Point(0 + (i * 200), 350));
            this.Obstacle.add(new Point(0 + (i * 200), 390));
            this.Obstacle.add(new Point(40 + (i * 200), 350));
            this.Obstacle.add(new Point(40 + (i * 200), 390));
        }

        for (int i = 0; i < 7; i++) {
            this.Obstacle.add(new Point(480 + (i * 40), 550));
        }
    }

    private void drawDamageObject() {
        initialShot();
        if (drawInitialTank == 0) {
            jlabel2.setIcon(new ImageIcon(
                    getClass().getResource("/Image/tankRight.png")));
            jlabel2.setBounds(0, 70, 40, 40);
            getContentPane().add(jlabel2);
        }
        // set linklist for damage object
        for (int i = 0; i < 5; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(i * 40, 230);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);
            this.add(woddenbox);
        }
        for (int i = 0; i < 5; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(i * 40, 550);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);

            this.add(woddenbox);
        }
        for (int i = 0; i < 5; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(1120 + i * 40, 230);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);

            this.add(woddenbox);
        }
        for (int i = 0; i < 5; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(1120 + i * 40, 550);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);

            this.add(woddenbox);
        }
        for (int i = 0; i < 7; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(480 + (i * 40), 510);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);

            this.add(woddenbox);
        }
        for (int i = 0; i < 2; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox1 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox2 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox3 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(80 + (i * 200), 350);
            Point woddenboxPoint1 = new Point(80 + (i * 200), 390);
            Point woddenboxPoint2 = new Point(120 + (i * 200), 350);
            Point woddenboxPoint3 = new Point(120 + (i * 200), 390);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox1.setBounds(woddenboxPoint1.x, woddenboxPoint1.y, 40, 40);
            woddenbox2.setBounds(woddenboxPoint2.x, woddenboxPoint2.y, 40, 40);
            woddenbox3.setBounds(woddenboxPoint3.x, woddenboxPoint3.y, 40, 40);
            woddenbox.setVisible(true);
            woddenbox1.setVisible(true);
            woddenbox2.setVisible(true);
            woddenbox3.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            WoodenBox wb1 = new WoodenBox(woddenboxPoint1, woddenbox1);
            WoodenBox wb2 = new WoodenBox(woddenboxPoint2, woddenbox2);
            WoodenBox wb3 = new WoodenBox(woddenboxPoint3, woddenbox3);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);
            Wooden.add(wb1);
            Wooden.add(wb2);
            Wooden.add(wb3);
            this.add(woddenbox);
            this.add(woddenbox1);
            this.add(woddenbox2);
            this.add(woddenbox3);
        }
        for (int i = 0; i < 2; i++) {
            JLabel woddenbox = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox1 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox2 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            JLabel woddenbox3 = new JLabel(new ImageIcon(getClass().getResource("/Image/WoodenBoxFullHealth.png")));
            Point woddenboxPoint = new Point(880 + (i * 200), 350);
            Point woddenboxPoint1 = new Point(880 + (i * 200), 390);
            Point woddenboxPoint2 = new Point(920 + (i * 200), 350);
            Point woddenboxPoint3 = new Point(920 + (i * 200), 390);
            woddenbox.setBounds(woddenboxPoint.x, woddenboxPoint.y, 40, 40);
            woddenbox1.setBounds(woddenboxPoint1.x, woddenboxPoint1.y, 40, 40);
            woddenbox2.setBounds(woddenboxPoint2.x, woddenboxPoint2.y, 40, 40);
            woddenbox3.setBounds(woddenboxPoint3.x, woddenboxPoint3.y, 40, 40);
            woddenbox.setVisible(true);
            woddenbox1.setVisible(true);
            woddenbox2.setVisible(true);
            woddenbox3.setVisible(true);
            WoodenBox wb = new WoodenBox(woddenboxPoint, woddenbox);
            WoodenBox wb1 = new WoodenBox(woddenboxPoint1, woddenbox1);
            WoodenBox wb2 = new WoodenBox(woddenboxPoint2, woddenbox2);
            WoodenBox wb3 = new WoodenBox(woddenboxPoint3, woddenbox3);
            // Wooden class no need jlabel as pass is a referrence
            Wooden.add(wb);
            Wooden.add(wb1);
            Wooden.add(wb2);
            Wooden.add(wb3);
            this.add(woddenbox);
            this.add(woddenbox1);
            this.add(woddenbox2);
            this.add(woddenbox3);
        }
        jlabel1.setIcon(new ImageIcon(
                getClass().getResource("/Image/background.png")));
        jlabel1.setBounds(0, 70, 1305, 678);
        jlabel1.setVisible(true);
        getContentPane().add(jlabel1);
    }

    private void drawUserTank() {
        userTank1.setDirection(currentDirection);
        userTank1.draw();
    }

    private void initialShot() {
        // Create a pool of JLabels for bullets
        for (int i = 0; i < MaxBulletCount; i++) {
            JLabel bullet = new JLabel(new ImageIcon(getClass().getResource("/Image/bullet.png")));
            bullet.setSize(40, 40);
            bullet.setVisible(false);
            bullet.setLocation(-50, -50);
            Shots.add(bullet);
            add(bullet);
        }
    }

    private void drawShots() {
        Iterator<Shot> iterator = shots.iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            shot.move();
            shot.draw();
            if (shot.isBulletAtTarget()) {
                iterator.remove();
            }
        }
    }

    private void userTankMove() {
        userTank1.setDirection(currentDirection);
        Point previous = new Point(userTank1.getPosition());
        userTank1.move();
        Point pos = new Point(userTank1.getPosition());
        userTank1.isCollision(Obstacle, previous, pos);
        for (WoodenBox p : Wooden) {
            if (pos.x == p.getPos().x && pos.y == p.getPos().y) {
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
        return currentBullet < MaxBulletCount;
    }

    private void reloadBullet() {
        if (reloadTimer != null && reloadTimer.isRunning()) {
            reloadTimer.stop();
        }

        reloadTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset bullet states
                for (JLabel bullet : Shots) {
                    bullet.setVisible(false); // Hide the bullet
                    bullet.setLocation(-50, -50); // Move the bullet off-screen
                }
                currentBullet = 0; // Reset bullet count
                reloadTimer.stop(); // Stop the timer after reloading
            }
        });

        reloadTimer.setRepeats(false);
        reloadTimer.start();

        System.out.println("Reloading started, will take 5 seconds...");
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