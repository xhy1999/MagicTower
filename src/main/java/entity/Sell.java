package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商店的商品
 * @author xuehy
 * @since 2020/6/9
 */
public final class Sell implements Cloneable, Serializable {

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
