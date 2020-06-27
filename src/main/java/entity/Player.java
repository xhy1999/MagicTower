package entity;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xhy
 */
public class Player {

    public static final int DIRECTION_UP = 0, DIRECTION_DOWN = 1, DIRECTION_LEFT = 2, DIRECTION_RIGHT = 3;

    public String name;
    public int hp;
    public int attack;
    public int defense;
    public int exp;
    public int money;
    public int level;
    public int yKey;
    public int bKey;
    public int rKey;
    public int maxFloor;
    public int minFloor;
    public byte x;
    public byte y;

    public Map<String, Integer> inventory;

    /**
     * 注意 不需要父类的icon
     * [方向][帧数]
     */
    ImageIcon[][] playerIcon;

    public Player() {
        this.name = "Hero";
        this.hp = 2000;
        this.attack = 100;
        this.defense = 100;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.yKey = 100;
        this.bKey = 100;
        this.rKey = 100;
        this.maxFloor = 1;
        this.minFloor = 1;
        this.x = 0;
        this.y = 0;
        this.inventory = new HashMap<>();
        this.playerIcon = new ImageIcon[4][4];
    }

    public ImageIcon[][] getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(ImageIcon[][] playerIcon) {
        this.playerIcon = playerIcon;
    }

}
