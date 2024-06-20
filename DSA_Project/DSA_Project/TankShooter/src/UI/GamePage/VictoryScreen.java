package UI.GamePage;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VictoryScreen extends JFrame implements Runnable {
    private JLabel background = new JLabel();
    private JButton Back = new JButton();

    public VictoryScreen() {
        initial();
    }

    private void initial() {
        Back.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Back.setText("Back to home page");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        Back.setVisible(true);
        Back.setBounds(20, 400, 500, 50);
        this.add(Back);
        background.setIcon(new ImageIcon(getClass().getResource("/Image/BackGround_Victory.png")));
        background.setBounds(0, 0, 1320, 820);
        background.setVisible(true);
        this.add(background);

        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setSize(1320, 820);
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
        VictoryScreen v = new VictoryScreen();
        new Thread(v).start();
    }
}
