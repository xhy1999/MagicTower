package load;

import entity.Wall;

import javax.swing.*;
import java.util.HashMap;

/**
 * 墙加载类
 * @author xuehy
 * @since 2020/6/9
 */
public final class LoadWall {

    public HashMap<String, Wall> initWall() {
        HashMap<String, Wall> wallMap = new HashMap<>(8);

        Wall wall = new Wall("wall01",
                new ImageIcon(getClass().getResource("/image/wall/wall01_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall02",
                new ImageIcon(getClass().getResource("/image/wall/wall02_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall03",
                new ImageIcon(getClass().getResource("/image/wall/wall03_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall04",
                new ImageIcon(getClass().getResource("/image/wall/wall04_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall05",
                new ImageIcon(getClass().getResource("/image/wall/wall05_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall05_2.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall06",
                new ImageIcon(getClass().getResource("/image/wall/wall06_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall06_2.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall07",
                new ImageIcon(getClass().getResource("/image/wall/wall07_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall08",
                new ImageIcon(getClass().getResource("/image/wall/wall08_1.png")));
        wallMap.put(wall.getId(), wall);

        return wallMap;
    }

}
