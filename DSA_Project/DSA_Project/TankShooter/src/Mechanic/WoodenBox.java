package Mechanic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class WoodenBox {
    private boolean isDamage = false;
    private int Health = 4;
    private Point Pos;

    public boolean isDamage() {
        return isDamage;
    }

    public void setDamage(boolean isDamage) {
        Health--;
        this.isDamage = isDamage;
    }

    public WoodenBox(Point position) {
        this.Pos = position;
    }

    private void drawState(Graphics g) {
        if (Health <= 2) {
            Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\WoodenBoxHalf.png")
                    .getImage();
            g.drawImage(img, Pos.x, Pos.y, null);
        } else {
            Image img = new ImageIcon("DSA_Project\\DSA_Project\\TankShooter\\src\\Image\\WoodenBoxFullHealth.png")
                    .getImage();
            g.drawImage(img, Pos.x, Pos.y, null);
        }

    }
}
