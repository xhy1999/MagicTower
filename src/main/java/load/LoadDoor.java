package load;

import entity.Door;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoadDoor {

    public Map<String, Door> initDoor() {
        Map<String, Door> doorMap = new HashMap<>(9);

        /**
         * 黄门
         */
        Door door = new Door("door01", true,
                new ImageIcon(getClass().getResource("/image/door/door01_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door01_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door01_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door01_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 蓝门
         */
        door = new Door("door02", true,
                new ImageIcon(getClass().getResource("/image/door/door02_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door02_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door02_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door02_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 红门
         */
        door = new Door("door03", true,
                new ImageIcon(getClass().getResource("/image/door/door03_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door03_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door03_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door03_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 特殊门
         */
        //可以直接打开
        door = new Door("door04", true,
                new ImageIcon(getClass().getResource("/image/door/door04_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_4.png")));
        doorMap.put(door.getId(), door);
        //不能直接打开
        //第二层的门
        door = new Door("door04_1", false,
                new ImageIcon(getClass().getResource("/image/door/door04_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door04_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 特殊门
         */
        //可以直接打开
        door = new Door("door05", true,
                new ImageIcon(getClass().getResource("/image/door/door05_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_4.png")));
        doorMap.put(door.getId(), door);
        //不能直接打开
        door = new Door("door05_1", false,
                new ImageIcon(getClass().getResource("/image/door/door05_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door05_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 墙门
         */
        door = new Door("door06", true,
                new ImageIcon(getClass().getResource("/image/door/door06_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door06_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door06_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door06_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 墙门
         */
        door = new Door("door07", true,
                new ImageIcon(getClass().getResource("/image/door/door07_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door07_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door07_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door07_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 墙门
         */
        door = new Door("door08", true,
                new ImageIcon(getClass().getResource("/image/door/door08_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door08_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door08_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door08_4.png")));
        doorMap.put(door.getId(), door);

        /**
         * 墙门
         */
        door = new Door("door09", true,
                new ImageIcon(getClass().getResource("/image/door/door09_1.png")),
                new ImageIcon(getClass().getResource("/image/door/door09_2.png")),
                new ImageIcon(getClass().getResource("/image/door/door09_3.png")),
                new ImageIcon(getClass().getResource("/image/door/door09_4.png")));
        doorMap.put(door.getId(), door);

        return doorMap;
    }

}
