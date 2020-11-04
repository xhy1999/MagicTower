package pane;

import entity.Item;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class SpecialItemPane {

    public static JLayeredPane specialItemPane = new JLayeredPane();
    private static JPanel showPanel;

    static {
        specialItemPane.setBounds(169, 96, 400, 128);
        specialItemPane.setBackground(Color.black);
    }

    public static void showSpecialItem(Item item) {
        TowerPanel.canMove = false;
        specialItemPane.removeAll();
        showPanel = new JPanel(null);
        showPanel.setSize(400, 128);
        showPanel.setBackground(Color.black);
        showPanel.setBorder(BorderFactory.createLineBorder(new Color(228, 122, 0), 3));
        JTextArea content = new JTextArea();
        content.setText(item.msg);
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(7, 48, 390, 58);
        content.setFont(new Font("宋体", Font.BOLD, 16));
        content.setBackground(Color.black);
        content.setForeground(Color.WHITE);
        JLabel tip = new JLabel("Space...");
        tip.setBounds(354, 104, 50, 25);
        tip.setFont(new Font("微软雅黑", Font.BOLD, 11));
        tip.setForeground(Color.white);
        tip.setBackground(Color.white);
        JLabel name = new JLabel(item.getName(), JLabel.CENTER);
        name.setBounds(0, 10, 400, 30);
        name.setFont(new Font("微软雅黑", Font.BOLD, 20));
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        showPanel.add(name);
        showPanel.add(content);
        showPanel.add(tip);
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean close = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_ESCAPE:
                        close = true;
                        break;
                }
                if (close) {
                    specialItemPane.removeKeyListener(this);
                    specialItemPane.setVisible(false);
                    TowerPanel.input.clear();
                    TowerPanel.canMove = true;
                }
            }
        });
        specialItemPane.add(showPanel);
        specialItemPane.setVisible(true);
        specialItemPane.repaint();
        content.requestFocus();
    }

}
