package Mechanic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WoodenBox {
    private boolean isDamage = false;
    private int Health = 4;

    public boolean isDamage() {
        return isDamage;
    }

    public void setDamage(boolean isDamage) {
        this.isDamage = isDamage;
    }

    public WoodenBox() {
    }

    private void drawState(Graphics g) {
        int initialX = 10;
        if (Health <= 2) {

        } else {
            Image img = new ImageIcon("src\\Image\\WoodenBox.png").getImage();
            g.drawImage(img, initialX + 40, 30, null);
        }

    }
}
