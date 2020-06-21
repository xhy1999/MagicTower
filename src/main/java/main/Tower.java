package main;

import entity.GameMap;
import entity.Player;
import load.LoadMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xhy
 */
public class Tower {

    /**
     * 人物方向
     */
    public int DIRECTION;

    /**
     * 楼层
     */
    private int floor = 1;

    /**
     * 主角
     */
    public Player player;


    private Image[] floorImage = new Image[3];
    private Image[] wallImage = new Image[8];

    public List<GameMap> gameMapList = new ArrayList<>();

//    public AudioPlayer audioPlayer;

    public Tower() {
        player = new Player();
        loadIcon();
        gameMapList = new LoadMap().initMap();
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Player getPlayer() {
        return player;
    }


    public List<GameMap> getGameMapList() {
        return gameMapList;
    }

}
