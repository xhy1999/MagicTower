package entity;

import java.io.Serializable;

/**
 * 对话内容
 * @author xuehy
 * @since 2020/6/9
 */
public final class Dialogue implements Cloneable, Serializable {

    public String name;
    public String text;

    public Dialogue(String name, String text) {
        this.name = name;
        this.text = text;
    }

}
