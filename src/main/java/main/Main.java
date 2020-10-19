package main;

import entity.Tower;

/**
 * @author Xhy
 */
public class Main {

    public static void main(String[] args) {
        TowerPanel game = new TowerPanel(new Tower());
        game.start();
    }

}
