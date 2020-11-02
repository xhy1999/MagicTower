package entity;

import java.io.Serializable;

public final class Dialogue implements Cloneable, Serializable {

    public String name;
    public String text;

    public Dialogue(String name, String text) {
        this.name = name;
        this.text = text;
    }

}
