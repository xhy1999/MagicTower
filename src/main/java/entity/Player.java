package entity;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xhy
 */
public class Player implements Cloneable {

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
        //TODO 正式版这里要改为 2000
        this.hp = 20000;
        //TODO 正式版这里要改为 10
        this.attack = 10000;
        //TODO 正式版这里要改为 10
        this.defense = 10000;
        //TODO 正式版这里要改为 0
        this.exp = 0;
        //TODO 正式版这里要改为 0
        this.money = 0;
        //TODO 正式版这里要改为 1
        this.level = 1;
        //TODO 正式版这里要改为 0
        this.yKey = 10;
        //TODO 正式版这里要改为 0
        this.bKey = 10;
        //TODO 正式版这里要改为 0
        this.rKey = 10;
        this.maxFloor = 0;
        this.minFloor = 0;
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

    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", exp=" + exp + ", money=" + money + ", level=" + level + ", yKey=" + yKey + ", bKey=" + bKey + ", rKey=" + rKey + ", maxFloor=" + maxFloor + ", minFloor=" + minFloor + ", x=" + x + ", y=" + y + ", inventory=" + inventory + ", playerIcon=" + Arrays.toString(playerIcon) + '}';
    }
}
