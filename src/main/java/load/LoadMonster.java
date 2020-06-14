package load;

import entity.Monster;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xhy
 */
public class LoadMonster {


    public Map<String, Monster> initMonster() {
        Map<String, Monster> monsterMap = new HashMap<>();

        /**
         * 史莱姆系
         */
        Monster monster = new Monster("monster01_1", "绿色史莱姆", 40, 18, 1, 1, 1,
                new ImageIcon(getClass().getResource("/image/monster/monster01_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_2", "红色史莱姆", 50, 20, 4, 1, 2,
                new ImageIcon(getClass().getResource("/image/monster/monster01_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_3", "黑色史莱姆", 80, 37, 9, 1, 5,
                new ImageIcon(getClass().getResource("/image/monster/monster01_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_4", "史莱姆人", 90, 79, 24, 2, 10,
                new ImageIcon(getClass().getResource("/image/monster/monster01_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_5", "水银人", 810, 802, 753, 15, 140,
                new ImageIcon(getClass().getResource("/image/monster/monster01_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_6", "肥肥史莱姆", 172, 130, 60, 2, 19,
                new ImageIcon(getClass().getResource("/image/monster/monster01_6_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_6_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_7", "史莱姆小队长", 200, 150, 80, 4, 30,
                new ImageIcon(getClass().getResource("/image/monster/monster01_7_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_7_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_8", "史莱姆中队长", 230, 200, 100, 8, 50,
                new ImageIcon(getClass().getResource("/image/monster/monster01_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_9", "史莱姆大队长", 250, 250, 150, 15, 60,
                new ImageIcon(getClass().getResource("/image/monster/monster01_9_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_9_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_10", "烈焰史莱姆", 300, 300, 170, 20, 70,
                new ImageIcon(getClass().getResource("/image/monster/monster01_10_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_10_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_11", "烈焰人", 320, 310, 180, 21, 75,
                new ImageIcon(getClass().getResource("/image/monster/monster01_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_12", "烈焰肥肥怪", 333, 320, 150, 23, 75,
                new ImageIcon(getClass().getResource("/image/monster/monster01_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster01_13", "史莱姆王", 444, 270, 150, 30, 50,
                new ImageIcon(getClass().getResource("/image/monster/monster01_13_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster01_13_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 蝙蝠系
         */
        monster = new Monster("monster02_1", "小蝙蝠", 55, 32, 2, 1, 3,
                new ImageIcon(getClass().getResource("/image/monster/monster02_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_2", "大蝙蝠", 65, 55, 150, 30, 50,
                new ImageIcon(getClass().getResource("/image/monster/monster02_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_3", "红蝙蝠", 210, 185, 70, 3, 24,
                new ImageIcon(getClass().getResource("/image/monster/monster02_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster02_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster02_4", "毒蝙蝠", 1091, 971, 950, 16, 150,
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
        monster = new Monster("monster03_1", "骷髅人", 95, 70, 0, 1, 5,
                new ImageIcon(getClass().getResource("/image/monster/monster03_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_2", "骷髅兵", 190, 100, 5, 2, 13,
                new ImageIcon(getClass().getResource("/image/monster/monster03_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_3", "中级骷髅兵", 290, 170, 23, 3, 21,
                new ImageIcon(getClass().getResource("/image/monster/monster03_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_4", "骷髅战士", 800, 602, 560, 4, 30,
                new ImageIcon(getClass().getResource("/image/monster/monster03_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_5", "骷髅武士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_5_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_5_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_6", "高级骷髅兵", 1020, 1341, 600, 15, 145,
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

        monster = new Monster("monster03_11", "骷髅大臣", 9999, 981, 981, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_11_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_11_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster03_12", "骷髅法王", 9999, 980, 980, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster03_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster03_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 魔法师系
         */
        monster = new Monster("monster04_1", "魔法师", 65, 10, 5, 1, 4,
                new ImageIcon(getClass().getResource("/image/monster/monster04_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_2", "中级魔法师", 190, 20, 20, 1, 8,
                new ImageIcon(getClass().getResource("/image/monster/monster04_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_3", "高级魔法师", 290, 40, 90, 9, 27,
                new ImageIcon(getClass().getResource("/image/monster/monster04_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_4", "大法师", 450, 100, 302, 8, 77,
                new ImageIcon(getClass().getResource("/image/monster/monster04_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_5", "红衣大法师", 875, 120, 513, 11, 101,
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

        monster = new Monster("monster04_12", "地狱使者", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster04_12_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_12_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster04_13", "暗黑大法师", 1650, 220, 920, 51, 500,
                new ImageIcon(getClass().getResource("/image/monster/monster04_13_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster04_13_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 兽人系
         */
        monster = new Monster("monster05_1", "兽面人", 190, 90, 33, 2, 15,
                new ImageIcon(getClass().getResource("/image/monster/monster05_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_2", "兽面武士", 511, 353, 230, 6, 50,
                new ImageIcon(getClass().getResource("/image/monster/monster05_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster05_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster05_3", "毒兽面人", 1310, 1083, 951, 16, 512,
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
        monster = new Monster("monster06_1", "初级守卫", 85, 40, 55, 1, 6,
                new ImageIcon(getClass().getResource("/image/monster/monster06_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_2", "中级守卫", 775, 220, 310, 7, 63,
                new ImageIcon(getClass().getResource("/image/monster/monster06_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_3", "高级守卫", 1150, 651, 860, 12, 110,
                new ImageIcon(getClass().getResource("/image/monster/monster06_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster06_4", "冥界守卫", 1250, 500, 400, 55, 55,
                new ImageIcon(getClass().getResource("/image/monster/monster06_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster06_4_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 骑士系
         */
        monster = new Monster("monster07_1", "骑士", 130, 115, 43, 2, 17,
                new ImageIcon(getClass().getResource("/image/monster/monster07_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_2", "铁骑士", 420, 380, 258, 6, 54,
                new ImageIcon(getClass().getResource("/image/monster/monster07_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_3", "近卫骑士", 992, 737, 677, 13, 124,
                new ImageIcon(getClass().getResource("/image/monster/monster07_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster07_4", "灵武士", 1060, 1118, 1030, 11, 100,
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

        monster = new Monster("monster07_8", "烈焰武士", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster07_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster07_8_2.png")));
        monsterMap.put(monster.getId(), monster);

        /**
         * 双刀剑兵系
         */
        monster = new Monster("monster08_1", "双刀剑士", 280, 210, 110, 4, 34,
                new ImageIcon(getClass().getResource("/image/monster/monster08_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster08_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster08_2", "剑王", 1065, 894, 770, 15, 145,
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
        monster = new Monster("monster09_1", "石怪", 30, 45, 70, 1, 7,
                new ImageIcon(getClass().getResource("/image/monster/monster09_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster09_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster09_2", "铁怪", 50, 600, 990, 14, 132,
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
        monster = new Monster("monster10_1", "魔法师首领", 330, 70, 150, 5, 43,
                new ImageIcon(getClass().getResource("/image/monster/monster10_1_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_1_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_2", "魔法师元帅", 380, 90, 280, 6, 59,
                new ImageIcon(getClass().getResource("/image/monster/monster10_2_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_2_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_3", "黄衣大魔王", 635, 600, 500, 9, 88,
                new ImageIcon(getClass().getResource("/image/monster/monster10_3_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_3_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_4", "红衣大魔王", 1000, 1200, 980, 41, 400,
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_2.png")));
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

        monster = new Monster("monster10_15", "大魔王·格勒第", 5000, 1500, 1200, 0, 0,
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_16", "烈焰大魔王", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_16_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_16_2.png")));
        monsterMap.put(monster.getId(), monster);

        monster = new Monster("monster10_17", "烈焰吸血鬼", 9999, 9999, 9999, 999, 999,
                new ImageIcon(getClass().getResource("/image/monster/monster10_17_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_17_2.png")));
        monsterMap.put(monster.getId(), monster);

        return monsterMap;
    }

}
