package UI.Map1;

public class Map2_Defence implements Runnable {
    public Map2_Defence() {
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
        Map2_Defence map2 = new Map2_Defence();
        new Thread(map2).start();
    }
}
