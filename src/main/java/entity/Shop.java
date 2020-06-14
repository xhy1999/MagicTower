package entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Shop extends Entity {

    public int buyNum;
    public String dialogue;
    public boolean canMeet;
    public List<String> sellList = new ArrayList<String>();

    public Shop(String id, String name, boolean canMeet, ImageIcon imageIcon1) {
        this.id = id;
        this.name = name;
        this.canMeet = canMeet;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon1;
    }

    public Shop(String id, String name, boolean canMeet, String dialogue, List<String> sellList, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.canMeet = canMeet;
        this.dialogue = dialogue;
        this.sellList = sellList;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

}
