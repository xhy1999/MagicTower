package entity;

import main.Tower;

import javax.swing.*;
import java.util.List;

public class NPC extends Entity {

    public List<Dialogue> dialogues;
    //是否可以开始对话
    public boolean canMeet;
    //对话完成后是否消失
    public boolean canRemove;

    /**
     *
     * @param id        Id
     * @param name      名字
     * @param canMeet   是否可以开始对话
     * @param canRemove 对话完成后是否消失
     * @param imageIcon NPC的icon图片
     * @param dialogues 对话内容列表
     */
    public NPC(String id, String name, boolean canMeet, boolean canRemove, ImageIcon imageIcon, List<Dialogue> dialogues) {
        this.id = id;
        this.name = name;
        this.canMeet = canMeet;
        this.canRemove = canRemove;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
        this.dialogues = dialogues;
    }

    /**
     *
     * @param id            Id
     * @param name          名字
     * @param canMeet       是否可以开始对话
     * @param canRemove     对话完成后是否消失
     * @param imageIcon1    NPC的第一帧icon图片
     * @param imageIcon2    NPC的第二帧icon图片
     * @param dialogues     对话内容列表
     */
    public NPC(String id, String name, boolean canMeet, boolean canRemove, ImageIcon imageIcon1, ImageIcon imageIcon2, List<Dialogue> dialogues) {
        this.id = id;
        this.name = name;
        this.canMeet = canMeet;
        this.canRemove = canRemove;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
        this.dialogues = dialogues;
    }

    public void script_start(Tower tower) {
        if (this.id.equals("npc01_1_2")) {
            if (tower.getPlayer().inventory.containsKey("item09_5") && tower.getPlayer().inventory.get("item09_5").equals(1)) {
                if (tower.getPlayer().inventory.containsKey("MysteryTreasure") && !tower.getPlayer().inventory.get("MysteryTreasure").equals(1)) {
                    tower.getGameMapList().get(0 + 2).layer1[8][4] = "npc01_1_4";
                    return;
                }
                this.canMeet = true;
                return;
            }
            if (tower.getPlayer().inventory.containsKey("MysteryTreasure") && tower.getPlayer().inventory.get("MysteryTreasure").equals(1)) {
                tower.getGameMapList().get(0 + 2).layer1[8][4] = "npc01_1_3";
                return;
            }
        }
        else if (this.id.equals("npc02_2_2")) {
            if (tower.getPlayer().exp >= 500) {
                tower.getPlayer().exp -= 500;
                this.canMeet = true;
            }
        }
        else if (this.id.equals("npc03_2_2")) {
            if (tower.getPlayer().money >= 500) {
                tower.getPlayer().money -= 500;
                this.canMeet = true;
            }
        }
    }

    public void script_end(Tower tower) {
        if (this.id.equals("npc01_1_1")) {
            tower.getPlayer().yKey++;
            tower.getPlayer().bKey++;
            tower.getPlayer().rKey++;
            tower.getGameMapList().get(0 + 2).layer1[8][4] = "npc01_1_2";
        }
        else if (this.id.equals("npc01_1_2")) {
            tower.getPlayer().attack = tower.getPlayer().attack * 4/3;
            tower.getPlayer().defense = tower.getPlayer().defense * 4/3;
            tower.getGameMapList().get(20 + 2).layer3[7][5] = "stair02";
        }
        else if (this.id.equals("npc01_1_3")) {
            tower.getGameMapList().get(0 + 2).layer1[8][4] = "npc01_1_2";
            tower.getPlayer().inventory.put("MysteryTreasure", 0);
        }
        else if (this.id.equals("npc02_1")) {
            tower.getGameMapList().get(2 + 2).layer2[10][7] = "item04_2";
        }
        else if (this.id.equals("npc02_2_1")) {
            tower.getGameMapList().get(15 + 2).layer1[3][4] = "npc02_2_2";
        }
        else if (this.id.equals("npc02_2_2")) {
            tower.getGameMapList().get(15 + 2).layer2[3][4] = "item04_4";
        }
        else if (this.id.equals("npc03_1")) {
            tower.getGameMapList().get(2 + 2).layer2[10][9] = "item05_2";
        }
        else if (this.id.equals("npc03_2_1")) {
            tower.getGameMapList().get(15 + 2).layer1[3][6] = "npc03_2_2";
        }
        else if (this.id.equals("npc03_2_2")) {
            tower.getGameMapList().get(15 + 2).layer2[3][6] = "item05_4";
        }
        else if (this.id.equals("npc02_3")) {
            tower.getPlayer().inventory.put("MysteryTreasure", 1);
        }
        else if (this.id.equals("npc04_1")) {
            tower.getDoorMap().get("door04_1").openable = true;
            tower.getGameMapList().get(4 + 2).layer1[0][5] = "npc04_2";
        }
        else if (this.id.equals("npc04_2")) {
            tower.getGameMapList().get(18 + 2).layer3[8][5] = "";
            tower.getGameMapList().get(18 + 2).layer3[9][5] = "";
        }
        else if (this.id.equals("npc05_1")) {
            this.canMeet = false;
            tower.getGameMapList().get(18 + 2).layer3[10][10] = "stair02";
        }
    }

}
