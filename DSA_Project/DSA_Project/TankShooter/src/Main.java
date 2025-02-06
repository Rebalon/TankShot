/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: Main method to run the game
*/

import UI.GamePage.HomePage;

public class Main {

    public static void main(String[] args) {
        HomePage home = new HomePage();// if file created set to false
        new Thread(home).start();
    }

}