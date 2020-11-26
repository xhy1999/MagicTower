package pane;

import entity.Dialogue;
import entity.NPC;
import entity.Shop;
import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class NpcDialogPane {

    public static JLayeredPane npcDialogPane = new JLayeredPane();
    private static JPanel showPanel;
    private static JLabel name;
    private static JTextArea content;
    private static JLabel pict;

    //秘籍彩蛋
    private static String secretScript = "";
    static byte nowDialog = 0;
    static int dialogNum = 0;

    static {
        npcDialogPane.setBounds(240, 96, 256, 128);
        npcDialogPane.setBackground(Color.black);
    }

    public static void showNpcDialog(Tower tower, String npcId, Byte x, Byte y) {
        TowerPanel.canMove = false;
        NPC npc;
        try {
            npc = tower.getNpcMap().get(npcId);
        } catch (Exception e) {
            System.err.println("layer1 (x=" + y + ",y=" + x + ") npcId(" + npcId + ") 不存在!");
            TowerPanel.canMove = true;
            return;
        }
        npc.script_start(tower);
        //重新获取一边,以防npc改变而这里没变
        npc = tower.getNpcMap().get(npcId);
        if (!npc.canMeet) {
            TowerPanel.canMove = true;
            return;
        }
        npcDialogPane.removeAll();
        init(npc, tower.getPlayer().getPlayerIcon()[1][0].getImage());
        NPC finalNpc = npc;
        //必须将监听器设置给content 否则可能出现无法响应Key的情况
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean close = false;
                boolean next = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        if (dialogNum <= ++nowDialog) {
                            close = true;
                        } else {
                            next = true;
                        }
                        TowerPanel.musicPlayer.dialogueSpace();
                        break;
                }
                if (finalNpc.getName().equals("奇怪的人")) {
                    switch (arg0.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            secretScript += "8";
                            break;
                        case KeyEvent.VK_DOWN:
                            secretScript += "2";
                            break;
                        case KeyEvent.VK_LEFT:
                            secretScript += "4";
                            break;
                        case KeyEvent.VK_RIGHT:
                            secretScript += "6";
                            break;
                        case KeyEvent.VK_A:
                            secretScript += "A";
                            break;
                        case KeyEvent.VK_B:
                            secretScript += "B";
                            break;
                    }
                    if (secretScript.equals("88224646BABA")) {
                        close = true;
                        tower.getGameMapList().get(0).layer3[10][5] = "stair03_1";
                        tower.getGameMapList().get(1).layer1[9][6] = "";
                    }
                }
                if (next) {
                    updateDialog(finalNpc, tower.getPlayer().getPlayerIcon()[1][0].getImage());
                } else if (close) {
                    secretScript = "";
                    nowDialog = 0;
                    dialogNum = 0;
                    npcDialogPane.removeKeyListener(this);
                    npcDialogPane.setVisible(false);
                    TowerPanel.canMove = true;
                    TowerPanel.input.clear();
                    if (finalNpc.canRemove && !(x == null || y == null)) {
                        if (TowerPanel.isNormalFloor()) {
                            tower.getGameMapList().get(TowerPanel.floor).layer1[y][x] = "";
                        } else {
                            tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1[y][x] = "";
                        }
                    }
                    finalNpc.script_end(tower);
                    if (npcId.equals("npc07_1_2")) {
                        //TODO towerPanel.over();    结局2
                        TowerPanel.end = 2;
                        TowerPanel.running = false;
                    } else if (npcId.equals("npc07_2_2")) {
                        //TODO towerPanel.over();    结局3
                        TowerPanel.running = false;
                        TowerPanel.end = 3;
                    }
                }
            }
        });
        npcDialogPane.add(showPanel);
        npcDialogPane.setVisible(true);
        npcDialogPane.repaint();
        content.requestFocus();
    }

    private static void init(NPC npc, Image playerImg) {
        dialogNum = npc.dialogues.size();
        showPanel = new JPanel(null);
        showPanel.setSize(256, 128);
        showPanel.setBackground(Color.black);
        name = new JLabel();
        name.setFont(new Font("宋体", Font.BOLD, 13));
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        content = new JTextArea();
        content.setBorder(BorderFactory.createLineBorder(Color.white));
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(4, 48, 248, 58);
        content.setFont(new Font("宋体", Font.BOLD, 16));
        content.setBackground(Color.black);
        content.setForeground(Color.WHITE);
        JLabel tip = new JLabel("Space...");
        tip.setBounds(212, 105, 50, 25);
        tip.setFont(new Font("微软雅黑", Font.BOLD, 11));
        tip.setForeground(Color.white);
        tip.setBackground(Color.white);
        pict = new JLabel();
        updateDialog(npc, playerImg);
        showPanel.add(name);
        showPanel.add(content);
        showPanel.add(tip);
        showPanel.add(pict);
    }

    private static void updateDialog(NPC npc, Image playerImg) {
        Dialogue dialogue = npc.dialogues.get(nowDialog);
        content.setText(dialogue.text);
        ImageIcon photo;
        if (dialogue.name.contains("player")) {
            pict.setBounds(208, 8, TowerPanel.CS, TowerPanel.CS);
            name.setText("勇士");
            name.setBounds(176, 16, 32, 16);
            photo = new ImageIcon(playerImg);
            System.out.println("勇士:\n" + dialogue.text);
        } else {
            pict.setBounds(13, 8, TowerPanel.CS, TowerPanel.CS);
            name.setText(npc.getName());
            name.setBounds(48, 16, 120, 16);
            photo = new ImageIcon(npc.getIcon()[0].getImage());
            System.out.println(npc.getName() + ":\n" + dialogue.text);
        }
        pict.setIcon(photo);
    }

}
