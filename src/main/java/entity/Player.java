package entity;

import javax.swing.*;

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

    /**
     * 注意 不需要父类的icon
     * [方向][帧数]
     */
    ImageIcon[][] playerIcon;

    public Player() {
        this.name = "Hero";
        this.hp = 999999;
        this.attack = 1000;
        this.defense = 1000;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.yKey = 999;
        this.bKey = 999;
        this.rKey = 999;
        this.maxFloor = 1;
        this.minFloor = 1;
        this.x = 6;
        this.y = 10;
        this.playerIcon = new ImageIcon[4][4];
    }

    public ImageIcon[][] getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(ImageIcon[][] playerIcon) {
        this.playerIcon = playerIcon;
    }

}
