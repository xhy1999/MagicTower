package entity;

import main.Tower;
import main.TowerPanel;

import javax.swing.*;

/**
 * @author Xhy
 */
public class Monster extends Entity {

    public boolean scriptStart;
    public boolean scriptEnd;

    public Monster() {

    }

    public Monster(String id, String name, int hp, int attack, int defense, int exp, int money, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.money = money;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

    public Monster(String id, String name, int hp, int attack, int defense, int exp, int money, ImageIcon imageIcon1, ImageIcon imageIcon2, boolean scriptStart, boolean scriptEnd) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.money = money;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
        this.scriptStart = scriptStart;
        this.scriptEnd = scriptEnd;
    }

    public void updateAttribute(int hp, int attack, int defense, int exp, int money) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.money = money;
    }

    public void script_start(Tower tower) {
        if (!scriptStart) {
            return;
        }
    }

    public void script_end(TowerPanel towerPanel, Tower tower) {
        if (!scriptEnd) {
            return;
        }
        if (this.getId().equals("monster10_4")) {
            if (TowerPanel.floor == 16) {
                tower.getMonsterMap().get("monster03_4").updateAttribute(3333, 1200, 1133, 112, 100);
                tower.getMonsterMap().get("monster04_13").updateAttribute(2000, 1106, 973, 106, 93);
                tower.getMonsterMap().get("monster07_4").updateAttribute(1600, 1306, 1200, 117, 100);
                tower.getMonsterMap().get("monster10_4").updateAttribute(20000, 1333, 1333, 133, 133);
            }
        }
        else if (this.getId().equals("monster10_15")) {
            if (TowerPanel.floor == 19) {
                this.updateAttribute(45000, 2550, 2250, 375, 330);
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc06_2_2");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                    tower.getNpcMap().get("npc06_2_2").canMeet = false;
                }).start();
            }
            else if (TowerPanel.floor == 21) {
                tower.getMonsterMap().get("monster03_4").updateAttribute(4999, 2400, 2266, 140, 125);
                tower.getMonsterMap().get("monster04_13").updateAttribute(3000, 2212, 1946, 132, 116);
                tower.getMonsterMap().get("monster07_4").updateAttribute(2400, 2612, 2400, 146, 125);
                tower.getMonsterMap().get("monster10_4").updateAttribute(30000, 2666, 2666, 166, 166);
                this.updateAttribute(60000, 3400, 3000, 390, 343);
                this.name = "吸血鬼";
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc06_2_3");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                    tower.getNpcMap().get("npc06_2_3").canMeet = false;
                }).start();
                if (tower.specialFloor) {
                    tower.getGameMapList().get(21).layer3[1][5] = "stair02";
                    tower.getGameMapList().get(21).layer3[6][5] = "";
                    tower.getGameMapList().get(21).upPositionX = 5;
                    tower.getGameMapList().get(21).upPositionY = 6;
                } else {
                    //TODO towerPanel.over();   结局1
                }
            }
        }
        else if (this.getId().contains("monster11")) {
            if (this.getId().equals("monster11_8")) {
                String[][] monsterLayer = tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1;
                //y
                for (int i = 0; i < monsterLayer.length; i++) {
                    //x
                    for (int j = 0; j < monsterLayer[i].length; j++) {
                        if (monsterLayer[j][i] != null && monsterLayer[j][i].contains("monster11")) {
                            tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1[j][i] = "";
                        }
                    }
                }
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc07_1_2");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                }).start();
                //TODO towerPanel.over();    结局2
            }
            else if (tower.getNpcMap().get("npc07_1_1").canMeet) {
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc07_1_1");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                    tower.getNpcMap().get("npc07_1_1").canMeet = false;
                }).start();
            }
        }
        else if (this.getId().contains("monster12")) {
            if (this.getId().equals("monster12_8")) {
                String[][] monsterLayer = tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1;
                //y
                for (int i = 0; i < monsterLayer.length; i++) {
                    //x
                    for (int j = 0; j < monsterLayer[i].length; j++) {
                        if (monsterLayer[j][i] != null && monsterLayer[j][i].contains("monster12")) {
                            tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1[j][i] = "";
                        }
                    }
                }
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc07_2_2");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                }).start();
                //TODO towerPanel.over();    结局3
            }
            else if (tower.getNpcMap().get("npc07_2_1").canMeet) {
                towerPanel.canMove = false;
                new Thread(() -> {
                    towerPanel.meetNpc("npc07_2_1");
                    towerPanel.canMove = true;
                    towerPanel.input.clear();
                    tower.getNpcMap().get("npc07_2_1").canMeet = false;
                }).start();
            }
        }
    }

}
