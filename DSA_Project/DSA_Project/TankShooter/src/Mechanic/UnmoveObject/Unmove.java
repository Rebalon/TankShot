/* Name: Nguyen Van Lac Thien - ITCSIU22245
 Purpose: Abstract class Unmove to generalization what method need to implement in the subclass. Avoiding mistake.
*/

package Mechanic.UnmoveObject;

import java.awt.Point;

import javax.swing.JLabel;

public abstract class Unmove {
    public abstract JLabel drawObject();

    public abstract void isDamage();

    public abstract Point getPos();

    public abstract boolean isDestroy();

    public abstract JLabel getJLabel();
}
