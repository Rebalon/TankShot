package Mechanic.Move;

import java.awt.Point;

import javax.swing.JLabel;

public abstract class Move {
    public abstract JLabel draw();

    public abstract void move();

    public abstract Point getPos();
}
