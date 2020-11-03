package main;

import entity.Tower;

import static pane.FloorTransferPane.floorTransferPane;
import static pane.MonsterManualPane.monsterManualPane;

/**
 * @author Xhy
 */
public class Main {

    public static void main(String[] args) {
        TowerPanel game = new TowerPanel(new Tower());
        game.add(monsterManualPane);
        game.add(floorTransferPane);
        game.start();
    }

}
