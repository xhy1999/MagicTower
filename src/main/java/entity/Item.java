package entity;

import javax.swing.*;

public class Item extends Entity {

    public Item(String id, String name, ImageIcon imageIcon) {
        this.id = id;
        this.name = name;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

    public Item(String id, String name, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

}
