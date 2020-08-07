package main;

import entity.*;
import load.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Xhy
 */
public class Tower {

    /**
     * 主角
     */
    private Player player;


    /**
     * 怪物
     */
    private Map<String, Monster> monsterMap;
    private Map<String, Wall> wallMap;
    private Map<String, Door> doorMap;
    private Map<String, Stair> stairMap;
    private Map<String, Item> itemMap;
    private Map<String, NPC> npcMap;
    private Map<String, Shop> shopMap;

    private Image[] floorImage = new Image[3];
    private Image[] wallImage = new Image[8];

    private List<GameMap> gameMapList;
    private Map<String, GameMap> specialMap;
    //TODO 正式版需要修改为 false
    public static boolean specialFloor = true;

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

    public void setFloorImage(Image[] floorImage) {
        this.floorImage = floorImage;
    }

    public Image[] getWallImage() {
        return wallImage;
    }

    public void setWallImage(Image[] wallImage) {
        this.wallImage = wallImage;
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
