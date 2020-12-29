package entity;

import lombok.Data;
import score.UploadScore;
import util.CopyUtil;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

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
    public int killNum;
    public int killBossNum;     //击杀boss总数
    public int killBoss1Num;    //击杀boss1(21层吸血鬼)数量,最高为1
    public int killBoss2Num;    //击杀boss2(大鱿鱼)数量,最高为9
    public int killBoss3Num;    //击杀boss3(魔龙)数量,最高为9
    public int stepNum;
    public long startPlayTime;
    private Integer playerScore;

    public HashMap<String, Integer> inventory;

    /**
     * 注意 不需要父类的icon
     * [方向][帧数]
     */
    ImageIcon[][] playerIcon;

    public Player() {
        this.name = "勇士";
        //TODO 正式版这里要改为 2000
        this.hp = 209020;
        //TODO 正式版这里要改为 10
        this.attack = 4307;
        //TODO 正式版这里要改为 10
        this.defense = 3929;
        //TODO 正式版这里要改为 0
        this.exp = 14;
        //TODO 正式版这里要改为 0
        this.money = 7;
        //TODO 正式版这里要改为 1
        this.level = 192;
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
        this.killNum = 0;
        this.killBossNum = 0;
        this.killBoss1Num = 0;
        this.killBoss2Num = 0;
        this.killBoss3Num = 0;
        this.stepNum = 0;
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
        Player clonePlayer = (Player) super.clone();
        try {
            clonePlayer.setInventory(CopyUtil.deepCopy(this.inventory));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clonePlayer;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", exp=" + exp + ", money=" + money + ", level=" + level + ", yKey=" + yKey + ", bKey=" + bKey + ", rKey=" + rKey + ", maxFloor=" + maxFloor + ", minFloor=" + minFloor + ", x=" + x + ", y=" + y + ", inventory=" + inventory + ", playerIcon=" + Arrays.toString(playerIcon) + '}';
    }

    public void calculateScore() {
        this.killBossNum = this.killBoss1Num + this.killBoss2Num + this.killBoss3Num;
        double score = hp * 0.01 + attack * 1.8 + defense * 1.8 + exp / 3 + money * 0.3 + yKey * 3 + bKey * 15 + rKey * 30;
        score = score * (1 + 0.1 * this.killBoss1Num + 0.2 * this.killBoss2Num + 0.5 * this.killBoss3Num);
        this.playerScore = (int) score;
    }

}
