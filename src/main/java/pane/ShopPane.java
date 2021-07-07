package pane;

import entity.Shop;
import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * 商店绘制类
 * @author xuehy
 * @since 2020/6/9
 */
public class ShopPane {

    public static JLayeredPane shopPane = new JLayeredPane();
    private static JPanel showPanel;
    private static JTextArea shopDialogue;
    private static JLabel selectLabel;
    static byte nowSelected = 0;

    static {
        shopPane.setBounds(234, 71, 268, 227);
        shopPane.setBackground(Color.black);
    }

    public static void showShop(Tower tower, String shopId) {
        TowerPanel.canMove = false;
        shopPane.removeAll();
        Shop shop = tower.getShopMap().get(shopId);
        init(shop);
        //必须将监听器设置给shopDialogue 否则可能出现无法响应Key的情况
        shopDialogue.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                Shop shop = tower.getShopMap().get(shopId);
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        TowerPanel.input.clear();
                        if (nowSelected <= 0) {
                            break;
                        }
                        TowerPanel.musicPlayer.shopSelect();
                        nowSelected--;
                        selectLabel.setBounds(10, 100 + nowSelected * 30, 30, 30);
                        break;
                    case KeyEvent.VK_DOWN:
                        TowerPanel.input.clear();
                        if (nowSelected >= 3) {
                            break;
                        }
                        TowerPanel.musicPlayer.shopSelect();
                        nowSelected++;
                        selectLabel.setBounds(10, 100 + nowSelected * 30, 30, 30);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (shop.sell.name.get(nowSelected).contains("离开")) {
                            shopPane.removeKeyListener(this);
                            shopPane.setVisible(false);
                            TowerPanel.musicPlayer.upAndDown();
                            TowerPanel.canMove = true;
                            TowerPanel.input.clear();
                            nowSelected = 0;
                            break;
                        }
                        short price;
                        if (shop.price != 0) {
                            price = (short) shop.price;
                        } else {
                            price = Short.valueOf(shop.sell.price.get(nowSelected).toString());
                        }
                        if (shop.need.equals("money")) {
                            buyItemByMoney(tower, shop, price);
                        } else if (shop.need.equals("exp")) {
                            buyItemByExp(tower, shop, price);
                        } else if (shop.need.equals("item")) {
                            sellItem(tower, shop);
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        shopPane.removeKeyListener(this);
                        shopPane.setVisible(false);
                        TowerPanel.musicPlayer.upAndDown();
                        TowerPanel.canMove = true;
                        TowerPanel.input.clear();
                        nowSelected = 0;
                        break;
                }
            }
        });
        shopPane.add(showPanel);
        shopPane.setVisible(true);
        shopPane.repaint();
        shopDialogue.requestFocus();
    }

    private static void init(Shop shop) {
        showPanel = new JPanel(null);
        showPanel.setSize(268, 227);
        showPanel.setBackground(Color.black);
        ImageIcon photo = new ImageIcon(shop.getIcon()[0].getImage());
        JLabel shopImg = new JLabel();
        shopImg.setBounds(10, 8, TowerPanel.CS, TowerPanel.CS);
        shopImg.setIcon(photo);
        shopDialogue = new JTextArea();
        shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
        shopDialogue.setLineWrap(true);
        shopDialogue.setEditable(false);
        shopDialogue.setBounds(4, 48, 260, 40);
        shopDialogue.setFont(new Font("宋体", Font.BOLD, 16));
        shopDialogue.setBackground(Color.black);
        shopDialogue.setForeground(Color.WHITE);
        JLabel name = new JLabel(shop.getName());
        name.setBounds(50, 12, 200, 25);
        name.setFont(new Font("微软雅黑", Font.BOLD, 20));
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        selectLabel = new JLabel();
        selectLabel.setIcon(new ImageIcon(ShopPane.class.getResource("/image/icon/selected.png")));
        selectLabel.setBounds(10, 100, 30, 30);
        selectLabel.setForeground(Color.white);
        showPanel.add(shopImg);
        showPanel.add(shopDialogue);
        showPanel.add(name);
        showPanel.add(selectLabel);
        for (int i = 0; i < shop.sell.name.size(); i++) {
            JLabel label = new JLabel(shop.sell.name.get(i), JLabel.CENTER);
            label.setBounds(34, 100 + 30 * i, 200, 30);
            label.setForeground(Color.white);
            label.setFont(new Font("微软雅黑", Font.BOLD, 16));
            showPanel.add(label);
        }
    }

    private static boolean buyItemByMoney(Tower tower, Shop shop, int price) {
        if (tower.getPlayer().money >= price) {
            TowerPanel.musicPlayer.shopBuySuc();
            tower.getPlayer().money -= price;
            shop.useNum++;
            shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
            java.util.List<String> attributeList = shop.sell.attribute;
            java.util.List<Short> valList = shop.sell.val;
            if (attributeList.get(nowSelected).contains("hp")) {
                tower.getPlayer().hp += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("attack")) {
                tower.getPlayer().attack += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("defense")) {
                tower.getPlayer().defense += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("yKey")) {
                tower.getPlayer().yKey += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("bKey")) {
                tower.getPlayer().bKey += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("rKey")) {
                tower.getPlayer().rKey += valList.get(nowSelected);
            }
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

    private static boolean buyItemByExp(Tower tower, Shop shop, int price) {
        if (tower.getPlayer().exp >= price) {
            TowerPanel.musicPlayer.shopExpBuySuc();
            tower.getPlayer().exp -= price;
            shop.useNum++;
            shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
            java.util.List<String> attributeList = shop.sell.attribute;
            java.util.List<Short> valList = shop.sell.val;
            if (attributeList.get(nowSelected).contains("lv")) {
                int var = valList.get(nowSelected);
                tower.getPlayer().level += var;
                tower.getPlayer().hp += 1000 * var;
                tower.getPlayer().attack += 7 * var;
                tower.getPlayer().defense += 7 * var;
            } else if (attributeList.get(nowSelected).contains("attack")) {
                tower.getPlayer().attack += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("defense")) {
                tower.getPlayer().defense += valList.get(nowSelected);
            }
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

    private static boolean sellItem(Tower tower, Shop shop) {
        boolean sell = false;
        List<String> attributeList = shop.sell.attribute;
        if (attributeList.get(nowSelected).contains("yKey")) {
            if (tower.getPlayer().yKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().yKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        } else if (attributeList.get(nowSelected).contains("bKey")) {
            if (tower.getPlayer().bKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().bKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        } else if (attributeList.get(nowSelected).contains("rKey")) {
            if (tower.getPlayer().rKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().rKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        }
        if (sell) {
            shop.useNum++;
            TowerPanel.musicPlayer.shopBuySuc();
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

}
