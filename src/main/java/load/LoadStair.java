package load;

import entity.Stair;
import entity.Wall;
import main.Tower;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoadStair {

    public HashMap<String, Stair> initStair() {
        HashMap<String, Stair> stairMap = new HashMap<>(2);

        Stair stair = new Stair("stair01", new ImageIcon(getClass().getResource("/image/stair/stair01.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair02", new ImageIcon(getClass().getResource("/image/stair/stair02.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair03_1", new ImageIcon(getClass().getResource("/image/stair/stair03.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair04_1_1", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);
        stair = new Stair("stair04_1_2", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);
        stair = new Stair("stair04_1_3", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);
        stair = new Stair("stair04_1_4", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair04_2_1", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);
        stair = new Stair("stair04_2_2", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair04_3_1", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);
        stair = new Stair("stair04_3_2", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);

        stair = new Stair("stair04_4", new ImageIcon(getClass().getResource("/image/stair/stair04_1.png")),
                new ImageIcon(getClass().getResource("/image/stair/stair04_2.png")));
        stairMap.put(stair.getId(), stair);

        return stairMap;
    }

}
