package pane;

import entity.Monster;
import entity.Tower;
import main.TowerPanel;
import util.FightCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 怪物手册绘制类
 * @author xuehy
 * @since 2020/6/9
 */
public final class MonsterManualPane {

    public static JLayeredPane monsterManualPane = new JLayeredPane();
    private static JPanel showPanel;

    static {
        monsterManualPane.setBounds(TowerPanel.CS * 6, TowerPanel.CS, TowerPanel.CS * 11, TowerPanel.CS * 11);
        monsterManualPane.setBackground(Color.black);
    }

    public static void showMonsterManual(Tower tower) {
        List<FightCalc> fightCalcList = calculate(tower);
        if (fightCalcList.size() == 0) {
            return;
        }
        TowerPanel.canMove = false;
        monsterManualPane.removeAll();
        showPanel = new JPanel(null);
        showPanel.setSize(TowerPanel.CS * 11, TowerPanel.CS * 11);
        showPanel.setBackground(Color.black);
        update(fightCalcList, tower.getFloorImage()[0]);
        monsterManualPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_ESCAPE:
                    case KeyEvent.VK_ENTER:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (TowerPanel.nowMonsterManual != 0) {
                            TowerPanel.nowMonsterManual--;
                            changeFlag = true;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (TowerPanel.nowMonsterManual < fightCalcList.size() / 8.0 - 1) {
                            TowerPanel.nowMonsterManual++;
                            changeFlag = true;
                        }
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    monsterManualPane.removeKeyListener(this);
                    monsterManualPane.setVisible(false);
                    TowerPanel.canMove = true;
                    TowerPanel.input.clear();
                }
                if (changeFlag) {
                    update(fightCalcList, tower.getFloorImage()[0]);
                }
            }
        });
        monsterManualPane.add(showPanel);
        monsterManualPane.setVisible(true);
        monsterManualPane.requestFocus();
        monsterManualPane.repaint();
    }

    private static void update(List<FightCalc> fightCalcList, Image floorImage) {
        showPanel.removeAll();
        for (int i = TowerPanel.nowMonsterManual << 3, length = fightCalcList.size(); i < length && i < TowerPanel.nowMonsterManual + 1 << 3; i++) {
            Monster monster = fightCalcList.get(i).getMonster();
            JLabel mainLabel = new JLabel();
            mainLabel.setBounds(3, 8 + 42 * (i % 8), 346, 40);
            mainLabel.setForeground(Color.white);
            mainLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

            JLabel picLabel = new JLabel();
            picLabel.setBounds(3, 4, TowerPanel.CS, TowerPanel.CS);
            picLabel.setIcon(monster.getIcon()[0]);

            ImageIcon background = new ImageIcon(floorImage);
            background.setImage(background.getImage().getScaledInstance(background.getIconWidth(), background.getIconHeight(), Image.SCALE_DEFAULT));

            JLabel backgroundLabel = new JLabel();
            backgroundLabel.setIcon(background);
            backgroundLabel.setBounds(3, 4, TowerPanel.CS, TowerPanel.CS);

            JLabel nameLabel = new JLabel("名称", JLabel.CENTER);
            nameLabel.setBounds(38, 3, 30, 15);
            nameLabel.setForeground(Color.white);
            nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel nameValLabel = new JLabel(monster.getName(), JLabel.CENTER);
            nameValLabel.setBounds(68, 3, 95, 15);
            nameValLabel.setForeground(Color.white);
            nameValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel hpLabel = new JLabel("生命", JLabel.CENTER);
            hpLabel.setBounds(38, 21, 30, 15);
            hpLabel.setForeground(Color.white);
            hpLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel hpValLabel = new JLabel(String.valueOf(monster.getHp()), JLabel.CENTER);
            hpValLabel.setBounds(68, 21, 95, 15);
            hpValLabel.setForeground(Color.white);
            hpValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel attackLabel = new JLabel("攻击", JLabel.CENTER);
            attackLabel.setBounds(163, 3, 30, 15);
            attackLabel.setForeground(Color.white);
            attackLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel attackValLabel = new JLabel(String.valueOf(monster.getAttack()), JLabel.RIGHT);
            attackValLabel.setBounds(193, 3, 35, 15);
            attackValLabel.setForeground(Color.white);
            attackValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel defenseLabel = new JLabel("防御", JLabel.CENTER);
            defenseLabel.setBounds(163, 21, 30, 15);
            defenseLabel.setForeground(Color.white);
            defenseLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel defenseValLabel = new JLabel(String.valueOf(monster.getDefense()), JLabel.RIGHT);
            defenseValLabel.setBounds(193, 21, 35, 15);
            defenseValLabel.setForeground(Color.white);
            defenseValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel rewardLabel = new JLabel("金·经", JLabel.CENTER);
            rewardLabel.setBounds(228, 3, 45, 15);
            rewardLabel.setForeground(Color.white);
            rewardLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel rewardValLabel = new JLabel(monster.getMoney() + "·" + monster.getExp(), JLabel.CENTER);
            rewardValLabel.setBounds(273, 3, 70, 15);
            rewardValLabel.setForeground(Color.white);
            rewardValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel damageLabel = new JLabel("损失", JLabel.CENTER);
            damageLabel.setBounds(228, 21, 45, 15);
            damageLabel.setForeground(Color.white);
            damageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            JLabel damageValLabel = new JLabel("", JLabel.CENTER);
            if (fightCalcList.get(i).canAttack) {
                damageValLabel.setText(String.valueOf(fightCalcList.get(i).mDamageTotal));
            } else {
                damageValLabel.setText("DIE");
            }
            damageValLabel.setBounds(273, 21, 70, 15);
            damageValLabel.setForeground(Color.white);
            damageValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            mainLabel.add(picLabel);
            mainLabel.add(backgroundLabel);
            mainLabel.add(nameLabel);
            mainLabel.add(nameValLabel);
            mainLabel.add(hpLabel);
            mainLabel.add(hpValLabel);
            mainLabel.add(attackLabel);
            mainLabel.add(attackValLabel);
            mainLabel.add(defenseLabel);
            mainLabel.add(defenseValLabel);
            mainLabel.add(rewardLabel);
            mainLabel.add(rewardValLabel);
            mainLabel.add(damageLabel);
            mainLabel.add(damageValLabel);
            showPanel.add(mainLabel);
        }
        showPanel.repaint();
    }

    private static List<FightCalc> calculate(Tower tower) {
        //System.out.println("开始计算");
        String[][] monsterLayer;
        if (TowerPanel.isNormalFloor()) {
            monsterLayer = tower.getGameMapList().get(TowerPanel.floor).layer1;
        } else {
            monsterLayer = tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1;
        }
        Set<String> monsterIdSet = new HashSet<>();
        //y
        for (int i = 0; i < monsterLayer.length; i++) {
            //x
            for (int j = 0; j < monsterLayer[i].length; j++) {
                if (monsterLayer[j][i] != null && monsterLayer[j][i].contains("monster")) {
                    monsterIdSet.add(monsterLayer[j][i]);
                }
            }
        }
        List<FightCalc> fightCalcList = new ArrayList<>();
        List<FightCalc> dieAttackList = new ArrayList<>();
        //血影和魔龙只需计算一次
        boolean monster11 = false, monster12 = false;
        for (String monsterId : monsterIdSet) {
            Monster monster = tower.getMonsterMap().get(monsterId);
            if (monster.getId().contains("monster11")) {
                if (monster11) {
                    continue;
                }
                monster = tower.getMonsterMap().get("monster11_8");
                monster11 = true;
            } else if (monster.getId().contains("monster12")) {
                if (monster12) {
                    continue;
                }
                monster = tower.getMonsterMap().get("monster12_8");
                monster12 = true;
            }
            FightCalc fightCalc = new FightCalc(tower.getPlayer(), monster);
            int no = 0;
            if (fightCalc.canAttack) {
                for (int j = 0; j < fightCalcList.size(); j++) {
                    if (fightCalcList.get(j).mDamageTotal >= fightCalc.mDamageTotal) {
                        no = j;
                        break;
                    }
                    if (j == fightCalcList.size() - 1) {
                        no = fightCalcList.size();
                        break;
                    }
                }
                fightCalcList.add(no, fightCalc);
            } else {
                for (int j = 0; j < dieAttackList.size(); j++) {
                    if (dieAttackList.get(j).getMonster().getAttack() >= fightCalc.getMonster().getAttack()) {
                        no = j;
                        break;
                    }
                    if (j == dieAttackList.size() - 1) {
                        no = dieAttackList.size();
                        break;
                    }
                }
                dieAttackList.add(no, fightCalc);
            }
        }
        fightCalcList.addAll(dieAttackList);
        return fightCalcList;
    }

}
