package load;

import entity.Door;
import entity.Item;
import entity.Wall;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoadItem {

    public Map<String, Item> initItem() {
        Map<String, Item> itemMap = new HashMap<>(10);

        Item item = new Item("item01_1", "黄钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_2", "蓝钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_3", "红钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_4", "绿钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_5", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_6", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_7", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_7.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_8", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_8.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_9", "生锈的钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_9.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_10", "神秘钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_10.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_1", "红宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_2", "蓝宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_3", "绿宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_4", "黄宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_1", "小体力药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_2", "大体力药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_3", "经验药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_4", "力量药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_1", "铁剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_2", "银剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_3", "武士剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_4", "圣剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_5", "神圣剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_1", "铁盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_2", "银盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_3", "武士盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_4", "圣盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_5", "神圣盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_1", "解毒药水", null,
                new ImageIcon(getClass().getResource("/image/item/item06_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_2", "抗衰弱药水", null,
                new ImageIcon(getClass().getResource("/image/item/item06_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_3", "圣水", null,
                new ImageIcon(getClass().getResource("/image/item/item06_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_4", "茶壶", null,
                new ImageIcon(getClass().getResource("/image/item/item06_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_5", "痰盂", null,
                new ImageIcon(getClass().getResource("/image/item/item06_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_6", "老八瓶", null,
                new ImageIcon(getClass().getResource("/image/item/item06_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_1", "神秘法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_2", "红色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_3", "金色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_4", "蓝色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_5", "紫色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_1", "小飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_2", "红色飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_3", "金色飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_1", "幸运金币", null,
                new ImageIcon(getClass().getResource("/image/item/item09_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_2", "炸药", null,
                new ImageIcon(getClass().getResource("/image/item/item09_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_3", "蛋", null,
                new ImageIcon(getClass().getResource("/image/item/item09_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_4", "寒冰之境", null,
                new ImageIcon(getClass().getResource("/image/item/item09_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_5", "十字架", null,
                new ImageIcon(getClass().getResource("/image/item/item09_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_6", "圣光徽", "\b该宝物可以查看怪物的基本信息。使用时按下键盘上的\bL\b键便可进行查看,再次按\bL\b键则取消显示。",
                new ImageIcon(getClass().getResource("/image/item/item09_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_7", "稿子", null,
                new ImageIcon(getClass().getResource("/image/item/item09_7.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_8", "红宝石稿子", null,
                new ImageIcon(getClass().getResource("/image/item/item09_8.png")));
        itemMap.put(item.getId(), item);

        return itemMap;
    }

}
