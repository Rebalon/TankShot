import UI.GamePage.HomePage;

public class Main {

    public static void main(String[] args) {
        HomePage home = new HomePage(false);
        new Thread(home).start();
    }

}