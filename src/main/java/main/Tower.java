package main;

import entity.*;
import load.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xhy
 */
public class Tower implements Cloneable {

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
    //TODO 正式版需要修改为 false
    public static boolean specialFloor = true;

    private static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> copy_list = (List<T>) in.readObject();
        return copy_list;
    }

    private static <T extends Serializable> T deepCopyMap(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        T clonedObj = (T) ois.readObject();
        ois.close();
        return clonedObj;
    }

    public Tower clone() throws CloneNotSupportedException {
        Tower cloneTower = (Tower) super.clone();
        cloneTower.setPlayer(this.player.clone());
        try {
            cloneTower.gameMapList = deepCopy(this.gameMapList);
            cloneTower.specialMap = deepCopyMap(this.specialMap);
            cloneTower.monsterMap = deepCopyMap(this.monsterMap);
            cloneTower.shopMap = deepCopyMap(this.shopMap);
            cloneTower.npcMap = deepCopyMap(this.npcMap);
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

    public void setGameMapList(List<GameMap> gameMapList) {
        this.gameMapList = gameMapList;
    }

    public void setSpecialMap(HashMap<String, GameMap> specialMap) {
        this.specialMap = specialMap;
    }

    public List<GameMap> getGameMapList() {
        return gameMapList;
    }

    public Map<String, GameMap> getSpecialMap() {
        return specialMap;
    }

}
