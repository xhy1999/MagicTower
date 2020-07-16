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
        if (this.id.equals("npc01_2")) {
            if (tower.getPlayer().inventory.containsKey("item09_5") && tower.getPlayer().inventory.get("item09_5").equals(1)) {
                this.canMeet = true;
            }
        }
    }

    public void script_end(Tower tower) {
        if (this.id.equals("npc01_1")) {
            tower.getPlayer().yKey++;
            tower.getPlayer().bKey++;
            tower.getPlayer().rKey++;
            tower.getGameMapList().get(0 + 2).layer1[8][4] = "npc01_2";
        }
        else if (this.id.equals("npc01_2")) {
            tower.getPlayer().attack = tower.getPlayer().attack * 4/3;
            tower.getPlayer().defense = tower.getPlayer().defense * 4/3;
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
