/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the Leader Board page and its functions.
*/

package UI.GamePage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LeaderBoard extends JFrame implements Runnable {
    private JButton Back = new JButton();
    private JLabel LDB = new JLabel();
    private JLabel background = new JLabel();
    private JButton Map1 = new JButton();
    private JButton Map2 = new JButton();
    private JTable Display = new JTable();
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
    private String gameModeDef2;
    private String gameModeSur2;
    private String user1NameDef2;
    private String user1ScoreDef2;
    private String user1PlayTimeDef2;
    private String user2NameDef2;
    private String user2ScoreDef2;
    private String user2PlayTimeDef2;
    private String user3NameDef2;
    private String user3ScoreDef2;
    private String user3PlayTimeDef2;
    private String user1NameSur2;
    private String user1ScoreSur2;
    private String user1PlayTimeSur2;
    private String user2NameSur2;
    private String user2ScoreSur2;
    private String user2PlayTimeSur2;
    private String user3NameSur2;
    private String user3ScoreSur2;
    private String user3PlayTimeSur2;
    private boolean isAddedMap1 = false;
    private boolean isAddedMap2 = false;
    private DefaultTableModel model = new DefaultTableModel(
            new Object[][] {}, // Initial empty data
            new String[] { "Name", "Score", "Play Time" } // Column names
    );

    public LeaderBoard() {
        initial();
    }

    private void initial() {
        LDB.setFont(new java.awt.Font("Times New Roman", 1, 80)); // NOI18N
        LDB.setForeground(new java.awt.Color(0, 0, 0));
        LDB.setText("Leader Board");
        LDB.setVisible(true);
        LDB.setBounds(410, 0, 800, 80);
        this.add(LDB);

        readTextFromFile_Map1();// creat value for table
        readTextFromFile_Map2();
        Map1.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Map1.setVisible(true);
        Map1.setBounds(200, 600, 200, 50);
        Map1.setText("Map 1");
        this.add(Map1);
        Map1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMap1ActionPerformed(evt);
            }
        });

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

        Map2.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Map2.setVisible(true);
        Map2.setBounds(900, 600, 200, 50);
        Map2.setText("Map 2");
        this.add(Map2);

        Map2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMap2ActionPerformed(evt);
            }
        });

        Display = new JTable(model);
        Display.setFont(new java.awt.Font("Times New Roman", 1, 50));
        Display.setBounds(200, 100, 900, 450);
        Display.setRowHeight(50);
        add(Display);

        // Set up a JScrollPane for the table
        JScrollPane scrollPane = new JScrollPane(Display);
        scrollPane.setBounds(200, 100, 900, 450);
        add(scrollPane);

        background.setIcon(new ImageIcon(getClass().getResource("/Image/LeaderBoard.png")));
        background.setBounds(0, 0, 1320, 820);
        background.setVisible(true);
        this.add(background);

        this.setName("Leader Board");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setSize(1320, 820);
    }

    private void btnMap1ActionPerformed(ActionEvent evt) {
        if (isAddedMap2) {
            for (int i = 0; i < 8; i++) {
                model.removeRow(0);
            }
        }
        if (!isAddedMap1) {
            isAddedMap2 = false;
            model.addRow(new Object[] { gameModeDef, gameModeDef, gameModeDef });
            model.addRow(new Object[] { user1NameDef, user1ScoreDef, user1PlayTimeDef });
            model.addRow(new Object[] { user2NameDef, user2ScoreDef, user2PlayTimeDef });
            model.addRow(new Object[] { user3NameDef, user3ScoreDef, user3PlayTimeDef });
            model.addRow(new Object[] { gameModeSur, gameModeSur, gameModeSur });
            model.addRow(new Object[] { user1NameSur, user1ScoreSur, user1PlayTimeSur });
            model.addRow(new Object[] { user2NameSur, user2ScoreSur, user2PlayTimeSur });
            model.addRow(new Object[] { user3NameSur, user3ScoreSur, user3PlayTimeSur });
            isAddedMap1 = true;
        }
    }

    private void btnMap2ActionPerformed(ActionEvent evt) {
        if (isAddedMap1) {
            for (int i = 0; i < 8; i++) {
                model.removeRow(0);
            }
        }
        if (!isAddedMap2) {
            isAddedMap1 = false;
            model.addRow(new Object[] { gameModeDef2, gameModeDef2, gameModeDef2 });
            model.addRow(new Object[] { user1NameDef2, user1ScoreDef2, user1PlayTimeDef2 });
            model.addRow(new Object[] { user2NameDef2, user2ScoreDef2, user2PlayTimeDef2 });
            model.addRow(new Object[] { user3NameDef2, user3ScoreDef2, user3PlayTimeDef2 });
            model.addRow(new Object[] { gameModeSur2, gameModeSur2, gameModeSur2 });
            model.addRow(new Object[] { user1NameSur2, user1ScoreSur2, user1PlayTimeSur2 });
            model.addRow(new Object[] { user2NameSur2, user2ScoreSur2, user2PlayTimeSur2 });
            model.addRow(new Object[] { user3NameSur2, user3ScoreSur2, user3PlayTimeSur2 });
            isAddedMap2 = true;
        }
    }

    private void readTextFromFile_Map1() {
        String filePath = "E:\\TankShotter_LeaderBoard_Map1.txt";
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
            gameModeDef2 = mode;
            String mode1 = lines.get(5);
            gameModeSur2 = mode1;
            for (int i = 2; i < 5; i++) {
                String line = lines.get(i);
                String[] parts = line.split("\\s+"); // Split by one or more spaces

                if (parts.length == 3) { // Ensure there are exactly 3 parts
                    String name = parts[0];
                    String score = parts[1];
                    String playTime = parts[2];
                    if (i == 2) {
                        user1NameDef2 = name;
                        user1ScoreDef2 = score;
                        user1PlayTimeDef2 = playTime;
                    } else if (i == 3) {
                        user2NameDef2 = name;
                        user2ScoreDef2 = score;
                        user2PlayTimeDef2 = playTime;
                    } else if (i == 4) {
                        user3NameDef2 = name;
                        user3ScoreDef2 = score;
                        user3PlayTimeDef2 = playTime;
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
                        user1NameSur2 = name;
                        user1ScoreSur2 = score;
                        user1PlayTimeSur2 = playTime;
                    } else if (i == 7) {
                        user2NameSur2 = name;
                        user2ScoreSur2 = score;
                        user2PlayTimeSur2 = playTime;
                    } else if (i == 8) {
                        user3NameSur2 = name;
                        user3ScoreSur2 = score;
                        user3PlayTimeSur2 = playTime;
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
        LeaderBoard ld = new LeaderBoard();
        new Thread(ld).start();
    }

}
