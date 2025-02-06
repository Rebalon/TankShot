package UI.Map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.prefs.Preferences;
import UI.GamePage.HomePage;

public class CompareScore {
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
    private static final String GAME_FOLDER_KEY = "game_folder";
    private String savePath = null;
    private int score;
    private String formattedTime;
    private int CaseOfResult;

    public CompareScore(int UserScore, String userTime) {
        this.score = UserScore;
        this.formattedTime = userTime;
        // Get and check if game folder was chosen
        Preferences prefs = Preferences.userRoot().node(HomePage.class.getName());
        // Retrieve the saved game folder path
        String savedGameFolder = prefs.get(GAME_FOLDER_KEY, null);
        savePath = savedGameFolder;
        Compare();
    }

    private void Compare() {
        readTextFromFile_Map1();
        if (this.score >= Integer.parseInt(user1ScoreDef)) {
            if (this.score > Integer.parseInt(user1ScoreDef)) {
                CaseOfResult = 1;
            } else {// score = 1st
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player1time = (Integer.valueOf(user1PlayTimeDef.charAt(0)) * 10
                        + Integer.valueOf(user1PlayTimeDef.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user1PlayTimeDef.charAt(3)) * 10 + Integer.valueOf(user1PlayTimeDef.charAt(4)))
                                * 60
                        + Integer.valueOf(user1PlayTimeDef.charAt(6)) * 10
                        + Integer.valueOf(user1PlayTimeDef.charAt(7));
                if (time >= player1time) {// rank 2nd
                    CaseOfResult = 2;
                } else {// rank 1st
                    CaseOfResult = 3;
                }
            }
        }

        else if (this.score >= Integer.parseInt(user2ScoreDef)) {
            if (this.score > Integer.parseInt(user2ScoreDef)) {
                CaseOfResult = 4;
            } else {// score = 2nd
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player2time = (Integer.valueOf(user2PlayTimeDef.charAt(0)) * 10
                        + Integer.valueOf(user2PlayTimeDef.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user2PlayTimeDef.charAt(3)) * 10 + Integer.valueOf(user2PlayTimeDef.charAt(4)))
                                * 60
                        + Integer.valueOf(user2PlayTimeDef.charAt(6)) * 10
                        + Integer.valueOf(user2PlayTimeDef.charAt(7));
                if (time >= player2time) {// rank 3
                    CaseOfResult = 5;
                } else {// rank 2
                    CaseOfResult = 6;
                }

            }
        } else if (this.score >= Integer.parseInt(user3ScoreDef)) {
            if (this.score > Integer.parseInt(user3ScoreDef)) {
                CaseOfResult = 7;
            } else {
                int time = (Integer.valueOf(formattedTime.charAt(0)) * 10 + Integer.valueOf(formattedTime.charAt(1)))
                        * 60 * 60 +
                        (Integer.valueOf(formattedTime.charAt(3)) * 10 + Integer.valueOf(formattedTime.charAt(4))) * 60
                        + Integer.valueOf(formattedTime.charAt(6)) * 10
                        + Integer.valueOf(formattedTime.charAt(7));
                int player3time = (Integer.valueOf(user3PlayTimeDef.charAt(0)) * 10
                        + Integer.valueOf(user3PlayTimeDef.charAt(1))) * 60 * 60 +
                        (Integer.valueOf(user3PlayTimeDef.charAt(3) * 10 + Integer.valueOf(user3PlayTimeDef.charAt(4)))
                                * 60
                                + Integer.valueOf(user3PlayTimeDef.charAt(6)) * 10
                                + Integer.valueOf(user3PlayTimeDef.charAt(7)));
                if (time < player3time) {// rank 3
                    CaseOfResult = 8;
                }
            }
        } else {// out of rank
            CaseOfResult = 9;
        }
    }

    private void readTextFromFile_Map1() {
        String filePath = savePath + "\\TankShotter_LeaderBoard_Map1.txt";
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

    public void Case1(String Username) {
        user3NameDef = user2NameDef;
        user2NameDef = user1NameDef;
        user1NameDef = Username;

        user3ScoreDef = user2ScoreDef;
        user2ScoreDef = user1ScoreDef;
        user1ScoreDef = String.valueOf(score);

        user3PlayTimeDef = user2PlayTimeDef;
        user2PlayTimeDef = user1PlayTimeDef;
        user1PlayTimeDef = formattedTime;
        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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
            System.out.println(content);
        } else {
            System.out.println("File creation failed.");
        }
    }

    public void Case2(String Username) {
        user3NameDef = user2NameDef;
        user2NameDef = Username;

        user3ScoreDef = user2ScoreDef;
        user2ScoreDef = String.valueOf(score);

        user3PlayTimeDef = user2PlayTimeDef;
        user2PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case3(String Username) {

        user3NameDef = user2NameDef;
        user2NameDef = user1NameDef;
        user1NameDef = Username;

        user3ScoreDef = user2ScoreDef;
        user2ScoreDef = user1ScoreDef;
        user1ScoreDef = String.valueOf(score);

        user3PlayTimeDef = user2PlayTimeDef;
        user2PlayTimeDef = user1PlayTimeDef;
        user1PlayTimeDef = formattedTime;
        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case4(String Username) {
        user3NameDef = user2NameDef;
        user2NameDef = Username;

        user3ScoreDef = user2ScoreDef;
        user2ScoreDef = String.valueOf(score);

        user3PlayTimeDef = user2PlayTimeDef;
        user2PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case5(String Username) {
        user3NameDef = Username;

        user3ScoreDef = String.valueOf(score);

        user3PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case6(String Username) {
        user3NameDef = user2NameDef;
        user2NameDef = Username;

        user3ScoreDef = user2ScoreDef;
        user2ScoreDef = String.valueOf(score);

        user3PlayTimeDef = user2PlayTimeDef;
        user2PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case7(String Username) {
        user3NameDef = Username;

        user3ScoreDef = String.valueOf(score);

        user3PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public void Case8(String Username) {

        user3NameDef = Username;

        user3ScoreDef = String.valueOf(score);

        user3PlayTimeDef = formattedTime;

        String filePath = savePath; // Change this to your desired directory
        String fileName = "TankShotter_LeaderBoard_Map1.txt";

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

    public int getCaseOfResult() {
        return CaseOfResult;
    }
}
