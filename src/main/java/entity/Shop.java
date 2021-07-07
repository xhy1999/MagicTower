package entity;

import javax.swing.*;
import java.io.Serializable;

/**
 * 商店
 * @author xuehy
 * @since 2020/6/9
 */
public final class Shop extends Entity implements Cloneable, Serializable {

    public String need;
    public int price;
    public int useNum;
    public String dialogue;
    public boolean canMeet;
    public Sell sell;

    public Shop(String id, String name, String need, int price, boolean canMeet, ImageIcon imageIcon1) {
        this.id = id;
        this.name = name;
        this.need = need;
        this.price = price;
        this.canMeet = canMeet;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon1;
    }

    public Shop(String id, String name, String need, int price, boolean canMeet, String dialogue, Sell sell, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.need = need;
        this.price = price;
        this.canMeet = canMeet;
        this.dialogue = dialogue;
        this.sell = sell;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

}
