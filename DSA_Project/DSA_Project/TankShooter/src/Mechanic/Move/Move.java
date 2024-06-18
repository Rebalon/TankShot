package Mechanic.Move;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JLabel;

import Mechanic.UnmoveObject.Unmove;

public abstract class Move {
    public abstract JLabel draw();

    public abstract void move();

    public abstract Point getPos();

    public abstract void isCollision(LinkedList<Unmove> obstacle, Point previous, Point newPos, LinkedList<Move> tank);

    public abstract void isDamage();

    public abstract boolean isDestroy();

    public abstract JLabel getImage();
}
