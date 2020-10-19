package entity;

import javax.swing.*;

public class Door extends Entity {

    //openable为true才能打开
    public boolean openable;

    public Door(String id, boolean openable, ImageIcon imageIcon1, ImageIcon imageIcon2, ImageIcon imageIcon3, ImageIcon imageIcon4) {
        this.id = id;
        this.openable = openable;
        this.icon = new ImageIcon[4];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
        this.icon[2] = imageIcon3;
        this.icon[3] = imageIcon4;
    }

}
