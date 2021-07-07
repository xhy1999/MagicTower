package pane;

import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 楼层传送绘制类
 * @author xuehy
 * @since 2020/6/9
 */
public final class FloorTransferPane {

    public static JLayeredPane floorTransferPane = new JLayeredPane();
    private static JLabel floorNoLabel;
    private static JLabel downPicLabel;
    private static JLabel upPicLabel;
    private static JPanel showPanel;

    static {
        floorTransferPane.setBounds(TowerPanel.CS * 6, TowerPanel.CS, TowerPanel.CS * 11, TowerPanel.CS * 11);
        floorTransferPane.setBackground(Color.black);
    }

    public static void showFloorTransfer(Tower tower) {
        if ((TowerPanel.nowSelectFloor = TowerPanel.floor) < 0) {
            TowerPanel.nowSelectFloor = 0;
        }
        TowerPanel.canMove = false;
        floorTransferPane.removeAll();
        init(tower);
        floorTransferPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_F:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_UP:
                        if (TowerPanel.nowSelectFloor + 1 > tower.getPlayer().maxFloor) {
                            break;
                        }
                        TowerPanel.musicPlayer.floorTransferSelect();
                        TowerPanel.nowSelectFloor++;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (TowerPanel.nowSelectFloor - 1 < tower.getPlayer().minFloor) {
                            break;
                        }
                        TowerPanel.musicPlayer.floorTransferSelect();
                        TowerPanel.nowSelectFloor--;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        closeFlag = true;
                        TowerPanel.musicPlayer.upAndDown();
                        if (TowerPanel.floor < TowerPanel.nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(TowerPanel.nowSelectFloor).upPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(TowerPanel.nowSelectFloor).upPositionY;
                            TowerPanel.DIRECTION = TowerPanel.DIRECTION_DOWN;
                            TowerPanel.musicPlayer.playBackgroundMusic(TowerPanel.nowSelectFloor);
                            TowerPanel.nowMonsterManual = 0;
                        } else if (TowerPanel.floor > TowerPanel.nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(TowerPanel.nowSelectFloor).downPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(TowerPanel.nowSelectFloor).downPositionY;
                            TowerPanel.DIRECTION = TowerPanel.DIRECTION_DOWN;
                            TowerPanel.musicPlayer.playBackgroundMusic(TowerPanel.nowSelectFloor);
                            TowerPanel.nowMonsterManual = 0;
                        }
                        TowerPanel.floor = TowerPanel.nowSelectFloor;
                        TowerPanel.updateFloorNum();
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    floorTransferPane.removeKeyListener(this);
                    floorTransferPane.setVisible(false);
                    TowerPanel.canMove = true;
                    TowerPanel.input.clear();
                }
                if (changeFlag) {
                    update(tower);
                }
            }
        });
        floorTransferPane.add(showPanel);
        floorTransferPane.setVisible(true);
        floorTransferPane.requestFocus();
        floorTransferPane.repaint();
    }

    private static void init(Tower tower) {
        showPanel = new JPanel(null);
        showPanel.setSize(TowerPanel.CS * 11, TowerPanel.CS * 11);
        showPanel.setBackground(Color.black);

        JLabel mainLabel = new JLabel();
        mainLabel.setBounds(50, 135, 250, 80);
        mainLabel.setForeground(Color.white);
        mainLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), 3));

        floorNoLabel = new JLabel(String.valueOf(TowerPanel.nowSelectFloor), JLabel.CENTER);
        floorNoLabel.setBounds(75, 0, 100, 80);
        floorNoLabel.setForeground(Color.white);
        floorNoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 38));

        JLabel floorLabel = new JLabel("F", JLabel.CENTER);
        floorLabel.setBounds(140, 0, 100, 80);
        floorLabel.setForeground(Color.white);
        floorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 38));

        upPicLabel = new JLabel();
        if (TowerPanel.nowSelectFloor + 1 <= tower.getPlayer().maxFloor) {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_1.png")));
        } else {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_2.png")));
        }
        upPicLabel.setBounds(160, 85, TowerPanel.CS, TowerPanel.CS);
        upPicLabel.setForeground(Color.white);

        downPicLabel = new JLabel();
        if (TowerPanel.nowSelectFloor - 1 >= tower.getPlayer().minFloor) {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_1.png")));
        } else {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_2.png")));
        }
        downPicLabel.setBounds(160, 233, TowerPanel.CS, TowerPanel.CS);
        downPicLabel.setForeground(Color.white);

        JLabel enterLabel = new JLabel("-Enter-", JLabel.CENTER);
        enterLabel.setBounds(220, 230, 80, 30);
        enterLabel.setForeground(Color.white);
        enterLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JLabel quitLabel = new JLabel("-Quit(F)-", JLabel.CENTER);
        quitLabel.setBounds(240, 310, 100, 30);
        quitLabel.setForeground(Color.white);
        quitLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        mainLabel.add(floorNoLabel);
        mainLabel.add(floorLabel);

        showPanel.add(mainLabel);
        showPanel.add(upPicLabel);
        showPanel.add(downPicLabel);
        showPanel.add(enterLabel);
        showPanel.add(quitLabel);
        showPanel.repaint();
    }

    private static void update(Tower tower) {
        floorNoLabel.setText(String.valueOf(TowerPanel.nowSelectFloor));
        if (TowerPanel.nowSelectFloor - 1 >= tower.getPlayer().minFloor) {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_1.png")));
        } else {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_2.png")));
        }
        if (TowerPanel.nowSelectFloor + 1 <= tower.getPlayer().maxFloor) {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_1.png")));
        } else {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_2.png")));
        }
    }

}
