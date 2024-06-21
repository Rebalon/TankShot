package UI.Map;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Mechanic.MoveObject.Boss;
import Mechanic.MoveObject.EnemyTank1;
import Mechanic.MoveObject.EnemyTank2;
import Mechanic.MoveObject.EnemyTank3;
import Mechanic.MoveObject.Move;
import Mechanic.MoveObject.Shot;
import Mechanic.MoveObject.UserTank;
import Mechanic.UnmoveObject.BrickBlock;
import Mechanic.UnmoveObject.BrickRegenerate;
import Mechanic.UnmoveObject.BulletIncrease;
import Mechanic.UnmoveObject.Clock;
import Mechanic.UnmoveObject.HeartUp;
import Mechanic.UnmoveObject.SteelBlock;
import Mechanic.UnmoveObject.Unmove;
import Mechanic.UnmoveObject.Water;
import Mechanic.UnmoveObject.WoodenBox;
import Mechanic.UnmoveObject.invisibleObstacle;
import UI.Direction;
import UI.GamePage.HomePage;
import UI.GamePage.VictoryScreen;

public class Map2_Survival extends JFrame implements Runnable {
    private LinkedList<Point> Boss = new LinkedList<>();
    private Boss bossT;
    private JLabel HeartIcon = new JLabel();
    private JLabel HeartDisplay = new JLabel();
    private JLabel bulletIcon = new JLabel();
    private JLabel RemainBullet = new JLabel();
    private JLabel X = new JLabel();
    private JLabel bulletPerShot = new JLabel();
    private JLabel runTimeDisplay = new JLabel("Playtime: 00:00:00");
    private Instant startTime;
    private Timer runTimeTimer;
    private JLabel Score = new JLabel("Score: 0");
    private JLabel EnemyTank3Icon = new JLabel();
    private JLabel EnemyTank2Icon = new JLabel();
    private JLabel EnemyTank3Killed = new JLabel();
    private JLabel EnemyTank2Killed = new JLabel();
    private JLabel UserTankImage = new JLabel();
    private JLabel UserTankLife = new JLabel();
    private JButton back = new JButton();
    private int numOfEnemy3Kill = 0;
    private int numOfEnemy2Kill = 0;
    private int numberOfLife = 3;
    private Point userTank;
    private int direction = Direction.NO_DIRECTION;
    private int currentDirection = Direction.EAST;
    private UserTank userTank1;
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
    private int score = 0;
    private JLabel j = new JLabel("Press Space to reload");
    private JLabel f = new JLabel("Wait for 3 seconds");
    private boolean isPause = false;
    private int pausetime = 0;
    private String formattedTime;
    private int time;

    public Map2_Survival() {
        initial();
    }

    private void initial() {
        this.setName("TankShooter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        drawMap();
        drawDamageObject();

        HeartIcon.setFont(new java.awt.Font("Times New Roman", 1, 80));
        HeartIcon.setIcon(new ImageIcon(getClass().getResource("/Image/HeartImage.png")));
        HeartIcon.setVisible(true);
        HeartIcon.setBounds(0, 0, 40, 40);
        this.add(HeartIcon);

        HeartDisplay.setFont(new java.awt.Font("Times New Roman", 1, 40));
        HeartDisplay.setText(String.valueOf(userTank1.getHealth()));
        HeartDisplay.setVisible(true);
        HeartDisplay.setBounds(80, 0, 40, 40);
        this.add(HeartDisplay);

        bulletIcon.setFont(new java.awt.Font("Times New Roman", 1, 80));
        bulletIcon.setIcon(new ImageIcon(getClass().getResource("/Image/bullet.png")));
        bulletIcon.setVisible(true);
        bulletIcon.setBounds(160, 0, 40, 40);
        this.add(bulletIcon);

        RemainBullet.setFont(new java.awt.Font("Times New Roman", 1, 40));
        RemainBullet.setText(String.valueOf(this.MaxBulletCount - this.currentBullet));
        RemainBullet.setVisible(true);
        RemainBullet.setBounds(200, 0, 40, 40);
        this.add(RemainBullet);

        X.setFont(new java.awt.Font("Times New Roman", 1, 20));
        X.setText("X");
        X.setVisible(true);
        X.setBounds(250, 0, 40, 40);
        this.add(X);

        bulletPerShot.setFont(new java.awt.Font("Times New Roman", 1, 40));
        bulletPerShot.setText(String.valueOf(userTank1.getCurrentNumOfBullet()));
        bulletPerShot.setVisible(true);
        bulletPerShot.setBounds(280, 0, 40, 40);
        this.add(bulletPerShot);

        runTimeDisplay.setFont(new java.awt.Font("Times New Roman", 1, 40));
        runTimeDisplay.setBounds(400, 0, 400, 40);
        this.add(runTimeDisplay);
        initializeTimer();

        Score.setFont(new java.awt.Font("Times New Roman", 1, 40));
        Score.setVisible(true);
        Score.setBounds(800, 0, 200, 40);
        this.add(Score);

        EnemyTank3Icon.setFont(new java.awt.Font("Times New Roman", 1, 80));
        EnemyTank3Icon.setIcon(new ImageIcon(getClass().getResource("/Image/enemyTank_3.png")));
        EnemyTank3Icon.setVisible(true);
        EnemyTank3Icon.setBounds(1000, 0, 40, 40);
        this.add(EnemyTank3Icon);

        EnemyTank3Killed.setFont(new java.awt.Font("Times New Roman", 1, 40));
        EnemyTank3Killed.setText(String.valueOf(numOfEnemy3Kill));
        EnemyTank3Killed.setVisible(true);
        EnemyTank3Killed.setBounds(1010, 40, 40, 40);
        this.add(EnemyTank3Killed);

        EnemyTank2Icon.setFont(new java.awt.Font("Times New Roman", 1, 80));
        EnemyTank2Icon.setIcon(new ImageIcon(getClass().getResource("/Image/enemyTank_2.png")));
        EnemyTank2Icon.setVisible(true);
        EnemyTank2Icon.setBounds(1080, 0, 40, 40);
        this.add(EnemyTank2Icon);

        EnemyTank2Killed.setFont(new java.awt.Font("Times New Roman", 1, 40));
        EnemyTank2Killed.setText(String.valueOf(numOfEnemy2Kill));
        EnemyTank2Killed.setVisible(true);
        EnemyTank2Killed.setBounds(1090, 40, 40, 40);
        this.add(EnemyTank2Killed);

        UserTankImage.setFont(new java.awt.Font("Times New Roman", 1, 80));
        UserTankImage.setIcon(new ImageIcon(getClass().getResource("/Image/tankUp.png")));
        UserTankImage.setVisible(true);
        UserTankImage.setBounds(1160, 0, 40, 40);
        this.add(UserTankImage);

        UserTankLife.setFont(new java.awt.Font("Times New Roman", 1, 40));
        UserTankLife.setText(String.valueOf(numberOfLife));
        UserTankLife.setVisible(true);
        UserTankLife.setBounds(1170, 40, 40, 40);
        this.add(UserTankLife);

        back.setFont(new java.awt.Font("Times New Roman", 1, 20));
        back.setText("Back");
        back.setVisible(true);
        back.setBounds(1220, 0, 100, 20);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        this.add(back);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (!isPause) {
                            direction = Direction.NORTH;
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (!isPause) {
                            direction = Direction.SOUTH;
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (!isPause) {
                            direction = Direction.WEST;
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (!isPause) {
                            direction = Direction.EAST;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!isPause) {
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
                                    } else if (userTank1.getCurrentNumOfBullet() == 2) {// No increse in number of
                                                                                        // bullet
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
                                    f.setFont(new java.awt.Font("Times New Roman", 1, 20));
                                    f.setVisible(true);
                                    f.setBounds(160, 40, 200, 20);
                                    f.setForeground(new java.awt.Color(0, 0, 0));
                                    getContentPane().add(f, 0);
                                    reloadBullet();
                                }
                            } else {
                                System.out.println("Reloading started, will take 3 seconds...");
                            }
                        }
                        break;
                    case KeyEvent.VK_P:
                        // Add is dead condition
                        if (!isPause) {
                            isPause = true;
                        } else {
                            isPause = false;
                        }
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
            }
        });

        this.pack();
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Update = new Timer(24, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) {
                    updateGame();
                    getContentPane().repaint();
                }
            }
        });
        Update.start();
        EnemymoveTimer = new Timer(1000, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) {
                    for (Move move : EnemyTank) {
                        Point previous = new Point(move.getPos());
                        move.move();
                        Point pos = new Point(move.getPos());
                        move.isCollision(damageBlock, previous, pos, EnemyTank, Boss);
                        revalidate();
                        repaint();
                    }
                }
            }
        });

        EnemymoveTimer.start();

        EnemyShotTimer = new Timer(1500, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) {
                    for (Move move : EnemyTank) {
                        if (move instanceof EnemyTank2) {
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
            }
        });
        EnemyShotTimer.start();

        EnemyShotTimer = new Timer(4000, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) {
                    for (int i = 0; i < 8; i++) {
                        int i1 = i;
                        Shot shot = new Shot(new Point(480 + i1 * 40, 270));
                        shot.setDirection(2);
                        EnemyShot.add(shot);
                        getContentPane().add(shot.draw());

                        // Ensure the bullet appears on top
                        setComponentZOrder(shot.draw(), 0);

                        // Create a Timer to delay the addition of shot1 by 1 second
                        Timer timer = new Timer(100, x -> {
                            Shot shot1 = new Shot(new Point(480 + i1 * 40, 270));
                            shot1.setDirection(2);
                            EnemyShot.add(shot1);
                            getContentPane().add(shot1.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot1.draw(), 0);
                        });
                        timer.setRepeats(false);
                        timer.start();
                        Timer timer1 = new Timer(200, x -> {
                            Shot shot2 = new Shot(new Point(480 + i1 * 40, 270));
                            shot2.setDirection(2);
                            EnemyShot.add(shot2);
                            getContentPane().add(shot2.draw());

                            // Ensure the bullet appears on top
                            setComponentZOrder(shot2.draw(), 0);
                        });
                        timer1.setRepeats(false);
                        timer1.start();
                        Timer timer2 = new Timer(300, x -> {
                            Shot shot3 = new Shot(new Point(480 + i1 * 40, 270));
                            shot3.setDirection(2);
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

        Item = new Timer(3500, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                Point pos = new Point(600, 550);
                getContentPane().add(DropItem(pos), 2);
            }
        });
        Item.setRepeats(false);
    }

    private void initializeTimer() {
        // Capture the start time
        startTime = Instant.now();

        // Create a Timer that updates every second (1000 milliseconds)
        runTimeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) {
                    updateRunTime();
                } else {
                    calPausetime();
                }
            }
        });

        // Start the timer
        runTimeTimer.start();
    }

    private void calPausetime() {
        pausetime++;
    }

    private void updateRunTime() {
        // Calculate the elapsed time
        Duration elapsedTime = Duration.between(startTime, Instant.now().minusSeconds(pausetime));
        // Format the elapsed time as HH:mm:ss
        formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHours(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());

        // Update the JLabel text
        runTimeDisplay.setText("Playtime: " + formattedTime);
        time = ((Integer.valueOf(formattedTime.charAt(0)) - 48) * 10 + Integer.valueOf(formattedTime.charAt(1)) - 48)
                * 60 * 60
                +
                ((Integer.valueOf(formattedTime.charAt(3)) - 48) * 10 + Integer.valueOf(formattedTime.charAt(4)) - 48)
                        * 60
                + (Integer.valueOf(formattedTime.charAt(6)) - 48) * 10
                + Integer.valueOf(formattedTime.charAt(7)) - 48;
    }

    private void drawMap() {
        // Drawing map for battle
        for (int i = 0; i < 7; i++) {
            if (i == 3) {
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
        for (int i = 0; i < 33; i++) {
            Point waterPoint = new Point(i * 40, 310);
            Water wat = new Water(waterPoint);
            this.damageBlock.add(wat);
            this.add(wat.drawObject());
        }
    }

    private void drawDamageObject() {
        this.userTank = new Point(200, 670);
        this.userTank1 = new UserTank(userTank);
        ControlTank.add(userTank1);
        this.add(userTank1.draw());

        Point bossTank = new Point(360, 70);
        this.bossT = new Boss(bossTank);
        for (int h = 0; h < 7; h++) {
            for (int i = 0; i < 16; i++) {
                Boss.add(new Point(360 + i * 40, 70 + h * 40));
            }
        }
        this.add(bossT.draw());

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

        for (int i = 0; i < 5; i++) {
            Point woddenboxPoint = new Point(i * 40, 550);
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
            Point BrickBlockPoint = new Point(480 + (i * 40), 510);
            BrickBlock brk = new BrickBlock(BrickBlockPoint);

            damageBlock.add(brk);
            this.add(brk.drawObject());
        }
        for (int i = 0; i < 7; i++) {
            Point BrickBlockPoint = new Point(480 + (i * 40), 470);
            BrickBlock brk = new BrickBlock(BrickBlockPoint);

            damageBlock.add(brk);
            this.add(brk.drawObject());
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

    private void updateGame() {
        if (bossT.isDestroy() && time <= 30) {
            for (int h = 0; h < 7; h++) {
                for (int i = 0; i < 16; i++) {
                    Boss.remove(new Point(360 + i * 40, 70 + h * 40));
                }
            }
            score += 30000;
            score += 30000;
            MovetoVictory();
        } else if (bossT.isDestroy() && time <= 60) {
            for (int h = 0; h < 7; h++) {
                for (int i = 0; i < 16; i++) {
                    Boss.remove(new Point(360 + i * 40, 70 + h * 40));
                }
            }
            score += 30000;
            score += 20000;
            MovetoVictory();
        } else if (bossT.isDestroy() && time <= 90) {
            for (int h = 0; h < 7; h++) {
                for (int i = 0; i < 16; i++) {
                    Boss.remove(new Point(360 + i * 40, 70 + h * 40));
                }
            }
            score += 30000;
            score += 10000;
            MovetoVictory();
        } else if (time > 90) {
            isPause = true;
            JOptionPane.showMessageDialog(null, "Time up!!! You are defeated!!!");
            JOptionPane.showMessageDialog(null, "Back to home page!!!");
            this.dispose();
            HomePage home = new HomePage(false);
            new Thread(home).start();
        }
        if (userTank1.getHealth() < 0) {
            userTank1.setHealth(0);
        }
        if (userTank1.getHealth() == 0) {
            HeartDisplay.setText(String.valueOf(userTank1.getHealth()));
            endGame();
        }
        // Update all bullets
        UserTankLife.setText(String.valueOf(numberOfLife));
        EnemyTank3Killed.setText(String.valueOf(numOfEnemy3Kill));
        EnemyTank2Killed.setText(String.valueOf(numOfEnemy2Kill));
        bulletPerShot.setText(String.valueOf(userTank1.getCurrentNumOfBullet()));
        HeartDisplay.setText(String.valueOf(userTank1.getHealth()));
        RemainBullet.setText(String.valueOf(this.MaxBulletCount - this.currentBullet));
        Score.setText("Score: " + score);
        if (currentBullet == 12) {
            j.setFont(new java.awt.Font("Times New Roman", 1, 80));
            j.setVisible(true);
            j.setBounds(300, 200, 800, 80);
            j.setForeground(new java.awt.Color(255, 255, 255));
            this.add(j, 0);
        } else {
            this.remove(j);
            this.remove(f);
        }

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
            shot.setObstacle(damageBlock, EnemyTank, Boss, bossT);
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
            shot.setObstacle(damageBlock, ControlTank, null, null);
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
                if ((unm instanceof Clock || unm instanceof BulletIncrease || unm instanceof HeartUp
                        || unm instanceof BrickRegenerate)
                        && unm.getPos().equals(new Point(600, 550))) {
                    if (unm instanceof BrickRegenerate) {
                        Regenration();
                    }
                    if (unm instanceof Clock) {
                        score += 100;
                        stopEnemyMove();
                    }
                    if (unm instanceof BulletIncrease || unm instanceof HeartUp) {
                        score += 200;
                    }
                    timeDropSup = false;
                }
                if ((unm instanceof Clock || unm instanceof BulletIncrease || unm instanceof HeartUp
                        || unm instanceof BrickRegenerate)
                        && !unm.getPos().equals(new Point(600, 550))) {
                    if (unm instanceof BrickRegenerate) {
                        Regenration();
                    }
                    if (unm instanceof Clock) {
                        score += 100;
                        stopEnemyMove();
                    }
                    if (unm instanceof BulletIncrease || unm instanceof HeartUp) {
                        score += 200;
                    }
                }
                if (unm instanceof BrickBlock) {
                    continue;
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
                if (move instanceof EnemyTank3) {
                    numOfEnemy3Kill += 1;
                    score += 1000;
                } else if (move instanceof EnemyTank2) {
                    numOfEnemy2Kill += 1;
                    score += 500;
                }
                this.remove(move.getImage());
                iterator3.remove();
                currentEnemy--;
            }
        }

        // one for user tank later
        if (currentEnemy < 3) {
            this.add(Enemyspawn(), 1);
        }
        if (!timeDropSup) {
            timeDropSup = true;
            Item.start();
        }
        this.revalidate();
        this.repaint();
    }

    private void Regenration() {
        Iterator<Unmove> iterator = damageBlock.iterator();
        while (iterator.hasNext()) {
            Unmove unm = iterator.next();
            if (unm instanceof BrickBlock) {
                ((BrickBlock) unm).setHealth(10);
            }
        }
        this.repaint();
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
                item = new BrickRegenerate(position);
            } else if (randomItem > 20) {
                item = new BulletIncrease(position);
            } else if (randomItem > 10) {
                item = new HeartUp(position);
            } else {
                item = new Clock(position);
            }
        } else if (!userTank1.isAtMaxHealth()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 40) {
                item = new BrickRegenerate(position);
            } else if (randomItem > 20) {
                item = new HeartUp(position);
            } else {
                item = new Clock(position);
            }
        } else if (!userTank1.isMaxBullet()) {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 40) {
                item = new BrickRegenerate(position);
            } else if (randomItem > 20) {
                item = new BulletIncrease(position);
            } else {
                item = new Clock(position);
            }
        } else {
            Random random = new Random();
            int randomItem = random.nextInt(101) + 1;
            if (randomItem > 15) {
                item = new BrickRegenerate(position);
            } else {
                item = new Clock(position);
            }

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
        userTank1.isCollision(damageBlock, previous, pos, EnemyTank, Boss);
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
        int randomEnemy = random.nextInt(2) + 2;
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

    public int getNumberOfLife() {
        return numberOfLife;
    }

    private void btnBackActionPerformed(ActionEvent evt) {
        isPause = true; // Pause the game
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to go back to the homepage?",
                "Return to homepage",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            System.out.println("User chose 'Yes'.");
            this.dispose();
            HomePage home = new HomePage(false);
            new Thread(home).start();
        } else {
            isPause = false; // Unpause the game if user chooses 'No' or closes the dialog
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("User chose 'No'.");
            } else {
                System.out.println("Dialog was closed or canceled without a specific choice.");
            }
        }

        // Ensure the game window regains focus
        this.requestFocus();
    }

    private void endGame() {
        isPause = true;
        JOptionPane.showMessageDialog(null, "You are defeated");
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to go back homepage or continue ?\nPress 'Yes' for continue 'No' to return homepage",
                "Choose what next???",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            if (numberOfLife >= 0) {
                userTank1.setHealth(2);
                userTank1.getImage().setVisible(true);
                isPause = false;
                numberOfLife--;
            } else {
                JOptionPane.showMessageDialog(null, "No more chance !!!. Good luck next time !!!");
                JOptionPane.showMessageDialog(null, "Return to homepage!!!");
                this.dispose();
                HomePage home = new HomePage(false);
                new Thread(home).start();
            }
            System.out.println("User chose 'Yes'.");
        } else if (response == JOptionPane.NO_OPTION) {
            System.out.println("User chose 'No'.");
            JOptionPane.showMessageDialog(null, "Good luck next time !!!");
            this.dispose();
            HomePage home = new HomePage(false);
            new Thread(home).start();
        } else {
            isPause = false;
            System.out.println("Dialog was closed or canceled without a specific choice.");
        }
    }

    private String gameModeDef;
    private String gameModeSur;
    private String user1NameDef;
    private String user1ScoreDef;
    private String user1PlayTimeDef;
    private String user2NameDef;
    private String user2ScoreDef;
    private String user2PlayTimeDef;
    private String user3NameDef;
    private String user3ScoreDef;
    private String user3PlayTimeDef;
    private String user1NameSur;
    private String user1ScoreSur;
    private String user1PlayTimeSur;
    private String user2NameSur;
    private String user2ScoreSur;
    private String user2PlayTimeSur;
    private String user3NameSur;
    private String user3ScoreSur;
    private String user3PlayTimeSur;

    private void MovetoVictory() {
        isPause = true;
        JOptionPane.showMessageDialog(null, "You win!!! Congratulation!!!");
        readTextFromFile_Map2();
        if (this.score >= Integer.parseInt(user1ScoreSur)) {
            if (this.score > Integer.parseInt(user1ScoreSur)) {
                String name = JOptionPane.showInputDialog("Please enter your name: ");
                user3NameSur = user2NameSur;
                user2NameSur = user1NameSur;
                user1NameSur = name;

                user3ScoreSur = user2ScoreSur;
                user2ScoreSur = user1ScoreSur;
                user1ScoreSur = String.valueOf(score);

                user3PlayTimeSur = user2PlayTimeSur;
                user2PlayTimeSur = user1PlayTimeSur;
                user1PlayTimeSur = formattedTime;
                String filePath = "E:\\"; // Change this to your desired directory
                String fileName = "TankShotter_LeaderBoard_Map2.txt";

                String content = "Name  Score  Play Time\n" + gameModeDef +
                        "\n" +
                        user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                        user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                        user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                        gameModeSur + "\n" +
                        user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                        user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                        user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                if (isFileCreated) {
                    System.out.println("File created and saved successfully!");
                } else {
                    System.out.println("File creation failed.");
                }
            } else {
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player1time = (Integer.valueOf(user1PlayTimeSur.charAt(0)) * 10
                        + Integer.valueOf(user1PlayTimeSur.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user1PlayTimeSur.charAt(3)) * 10 + Integer.valueOf(user1PlayTimeSur.charAt(4)))
                                * 60
                        + Integer.valueOf(user1PlayTimeSur.charAt(6)) * 10
                        + Integer.valueOf(user1PlayTimeSur.charAt(7));
                if (time >= player1time) {
                    String name = JOptionPane.showInputDialog("Please enter your name: ");
                    user3NameSur = user2NameSur;
                    user2NameSur = name;

                    user3ScoreSur = user2ScoreSur;
                    user2ScoreSur = String.valueOf(score);

                    user3PlayTimeSur = user2PlayTimeSur;
                    user2PlayTimeSur = formattedTime;

                    String filePath = "E:\\"; // Change this to your desired directory
                    String fileName = "TankShotter_LeaderBoard_Map2.txt";

                    String content = "Name  Score  Play Time\n" + gameModeDef +
                            "\n" +
                            user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                            user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                            user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                            gameModeSur + "\n" +
                            user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                            user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                            user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                    boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                    if (isFileCreated) {
                        System.out.println("File created and saved successfully!");
                    } else {
                        System.out.println("File creation failed.");
                    }
                } else {
                    String name = JOptionPane.showInputDialog("Please enter your name: ");
                    user3NameSur = user2NameSur;
                    user2NameSur = user1NameSur;
                    user1NameSur = name;

                    user3ScoreSur = user2ScoreSur;
                    user2ScoreSur = user1ScoreSur;
                    user1ScoreSur = String.valueOf(score);

                    user3PlayTimeSur = user2PlayTimeSur;
                    user2PlayTimeSur = user1PlayTimeSur;
                    user1PlayTimeSur = formattedTime;
                    String filePath = "E:\\"; // Change this to your desired directory
                    String fileName = "TankShotter_LeaderBoard_Map2.txt";

                    String content = "Name  Score  Play Time\n" + gameModeDef +
                            "\n" +
                            user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                            user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                            user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                            gameModeSur + "\n" +
                            user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                            user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                            user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                    boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                    if (isFileCreated) {
                        System.out.println("File created and saved successfully!");
                    } else {
                        System.out.println("File creation failed.");
                    }
                }
            }
        }

        else if (this.score >= Integer.parseInt(user2ScoreSur)) {
            if (this.score > Integer.parseInt(user2ScoreSur)) {
                String name = JOptionPane.showInputDialog("Please enter your name: ");
                user3NameSur = user2NameSur;
                user2NameSur = name;

                user3ScoreSur = user2ScoreSur;
                user2ScoreSur = String.valueOf(score);

                user3PlayTimeSur = user2PlayTimeSur;
                user2PlayTimeSur = formattedTime;

                String filePath = "E:\\"; // Change this to your desired directory
                String fileName = "TankShotter_LeaderBoard_Map2.txt";

                String content = "Name  Score  Play Time\n" + gameModeDef +
                        "\n" +
                        user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                        user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                        user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                        gameModeSur + "\n" +
                        user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                        user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                        user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                if (isFileCreated) {
                    System.out.println("File created and saved successfully!");
                } else {
                    System.out.println("File creation failed.");
                }
            } else {
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player2time = (Integer.valueOf(user2PlayTimeDef.charAt(0)) * 10
                        + Integer.valueOf(user2PlayTimeSur.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user2PlayTimeSur.charAt(3)) * 10 + Integer.valueOf(user2PlayTimeDef.charAt(4)))
                                * 60
                        + Integer.valueOf(user2PlayTimeSur.charAt(6)) * 10
                        + Integer.valueOf(user2PlayTimeSur.charAt(7));
                if (time >= player2time) {
                    String name = JOptionPane.showInputDialog("Please enter your name: ");
                    user3NameSur = name;

                    user3ScoreSur = String.valueOf(score);

                    user3PlayTimeSur = formattedTime;

                    String filePath = "E:\\"; // Change this to your desired directory
                    String fileName = "TankShotter_LeaderBoard_Map2.txt";

                    String content = "Name  Score  Play Time\n" + gameModeDef +
                            "\n" +
                            user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                            user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                            user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                            gameModeSur + "\n" +
                            user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                            user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                            user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                    boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                    if (isFileCreated) {
                        System.out.println("File created and saved successfully!");
                    } else {
                        System.out.println("File creation failed.");
                    }
                } else {
                    String name = JOptionPane.showInputDialog("Please enter your name: ");
                    user3NameSur = user2NameSur;
                    user2NameSur = name;

                    user3ScoreSur = user2ScoreSur;
                    user2ScoreSur = String.valueOf(score);

                    user3PlayTimeSur = user2PlayTimeDef;
                    user2PlayTimeSur = formattedTime;

                    String filePath = "E:\\"; // Change this to your desired directory
                    String fileName = "TankShotter_LeaderBoard_Map2.txt";

                    String content = "Name  Score  Play Time\n" + gameModeDef +
                            "\n" +
                            user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                            user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                            user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                            gameModeSur + "\n" +
                            user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                            user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                            user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                    boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                    if (isFileCreated) {
                        System.out.println("File created and saved successfully!");
                    } else {
                        System.out.println("File creation failed.");
                    }
                }

            }
        } else if (this.score >= Integer.parseInt(user3ScoreSur)) {
            if (this.score > Integer.parseInt(user3ScoreSur)) {
                String name = JOptionPane.showInputDialog("Please enter your name: ");
                user3NameSur = name;

                user3ScoreSur = String.valueOf(score);

                user3PlayTimeSur = formattedTime;

                String filePath = "E:\\"; // Change this to your desired directory
                String fileName = "TankShotter_LeaderBoard_Map2.txt";

                String content = "Name  Score  Play Time\n" + gameModeDef +
                        "\n" +
                        user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                        user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                        user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                        gameModeSur + "\n" +
                        user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                        user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                        user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                if (isFileCreated) {
                    System.out.println("File created and saved successfully!");
                } else {
                    System.out.println("File creation failed.");
                }
            } else {
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player3time = (Integer.valueOf(user3PlayTimeSur.charAt(0)) * 10
                        + Integer.valueOf(user3PlayTimeSur.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user3PlayTimeSur.charAt(3)) * 10 + Integer.valueOf(user3PlayTimeSur.charAt(4)))
                                * 60
                        + Integer.valueOf(user3PlayTimeSur.charAt(6)) * 10
                        + Integer.valueOf(user3PlayTimeSur.charAt(7));
                if (time < player3time) {
                    String name = JOptionPane.showInputDialog("Please enter your name: ");
                    user3NameSur = name;

                    user3ScoreSur = String.valueOf(score);

                    user3PlayTimeSur = formattedTime;

                    String filePath = "E:\\"; // Change this to your desired directory
                    String fileName = "TankShotter_LeaderBoard_Map2.txt";

                    String content = "Name  Score  Play Time\n" + gameModeDef +
                            "\n" +
                            user1NameDef + "      " + user1ScoreDef + "   " + user1PlayTimeDef + "\n" +
                            user2NameDef + "      " + user2ScoreDef + "   " + user2PlayTimeDef + "\n" +
                            user3NameDef + "      " + user3ScoreDef + "   " + user3PlayTimeDef + "\n" +
                            gameModeSur + "\n" +
                            user1NameSur + "      " + user1ScoreSur + "   " + user1PlayTimeSur + "\n" +
                            user2NameSur + "      " + user2ScoreSur + "   " + user2PlayTimeSur + "\n" +
                            user3NameSur + "      " + user3ScoreSur + "   " + user3PlayTimeSur + "\n";
                    boolean isFileCreated = HomePage.createAndWriteFileUsingNIO(filePath, fileName, content);
                    if (isFileCreated) {
                        System.out.println("File created and saved successfully!");
                    } else {
                        System.out.println("File creation failed.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Your score and play time is not satisfy to enter leader board!!! \n Good luck next time");
        }
        this.dispose();
        VictoryScreen v = new VictoryScreen();
        new Thread(v).start();
    }

    private void readTextFromFile_Map2() {
        String filePath = "E:\\TankShotter_LeaderBoard_Map2.txt";
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            // Check if the file is not empty
            if (lines.isEmpty()) {
                System.out.println("The file is empty.");
                return;
            }
            // Skip the header and parse each line
            String mode = lines.get(1);
            gameModeDef = mode;
            String mode1 = lines.get(5);
            gameModeSur = mode1;
            for (int i = 2; i < 5; i++) {
                String line = lines.get(i);
                String[] parts = line.split("\\s+"); // Split by one or more spaces

                if (parts.length == 3) { // Ensure there are exactly 3 parts
                    String name = parts[0];
                    String score = parts[1];
                    String playTime = parts[2];
                    if (i == 2) {
                        user1NameDef = name;
                        user1ScoreDef = score;
                        user1PlayTimeDef = playTime;
                    } else if (i == 3) {
                        user2NameDef = name;
                        user2ScoreDef = score;
                        user2PlayTimeDef = playTime;
                    } else if (i == 4) {
                        user3NameDef = name;
                        user3ScoreDef = score;
                        user3PlayTimeDef = playTime;
                    }
                } else {
                    System.out.println("Line format is incorrect: " + line);
                }
            }

            for (int i = 6; i < 9; i++) {
                String line = lines.get(i);
                String[] parts = line.split("\\s+"); // Split by one or more spaces

                if (parts.length == 3) { // Ensure there are exactly 3 parts
                    String name = parts[0];
                    String score = parts[1];
                    String playTime = parts[2];
                    if (i == 6) {
                        user1NameSur = name;
                        user1ScoreSur = score;
                        user1PlayTimeSur = playTime;
                    } else if (i == 7) {
                        user2NameSur = name;
                        user2ScoreSur = score;
                        user2PlayTimeSur = playTime;
                    } else if (i == 8) {
                        user3NameSur = name;
                        user3ScoreSur = score;
                        user3PlayTimeSur = playTime;
                    }
                } else {
                    System.out.println("Line format is incorrect: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read the file: " + e.getMessage());
            e.printStackTrace();
        }
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
        Map2_Survival map2 = new Map2_Survival();
        new Thread(map2).start();
    }
}