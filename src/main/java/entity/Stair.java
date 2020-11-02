package entity;

import main.TowerPanel;

import javax.swing.*;

public final class Stair extends Entity {

    public Stair(String id, ImageIcon imageIcon) {
        this.id = id;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

    public Stair(String id, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

    public void script(TowerPanel towerPanel, Tower tower, String specialGameMapNo) {
        if (this.id.equals("stair03_1")) {
            towerPanel.musicPlayer.fall();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_1";
            towerPanel.floor = -1;
            tower.getPlayer().x = 5;
            tower.getPlayer().y = 10;
        }
        else if (this.id.equals("stair04_1_1")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_2";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
        else if (this.id.equals("stair04_1_2")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_1";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).downPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).downPositionY;
        }
        else if (this.id.equals("stair04_1_3")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_3";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
        else if (this.id.equals("stair04_1_4")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_2";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).downPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).downPositionY;
        }
        else if (this.id.equals("stair04_2_1")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(23);
            towerPanel.specialGameMapNo = "23_L";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
        else if (this.id.equals("stair04_2_2")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(22);
            towerPanel.specialGameMapNo = null;
            towerPanel.floor = 22;
            towerPanel.DIRECTION = towerPanel.DIRECTION_DOWN;
            tower.getPlayer().x = tower.getGameMapList().get(towerPanel.floor).downPositionX;
            tower.getPlayer().y = tower.getGameMapList().get(towerPanel.floor).downPositionY;
        }
        else if (this.id.equals("stair04_3_1")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(23);
            towerPanel.specialGameMapNo = "23_R";
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
        else if (this.id.equals("stair04_3_2")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(22);
            towerPanel.specialGameMapNo = null;
            towerPanel.floor = 22;
            towerPanel.DIRECTION = towerPanel.DIRECTION_DOWN;
            tower.getPlayer().x = tower.getGameMapList().get(towerPanel.floor).downPositionX;
            tower.getPlayer().y = tower.getGameMapList().get(towerPanel.floor).downPositionY;
        }
        else if (this.id.equals("stair04_4")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(0);
            towerPanel.specialGameMapNo = "hell";
            towerPanel.floor = -1;
            towerPanel.DIRECTION = towerPanel.DIRECTION_DOWN;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
    }

}
