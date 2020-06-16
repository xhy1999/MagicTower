package entity;

import java.util.List;

public class Sell {

    public List<String> name;
    public List<String> attribute;
    public List<Short> val;

    public Sell(List<String> name, List<String> attribute, List<Short> val) {
        this.name = name;
        this.attribute = attribute;
        this.val = val;
    }

}
