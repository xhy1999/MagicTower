package load;

import entity.Sell;
import entity.Shop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadShop {

    public Map<String, Shop> initShop() {
        Map<String, Shop> shopMap = new HashMap<>();

        Shop shop = new Shop("shop01_1", "贪婪之神", false,
                new ImageIcon(getClass().getResource("/image/shop/shop01_1_1.png")));
        shopMap.put(shop.getId(), shop);

        shop = new Shop("shop01_3", "贪婪之神", false,
                new ImageIcon(getClass().getResource("/image/shop/shop01_3_1.png")));
        shopMap.put(shop.getId(), shop);


        List<String> name = new ArrayList<>();
        name.add("增加800点生命值");
        name.add("增加4点攻击");
        name.add("增加4点防御");
        name.add("离开商店");

        List<String> attribute = new ArrayList<>();
        attribute.add("hp");
        attribute.add("attack");
        attribute.add("defense");

        List<Short> val = new ArrayList<>();
        val.add(new Short((short) 800));
        val.add(new Short((short) 4));
        val.add(new Short((short) 4));

        Sell sell = new Sell(name, attribute, val);

        shop = new Shop("shop01_2", "贪婪之神", true, "\b人类啊,如果你愿意给我%%个金币的话,我可以提升你的战斗力!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop01_2_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop01_2_2.png")));
        shopMap.put(shop.getId(), shop);

        return shopMap;
    }

}
