/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: draw the Home page and its functions.
*/

package UI.GamePage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomePage extends JFrame implements Runnable {
    private JLabel background = new JLabel();
    private JLabel jlabel1 = new JLabel();
    private JButton newGame = new JButton();
    private JButton LeaderBoard = new JButton();
    private JButton Guild = new JButton();
    private String savePath = null;
    private static final String GAME_FOLDER_KEY = "game_folder";

    public HomePage() {
        initial();
    }

    private void initial() {

        // Get and check if game folder was chosen
        Preferences prefs = Preferences.userRoot().node(HomePage.class.getName());
        // Retrieve the saved game folder path
        String savedGameFolder = prefs.get(GAME_FOLDER_KEY, null);
        SelectSaveLocation selectSaveLocation = new SelectSaveLocation();
        if (savedGameFolder == null || !selectSaveLocation.isValidDirectory(savedGameFolder)) {
            savePath = selectSaveLocation.Select();
        } else {
            savePath = savedGameFolder;
            // System.out.println("Game folder is located at: " + savedGameFolder);
        }
        // System.out.println(installationPath);

        jlabel1.setFont(new java.awt.Font("Times New Roman", 1, 80)); // NOI18N
        jlabel1.setForeground(new java.awt.Color(0, 0, 0));
        jlabel1.setText("Tank Shooter");
        jlabel1.setVisible(true);
        jlabel1.setBounds(410, 0, 500, 80);
        this.add(jlabel1);

        newGame.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        newGame.setText("New Game");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewGameActionPerformed(evt);
            }
        });
        newGame.setVisible(true);
        newGame.setBounds(500, 200, 300, 50);
        this.add(newGame);

        LeaderBoard.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        LeaderBoard.setText("LeaderBoard");
        LeaderBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaderBoardActionPerformed(evt);
            }
        });
        LeaderBoard.setVisible(true);
        LeaderBoard.setBounds(400, 300, 500, 50);
        this.add(LeaderBoard);

        Guild.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        Guild.setText("How to play???");
        Guild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuildActionPerformed(evt);
            }
        });
        Guild.setVisible(true);
        Guild.setBounds(400, 400, 500, 50);
        this.add(Guild);

        this.setName("HomePage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        background.setIcon(new ImageIcon(getClass().getResource("/Image/HomePage.png")));
        background.setBounds(0, 0, 1320, 820);
        background.setVisible(true);

        initialLeaderBoardFile();

        this.add(background);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setSize(1320, 820);
    }

    private void btnnewGameActionPerformed(ActionEvent evt) {
        this.dispose();
        ChooseMap ch = new ChooseMap();
        new Thread(ch).start();
    }

    private void btnLeaderBoardActionPerformed(ActionEvent evt) {
        this.dispose();
        LeaderBoard LB = new LeaderBoard(savePath);
        new Thread(LB).start();
    }

    private void btnGuildActionPerformed(ActionEvent evt) {
        this.dispose();
        Guild g = new Guild();
        new Thread(g).start();
    }

    private void initialLeaderBoardFile() {
        // Define the file path and name
        // System.out.println(savePath);
        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";
        // Combine file path and name
        Path path = Paths.get(filePath, fileName);

        // // Check if the file exists
        if (Files.exists(path)) {
            // System.out.println("File exists: " + path.toString());
            return;
        }
        // } else {
        // System.out.println("File does not exist: " + path.toString());
        // }
        String content = "Name  Score  Play Time\n" +
                "Defence\n" +
                "Hung     0   0:00:00\n" +
                "Nam      0   0:00:00\n" +
                "Tuan     0   0:00:00\n" +
                "Survival\n" +
                "Khai     0   0:00:00\n" +
                "Nam      0   0:00:00\n" +
                "Tuan     0   0:00:00\n";
        // Create the file and write content to it
        boolean isFileCreated = createAndWriteFileUsingNIO(filePath, fileName, content);
        if (isFileCreated) {
            System.out.println("File created and saved successfully!");
        } else {
            System.out.println("File creation failed.");
        }
        String filePath1 = savePath; // Change this to your desired directory
        String fileName1 = "TankShotter_LeaderBoard_Map2.txt";
        String content1 = "Name  Score  Play Time\n" +
                "Defence\n" +
                "Hung     0   0:00:00\n" +
                "Nam      0   0:00:00\n" +
                "Tuan     0   0:00:00\n" +
                "Survival\n" +
                "Hung     0   0:00:00\n" +
                "Nam      0   0:00:00\n" +
                "Tuan     0   0:00:00\n";
        // Create the file and write content to it
        boolean isFileCreated1 = createAndWriteFileUsingNIO(filePath1, fileName1, content1);
        if (isFileCreated1) {
            System.out.println("File created and saved successfully!");
        } else {
            System.out.println("File creation failed.");
        }
    }

    public static boolean createAndWriteFileUsingNIO(String filePath, String fileName, String content) {
        try {
            // Create directories if they do not exist
            Path directoryPath = Paths.get(filePath);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Create the file path
            Path filePathWithFileName = Paths.get(filePath, fileName);

            // Write content to the file
            Files.write(filePathWithFileName, content.getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
        HomePage home = new HomePage();
        new Thread(home).start();
    }
}
