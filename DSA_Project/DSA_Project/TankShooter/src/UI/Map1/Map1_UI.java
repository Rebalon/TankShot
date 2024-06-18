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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import Mechanic.Move.EnemyTank1;
import Mechanic.Move.EnemyTank2;
import Mechanic.Move.EnemyTank3;
import Mechanic.Move.Move;
import Mechanic.Move.Shot;
import Mechanic.Move.UserTank;
import Mechanic.UnmoveObject.BrickBlock;
import Mechanic.UnmoveObject.BulletIncrease;
import Mechanic.UnmoveObject.Clock;
import Mechanic.UnmoveObject.HeartGoal;
import Mechanic.UnmoveObject.HeartUp;
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
    private JLabel jlabel1 = new JLabel();
    private LinkedList<Unmove> damageBlock = new LinkedList<>();
    private LinkedList<Move> EnemyTank = new LinkedList<>();
    private LinkedList<Move> ControlTank = new LinkedList<>();
    private LinkedList<Shot> shots = new LinkedList<>();
    private LinkedList<Shot> EnemyShot = new LinkedList<>();
    private int currentBullet = 0;
    private int MaxBulletCount = 12;
    private Timer Update;
    private Timer reloadTimer;
    private Timer EnemyShotTimer;
    private Timer EnemymoveTimer;
    private Timer Item;
    private int currentEnemy = 0;
    private boolean timeDropSup = false;
    private boolean isLoading = false;
    private int gachaPercentage = 10;

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
                        if (!isLoading) {
                            if (canFire()) {
                                if (userTank1.getCurrentNumOfBullet() == 1) {
                                    Point tankPos = new Point(userTank1.getPos());
                                    Shot shot = new Shot(tankPos);
                                    shot.setDirection(currentDirection);
                                    shots.add(shot);
                                    getContentPane().add(shot.draw());
                                    // Ensure the bullet appears on top
                                    setComponentZOrder(shot.draw(), 0);
                                    currentBullet++;
                                } else if (userTank1.getCurrentNumOfBullet() == 2) {// No increse in number of bullet
                                    Point tankPos = new Point(userTank1.getPos());
                                    Shot shot = new Shot(tankPos);
                                    shot.setDirection(currentDirection);
                                    shots.add(shot);
                                    getContentPane().add(shot.draw());
                                    // Ensure the bullet appears on top
                                    setComponentZOrder(shot.draw(), 0);
                                    currentBullet++;

                                    Timer timer = new Timer(100, x -> {
                                        Point tankPos1 = new Point(userTank1.getPos());
                                        Shot shot1 = new Shot(tankPos1);
                                        shot1.setDirection(currentDirection);
                                        shots.add(shot1);
                                        getContentPane().add(shot1.draw());
                                        // Ensure the bullet appears on top
                                        setComponentZOrder(shot1.draw(), 0);
                                    });
                                    timer.setRepeats(false);
                                    timer.start();
                                } else if (userTank1.getCurrentNumOfBullet() == 3) {
                                    Point tankPos = new Point(userTank1.getPos());
                                    Shot shot = new Shot(tankPos);
                                    shot.setDirection(currentDirection);
                                    shots.add(shot);
                                    getContentPane().add(shot.draw());
                                    // Ensure the bullet appears on top
                                    setComponentZOrder(shot.draw(), 0);
                                    currentBullet++;

                                    Timer timer = new Timer(100, x -> {
                                        Point tankPos1 = new Point(userTank1.getPos());
                                        Shot shot1 = new Shot(tankPos1);
                                        shot1.setDirection(currentDirection);
                                        shots.add(shot1);
                                        getContentPane().add(shot1.draw());
                                        // Ensure the bullet appears on top
                                        setComponentZOrder(shot1.draw(), 0);
                                    });
                                    timer.setRepeats(false);
                                    timer.start();
                                    Timer timer1 = new Timer(200, x -> {
                                        Point tankPos2 = new Point(userTank1.getPos());
                                        Shot shot2 = new Shot(tankPos2);
                                        shot2.setDirection(currentDirection);
                                        shots.add(shot2);
                                        getContentPane().add(shot2.draw());
                                        // Ensure the bullet appears on top
                                        setComponentZOrder(shot2.draw(), 0);
                                    });
                                    timer1.setRepeats(false);
                                    timer1.start();
                                }

                            } else {
                                reloadBullet();
                            }
                        } else {
                            System.out.println("Reloading started, will take 3 seconds...");
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
        Update = new Timer(12, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                getContentPane().repaint();
            }
        });
        Update.start();
        EnemymoveTimer = new Timer(1000, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Move move : EnemyTank) {
                    Point previous = new Point(move.getPos());
                    move.move();
                    Point pos = new Point(move.getPos());
                    move.isCollision(damageBlock, previous, pos, EnemyTank);
                    revalidate();
                    repaint();
                }
            }
        });

        EnemymoveTimer.start();

        EnemyShotTimer = new Timer(1500, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Move move : EnemyTank) {
                    if (move instanceof EnemyTank1) {
                        Point tankPos = new Point(move.getPos());
                        Shot shot = new Shot(tankPos);
                        Random random = new Random();
                        int randomDirect = random.nextInt(4) + 1;
                        shot.setDirection(randomDirect);
                        EnemyShot.add(shot);
                        getContentPane().add(shot.draw());
                        // Ensure the bullet appears on top
                        setComponentZOrder(shot.draw(), 0);
                    } else if (move instanceof EnemyTank2) {
                        Point tankPos = new Point(move.getPos());
                        Shot shot = new Shot(tankPos);
                        Random random = new Random();
                        int randomDirect = random.nextInt(4) + 1;
                        shot.setDirection(randomDirect);
                        EnemyShot.add(shot);
                        getContentPane().add(shot.draw());

                        // Ensure the bullet appears on top
                        setComponentZOrder(shot.draw(), 0);

                        // Create a Timer to delay the addition of shot1 by 1 second
                        Timer timer = new Timer(100, x -> {
                            Point tankPos1 = new Point(move.getPos());
                            Shot shot1 = new Shot(tankPos1);
                            shot1.setDirection(randomDirect);
                            EnemyShot.add(shot1);
                            getContentPane().add(shot1.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot1.draw(), 0);
                        });

                        // Ensure the timer only runs once
                        timer.setRepeats(false);
                        timer.start();
                    } else if (move instanceof EnemyTank3) {
                        Point tankPos = new Point(move.getPos());
                        Shot shot = new Shot(tankPos);
                        Random random = new Random();
                        int randomDirect = random.nextInt(4) + 1;
                        shot.setDirection(randomDirect);
                        EnemyShot.add(shot);
                        getContentPane().add(shot.draw());

                        // Ensure the bullet appears on top
                        setComponentZOrder(shot.draw(), 0);

                        // Create a Timer to delay the addition of shot1 by 1 second
                        Timer timer = new Timer(100, x -> {
                            Point tankPos1 = new Point(move.getPos());
                            Shot shot1 = new Shot(tankPos1);
                            shot1.setDirection(randomDirect);
                            EnemyShot.add(shot1);
                            getContentPane().add(shot1.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot1.draw(), 0);
                        });
                        timer.setRepeats(false);
                        timer.start();
                        Timer timer1 = new Timer(200, x -> {
                            Point tankPos2 = new Point(move.getPos());
                            Shot shot2 = new Shot(tankPos2);
                            shot2.setDirection(randomDirect);
                            EnemyShot.add(shot2);
                            getContentPane().add(shot2.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot2.draw(), 0);
                        });
                        timer1.setRepeats(false);
                        timer1.start();
                        Timer timer2 = new Timer(300, x -> {
                            Point tankPos3 = new Point(move.getPos());
                            Shot shot3 = new Shot(tankPos3);
                            shot3.setDirection(randomDirect);
                            EnemyShot.add(shot3);
                            getContentPane().add(shot3.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot3.draw(), 0);
                        });
                        timer2.setRepeats(false);
                        timer2.start();
                    }
                }
            }
        });
        EnemyShotTimer.start();

        Item = new Timer(1000, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                Point pos = new Point(600, 390);
                getContentPane().add(DropItem(pos), 2);
            }
        });
        Item.setRepeats(false);
        /* Item.start(); */
    }

    /*
     * private void draw(Graphics g) {
     * Enemyspawn();
     * }
     */

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
            if (i == 2 || i == 3 || i == 4) {
                continue;
            }
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
        ControlTank.add(userTank1);
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
        if (direction != Direction.NO_DIRECTION) {
            currentDirection = direction;
            userTankMove();
            revalidate();
            repaint();
            direction = Direction.NO_DIRECTION;
        }
        Iterator<Shot> iterator = shots.iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            shot.move();
            shot.setObstacle(damageBlock, EnemyTank);
            if (shot.getisDamage()) {
                this.remove(shot.getImage());
                iterator.remove();
            }
            shot.draw();
        }
        Iterator<Shot> iterator1 = EnemyShot.iterator();
        while (iterator1.hasNext()) {
            Shot shot = iterator1.next();
            shot.move();
            shot.setObstacle(damageBlock, ControlTank);
            if (shot.getisDamage()) {
                this.remove(shot.getImage());
                iterator1.remove();
            }
            shot.draw();
        }
        // Update all obstacle
        LinkedList<Unmove> newItems = new LinkedList<>();
        Iterator<Unmove> iterator2 = damageBlock.iterator();
        while (iterator2.hasNext()) {
            Unmove unm = iterator2.next();
            if (unm.isDestroy()) {
                if (unm instanceof WoodenBox) {
                    Random random = new Random();
                    int canGetItem = random.nextInt(101) + 1;
                    if (canGetItem <= gachaPercentage) {
                        Point pos = new Point(unm.getPos());
                        Unmove item = gachaItem(pos);
                        newItems.add(item);
                        this.add(item.drawObject(), 2);
                        gachaPercentage = 10;
                    } else {
                        gachaPercentage += 10;
                    }
                }
                if ((unm instanceof Clock || unm instanceof BulletIncrease || unm instanceof HeartUp)
                        && unm.getPos().equals(new Point(600, 390))) {
                    if (unm instanceof Clock) {
                        stopEnemyMove();
                    }
                    timeDropSup = false;
                }
                this.remove(unm.getJLabel());
                iterator2.remove();
            }
        }
        damageBlock.addAll(newItems);
        Iterator<Move> iterator3 = EnemyTank.iterator();
        while (iterator3.hasNext()) {
            Move move = iterator3.next();
            if (move.isDestroy()) {
                this.remove(move.getImage());
                iterator3.remove();
                currentEnemy--;
            }
        }

        // one for user tank later
        if (currentEnemy <= -1) {
            this.add(Enemyspawn(), 1);
        }
        if (!timeDropSup) {
            timeDropSup = true;
            Item.start();
        }

    }

    private void stopEnemyMove() {
        // Stop the enemy movement immediately
        if (EnemymoveTimer != null && EnemymoveTimer.isRunning()) {
            EnemymoveTimer.stop();
        }

        // Create a new Timer to wait for 5 seconds before restarting the enemy movement
        Timer delayTimer = new Timer(5000, event -> {
            // Start the enemy movement after 5 seconds
            if (EnemymoveTimer != null) {
                EnemymoveTimer.start();
            }
        });

        // Make sure the timer fires only once
        delayTimer.setRepeats(false);

        // Start the delay timer
        delayTimer.start();
    }

    private JLabel DropItem(Point position) {
        Unmove item = null;
        if (!userTank1.isAtMaxHealth() && !userTank1.isMaxBullet()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 40) {
                item = new Clock(position);
            } else if (randomItem > 20) {
                item = new BulletIncrease(position);
            } else {
                item = new HeartUp(position);
            }
        } else if (!userTank1.isAtMaxHealth()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 20) {
                item = new Clock(position);
            } else if (randomItem > 0) {
                item = new HeartUp(position);
            }
        } else if (!userTank1.isMaxBullet()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 20) {
                item = new Clock(position);
            } else if (randomItem > 0) {
                item = new BulletIncrease(position);
            }
        } else {
            item = new Clock(position);
        }
        damageBlock.add(item);
        return item.drawObject();
    }

    private Unmove gachaItem(Point position) {
        Unmove item = null;
        if (!userTank1.isAtMaxHealth() && !userTank1.isMaxBullet()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 40) {
                item = new Clock(position);
            } else if (randomItem > 20) {
                item = new BulletIncrease(position);
            } else {
                item = new HeartUp(position);
            }
        } else if (!userTank1.isAtMaxHealth()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 20) {
                item = new Clock(position);
            } else if (randomItem > 0) {
                item = new HeartUp(position);
            }
        } else if (!userTank1.isMaxBullet()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 20) {
                item = new Clock(position);
            } else if (randomItem > 0) {
                item = new BulletIncrease(position);
            }
        } else {
            item = new Clock(position);
        }
        return item;
    }

    private void userTankMove() {
        userTank1.setDirection(currentDirection);
        Point previous = new Point(userTank1.getPos());
        userTank1.move();
        Point pos = new Point(userTank1.getPos());
        userTank1.isCollision(damageBlock, previous, pos, EnemyTank);
    }

    private boolean canFire() {
        return currentBullet < MaxBulletCount;
    }

    private void reloadBullet() {
        if (reloadTimer != null && reloadTimer.isRunning()) {
            reloadTimer.stop();
        }
        isLoading = true;
        reloadTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBullet = 0; // Reset bullet count
                isLoading = false;
                reloadTimer.stop();
            }
        });

        reloadTimer.setRepeats(false);
        reloadTimer.start();
    }

    private JLabel Enemyspawn() {
        Random random = new Random();
        int randomEnemy = random.nextInt(2) + 1;
        Point randomPoint = new Point();
        boolean spawnable = false;
        int randomSpawnPoint = random.nextInt(3) + 1;
        // Try to find a spawnable point
        while (!spawnable) {
            if (randomSpawnPoint == 1) {
                int randomPosX = random.nextInt(2);
                int randomPosY = random.nextInt(2);
                randomPoint.setLocation(200 + randomPosX * 40, 110 + randomPosY * 40);
                spawnable = true; // Assume it's spawnable until we find otherwise

                for (Move move : EnemyTank) {
                    if (move.getPos().equals(randomPoint)) {
                        spawnable = false; // Not spawnable if position is already occupied
                        break;
                    }
                }
            } else if (randomSpawnPoint == 3) {
                int randomPosX = random.nextInt(6);
                int randomPosY = random.nextInt(2);
                randomPoint.setLocation(480 + randomPosX * 40, 110 + randomPosY * 40);
                spawnable = true; // Assume it's spawnable until we find otherwise

                for (Move move : EnemyTank) {
                    if (move.getPos().equals(randomPoint)) {
                        spawnable = false; // Not spawnable if position is already occupied
                        break;
                    }
                }
            } else {
                int randomPosX = random.nextInt(2);
                int randomPosY = random.nextInt(2);
                randomPoint.setLocation(1000 + randomPosX * 40, 110 + randomPosY * 40);
                spawnable = true; // Assume it's spawnable until we find otherwise

                for (Move move : EnemyTank) {
                    if (move.getPos().equals(randomPoint)) {
                        spawnable = false; // Not spawnable if position is already occupied
                        break;
                    }
                }
            }

        }
        Move enemy = null;
        switch (randomEnemy) {
            case 1:
                enemy = new EnemyTank1(randomPoint);
                break;
            case 2:
                enemy = new EnemyTank2(randomPoint);
                break;
            case 3:
                enemy = new EnemyTank3(randomPoint);
                break;
        }
        currentEnemy++;
        EnemyTank.add(enemy);
        return enemy.draw();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        while (true) {
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