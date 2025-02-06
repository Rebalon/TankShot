package UI.GamePage;

import javax.swing.*;
import java.io.File;
import java.util.prefs.Preferences;

public class SelectSaveLocation {
    private static final String GAME_FOLDER_KEY = "game_folder";

    public String Select() {
        File selectedDirectory = null;
        // Select loop until finish choosing an approriate path
        while (true) {
            // Create a file chooser that can select directories
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Installation Location");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow selecting directories
            fileChooser.setAcceptAllFileFilterUsed(false);

            // Show the file chooser and get the user's response
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedDirectory = fileChooser.getSelectedFile();
                // Double check
                if (selectedDirectory != null && selectedDirectory.exists() && selectedDirectory.isDirectory()) {
                    Preferences prefs = Preferences.userRoot().node(HomePage.class.getName());
                    prefs.put(GAME_FOLDER_KEY, selectedDirectory.getAbsolutePath());
                    break; // exit loop once a valid directory is selected
                }
            } else {
                // Show an error message with Yes and No buttons
                int response = JOptionPane.showConfirmDialog(null,
                        "You must select a location to store data! \nDo you want to quit?",
                        "Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                // If user clicks 'Yes', exit the application
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "The application will now close.");
                    System.exit(0); // This will terminate the application
                }
            }
        }
        return selectedDirectory != null ? selectedDirectory.getAbsolutePath() : "";
    }

    public boolean isValidDirectory(String path) {
        File folder = new File(path);
        return folder.exists() && folder.isDirectory();
    }
}
