package UI.GamePage;

import java.io.File;

public class SearchAllDrives {

    public static boolean searchFileInDrive(File directory, String fileName) {
        // Check if directory exists and is a valid directory
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Check if this is the file you're looking for
                    if (file.getName().equals(fileName)) {
                        return true; // File found
                    }

                    // If it's a directory, search it recursively
                    if (file.isDirectory()) {
                        boolean found = searchFileInDrive(file, fileName);
                        if (found) {
                            return true; // File found in subdirectory
                        }
                    }
                }
            }
        }
        return false; // File not found
    }

    public static void main(String[] args) {
        String fileName = "DataTankShotter.txt"; // The name of the file you're searching for

        // Get all the root directories (drives)
        File[] roots = File.listRoots();

        // Iterate over each root directory (drive) and search for the file
        for (File root : roots) {
            System.out.println("Searching in drive: " + root.getAbsolutePath());
            boolean fileFound = searchFileInDrive(root, fileName);

            if (fileFound) {
                System.out.println("File '" + fileName + "' found in drive " + root.getAbsolutePath());
                return; // Exit the program once the file is found
            }
        }

        System.out.println("File '" + fileName + "' not found in any drive.");
    }
}
