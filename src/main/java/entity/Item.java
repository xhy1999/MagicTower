package entity;

import javax.swing.*;

/**
 * 游戏道具
 * @author xuehy
 * @since 2020/6/9
 */
public final class Item extends Entity {

    public String msg;

    public Item(String id, String name, String msg, ImageIcon imageIcon) {
        this.id = id;
        this.name = name;
        this.msg = msg;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

}
