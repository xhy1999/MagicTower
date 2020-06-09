package entity;

import javax.swing.*;

/**
 * @author Xhy
 */
public class Monster extends Entity {

    public Monster() {

    }

    public Monster(String id, String name, int hp, int attack, int defense, int exp, int money, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.money = money;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

}
