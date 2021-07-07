package entity;

import load.*;
import util.CopyUtil;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 魔塔实体
 * @author xuehy
 * @since 2020/6/9
 */
public final class Tower implements Cloneable, Serializable {

    private Player player;

    private HashMap<String, Wall> wallMap;
    private HashMap<String, Door> doorMap;
    private HashMap<String, Stair> stairMap;
    private HashMap<String, Item> itemMap;

    private HashMap<String, Monster> monsterMap;
    private HashMap<String, Shop> shopMap;
    private HashMap<String, NPC> npcMap;

    private Image[] floorImage = new Image[3];
    private Image[] wallImage = new Image[8];

    //需要保存的东西
    public boolean canUseFloorTransfer;
    public boolean canUseMonsterManual;
    public String specialGameMapNo;
    public int floor;

    private List<GameMap> gameMapList;
    private HashMap<String, GameMap> specialMap;
    //TODO(是否挑战额外楼层即是否挑战血影) 正式版需要修改为 false
    public static boolean specialFloor = false;

    public Tower clone() throws CloneNotSupportedException {
        Tower cloneTower = (Tower) super.clone();
        cloneTower.setPlayer(this.player.clone());
        try {
            cloneTower.gameMapList = CopyUtil.deepCopyList(this.gameMapList);
            cloneTower.specialMap = CopyUtil.deepCopy(this.specialMap);
            cloneTower.doorMap = CopyUtil.deepCopy(this.doorMap);
            cloneTower.monsterMap = CopyUtil.deepCopy(this.monsterMap);
            cloneTower.shopMap = CopyUtil.deepCopy(this.shopMap);
            cloneTower.npcMap = CopyUtil.deepCopy(this.npcMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cloneTower;
    }

    public Tower() {
        player = new Player();
        loadIcon();
        gameMapList = new LoadMap().initMap();
        specialMap = new LoadSpecialMap().initSpecialMap();

        monsterMap = new LoadMonster().initMonster();
        wallMap = new LoadWall().initWall();
        doorMap = new LoadDoor().initDoor();
        stairMap = new LoadStair().initStair();
        itemMap = new LoadItem().initItem();
        npcMap = new LoadNPC().initNPC();
        shopMap = new LoadShop().initShop();
    }

    private void loadIcon() {
        ImageIcon icon;
        for (int i = 1; i < 4; i++) {
            //System.out.println(getClass().getResource("/image/wall/floor0" + i + "_1.png"));
            icon = new ImageIcon(getClass().getResource("/image/wall/floor0" + i + "_1.png"));
            // 地板
            floorImage[i - 1] = icon.getImage();
        }
        for (int i = 1; i < 9; i++) {
            icon = new ImageIcon(getClass().getResource("/image/wall/wall0" + i + "_1.png"));
            // 墙
            wallImage[i - 1] = icon.getImage();
        }
        //玩家图标
        ImageIcon[][] playerIcon = new ImageIcon[4][4];
        for (int i = 0; i < 4; i++) {
            //帧数从1-4
            for (int j = 1; j <= 4; j++) {
                playerIcon[i][j - 1] = new ImageIcon(getClass().getResource("/image/player/player01_" + i + "_" + j + ".png"));
            }
        }
        player.setPlayerIcon(playerIcon);
    }

    public Image[] getFloorImage() {
        return floorImage;
    }

    public Image[] getWallImage() {
        return wallImage;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, Monster> getMonsterMap() {
        return monsterMap;
    }

    public Map<String, Wall> getWallMap() {
        return wallMap;
    }

    public Map<String, Door> getDoorMap() {
        return doorMap;
    }

    public Map<String, Stair> getStairMap() {
        return stairMap;
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public Map<String, NPC> getNpcMap() {
        return npcMap;
    }

    public Map<String, Shop> getShopMap() {
        return shopMap;
    }

    public List<GameMap> getGameMapList() {
        return gameMapList;
    }

    public Map<String, GameMap> getSpecialMap() {
        return specialMap;
    }

}
