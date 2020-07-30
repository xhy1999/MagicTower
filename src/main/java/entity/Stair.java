package entity;

import main.MusicPlayer;
import main.Tower;
import main.TowerPanel;

import javax.swing.*;

public class Stair extends Entity {

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
            towerPanel.canUseFloorTransfer = false;
            towerPanel.floor = -1;
            tower.getPlayer().x = 5;
            tower.getPlayer().y = 10;
        }
        else if (this.id.equals("stair04_1")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_2";
            towerPanel.canUseFloorTransfer = false;
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
        else if (this.id.equals("stair04_2")) {
            towerPanel.musicPlayer.specialStair();
            towerPanel.musicPlayer.playBackgroundMusic(-1);
            towerPanel.specialGameMapNo = "1_1";
            towerPanel.canUseFloorTransfer = false;
            towerPanel.floor = -1;
            tower.getPlayer().x = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionX;
            tower.getPlayer().y = tower.getSpecialMap().get(towerPanel.specialGameMapNo).upPositionY;
        }
    }

}
