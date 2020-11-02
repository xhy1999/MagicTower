package load;

import entity.Monster;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author Xhy
 */
public final class LoadMonster {

    public HashMap<String, Monster> initMonster() {
        HashMap<String, Monster> monsterMap = new HashMap<>(128);

        /**
         * 史莱姆系
         */
        Monster monster = new Monster("monster01_1", "绿色史莱姆", 50, 20, 1, 1, 1,
                new ImageIcon(getClass().getResource("/image/monster/monster01_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_2", "红色史莱姆", 70, 15, 2, 2, 2,
                new ImageIcon(getClass().getResource("/image/monster/monster01_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_3", "黑色史莱姆", 200, 35, 10, 5, 5,
                new ImageIcon(getClass().getResource("/image/monster/monster01_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_4", "史莱姆人", 3100, 1150, 1050, 80, 92,
                new ImageIcon(getClass().getResource("/image/monster/monster01_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_5", "水银人", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_6", "肥肥史莱姆", 700, 250, 125, 30, 32,
                new ImageIcon(getClass().getResource("/image/monster/monster01_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_7", "史莱姆小队长", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_8", "史莱姆中队长", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_9", "史莱姆大队长", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_9_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_10", "烈焰史莱姆", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_10_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_10_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_11", "烈焰人", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_12", "烈焰肥肥怪", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_13", "史莱姆王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster01_13_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_13_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 蝙蝠系
         */
        monster = new Monster("monster02_1", "小蝙蝠", 100, 20, 5, 3, 3,
                new ImageIcon(getClass().getResource("/image/monster/monster02_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_2", "大蝙蝠", 150, 65, 30, 8, 10,
                new ImageIcon(getClass().getResource("/image/monster/monster02_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_3", "红蝙蝠", 550, 160, 90, 20, 25,
                new ImageIcon(getClass().getResource("/image/monster/monster02_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_4", "毒蝙蝠", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster02_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_5", "胆小蝙蝠", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster02_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_6", "烈焰小蝙蝠", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster02_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_7", "烈焰大蝙蝠", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster02_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_8", "黑暗大蝙蝠", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster02_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 骷髅系
         */
        monster = new Monster("monster03_1", "骷髅人", 110, 25, 5, 4, 5,
                new ImageIcon(getClass().getResource("/image/monster/monster03_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_2", "骷髅兵", 150, 40, 20, 6, 8,
                new ImageIcon(getClass().getResource("/image/monster/monster03_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_3", "中级骷髅兵", 400, 90, 50, 12, 15,
                new ImageIcon(getClass().getResource("/image/monster/monster03_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        //(2500, 900, 850, 84, 75, "冥队长")
        //(3333, 1200, 1133, 112, 100, "冥队长")(杀完16层魔王)
        //(4999, 2400, 2266, 140, 125, "冥队长")(杀完21层魔王)
        monster = new Monster("monster03_4", "骷髅战士", 2500, 900, 850, 75, 84,
                new ImageIcon(getClass().getResource("/image/monster/monster03_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_5", "骷髅武士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_6", "高级骷髅兵", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_7", "骷髅队长", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_8", "烈焰骷髅人", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_9", "骷髅法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_9_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_10", "骷髅祭祀", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_10_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_10_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_11", "骷髅大臣", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_12", "骷髅法王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 魔法师系
         */
        monster = new Monster("monster04_1", "魔法师", 125, 50, 25, 7, 10,
                new ImageIcon(getClass().getResource("/image/monster/monster04_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_2", "中级魔法师", 190, 20, 20, 1, 8,
                new ImageIcon(getClass().getResource("/image/monster/monster04_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_3", "高级魔法师", 100, 200, 110, 25, 30,
                new ImageIcon(getClass().getResource("/image/monster/monster04_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_4", "麻衣大法师", 250, 100, 70, 17, 20,
                new ImageIcon(getClass().getResource("/image/monster/monster04_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_5", "红衣大法师", 500, 300, 260, 45, 47,
                new ImageIcon(getClass().getResource("/image/monster/monster04_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_6", "烈焰魔法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_7", "烈焰大法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_8", "幽灵魔法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_9", "冥界魔法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_9_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_10", "冥界大法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_10_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_10_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_11", "冥界法王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_12", "地狱使者", 5000, 1000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster04_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        //(1500, 830, 730, 80, 70, "灵法师")
        //(2000, 1106, 973, 106, 93, "灵法师")(杀完16层魔王)
        //(3000, 2212, 1946, 132, 116, "灵法师")(杀完21层魔王)
        monster = new Monster("monster04_13", "暗黑大法师", 1500, 830, 730, 80, 70,
                new ImageIcon(getClass().getResource("/image/monster/monster04_13_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_13_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 兽人系
         */
        monster = new Monster("monster05_1", "兽面人", 300, 75, 45, 10, 13,
                new ImageIcon(getClass().getResource("/image/monster/monster05_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_2", "兽面武士", 900, 450, 330, 50, 50,
                new ImageIcon(getClass().getResource("/image/monster/monster05_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_3", "毒兽面人", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster05_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_4", "烈焰兽面人", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster05_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_5", "烈焰兽面武士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster05_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 守卫系
         */
        monster = new Monster("monster06_1", "初级守卫", 450, 150, 90, 19, 22,
                new ImageIcon(getClass().getResource("/image/monster/monster06_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_2", "中级守卫", 1250, 500, 400, 55, 55,
                new ImageIcon(getClass().getResource("/image/monster/monster06_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_3", "高级守卫", 1500, 560, 460, 60, 60,
                new ImageIcon(getClass().getResource("/image/monster/monster06_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_4", "冥界守卫", 2000, 680, 590, 70, 65,
                new ImageIcon(getClass().getResource("/image/monster/monster06_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 骑士系
         */
        monster = new Monster("monster07_1", "骑士", 850, 350, 200, 40, 45,
                new ImageIcon(getClass().getResource("/image/monster/monster07_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_2", "铁骑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster07_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_3", "近卫骑士", 900, 750, 650, 70, 77,
                new ImageIcon(getClass().getResource("/image/monster/monster07_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        //(1200, 980, 900, 88, 75, "灵武士")
        //(1600, 1306, 1200, 117, 100, "灵武士")(杀完16层魔王)
        //(2400, 2612, 2400, 146, 125, "灵武士")(杀完21层魔王)
        monster = new Monster("monster07_4", "灵武士", 1200, 980, 900, 75, 88,
                new ImageIcon(getClass().getResource("/image/monster/monster07_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_5", "冥界骑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster07_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_6", "黄金骑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster07_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_7", "烈焰骑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster07_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_8", "烈焰武士", 3600, 4500, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster07_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 双刀剑兵系
         */
        monster = new Monster("monster08_1", "双刀剑士", 1200, 620, 520, 65, 75,
                new ImageIcon(getClass().getResource("/image/monster/monster08_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster08_2", "剑王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster08_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster08_3", "双刀骑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster08_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster08_4", "双刀武士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster08_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster08_5", "烈焰剑士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster08_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 石怪系
         */
        monster = new Monster("monster09_1", "石怪", 500, 115, 65, 15, 15,
                new ImageIcon(getClass().getResource("/image/monster/monster09_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster09_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster09_2", "铁怪", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster09_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster09_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster09_3", "寄生石怪", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster09_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster09_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster09_4", "石怪史莱姆", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster09_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster09_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 魔王系
         */
        monster = new Monster("monster10_1", "魔法师首领", 1300, 300, 150, 35, 40,
                new ImageIcon(getClass().getResource("/image/monster/monster10_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_2", "魔法师元帅", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_3", "黄衣大魔王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        //(15000, 1000, 1000, 100, 100, "红衣魔王")
        //(20000, 1333, 1333, 133, 133, "红衣魔王")(杀完16层魔王)
        //(30000, 2666, 2666, 166, 166, "红衣魔王")(杀完21层魔王)
        monster = new Monster("monster10_4", "红衣大魔王", 15000, 1000, 1000, 100, 100,
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_5", "黄金大魔王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_6", "黑暗大魔王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_7", "魔王近卫兵", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_8", "魔王大祭司", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_9", "冰法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_9_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_10", "风法师", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_10_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_10_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_11", "死灵术士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_12", "混沌术士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_13", "魔神·戈尔", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_13_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_13_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_14", "魔神·戈勒", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_14_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_14_2.png")));
        monsterMap.put(monster.getId(), monster);

        //(30000, 1700, 1500, 150, 120, "冥灵魔王")(19层)
        //(45000, 2550, 2250, 375, 330, "冥灵魔王")(21层)
        //(60000, 3400, 3000, 390, 343, "冥灵魔王")(杀完21层魔王)
        monster = new Monster("monster10_15", "大魔王·格勒第", 30000, 1700, 1500, 120, 150,
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_16", "烈焰大魔王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_16_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_16_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_17", "烈焰吸血鬼", 80000, 4000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster10_17_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_17_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_1", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_1_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_2", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_2_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_3", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_3_2.png")),
        true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_4", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_4_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_5", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_5_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_6", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_6_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_7", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_7_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_8", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster11_9", "血影", 9999, 5000, 4000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster11_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_9_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_1", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_1_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_2", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_2_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_3", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_3_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_4", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_4_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_5", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_5_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_6", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_6_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_7", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_7_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_8", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster12_9", "魔龙", 9999, 9999, 5000, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster12_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_9_2.png")),
                true, true);
        monsterMap.put(monster.getId(), monster);

        return monsterMap;
    }

}
