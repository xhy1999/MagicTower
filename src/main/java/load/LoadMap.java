package load;

import entity.GameMap;
import entity.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadMap {

    private int width = 11;
    public int height = 11;

    public List<GameMap> initMap() {
        List<GameMap> mapList = new ArrayList<>();

        String[][] layer1;
        String[][] layer2;
        String[][] layer3;

        layer1 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer2 = new String[][]{
                {"item01_1", "item02_1", "item04_3", "item06_3", "item08_2", "", "", "", "", "", ""},
                {"item01_2", "item02_2", "item04_4", "item06_4", "item08_3", "", "", "", "", "", ""},
                {"item01_3", "item02_3", "item04_5", "item06_5", "item09_1", "", "", "", "", "", ""},
                {"item01_4", "item02_4", "item05_1", "item06_6", "item09_2", "", "", "", "", "", ""},
                {"item01_5", "item03_1", "item05_2", "item07_1", "item09_3", "", "", "", "", "", ""},
                {"item01_6", "item03_2", "item05_3", "item07_2", "item09_4", "", "", "", "", "", ""},
                {"item01_7", "item03_3", "item05_4", "item07_3", "item09_5", "", "", "", "", "", ""},
                {"item01_8", "item03_4", "item05_5", "item07_4", "item09_6", "", "", "", "", "", ""},
                {"item01_9", "item04_1", "item06_1", "item07_5", "item09_7", "", "", "", "", "", ""},
                {"item01_10", "item04_2", "item06_2", "item08_1", "item09_8", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer3 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", "stair02"}};

        GameMap gameMap = new GameMap(0, layer1, layer2, layer3);
        gameMap.upPositionX = 0;
        gameMap.upPositionY = 10;
        gameMap.downPositionX = 9;
        gameMap.downPositionY = 10;
        mapList.add(gameMap);

        layer1 = new String[][]{
                {"monster01_1", "monster01_11", "monster02_8", "monster03_10", "monster04_8", "monster05_5", "monster07_7", "monster09_4", "monster10_10", "", ""},
                {"monster01_2", "monster01_12", "monster03_1", "monster03_11", "monster04_9", "monster06_1", "monster07_8", "monster10_1", "monster10_11", "", ""},
                {"monster01_3", "monster01_13", "monster03_2", "monster03_12", "monster04_10", "monster06_2", "monster08_1", "monster10_2", "monster10_12", "", ""},
                {"monster01_4", "monster02_1", "monster03_3", "monster04_1", "monster04_11", "monster06_3", "monster08_2", "monster10_3", "monster10_13", "", ""},
                {"monster01_5", "monster02_2", "monster03_4", "monster04_2", "monster04_12", "monster07_1", "monster08_3", "monster10_4", "monster10_14", "", ""},
                {"monster01_6", "monster02_3", "monster03_5", "monster04_3", "monster04_13", "monster07_2", "monster08_4", "monster10_5", "monster10_15", "", ""},
                {"monster01_7", "monster02_4", "monster03_6", "monster04_4", "monster05_1", "monster07_3", "monster08_5", "monster10_6", "monster10_16", "", ""},
                {"monster01_8", "monster02_5", "monster03_7", "monster04_5", "monster05_2", "monster07_4", "monster09_1", "monster10_7", "monster10_17", "", ""},
                {"monster01_9", "monster02_6", "monster03_8", "monster04_6", "monster05_3", "monster07_5", "monster09_2", "monster10_8", "", "", ""},
                {"monster01_10", "monster02_7", "monster03_9", "monster04_7", "monster05_4", "monster07_6", "monster09_3", "monster10_9", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer2 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer3 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"stair01", "", "", "", "", "", "", "", "", "", "stair02"}};

        gameMap = new GameMap(1, layer1, layer2, layer3);
        gameMap.upPositionX = 1;
        gameMap.upPositionY = 10;
        gameMap.downPositionX = 9;
        gameMap.downPositionY = 10;
        mapList.add(gameMap);

        layer1 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "npc01_1", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer2 = new String[][]{
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer3 = new String[][]{
                {"wall01", "wall05", "wall05", "wall05", "wall05", "stair02", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall05", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall05", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall05", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall05", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall05", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall05", "wall01"},
                {"wall01", "wall01", "wall05", "wall05", "wall05", "", "wall05", "wall05", "wall05", "wall01", "wall01"},
                {"wall01", "wall01", "wall01", "wall01", "wall01", "door01", "wall01", "wall01", "wall01", "wall01", "wall01"},
                {"wall06", "wall01", "wall06", "wall01", "", "", "", "wall01", "wall06", "wall01", "wall06"},
                {"wall06", "wall06", "wall06", "wall06", "wall06", "", "wall06", "wall06", "wall06", "wall06", "wall06"},
                {"wall06", "wall06", "wall06", "wall06", "wall06", "stair01", "wall06", "wall06", "wall06", "wall06", "wall06"}};

        gameMap = new GameMap(2, layer1, layer2, layer3);
        gameMap.upPositionX = 5;
        gameMap.upPositionY = 9;
        gameMap.downPositionX = 5;
        gameMap.downPositionY = 1;
        mapList.add(gameMap);

        layer1 = new String[][]{
                {"", "", "", "monster01_1", "monster01_2", "monster01_1", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "monster03_1", "", "", "", "", "", "", "", ""},
                {"", "monster03_1", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "monster01_3", "", ""},
                {"", "monster03_2", "", "", "", "", "monster04_1", "monster01_1", "monster02_1", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "monster03_2", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "monster05_1", ""},
                {"", "", "", "", "", "", "", "", "", "", ""}};

        layer2 = new String[][]{
                {"", "", "item01_1", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"item03_1", "", "", "", "", "", "item03_1", "item01_1", "item03_1", "", ""},
                {"item01_1", "", "item02_1", "", "", "", "item03_1", "item01_1", "item03_1", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"item01_1", "", "", "", "", "", "", "", "", "", ""},
                {"item02_2", "", "item01_2", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", ""},
                {"item03_1", "item03_2", "item01_1", "", "item01_3", "", "", "", "item01_1", "", "item01_2"},
                {"item03_1", "", "item01_1", "", "", "", "", "", "item01_1", "item01_1", "item01_1"}};

        layer3 = new String[][]{
                {"stair02", "", "", "", "", "", "", "", "", "", ""},
                {"wall01", "wall01", "wall01", "wall01", "wall01", "wall01", "wall01", "wall01", "wall01", "wall01", ""},
                {"", "", "", "door01", "", "wall01", "", "", "", "wall01", ""},
                {"", "", "", "wall01", "", "wall01", "", "", "", "wall01", ""},
                {"wall01", "door01", "wall01", "wall01", "", "wall01", "wall01", "wall01", "", "wall01", ""},
                {"", "", "", "wall01", "", "door01", "", "", "", "wall01", ""},
                {"", "", "", "wall01", "", "wall01", "wall01", "wall01", "wall01", "wall01", ""},
                {"wall01", "door01", "wall01", "wall01", "", "", "", "", "", "", ""},
                {"", "", "", "wall01", "wall01", "door03", "wall01", "wall01", "wall01", "door01", "wall01"},
                {"", "", "", "wall01", "", "", "", "wall01", "", "", ""},
                {"", "", "", "wall01", "", "stair01", "", "wall01", "", "", ""}};

        gameMap = new GameMap(3, layer1, layer2, layer3);
        gameMap.upPositionX = 5;
        gameMap.upPositionY = 9;
        gameMap.downPositionX = 5;
        gameMap.downPositionY = 1;
        mapList.add(gameMap);

        return mapList;
    }

}
