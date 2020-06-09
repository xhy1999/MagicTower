package load;

import entity.Stair;
import entity.Wall;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoadStair {

    public Map<String, Stair> initStair() {
        Map<String, Stair> stairMap = new HashMap<>(2);

        Stair stair = new Stair("stair01", new ImageIcon(getClass().getResource("/image/stair/stair01.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair02", new ImageIcon(getClass().getResource("/image/stair/stair02.png")));
        stairMap.put(stair.getId(), stair);

        return stairMap;
    }

}
