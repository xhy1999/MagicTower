package entity;

import javax.swing.*;

public class Stair extends Entity {

    public Stair(String id, ImageIcon imageIcon) {
        this.id = id;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

}
