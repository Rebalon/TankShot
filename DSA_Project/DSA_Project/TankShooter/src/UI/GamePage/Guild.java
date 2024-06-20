package UI.GamePage;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Guild extends JFrame implements Runnable {
    private JButton Map1 = new JButton();
    private JButton Map2 = new JButton();

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
        Map1.setBounds(100, 200, 200, 120);
        this.add(Map1);

    }
    Map2.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
    Map2.setIcon(new ImageIcon(getClass().getResource("/Image/Map2_mini.png")));
    Map2.addActionListener(new java.awt.event.ActionListener() {

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnMap2ActionPerformed(evt);
    }

    });
    Map2.setVisible(true);
    Map2.setBounds(100,400,200,120);
    this.add(Map2);

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
