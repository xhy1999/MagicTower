package entity;

import javax.swing.*;
import java.io.Serializable;

/**
 * 实体
 * @author xuehy
 * @since 2020/6/9
 */
public class Entity implements Cloneable, Serializable {

    String id;
    int hp, attack, defense, exp, money;
    String name;
    //hostile为true才能攻击
    public boolean hostile = true;

    ImageIcon[] icon;

    public Entity() {

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
