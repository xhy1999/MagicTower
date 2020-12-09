package entity;

import lombok.Data;
import score.UploadScore;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xhy
 */
@Data
public final class Player implements Cloneable {

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
    public int killNum = 823;
    public int killBossNum = 8;
    public int stepNum = 4241;
    public long startPlayTime;

    public Map<String, Integer> inventory;

    /**
     * 注意 不需要父类的icon
     * [方向][帧数]
     */
    ImageIcon[][] playerIcon;

    public Player() {
        this.name = "勇士";
        //TODO 正式版这里要改为 2000
        this.hp = 136203;
        //TODO 正式版这里要改为 10
        this.attack = 4623;
        //TODO 正式版这里要改为 10
        this.defense = 4195;
        //TODO 正式版这里要改为 0
        this.exp = 32643;
        //TODO 正式版这里要改为 0
        this.money = 86431;
        //TODO 正式版这里要改为 1
        this.level = 151;
        //TODO 正式版这里要改为 0
        this.yKey = 9;
        //TODO 正式版这里要改为 0
        this.bKey = 99;
        //TODO 正式版这里要改为 0
        this.rKey = 999;
        this.maxFloor = 0;
        this.minFloor = 0;
        this.x = 0;
        this.y = 0;
        this.inventory = new HashMap<>();
        this.playerIcon = new ImageIcon[4][4];
        this.startPlayTime = UploadScore.getNetworkTime();
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
