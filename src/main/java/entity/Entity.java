package entity;

import javax.swing.*;
import java.io.Serializable;

/**
 * @author Xhy
 */
public class Entity implements Cloneable, Serializable {

    String id;
    int hp, attack, defense, exp, money;
    String name;
    boolean removed = false;
    //hostile为true才能攻击
    public boolean hostile = true;

    ImageIcon[] icon;

    public Entity() {

    }

    public Entity(String name, int hp, int attack, int defense, int exp, int money) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.money = money;
        this.name = name;
        this.icon = new ImageIcon[2];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public ImageIcon[] getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon[] icon) {
        this.icon = icon;
    }
}
