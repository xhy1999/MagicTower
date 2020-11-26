package end;

import main.TowerPanel;

import javax.swing.*;
import java.awt.*;

public class PostScript {

    /**
     * 字幕
     * 需要在线程中调用
     */
    public static void postScript(TowerPanel towerPanel) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        towerPanel.musicPlayer.playPostScriptBackgroundMusic();
        int x = 250, y = 490;
        //标题
        JLabel titleLabel = new JLabel("魔塔", JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 50));
        titleLabel.setVisible(true);
        JLabel titleEnglishLabel = new JLabel("Magic Tower", JLabel.CENTER);
        titleEnglishLabel.setForeground(Color.white);
        titleEnglishLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        titleEnglishLabel.setVisible(true);

        //程序设计
        JLabel programLabel = new JLabel("程序设计(Java):", JLabel.LEFT);
        programLabel.setForeground(Color.white);
        programLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        programLabel.setVisible(true);
        JLabel programValLabel = new JLabel("Vip、疯子", JLabel.LEFT);
        programValLabel.setForeground(Color.white);
        programValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        programValLabel.setVisible(true);

        //人物图片
        JLabel characterModelLabel = new JLabel("人物图片:", JLabel.LEFT);
        characterModelLabel.setForeground(Color.white);
        characterModelLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        characterModelLabel.setVisible(true);
        JLabel characterModelValLabel = new JLabel("原版魔塔", JLabel.LEFT);
        characterModelValLabel.setForeground(Color.white);
        characterModelValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        characterModelValLabel.setVisible(true);

        //音效
        JLabel soundEffectLabel = new JLabel("音效:", JLabel.LEFT);
        soundEffectLabel.setForeground(Color.white);
        soundEffectLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        soundEffectLabel.setVisible(true);
        JLabel soundEffectValLabel1 = new JLabel("Flash版魔塔(by 胖老鼠)", JLabel.LEFT);
        soundEffectValLabel1.setForeground(Color.white);
        soundEffectValLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        soundEffectValLabel1.setVisible(true);
        JLabel soundEffectValLabel2 = new JLabel("Flash版魔塔(by cos105hk)", JLabel.LEFT);
        soundEffectValLabel2.setForeground(Color.white);
        soundEffectValLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        soundEffectValLabel2.setVisible(true);

        //音乐
        JLabel musicLabel = new JLabel("音乐(部分为中译):", JLabel.LEFT);
        musicLabel.setForeground(Color.white);
        musicLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        musicLabel.setVisible(true);
        JLabel musicValLabel1 = new JLabel("冰和珊瑚的迷宫 [风来的西林外传:女剑士飞鸟见参]", JLabel.LEFT);
        musicValLabel1.setForeground(Color.white);
        musicValLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        musicValLabel1.setVisible(true);
        JLabel musicValLabel2 = new JLabel("废坑(悬崖的风穴) [风来的西林2:鬼袭来!西林城!]", JLabel.LEFT);
        musicValLabel2.setForeground(Color.white);
        musicValLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        musicValLabel2.setVisible(true);
        JLabel musicValLabel3 = new JLabel("片头曲 [浪漫沙加-吟游诗人之歌]", JLabel.LEFT);
        musicValLabel3.setForeground(Color.white);
        musicValLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        musicValLabel3.setVisible(true);
        JLabel musicValLabel4 = new JLabel("地牢2 [浪漫沙加-吟游诗人之歌]", JLabel.LEFT);
        musicValLabel4.setForeground(Color.white);
        musicValLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        musicValLabel4.setVisible(true);
        JLabel musicValLabel5 = new JLabel("最后的迷宫 [浪漫沙加-吟游诗人之歌]", JLabel.LEFT);
        musicValLabel5.setForeground(Color.white);
        musicValLabel5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        musicValLabel5.setVisible(true);
        JLabel musicValLabel6 = new JLabel("邪神复活 [浪漫沙加-吟游诗人之歌]", JLabel.LEFT);
        musicValLabel6.setForeground(Color.white);
        musicValLabel6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        musicValLabel6.setVisible(true);
        JLabel musicValLabel7 = new JLabel("Dance With Wind [阿玛迪斯战记]", JLabel.LEFT);
        musicValLabel7.setForeground(Color.white);
        musicValLabel7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        musicValLabel7.setVisible(true);

        //测试
        JLabel testLabel = new JLabel("测试:", JLabel.LEFT);
        testLabel.setForeground(Color.white);
        testLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        testLabel.setVisible(true);
        JLabel testValLabel = new JLabel("Vip、疯子", JLabel.LEFT);
        testValLabel.setForeground(Color.white);
        testValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        testValLabel.setVisible(true);

        //鸣谢
        JLabel thanksLabel = new JLabel("鸣谢:", JLabel.LEFT);
        thanksLabel.setForeground(Color.white);
        thanksLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        thanksLabel.setVisible(true);
        JLabel thanksValLabel1 = new JLabel("Flash版魔塔(by 胖老鼠)", JLabel.LEFT);
        thanksValLabel1.setForeground(Color.white);
        thanksValLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        thanksValLabel1.setVisible(true);
        JLabel thanksValLabel2 = new JLabel("Flash版魔塔(by cos105hk)", JLabel.LEFT);
        thanksValLabel2.setForeground(Color.white);
        thanksValLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        thanksValLabel2.setVisible(true);
        JLabel thanksValLabel3 = new JLabel("提供素材的地方", JLabel.LEFT);
        thanksValLabel3.setForeground(Color.white);
        thanksValLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        thanksValLabel3.setVisible(true);

        towerPanel.add(titleLabel);
        towerPanel.add(titleEnglishLabel);
        towerPanel.add(programLabel);
        towerPanel.add(programValLabel);
        towerPanel.add(characterModelLabel);
        towerPanel.add(characterModelValLabel);
        towerPanel.add(soundEffectLabel);
        towerPanel.add(soundEffectValLabel1);
        towerPanel.add(soundEffectValLabel2);
        towerPanel.add(musicLabel);
        towerPanel.add(musicValLabel1);
        towerPanel.add(musicValLabel2);
        towerPanel.add(musicValLabel3);
        towerPanel.add(musicValLabel4);
        towerPanel.add(musicValLabel5);
        towerPanel.add(musicValLabel6);
        towerPanel.add(musicValLabel7);
        towerPanel.add(testLabel);
        towerPanel.add(testValLabel);
        towerPanel.add(thanksLabel);
        towerPanel.add(thanksValLabel1);
        towerPanel.add(thanksValLabel2);
        towerPanel.add(thanksValLabel3);

        for (int i = 0; i <= y + 840 + 40; i++) {
            titleLabel.setBounds(x, y - i, 300, 50);
            titleEnglishLabel.setBounds(x, y + 50 - i, 300, 30);

            programLabel.setBounds(x, y + 140 - i, 300, 30);
            programValLabel.setBounds(x + 40, y + 170 - i, 300, 30);

            characterModelLabel.setBounds(x, y + 220 - i, 300, 30);
            characterModelValLabel.setBounds(x + 40, y + 250 - i, 300, 30);

            soundEffectLabel.setBounds(x, y + 300 - i, 300, 30);
            soundEffectValLabel1.setBounds(x + 40, y + 330 - i, 300, 30);
            soundEffectValLabel2.setBounds(x + 40, y + 360 - i, 300, 30);

            musicLabel.setBounds(x, y + 410 - i, 300, 30);
            musicValLabel1.setBounds(x + 40, y + 440 - i, 300, 30);
            musicValLabel2.setBounds(x + 40, y + 470 - i, 300, 30);
            musicValLabel3.setBounds(x + 40, y + 500 - i, 300, 30);
            musicValLabel4.setBounds(x + 40, y + 530 - i, 300, 30);
            musicValLabel5.setBounds(x + 40, y + 560 - i, 300, 30);
            musicValLabel6.setBounds(x + 40, y + 590 - i, 300, 30);
            musicValLabel7.setBounds(x + 40, y + 620 - i, 300, 30);

            testLabel.setBounds(x, y + 670 - i, 300, 30);
            testValLabel.setBounds(x + 40, y + 700 - i, 300, 30);

            thanksLabel.setBounds(x, y + 750 - i, 300, 30);
            thanksValLabel1.setBounds(x + 40, y + 780 - i, 300, 30);
            thanksValLabel2.setBounds(x + 40, y + 810 - i, 300, 30);
            thanksValLabel3.setBounds(x + 40, y + 840 - i, 300, 30);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //制作
        JLabel emailLabel = new JLabel("联系方式:", JLabel.LEFT);
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        emailLabel.setVisible(true);
        emailLabel.setBounds(155, 160, 300, 50);
        JLabel emailValLabel = new JLabel("xuehy1999@qq.com", JLabel.LEFT);
        emailValLabel.setForeground(Color.white);
        emailValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        emailValLabel.setVisible(true);
        emailValLabel.setBounds(155, 210, 300, 50);
        towerPanel.add(emailLabel);
        towerPanel.add(emailValLabel);
        for (int i = 0; i <= 0xFE; i++) {
            emailLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
            emailValLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
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
            emailLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
            emailValLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //end
        JLabel endLabel = new JLabel("二零二零年八月八日 · 宁波", JLabel.CENTER);
        endLabel.setForeground(Color.white);
        endLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        endLabel.setVisible(true);
        endLabel.setBounds(0, 0, 576, 430);
        towerPanel.add(endLabel);
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

        //end
        JLabel thankPlayLabel = new JLabel("Thanks for playing", JLabel.CENTER);
        thankPlayLabel.setForeground(Color.white);
        thankPlayLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        thankPlayLabel.setVisible(true);
        thankPlayLabel.setBounds(0, 0, 576, 430);
        towerPanel.add(thankPlayLabel);
        for (int i = 0; i <= 0xFE; i++) {
            thankPlayLabel.setForeground(new Color(0xFF, 0xFF, 0xFF, i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <= 300; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
