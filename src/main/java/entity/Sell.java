package entity;

import java.io.Serializable;
import java.util.List;

public class Sell implements Cloneable, Serializable {

    public List<String> name;
    public List<String> attribute;
    public List<Short> val;
    public List<Integer> price;

    public Sell(List<String> name, List<String> attribute, List<Short> val) {
        this.name = name;
        this.attribute = attribute;
        this.val = val;
    }

    public Sell(List<String> name, List<String> attribute, List<Short> val, List<Integer> price) {
        this.name = name;
        this.attribute = attribute;
        this.val = val;
        this.price = price;
    }

}
