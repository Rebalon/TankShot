package UI.GamePage;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Guild extends JFrame implements Runnable {
    private JButton Map1 = new JButton();
    private JButton Item = new JButton();
    private JButton Map2 = new JButton();
    private boolean isChooseNull = true;
    private boolean isChooseMap1 = false;
    private boolean isChooseMap2 = false;
    private JLabel Map1_Display = new JLabel();
    private JLabel Map2_Display = new JLabel();
    private JLabel Item_Display = new JLabel();
    private JButton Back = new JButton();

    public Guild() {
        initial();
    }

    private void initial() {
        Map1.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Map1.setIcon(new ImageIcon(getClass().getResource("/Image/Map1_mini.png")));
        Map1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMap1ActionPerformed(evt);
            }
        });
        Map1.setVisible(true);
        Map1.setBounds(50, 200, 200, 120);
        this.add(Map1);

        Map2.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Map2.setIcon(new ImageIcon(getClass().getResource("/Image/Map2_mini.png")));
        Map2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMap2ActionPerformed(evt);
            }

        });
        Map2.setVisible(true);
        Map2.setBounds(50, 400, 200, 120);
        this.add(Map2);

        Item.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        Item.setText("Item explain");
        Item.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemActionPerformed(evt);
            }

        });
        Item.setVisible(true);
        Item.setBounds(0, 600, 300, 40);
        this.add(Item);

        Back.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        Back.setVisible(true);
        Back.setBounds(1100, 0, 200, 50);
        this.add(Back);

        this.setName("Guild");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setSize(1320, 820);
    }

    private void btnMap1ActionPerformed(ActionEvent evt) {
        isChooseMap2 = false;
        isChooseMap1 = true;
        this.remove(Map2_Display);
        Map1_Display.setIcon(new ImageIcon(getClass().getResource("/Image/Map1_display_Expain.png")));
        Map1_Display.setBounds(300, 100, 1000, 600);
        Map1_Display.setVisible(true);
        this.add(Map1_Display, 0);
        this.revalidate();
        this.repaint();
        isChooseNull = false;
    }

    private void btnMap2ActionPerformed(ActionEvent evt) {
        isChooseMap2 = true;
        isChooseMap1 = false;
        this.remove(Map1_Display);
        Map2_Display.setIcon(new ImageIcon(getClass().getResource("/Image/Map2_display_Explain.png")));
        Map2_Display.setBounds(300, 100, 1000, 600);
        Map2_Display.setVisible(true);
        this.add(Map2_Display, 0);
        this.revalidate();
        this.repaint();
        isChooseNull = false;
    }

    private void btnItemActionPerformed(ActionEvent evt) {
        if (isChooseMap2) {
            this.remove(Map2_Display);
        }
        if (isChooseMap1) {
            this.remove(Map1_Display);
        }

        Item_Display.setIcon(new ImageIcon(getClass().getResource("/Image/Item_Explain.png")));
        Item_Display.setBounds(300, 100, 1000, 600);
        Item_Display.setVisible(true);
        this.add(Item_Display, 0);
        this.revalidate();
        this.repaint();
        isChooseNull = false;
    }

    private void btnBackActionPerformed(ActionEvent evt) {
        this.dispose();
        HomePage home = new HomePage(false);
        new Thread(home).start();
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
        Guild g = new Guild();
        new Thread(g).start();
    }
}
