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

    public void script(Tower tower) {
        if (this.id.equals("npc01_1")) {
            tower.player.yKey++;
            tower.player.bKey++;
            tower.player.rKey++;
            tower.gameMapList.get(2).layer1[8][4] = "npc01_2";
        }
    }

}
