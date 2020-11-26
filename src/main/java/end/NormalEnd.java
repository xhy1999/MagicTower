package end;

import main.TowerPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class NormalEnd {

    public static void end(TowerPanel towerPanel) {
        for (int i = 0; i <= 0xFF; i++) {
            towerPanel.setBackground(new Color(0, 0, 0, i));
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JLabel imgLabel = new JLabel();
        imgLabel.setForeground(Color.white);
        imgLabel.setBounds(40, 140, 300, 288);
        imgLabel.setVisible(true);

        int x = 240, y = 490;

        JLabel textLabel1 = new JLabel("这位勇士终于成功将公主救了出来...", JLabel.LEFT);
        textLabel1.setForeground(Color.white);
        textLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel1.setBounds(x, y, 400, 20);
        textLabel1.setVisible(true);
        JLabel textLabel2 = new JLabel("魔塔也被毁灭了...", JLabel.LEFT);
        textLabel2.setForeground(Color.white);
        textLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel2.setBounds(x, y + 20, 400, 20);
        textLabel2.setVisible(true);
        JLabel textLabel3 = new JLabel("剩下的只是一堆石头而已...", JLabel.LEFT);
        textLabel3.setForeground(Color.white);
        textLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel3.setBounds(x, y + 40, 400, 20);
        textLabel3.setVisible(true);

        JLabel textLabel4 = new JLabel("随后,勇士离开了这个国家", JLabel.LEFT);
        textLabel4.setForeground(Color.white);
        textLabel4.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel4.setBounds(x, y + 80, 400, 20);
        textLabel4.setVisible(true);
        JLabel textLabel5 = new JLabel("自此之后,再也没有人见过他的身影...", JLabel.LEFT);
        textLabel5.setForeground(Color.white);
        textLabel5.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel5.setBounds(x, y + 100, 400, 20);
        textLabel5.setVisible(true);

        JLabel textLabel6 = new JLabel("究竟他是谁?", JLabel.LEFT);
        textLabel6.setForeground(Color.white);
        textLabel6.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel6.setBounds(x, y + 140, 400, 20);
        textLabel6.setVisible(true);
        JLabel textLabel7 = new JLabel("究竟为何而来?", JLabel.LEFT);
        textLabel7.setForeground(Color.white);
        textLabel7.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel7.setBounds(x, y + 160, 400, 20);
        textLabel7.setVisible(true);
        JLabel textLabel8 = new JLabel("至今依然是个谜...", JLabel.LEFT);
        textLabel8.setForeground(Color.white);
        textLabel8.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel8.setBounds(x, y + 180, 400, 20);
        textLabel8.setVisible(true);

        JLabel textLabel9 = new JLabel("总之,任务完成了", JLabel.LEFT);
        textLabel9.setForeground(Color.white);
        textLabel9.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel9.setBounds(x, y + 220, 400, 20);
        textLabel9.setVisible(true);
        JLabel textLabel10 = new JLabel("这个国家也恢复了往日的和平景象...", JLabel.LEFT);
        textLabel10.setForeground(Color.white);
        textLabel10.setFont(new Font("微软雅黑", Font.BOLD, 18));
        textLabel10.setBounds(x, y + 240, 400, 20);
        textLabel10.setVisible(true);

        JLabel endLabel = new JLabel("~END~", JLabel.CENTER);
        endLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, 0x00));
        endLabel.setFont(new Font("微软雅黑", Font.PLAIN, 100));
        endLabel.setBounds(0, 0, 576, 450);
        endLabel.setVisible(true);

        towerPanel.add(textLabel1);
        towerPanel.add(textLabel2);
        towerPanel.add(textLabel3);
        towerPanel.add(textLabel4);
        towerPanel.add(textLabel5);
        towerPanel.add(textLabel6);
        towerPanel.add(textLabel7);
        towerPanel.add(textLabel8);
        towerPanel.add(textLabel9);
        towerPanel.add(textLabel10);
        towerPanel.add(imgLabel);
        towerPanel.add(endLabel);

        ImageUtil imageUtil = new ImageUtil();
        towerPanel.mainExecutor.execute(() -> {
            for (int i = 0; i <= 120; i++) {
                if (i <= 50) {
                    imgLabel.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", 2 * i)));
                } else {
                    imgLabel.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", (int) (120 - 0.4 * i))));
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= y + 240 + 50; i++) {
                textLabel1.setBounds(x, y - i, 400, 20);
                textLabel2.setBounds(x, y + 20 - i, 400, 20);
                textLabel3.setBounds(x, y + 40 - i, 400, 20);
                textLabel4.setBounds(x, y + 80 - i, 400, 20);
                textLabel5.setBounds(x, y + 100 - i, 400, 20);
                textLabel6.setBounds(x, y + 140 - i, 400, 20);
                textLabel7.setBounds(x, y + 160 - i, 400, 20);
                textLabel8.setBounds(x, y + 180 - i, 400, 20);
                textLabel9.setBounds(x, y + 220 - i, 400, 20);
                textLabel10.setBounds(x, y + 240 - i, 400, 20);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= 72; i++) {
                imgLabel.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", 72 - i)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= 0xFE; i++) {
                endLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0xFE; i >= 0; i--) {
                endLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            towerPanel.musicPlayer.stopEndBackgroundMusic();
            towerPanel.remove(textLabel1);
            towerPanel.remove(textLabel2);
            towerPanel.remove(textLabel3);
            towerPanel.remove(textLabel4);
            towerPanel.remove(textLabel5);
            towerPanel.remove(textLabel6);
            towerPanel.remove(textLabel7);
            towerPanel.remove(textLabel8);
            towerPanel.remove(textLabel9);
            towerPanel.remove(textLabel10);
            towerPanel.remove(imgLabel);
            towerPanel.remove(endLabel);
            towerPanel.mainExecutor.execute(() -> ImageScript.imageScript(towerPanel));
            PostScript.postScript(towerPanel);
        });
    }

}
