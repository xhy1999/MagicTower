package entity;

import main.Tower;

import javax.swing.*;
import java.util.List;

public class NPC extends Entity {

    public List<Dialogue> dialogues;
    public boolean canRemove;

    public NPC(String id, String name, boolean canRemove, ImageIcon imageIcon, List<Dialogue> dialogues) {
        this.id = id;
        this.name = name;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
        this.dialogues = dialogues;
    }

    public NPC(String id, String name, boolean canRemove, ImageIcon imageIcon1, ImageIcon imageIcon2, List<Dialogue> dialogues) {
        this.id = id;
        this.name = name;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
        this.dialogues = dialogues;
    }

    public void script(Tower tower) {
        if (this.id.equals("npc01_1")) {
            tower.player.yKey++;
            tower.player.bKey++;
            tower.player.rKey++;
            tower.gameMapList.get(2).layer1[8][4] = "npc01_2";
        }
    }

}
