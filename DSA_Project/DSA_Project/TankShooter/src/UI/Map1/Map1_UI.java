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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import Mechanic.Move.Shot;
import Mechanic.Move.UserTank;
import Mechanic.UnmoveObject.BrickBlock;
import Mechanic.UnmoveObject.HeartGoal;
import Mechanic.UnmoveObject.SteelBlock;
import Mechanic.UnmoveObject.Unmove;
import Mechanic.UnmoveObject.Water;
import Mechanic.UnmoveObject.WoodenBox;
import Mechanic.UnmoveObject.invisibleObstacle;
import UI.Direction;

public class Map1_UI extends JFrame implements Runnable {
    private Point userTank;
    private int direction = Direction.NO_DIRECTION;
    private int currentDirection = Direction.EAST;
    private UserTank userTank1;
    private Point mousePoint;
    private int drawInitialTank = 0;
    private JLabel jlabel1 = new JLabel();
    private LinkedList<Unmove> damageBlock = new LinkedList<>();
    private LinkedList<Shot> shots = new LinkedList<>();
    private int currentBullet = 0;
    private int MaxBulletCount = 12;
    private Timer reloadTimer;
    private Timer gameTimer;

    public Map1_UI() {
        initial();
    }

    private void initial() {
        this.setName("TankShooter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
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
                    case KeyEvent.VK_SPACE:
                        if (canFire()) {
                            Point tankPos = new Point(userTank1.getPos());
                            Shot shot = new Shot(tankPos);
                            shot.setDirection(currentDirection);
                            shots.add(shot);
                            getContentPane().add(shot.draw());
                            // Ensure the bullet appears on top
                            setComponentZOrder(shot.draw(), 0);
                            currentBullet++;
                        } else {
                            reloadBullet();
                        }
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
        gameTimer = new Timer(16, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
        gameTimer.start();
        /*
         * this.addMouseListener(new MouseAdapter() {
         * 
         * @Override
         * public void mouseClicked(MouseEvent e) {
         * 
         * }
         * });
         */
    }

    private void draw(Graphics g) {
        drawUserTank();
        if (!shots.isEmpty()) {

        }
        direction = Direction.NO_DIRECTION;
    }

    private void drawMap() {
        // Drawing map for battle
        for (int i = 0; i < 33; i++) {
            Point invisiblePoint = new Point(i * 40, 30);
            invisibleObstacle in = new invisibleObstacle(invisiblePoint);
            this.damageBlock.add(in);
            this.add(in.drawObject());
        }
        for (int i = 0; i < 33; i++) {
            Point invisiblePoint = new Point(i * 40, 750);
            invisibleObstacle in = new invisibleObstacle(invisiblePoint);
            this.damageBlock.add(in);
            this.add(in.drawObject());
        }
        for (int i = 0; i < 17; i++) {
            Point invisiblePoint = new Point(-40, 70 + i * 40);
            invisibleObstacle in = new invisibleObstacle(invisiblePoint);
            this.damageBlock.add(in);
            this.add(in.drawObject());
        }
        for (int i = 0; i < 17; i++) {
            Point invisiblePoint = new Point(1320, 70 + i * 40);
            invisibleObstacle in = new invisibleObstacle(invisiblePoint);
            this.damageBlock.add(in);
            this.add(in.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point waterPoint = new Point(i * 40, 190);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point waterPoint = new Point(160, 150 - (i * 40));
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point waterPoint = new Point(i * 40, 590);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point waterPoint = new Point(160, 630 + (i * 40));
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point waterPoint = new Point(1120 + i * 40, 190);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point waterPoint = new Point(1120, 150 - (i * 40));
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point waterPoint = new Point(1120 + i * 40, 590);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point waterPoint = new Point(1120, 630 + (i * 40));
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }

        for (int i = 0; i < 7; i++) {
            Point waterPoint = new Point(480 + (i * 40), 190);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
        for (int i = 0; i < 7; i++) {
            Point Steel = new Point(0 + (i * 200), 350);
            Point Steel1 = new Point(0 + (i * 200), 390);
            Point Steel2 = new Point(40 + (i * 200), 350);
            Point Steel3 = new Point(40 + (i * 200), 390);
            SteelBlock ste = new SteelBlock(Steel);
            SteelBlock ste1 = new SteelBlock(Steel1);
            SteelBlock ste2 = new SteelBlock(Steel2);
            SteelBlock ste3 = new SteelBlock(Steel3);
            this.damageBlock.add(ste);
            this.damageBlock.add(ste1);
            this.damageBlock.add(ste2);
            this.damageBlock.add(ste3);
            this.add(ste.drawObject());
            this.add(ste1.drawObject());
            this.add(ste2.drawObject());
            this.add(ste3.drawObject());
        }

        for (int i = 0; i < 7; i++) {
            Point waterPoint = new Point(480 + (i * 40), 550);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
    }

    private void drawDamageObject() {
        this.userTank = new Point(0, 70);
        this.userTank1 = new UserTank(userTank);
        this.add(userTank1.draw());
        // set linklist for damage object
        for (int i = 0; i < 3; i++) {
            Point BrickBlockPoint = new Point(560 + i * 40, 630);
            BrickBlock brk = new BrickBlock(BrickBlockPoint);

            damageBlock.add(brk);
            this.add(brk.drawObject());
        }
        for (int i = 0; i < 3; i++) {
            Point BrickBlockPoint = new Point(520, 630 + (i * 40));
            BrickBlock brk = new BrickBlock(BrickBlockPoint);

            damageBlock.add(brk);
            this.add(brk.drawObject());
        }
        for (int i = 0; i < 3; i++) {
            Point BrickBlockPoint = new Point(680, 630 + (i * 40));
            BrickBlock brk = new BrickBlock(BrickBlockPoint);

            damageBlock.add(brk);
            this.add(brk.drawObject());
        }

        Point Heart = new Point(600, 710);
        HeartGoal heart = new HeartGoal(Heart);

        damageBlock.add(heart);
        this.add(heart.drawObject());

        for (int i = 0; i < 5; i++) {
            Point woddenboxPoint = new Point(i * 40, 230);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            this.add(wb.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point woddenboxPoint = new Point(i * 40, 550);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            this.add(wb.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point woddenboxPoint = new Point(1120 + i * 40, 230);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            this.add(wb.drawObject());
        }
        for (int i = 0; i < 5; i++) {
            Point woddenboxPoint = new Point(1120 + i * 40, 550);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            this.add(wb.drawObject());
        }
        for (int i = 0; i < 7; i++) {
            Point woddenboxPoint = new Point(480 + (i * 40), 510);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            this.add(wb.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point woddenboxPoint = new Point(80 + (i * 200), 350);
            Point woddenboxPoint1 = new Point(80 + (i * 200), 390);
            Point woddenboxPoint2 = new Point(120 + (i * 200), 350);
            Point woddenboxPoint3 = new Point(120 + (i * 200), 390);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            WoodenBox wb1 = new WoodenBox(woddenboxPoint1);
            WoodenBox wb2 = new WoodenBox(woddenboxPoint2);
            WoodenBox wb3 = new WoodenBox(woddenboxPoint3);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            damageBlock.add(wb1);
            damageBlock.add(wb2);
            damageBlock.add(wb3);
            this.add(wb.drawObject());
            this.add(wb1.drawObject());
            this.add(wb2.drawObject());
            this.add(wb3.drawObject());
        }
        for (int i = 0; i < 2; i++) {
            Point woddenboxPoint = new Point(880 + (i * 200), 350);
            Point woddenboxPoint1 = new Point(880 + (i * 200), 390);
            Point woddenboxPoint2 = new Point(920 + (i * 200), 350);
            Point woddenboxPoint3 = new Point(920 + (i * 200), 390);
            WoodenBox wb = new WoodenBox(woddenboxPoint);
            WoodenBox wb1 = new WoodenBox(woddenboxPoint1);
            WoodenBox wb2 = new WoodenBox(woddenboxPoint2);
            WoodenBox wb3 = new WoodenBox(woddenboxPoint3);
            // Wooden class no need jlabel as pass is a referrence
            damageBlock.add(wb);
            damageBlock.add(wb1);
            damageBlock.add(wb2);
            damageBlock.add(wb3);
            this.add(wb.drawObject());
            this.add(wb1.drawObject());
            this.add(wb2.drawObject());
            this.add(wb3.drawObject());
        }
        jlabel1.setIcon(new ImageIcon(
                getClass().getResource("/Image/background.png")));
        jlabel1.setBounds(0, 70, 1320, 750);
        jlabel1.setVisible(true);
        getContentPane().add(jlabel1);
    }

    private void drawUserTank() {
        userTank1.setDirection(currentDirection);
    }

    private void updateGame() {
        // Update all bullets
        Iterator<Shot> iterator = shots.iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            shot.move();
            shot.setObstacle(damageBlock);
            if (shot.getisDamage()) {
                this.remove(shot.getBullet());
                iterator.remove();
            }
            shot.draw();
        }
        // Update all obstacle
        Iterator<Unmove> iterator2 = damageBlock.iterator();
        while (iterator2.hasNext()) {
            Unmove unm = iterator2.next();
            if (unm.isDestroy()) {
                this.remove(unm.getJLabel());
                iterator2.remove();
            }
        }
        repaint();
    }

    private void userTankMove() {
        userTank1.setDirection(currentDirection);
        Point previous = new Point(userTank1.getPos());
        userTank1.move();
        Point pos = new Point(userTank1.getPos());
        userTank1.isCollision(damageBlock, previous, pos);
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
                currentBullet = 0; // Reset bullet count
                reloadTimer.stop(); // Stop the timer after reloading
            }
        });

        reloadTimer.setRepeats(false);
        reloadTimer.start();

        System.out.println("Reloading started, will take 3 seconds...");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        while (true) {
            if (direction != Direction.NO_DIRECTION) {
                drawInitialTank++;
                currentDirection = direction;
                userTankMove();
                revalidate();
                repaint();
                direction = Direction.NO_DIRECTION;
            }
            /*
             * if (!shots.isEmpty()) {
             * isCollision();
             * }
             */
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