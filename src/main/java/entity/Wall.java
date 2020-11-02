package entity;

import javax.swing.*;

public final class Wall extends Entity {

    public Wall(String id, ImageIcon imageIcon) {
        this.id = id;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

    public Wall(String id, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

}
