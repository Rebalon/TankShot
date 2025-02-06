
/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the Choose map page and its functions.
*/

package UI.GamePage;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UI.Map.Map1_Defence;
import UI.Map.Map1_Survival;
import UI.Map.Map2_Defence;
import UI.Map.Map2_Survival;

public class ChooseMap extends JFrame implements Runnable {
    private JLabel background = new JLabel();
    private JLabel jlabel1 = new JLabel();
    private JLabel Map1_Display = new JLabel();
    private JLabel Map2_Display = new JLabel();
    private JButton Map1 = new JButton();
    private JButton Map2 = new JButton();
    private JButton Back = new JButton();
    private JButton ChooseThis = new JButton();
    private JComboBox<String> ChooseMode = new JComboBox<>();
    private String choosenMode;
    private boolean isChooseNull = true;
    private boolean isChooseMap1 = false;
    private boolean isChooseMap2 = false;

    public ChooseMap() {
        initial();
    }

    private void initial() {
        jlabel1.setFont(new java.awt.Font("Times New Roman", 1, 80)); // NOI18N
        jlabel1.setForeground(new java.awt.Color(0, 0, 0));
        jlabel1.setText("Choose Map");
        jlabel1.setVisible(true);
        jlabel1.setBounds(410, 0, 500, 80);
        this.add(jlabel1);

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

        ChooseThis.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        ChooseThis.setText("Choose");
        ChooseThis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });
        ChooseThis.setVisible(true);
        ChooseThis.setBounds(550, 550, 200, 30);
        this.add(ChooseThis);

        Map2.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Map2.setIcon(new ImageIcon(getClass().getResource("/Image/Map2_mini.png")));
        Map2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMap2ActionPerformed(evt);
            }
        });
        Map2.setVisible(true);
        Map2.setBounds(100, 400, 200, 120);
        this.add(Map2);

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

        ChooseMode.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        ChooseMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseModeActionPerformed(evt);
            }
        });
        ChooseMode.setVisible(true);
        ChooseMode.setBounds(950, 550, 200, 30);
        ChooseMode.addItem("Defence");
        ChooseMode.addItem("Survival");
        this.add(ChooseMode);
        this.setName("HomePage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        background.setIcon(new ImageIcon(getClass().getResource("/Image/BackGround_ChooseMap.png")));
        background.setBounds(0, 0, 1320, 820);
        background.setVisible(true);
        this.add(background);

        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setSize(1320, 820);
    }

    private void btnMap1ActionPerformed(ActionEvent evt) {
        isChooseMap2 = false;
        isChooseMap1 = true;
        this.remove(Map2_Display);
        Map1_Display.setIcon(new ImageIcon(getClass().getResource("/Image/Map1_display.png")));
        Map1_Display.setBounds(450, 150, 800, 400);
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
        Map2_Display.setIcon(new ImageIcon(getClass().getResource("/Image/Map2_display.png")));
        Map2_Display.setBounds(450, 150, 800, 400);
        Map2_Display.setVisible(true);
        this.add(Map2_Display, 0);
        this.revalidate();
        this.repaint();
        isChooseNull = false;
    }

    private void ChooseModeActionPerformed(ActionEvent evt) {
        choosenMode = (String) ChooseMode.getSelectedItem();
    }

    private void btnBackActionPerformed(ActionEvent evt) {
        this.dispose();
        HomePage home = new HomePage();
        new Thread(home).start();
    }

    private void btnChooseActionPerformed(ActionEvent evt) {
        if (isChooseNull) {
            JOptionPane.showMessageDialog(null, "Please choosing a map");
        } else {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to choose this map with mode " + choosenMode + "?",
                    "Confirm Map Choice",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                System.out.println("User chose 'Yes'. Proceed with map selection.");
                if (choosenMode.equals("Defence")) {
                    if (isChooseMap1) {
                        this.dispose();
                        Map1_Defence map1 = new Map1_Defence();
                        new Thread(map1).start();
                    }
                    if (isChooseMap2) {
                        this.dispose();
                        Map2_Defence map2 = new Map2_Defence();
                        new Thread(map2).start();
                    }
                } else {
                    if (isChooseMap1) {
                        this.dispose();
                        Map1_Survival map1 = new Map1_Survival();
                        new Thread(map1).start();
                    }
                    if (isChooseMap2) {
                        this.dispose();
                        Map2_Survival map2 = new Map2_Survival();
                        new Thread(map2).start();
                    }
                }
            } else if (response == JOptionPane.NO_OPTION) {
                System.out.println("User chose 'No'. Cancel the map selection.");
            } else {
                System.out.println("Dialog was closed or canceled without a specific choice.");
            }
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
        ChooseMap map = new ChooseMap();
        new Thread(map).start();
    }
}
