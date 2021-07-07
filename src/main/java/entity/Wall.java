package entity;

import javax.swing.*;

/**
 * 墙
 * @author xuehy
 * @since 2020/6/9
 */
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
