package main;

import entity.*;
import load.*;
import util.ImageUtil;
import util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author Xhy
 */
public class TowerPanel extends JPanel implements Runnable {

    /**
     * 单个图像大小,默认采用32x32图形,可根据需要调整比例
     * 当然,应始终和窗体大小比例协调;比如32x32的图片,
     * 如果一行设置15个,那么就是480
     */
    private static final int CS = 32;

    //标题栏高度
    private static final int TITLE_HEIGHT = 26;

    //行
    private static final int GAME_ROW = 11;
    //列
    private static final int GAME_COL = 11;

    /**
     * 窗体的宽和高
     */
    private static final int WINDOW_WIDTH = 18 * 32 - 10;
    private static final int WINDOW_HEIGHT = 13 * 32 - 10;

    /**
     * 人物方向
     */
    public static int DIRECTION;
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    /**
     * 设定显示图像对象
     */
    JLabel playerPicLabel;
    JLabel lvLabel;
    JLabel hpPicLabel, hpLabel;
    JLabel atkPicLabel, atkLabel;
    JLabel defPicLabel, defLabel;
    JLabel expPicLabel, expLabel;
    /*乘号*/ JLabel symbol1, symbol2, symbol3, symbol4;
    JLabel yKeyPicLabel, yKeyLabel;
    JLabel bKeyPicLabel, bKeyLabel;
    JLabel rKeyPicLabel, rKeyLabel;
    JLabel monPicLabel, monLabel;

    JDialog dialogBox;
    JLabel showMesLabel = new JLabel("魔塔(测试版)");

    /**
     * 帧数(每秒8帧)
     */
    byte frames = 0;

    private boolean running = false;
    public static boolean canMove = true;
    public static KeyInputHandler input;
    public static MusicPlayer musicPlayer;
    //TODO 正式版这里要改为 false
    public static boolean canUseFloorTransfer = true;
    public static boolean canUseMonsterManual = true;
    public static String specialGameMapNo;
    //TODO 正式版这里要改为 0
    public static int floor = 0;

    JFrame mainframe = new JFrame("魔塔v1.13  (复刻者:Vip、疯子)");
    Container contentPane;
    private List<Tower> gameSave;

    Tower tower;

    public TowerPanel(Tower tower) {
        this.tower = tower;
        this.gameSave = new LinkedList<>();
        tower.getPlayer().x = tower.getGameMapList().get(floor).upPositionX;
        tower.getPlayer().y = tower.getGameMapList().get(floor).upPositionY;
        //TODO 正式版这里要改为 0
        tower.getPlayer().maxFloor = 23;
        tower.getPlayer().minFloor = 0;
        musicPlayer = new MusicPlayer();
        musicPlayer.playBackgroundMusic(floor);
        DIRECTION = DIRECTION_UP;
        input = new KeyInputHandler(this);
        this.setLayout(null);
        //设定初始构造时面板大小
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        //this.createMenuBar();// 创建游戏菜单栏
        this.showAttribute();// 属性展示界面
        //设定焦点在本窗体
        this.setFocusable(true);
        //主窗体
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        ScreenUtil screenUtil = new ScreenUtil();
        mainframe.setLocation(screenUtil.getScreenWidth() / 3, screenUtil.getScreenWidth() / 6);
        //得到一个Toolkit对象
        Toolkit tool = this.getToolkit();
        Image image = tool.getImage(this.getClass().getResource("/image/icon/MT.png"));
        mainframe.setIconImage(image);
        contentPane = mainframe.getContentPane();
        contentPane.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        contentPane.add(this, BorderLayout.CENTER);
        mainframe.pack();
        this.setFocusable(true);
        mainframe.setResizable(false);
        mainframe.setVisible(true);
    }

    public void start() {
        running = true;
        new Thread(this).start();
        //audioPlayer = new AudioPlayer();
        //audioPlayer.startBackgroundMusic(tower.getPlayer().floor);
    }

    /**
     * 保存游戏
     */
    private void save() {
        tower.canUseFloorTransfer = canUseFloorTransfer;
        tower.canUseMonsterManual = canUseMonsterManual;
        tower.specialGameMapNo = specialGameMapNo;
        tower.floor = floor;
        try {
            gameSave.add(0, tower.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        showMesLabel.setText("数据保存成功");
    }

    /**
     * 读取游戏
     */
    private void load() {
        if (gameSave.size() == 0 || gameSave.get(0) == null) {
            return;
        }
        try {
            tower = gameSave.get(0).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        canUseFloorTransfer = tower.canUseFloorTransfer;
        canUseMonsterManual = tower.canUseMonsterManual;
        specialGameMapNo = tower.specialGameMapNo;
        floor = tower.floor;
        showMesLabel.setText("数据读取成功");
    }

    /**
     * 展示属性
     */
    public void showAttribute() {
        // 各属性的显示
        playerPicLabel = new JLabel();
        playerPicLabel.setBounds(32 + 14, 32 + 12, 36, 38);
        //playerPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/role.png")));


        lvLabel = new JLabel("Lv." + tower.getPlayer().level, JLabel.CENTER);
        lvLabel.setBounds(96 - 10, 32 + 17, 60, 32);
        lvLabel.setForeground(Color.white);
        lvLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        //lvLabel.setOpaque(true);
        //lvLabel.setBackground(new Color(255, 255, 255, 100));

        hpPicLabel = new JLabel("生命:");
        hpPicLabel.setBounds(32 + 5, 90, 45, 32);
        hpPicLabel.setForeground(Color.white);
        hpPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        hpLabel = new JLabel("" + tower.getPlayer().hp, JLabel.CENTER);
        hpLabel.setBounds(32 + 5 + 45, 90 + 1, 75, 32);
        hpLabel.setForeground(Color.white);
        hpLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        atkPicLabel = new JLabel("攻击:");
        atkPicLabel.setBounds(32 + 5, 122, 45, 32);
        atkPicLabel.setForeground(Color.white);
        atkPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        atkLabel = new JLabel("" + tower.getPlayer().attack, JLabel.CENTER);
        atkLabel.setBounds(32 + 5 + 45, 122 + 1, 75, 32);
        atkLabel.setForeground(Color.white);
        atkLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        defPicLabel = new JLabel("防御:");
        defPicLabel.setBounds(32 + 5, 154, 45, 32);
        defPicLabel.setForeground(Color.white);
        defPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        defLabel = new JLabel("" + tower.getPlayer().defense, JLabel.CENTER);
        defLabel.setBounds(32 + 5 + 45, 154 + 1, 75, 32);
        defLabel.setForeground(Color.white);
        defLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        expPicLabel = new JLabel("经验:");
        expPicLabel.setBounds(32 + 5, 186, 45, 32);
        expPicLabel.setForeground(Color.white);
        expPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        expLabel = new JLabel("" + tower.getPlayer().exp, JLabel.CENTER);
        expLabel.setBounds(32 + 5 + 45, 186 + 1, 75, 32);
        expLabel.setForeground(Color.white);
        expLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        symbol1 = new JLabel("×", JLabel.CENTER);
        symbol1.setBounds(25, 0, 32, 26);
        symbol1.setForeground(Color.white);
        symbol1.setFont(new Font("微软雅黑", Font.PLAIN, 32));

        yKeyPicLabel = new JLabel();
        yKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_1.png")));
        yKeyPicLabel.setBounds(32, 256, 64, 32);
        yKeyPicLabel.setForeground(Color.white);
        yKeyPicLabel.add(symbol1, BorderLayout.CENTER);

        yKeyLabel = new JLabel("" + tower.getPlayer().yKey, JLabel.CENTER);
        yKeyLabel.setBounds(96 - 15, 256, 64 + 15, 30);
        yKeyLabel.setForeground(Color.white);
        yKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol2 = new JLabel("×", JLabel.CENTER);
        symbol2.setBounds(25, 0, 32, 26);
        symbol2.setForeground(Color.white);
        symbol2.setFont(new Font("微软雅黑", Font.PLAIN, 32));

        bKeyPicLabel = new JLabel();
        bKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_2.png")));
        bKeyPicLabel.setBounds(32, 288, 64, 32);
        bKeyPicLabel.setForeground(Color.white);
        bKeyPicLabel.add(symbol2, BorderLayout.CENTER);

        bKeyLabel = new JLabel("" + tower.getPlayer().bKey, JLabel.CENTER);
        bKeyLabel.setBounds(96 - 15, 288, 64 + 15, 30);
        bKeyLabel.setForeground(Color.white);
        bKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol3 = new JLabel("×", JLabel.CENTER);
        symbol3.setBounds(25, 0, 32, 26);
        symbol3.setForeground(Color.white);
        symbol3.setFont(new Font("微软雅黑", Font.PLAIN, 32));

        rKeyPicLabel = new JLabel();
        rKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_3.png")));
        rKeyPicLabel.setBounds(32, 320, 64, 32);
        rKeyPicLabel.setForeground(Color.white);
        rKeyPicLabel.add(symbol3, BorderLayout.CENTER);

        rKeyLabel = new JLabel("" + tower.getPlayer().rKey, JLabel.CENTER);
        rKeyLabel.setBounds(96 - 15, 320, 64 + 15, 30);
        rKeyLabel.setForeground(Color.white);
        rKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol4 = new JLabel("×", JLabel.CENTER);
        symbol4.setBounds(25, 0, 32, 26);
        symbol4.setForeground(Color.white);
        symbol4.setFont(new Font("微软雅黑", Font.PLAIN, 32));

        monPicLabel = new JLabel();
        monPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/money.png")));
        monPicLabel.setBounds(32, 352, 64, 32);
        monPicLabel.setForeground(Color.white);
        monPicLabel.add(symbol4, BorderLayout.CENTER);

        monLabel = new JLabel("" + tower.getPlayer().money, JLabel.CENTER);
        monLabel.setBounds(96 - 15, 352, 64 + 15, 30);
        monLabel.setForeground(Color.white);
        monLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        showMesLabel.setForeground(Color.white);
        showMesLabel.setFont(new Font(null, Font.BOLD, 22));
        showMesLabel.setBounds(125 + 32, -2, 350, 35);

        this.add(playerPicLabel);
        this.add(lvLabel);
        this.add(hpPicLabel);
        this.add(hpLabel);
        this.add(atkPicLabel);
        this.add(atkLabel);
        this.add(defPicLabel);
        this.add(defLabel);
        this.add(expPicLabel);
        this.add(expLabel);
        this.add(yKeyPicLabel);
        this.add(yKeyLabel);
        this.add(bKeyPicLabel);
        this.add(bKeyLabel);
        this.add(rKeyPicLabel);
        this.add(rKeyLabel);
        this.add(monPicLabel);
        this.add(monLabel);
        this.add(showMesLabel);
    }

    private void drawAttribute(Graphics g) {
        //构造背景
        //System.out.println("开始构造背景...");
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j <= 17; j++) {
                if (i == 7 && (j == 1 || j == 2 || j == 3 || j == 4)) {
                    g.drawImage(tower.getWallImage()[1], j * CS, i * CS, this);
                    continue;
                }
                if (i == 0 || i == 12 || j == 0 || j == 5 || j == 17) {
                    g.drawImage(tower.getWallImage()[1], j * CS, i * CS, this);
                } else {
                    g.drawImage(tower.getFloorImage()[0], j * CS, i * CS, this);
                }
            }
        }
        //System.out.println("开始构造属性界面...");
        if (tower.getPlayer().hp > 999999) {
            hpLabel.setText(Math.floor(tower.getPlayer().hp / 1000) / 10 + "w");
        } else {
            hpLabel.setText("" + tower.getPlayer().hp);
        }
        lvLabel.setText("Lv." + tower.getPlayer().level);
        atkLabel.setText("" + tower.getPlayer().attack);
        defLabel.setText("" + tower.getPlayer().defense);
        expLabel.setText("" + tower.getPlayer().exp);
        monLabel.setText("" + tower.getPlayer().money);
        yKeyLabel.setText("" + tower.getPlayer().yKey);
        bKeyLabel.setText("" + tower.getPlayer().bKey);
        rKeyLabel.setText("" + tower.getPlayer().rKey);
    }

    private void drawPlayer(Graphics g) {
        int startX = 6 * 32;
        int startY = 1 * 32;
        byte x = tower.getPlayer().x;
        byte y = tower.getPlayer().y;
        g.drawImage(tower.getPlayer().getPlayerIcon()[DIRECTION][moveNo].getImage(), startX + x * CS, startY + y * CS, 32, 32, this);
    }

    private void drawMap(Graphics g) {
        //System.out.println("构造地图中..." + frames);
        repaint();
        GameMap gameMap;
        if (isNormalFloor()) {
            gameMap = tower.getGameMapList().get(floor);
        } else {
            gameMap = tower.getSpecialMap().get(specialGameMapNo);
        }
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        int startX = 6 * 32;
        int startY = 1 * 32;
        try {
            for (int i = 0; i < GAME_ROW; i++) {
                for (int j = 0; j < GAME_COL; j++) {
                    if (layer3[i][j].contains("wall")) {
                        try {
                            String wallId = tower.getWallMap().get(layer3[i][j]).getId();
                            g.drawImage(getImageFromIcons(tower.getWallMap().get(wallId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer3 (x=" + i + ",y=" + j + ") wallId(" + layer3[i][j] + ") 不存在!");
                        }
                    } else if (layer3[i][j].contains("door")) {
                        if (!layer3[i][j].contains("open")) {
                            try {
                                String doorId = tower.getDoorMap().get(layer3[i][j]).getId();
                                g.drawImage(tower.getDoorMap().get(doorId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                            } catch (Exception e) {
                                System.err.println("layer3 (x=" + i + ",y=" + j + ") doorId(" + layer3[i][j] + ") 不存在!");
                            }
                        } else {
                            String doorId = tower.getDoorMap().get(layer3[i][j].substring(0, layer3[i][j].indexOf("open"))).getId();
                            byte no;
                            try {
                                no = Byte.parseByte(layer3[i][j].substring(layer3[i][j].length() - 1));
                            } catch (Exception e) {
                                no = -1;
                                e.printStackTrace();
                            }
                            try {
                                g.drawImage(tower.getDoorMap().get(doorId).getIcon()[no].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (layer3[i][j].contains("stair")) {
                        try {
                            String stairId = tower.getStairMap().get(layer3[i][j]).getId();
                            g.drawImage(getImageFromIcons(tower.getStairMap().get(stairId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer3 (x=" + i + ",y=" + j + ") stairId(" + layer3[i][j] + ") 不存在!");
                        }
                    }
                    if (layer2[i][j].contains("item")) {
                        try {
                            String itemId = tower.getItemMap().get(layer2[i][j]).getId();
                            g.drawImage(tower.getItemMap().get(itemId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer2 (x=" + i + ",y=" + j + ") itemId(" + layer2[i][j] + ") 不存在!");
                        }
                    }
                    if (layer1[i][j].contains("monster")) {
                        try {
                            String monsterId = tower.getMonsterMap().get(layer1[i][j]).getId();
                            g.drawImage(getImageFromIcons(tower.getMonsterMap().get(monsterId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer1 (x=" + i + ",y=" + j + ") monsterId(" + layer1[i][j] + ") 不存在!");
                        }
                    } else if (layer1[i][j].contains("npc")) {
                        try {
                            String npcId = tower.getNpcMap().get(layer1[i][j]).getId();
                            g.drawImage(getImageFromIcons(tower.getNpcMap().get(npcId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer1 (x=" + i + ",y=" + j + ") npcId(" + layer1[i][j] + ") 不存在!");
                        }
                    } else if (layer1[i][j].contains("shop")) {
                        try {
                            String shopId = tower.getShopMap().get(layer1[i][j]).getId();
                            g.drawImage(getImageFromIcons(tower.getShopMap().get(shopId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer1 (x=" + i + ",y=" + j + ") shopId(" + layer1[i][j] + ") 不存在!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * @param icons
     * @param interval 间隔多少帧切换一次 当间隔大于每秒帧数时,不会改变
     * @return
     */
    private Image getImageFromIcons(ImageIcon[] icons, float interval) {
        if (frames <= interval - 1) {
            return icons[0].getImage();
        } else if (frames <= 2 * interval - 1) {
            return icons[1 % icons.length].getImage();
        } else if (frames <= 3 * interval - 1) {
            return icons[2 % icons.length].getImage();
        } else if (frames <= 4 * interval - 1) {
            return icons[3 % icons.length].getImage();
        } else if (frames <= 5 * interval - 1) {
            return icons[4 % icons.length].getImage();
        } else if (frames <= 6 * interval - 1) {
            return icons[5 % icons.length].getImage();
        } else if (frames <= 7 * interval - 1) {
            return icons[6 % icons.length].getImage();
        } else if (frames <= 8 * interval - 1) {
            return icons[7 % icons.length].getImage();
        }
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {// 描绘窗体，此处在默认JPanel基础上构建底层地图
        super.paintComponent(g);
        if (running) {
            drawAttribute(g);
            drawMap(g);
            try {
                drawPlayer(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        int fps = 0, tick = 0;
        double fpsTimer = System.currentTimeMillis();
        double nsPerTick = 1000000000.0 / 10;
        double then = System.nanoTime();
        double unp = 0;
        while (running) {
            double now = System.nanoTime();
            unp += (now - then) / nsPerTick;
            then = now;
            while (unp >= 1) {
                tick++;
                tick();
                --unp;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fps++;
            if (System.currentTimeMillis() - fpsTimer > 125) {
                playerPicLabel.setIcon(tower.getPlayer().getPlayerIcon()[1][frames % 4]);
                if (frames == 7) {
                    System.out.printf("FPS:%d %n", fps);
                    //System.out.printf("FPS:%d tick:%d %n", fps, tick);
                    frames = 0;
                    fps = 0;
                    tick = 0;
                } else {
                    frames++;
                }
                fpsTimer += 125;
            }
        }
        //System.out.println("end");
    }

    /**
     * 玩家上次移动时间
     */
    public long lastMove = System.currentTimeMillis();
    /**
     * 不动多久后玩家动作停止
     */
    private static short stopTime = 180;
    /**
     * 玩家动作帧数计数
     */
    private byte moveNo = 0;

    public void tick() {
        if (!canMove) {
            lastMove = System.currentTimeMillis();
            moveNo = 0;
            return;
        }
        if (isNormalFloor()) {
            String stair = tower.getGameMapList().get(floor).layer3[tower.getPlayer().y][tower.getPlayer().x];
            if (stair.equals("stair01")) {
                musicPlayer.upAndDown();
                floor--;
                tower.getPlayer().x = tower.getGameMapList().get(floor).downPositionX;
                tower.getPlayer().y = tower.getGameMapList().get(floor).downPositionY;
                showMesLabel.setText("魔塔 第" + floor + "层");
                DIRECTION = DIRECTION_DOWN;
                musicPlayer.playBackgroundMusic(floor);
                nowMonsterManual = 0;
                if (floor < tower.getPlayer().minFloor) {
                    tower.getPlayer().minFloor = floor;
                }
                return;
            } else if (stair.equals("stair02")) {
                musicPlayer.upAndDown();
                floor++;
                tower.getPlayer().x = tower.getGameMapList().get(floor).upPositionX;
                tower.getPlayer().y = tower.getGameMapList().get(floor).upPositionY;
                showMesLabel.setText("魔塔 第" + floor + "层");
                DIRECTION = DIRECTION_DOWN;
                musicPlayer.playBackgroundMusic(floor);
                nowMonsterManual = 0;
                if (floor > tower.getPlayer().maxFloor) {
                    tower.getPlayer().maxFloor = floor;
                }
                return;
            } else if (stair.contains("stair03") || stair.contains("stair04")) {
                tower.getStairMap().get(stair).script(this, tower, specialGameMapNo);
                return;
            }
        } else {
            String stair = tower.getSpecialMap().get(specialGameMapNo).layer3[tower.getPlayer().y][tower.getPlayer().x];
            if (stair.contains("stair03") || stair.contains("stair04")) {
                tower.getStairMap().get(stair).script(this, tower, specialGameMapNo);
                return;
            }
        }
        if (input.up.down) {
            this.DIRECTION = DIRECTION_UP;
            moveNo = (byte) ((moveNo + 1) % 4);
            if (!canMove(tower.getPlayer().x, (byte) (tower.getPlayer().y - 1))) {
                return;
            }
            musicPlayer.walk();
            tower.getPlayer().y--;
            lastMove = System.currentTimeMillis();
        } else if (input.down.down) {
            this.DIRECTION = DIRECTION_DOWN;
            moveNo = (byte) ((moveNo + 1) % 4);
            if (!canMove(tower.getPlayer().x, (byte) (tower.getPlayer().y + 1))) {
                return;
            }
            musicPlayer.walk();
            tower.getPlayer().y++;
            lastMove = System.currentTimeMillis();
        } else if (input.left.down) {
            this.DIRECTION = DIRECTION_LEFT;
            if (!canMove((byte) (tower.getPlayer().x - 1), tower.getPlayer().y)) {
                return;
            }
            musicPlayer.walk();
            moveNo = (byte) ((moveNo + 1) % 4);
            tower.getPlayer().x--;
            lastMove = System.currentTimeMillis();
        } else if (input.right.down) {
            this.DIRECTION = DIRECTION_RIGHT;
            if (!canMove((byte) (tower.getPlayer().x + 1), tower.getPlayer().y)) {
                return;
            }
            musicPlayer.walk();
            moveNo = (byte) ((moveNo + 1) % 4);
            tower.getPlayer().x++;
            lastMove = System.currentTimeMillis();
        } else if (canUseMonsterManual && input.use_rod.down) {
            canMove = false;
            new Thread(() -> {
                //System.out.println("开始计算");
                String[][] monsterLayer;
                if (isNormalFloor()) {
                    monsterLayer = tower.getGameMapList().get(floor).layer1;
                } else {
                    monsterLayer = tower.getSpecialMap().get(specialGameMapNo).layer1;
                }
                Map<String, Boolean> monsterIdMap = new HashMap<>();
                //y
                for (int i = 0; i < monsterLayer.length; i++) {
                    //x
                    for (int j = 0; j < monsterLayer[i].length; j++) {
                        if (monsterLayer[j][i] != null && monsterLayer[j][i].contains("monster")) {
                            monsterIdMap.put(monsterLayer[j][i], true);
                        }
                    }
                }
                Iterator iter = monsterIdMap.entrySet().iterator();
                int monsterNo = 0;
                List<FightCalc> fightCalcList = new ArrayList<>();
                List<FightCalc> dieAttackList = new ArrayList<>();
                boolean monster11 = false, monster12 = false;
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    Monster monster = tower.getMonsterMap().get(key);
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
                    monsterNo++;
                    int no = 0;
                    if (fightCalc.canAttack) {
                        for (int i = 0; i < fightCalcList.size(); i++) {
                            if (fightCalcList.get(i).mDamageTotal >= fightCalc.mDamageTotal) {
                                no = i;
                                break;
                            }
                            if (i == fightCalcList.size() - 1) {
                                no = fightCalcList.size();
                                break;
                            }
                        }
                        fightCalcList.add(no, fightCalc);
                    } else {
                        for (int i = 0; i < dieAttackList.size(); i++) {
                            if (dieAttackList.get(i).getMonster().getAttack() >= fightCalc.getMonster().getAttack()) {
                                no = i;
                                break;
                            }
                            if (i == dieAttackList.size() - 1) {
                                no = dieAttackList.size();
                                break;
                            }
                        }
                        dieAttackList.add(no, fightCalc);
                    }
                }
                fightCalcList.addAll(dieAttackList);
                //System.out.println("计算完成,共" + monsterNo + "只怪物");
                showMonsterManual(fightCalcList);
                canMove = true;
            }).start();
        } else if (canUseFloorTransfer && input.use_floor_transfer.down) {
            if (!isNormalFloor()) {
                musicPlayer.fail();
                return;
            }
            canMove = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    showFloorTransfer();
                    canMove = true;
                }
            }).start();
        }
        //TODO 正式版这里要去掉
        else if (input.escape.down) {
            end();
        } else if (input.save.down) {
            save();
        } else if (input.load.down) {
            load();
        }
        if (System.currentTimeMillis() - lastMove > stopTime) {
            moveNo = 0;
        }
        if (isNormalFloor()) {
            if (tower.getGameMapList().get(floor).layer3[tower.getPlayer().y][tower.getPlayer().x].contains("door") && !tower.getGameMapList().get(floor).layer3[tower.getPlayer().y][tower.getPlayer().x].contains("open")) {
                tower.getGameMapList().get(floor).layer3[tower.getPlayer().y][tower.getPlayer().x] += "open";
                return;
            }
        } else {
            if (tower.getSpecialMap().get(specialGameMapNo).layer3[tower.getPlayer().y][tower.getPlayer().x].contains("door") && !tower.getSpecialMap().get(specialGameMapNo).layer3[tower.getPlayer().y][tower.getPlayer().x].contains("open")) {
                tower.getSpecialMap().get(specialGameMapNo).layer3[tower.getPlayer().y][tower.getPlayer().x] += "open";
                return;
            }
        }
    }

    /**
     * 开门时间
     */
    private static final byte DOOR_OPEN_TIME = 40;
    /**
     * 对话中按下空格
     */
    private boolean escapeDown = false;

    /**
     * 判断能否移动到 (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    private boolean canMove(byte x, byte y) {
        GameMap gameMap;
        if (isNormalFloor()) {
            gameMap = tower.getGameMapList().get(floor);
        } else {
            gameMap = tower.getSpecialMap().get(specialGameMapNo);
        }
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        if (x >= GAME_COL || x < 0 || y >= GAME_ROW || y < 0) {
            return false;
        }
        if (layer1[y][x].contains("npc")) {
            canMove = false;
            new Thread(() -> {
                NPC npc;
                try {
                    npc = tower.getNpcMap().get(layer1[y][x]);
                } catch (Exception e) {
                    System.err.println("layer1 (x=" + y + ",y=" + x + ") npcId(" + layer1[y][x] + ") 不存在!");
                    return;
                }
                npc.script_start(tower);
                //重新获取一边,以防npc改变而这里没变
                npc = tower.getNpcMap().get(layer1[y][x]);
                if (!npc.canMeet) {
                    canMove = true;
                    return;
                }
                meetNpc(layer1[y][x]);
                if (npc.canRemove) {
                    if (isNormalFloor()) {
                        tower.getGameMapList().get(floor).layer1[y][x] = "";
                    } else {
                        tower.getSpecialMap().get(specialGameMapNo).layer1[y][x] = "";
                    }
                }
                npc.script_end(tower);
                canMove = true;
                input.clear();
            }).start();
            return false;
        } else if (layer1[y][x].contains("shop")) {
            canMove = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Shop shop;
                    try {
                        shop = tower.getShopMap().get(layer1[y][x]);
                    } catch (Exception e) {
                        System.err.println("layer1 (x=" + x + ",y=" + y + ") shopId(" + layer1[y][x] + ") 不存在!");
                        return;
                    }
                    if (!shop.canMeet) {
                        canMove = true;
                        return;
                    }
                    meetShop(shop.getId());
                }
            }).start();
            return false;
        }
        if (layer3[y][x].contains("wall")) {
            return false;
        } else if (layer3[y][x].contains("door") && !layer3[y][x].contains("open")) {
            boolean open = false;
            switch (layer3[y][x]) {
                case "door01":
                    if (tower.getPlayer().yKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        tower.getPlayer().yKey--;
                        open = true;
                    }
                    break;
                case "door02":
                    if (tower.getPlayer().bKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        tower.getPlayer().bKey--;
                        open = true;
                    }
                    break;
                case "door03":
                    if (tower.getPlayer().rKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        tower.getPlayer().rKey--;
                        open = true;
                    }
                    break;
                default:
                    break;
            }
            if (!open && (layer3[y][x].contains("door04") || layer3[y][x].contains("door05"))) {
                try {
                    if (tower.getDoorMap().get(layer3[y][x]).openable) {
                        musicPlayer.openSpecialDoor();
                        open = true;
                    }
                } catch (Exception e) {
                    System.err.println("layer3 (x=" + x + ",y=" + y + ") doorId(" + layer1[y][x] + ") 不存在!");
                    return false;
                }
            }
            if (open) {
                new Thread(() -> {
                    if (isNormalFloor()) {
                        if (tower.getGameMapList().get(floor).layer3[y][x].equals("") || tower.getGameMapList().get(floor).layer3[y][x].contains("open")) {
                            return;
                        }
                        byte f = (byte) floor;
                        for (int i = 1; i < 5; i++) {
                            if (i == 1) {
                                tower.getGameMapList().get(f).layer3[y][x] += "open1";
                            } else if (i == 4) {
                                tower.getGameMapList().get(f).layer3[y][x] = "";
                            } else {
                                String str = tower.getGameMapList().get(f).layer3[y][x];
                                try {
                                    tower.getGameMapList().get(f).layer3[y][x] = str.substring(0, str.length() - 1) + i;
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    tower.getGameMapList().get(f).layer3[y][x] = "";
                                }
                            }
                            try {
                                Thread.sleep(DOOR_OPEN_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        tower.getGameMapList().get(f).layer3[y][x] = "";
                    } else {
                        if (tower.getSpecialMap().get(specialGameMapNo).layer3[y][x].equals("") || tower.getSpecialMap().get(specialGameMapNo).layer3[y][x].contains("open")) {
                            return;
                        }
                        String f = specialGameMapNo;
                        for (int i = 1; i < 5; i++) {
                            if (i == 1) {
                                tower.getSpecialMap().get(f).layer3[y][x] += "open1";
                            } else if (i == 4) {
                                tower.getSpecialMap().get(f).layer3[y][x] = "";
                            } else {
                                String str = tower.getSpecialMap().get(f).layer3[y][x];
                                try {
                                    tower.getSpecialMap().get(f).layer3[y][x] = str.substring(0, str.length() - 1) + i;
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    tower.getSpecialMap().get(f).layer3[y][x] = "";
                                }
                            }
                            try {
                                Thread.sleep(DOOR_OPEN_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        tower.getSpecialMap().get(f).layer3[y][x] = "";
                    }
                }).start();
            }
            return false;
        }
        if (layer2[y][x].contains("item")) {
            boolean flag = false;
            if (layer2[y][x].contains("item01")) {
                switch (layer2[y][x]) {
                    case "item01_1":
                        showMesLabel.setText("获得1把黄钥匙");
                        tower.getPlayer().yKey++;
                        flag = true;
                        break;
                    case "item01_2":
                        showMesLabel.setText("获得1把蓝钥匙");
                        tower.getPlayer().bKey++;
                        flag = true;
                        break;
                    case "item01_3":
                        showMesLabel.setText("获得1把红钥匙");
                        tower.getPlayer().rKey++;
                        flag = true;
                        break;
                    case "item01_5":
                        showMesLabel.setText("获得万能钥匙,钥匙数量各+1");
                        tower.getPlayer().yKey++;
                        tower.getPlayer().bKey++;
                        tower.getPlayer().rKey++;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item02")) {
                switch (layer2[y][x]) {
                    case "item02_1":
                        showMesLabel.setText("获得红宝石,攻击+3");
                        tower.getPlayer().attack += 3;
                        flag = true;
                        break;
                    case "item02_2":
                        showMesLabel.setText("获得蓝宝石,防御+3");
                        tower.getPlayer().defense += 3;
                        flag = true;
                        break;
                    case "item02_3":
                        showMesLabel.setText("获得绿宝石,攻防各+3");
                        tower.getPlayer().attack += 3;
                        tower.getPlayer().defense += 3;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item03")) {
                switch (layer2[y][x]) {
                    case "item03_1":
                        showMesLabel.setText("获得小体力药水,生命+200");
                        tower.getPlayer().hp += 200;
                        flag = true;
                        break;
                    case "item03_2":
                        showMesLabel.setText("获得大体力药水,生命+500");
                        tower.getPlayer().hp += 500;
                        flag = true;
                        break;
                    case "item03_3":
                        showMesLabel.setText("获得经验药水,经验+10");
                        tower.getPlayer().exp += 10;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item04")) {
                switch (layer2[y][x]) {
                    case "item04_1":
                        showMesLabel.setText("获得铁剑,攻击+10");
                        tower.getPlayer().attack += 10;
                        flag = true;
                        break;
                    case "item04_2":
                        showMesLabel.setText("获得银剑,攻击+30");
                        tower.getPlayer().attack += 30;
                        flag = true;
                        break;
                    case "item04_3":
                        showMesLabel.setText("获得武士剑,攻击+50");
                        tower.getPlayer().attack += 50;
                        flag = true;
                        break;
                    case "item04_4":
                        showMesLabel.setText("获得圣剑,攻击+120");
                        tower.getPlayer().attack += 120;
                        flag = true;
                        break;
                    case "item04_5":
                        showMesLabel.setText("获得圣神剑,攻击+190");
                        tower.getPlayer().attack += 190;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item05")) {
                switch (layer2[y][x]) {
                    case "item05_1":
                        showMesLabel.setText("获得铁盾,防御+10");
                        tower.getPlayer().defense += 10;
                        flag = true;
                        break;
                    case "item05_2":
                        showMesLabel.setText("获得银盾,防御+30");
                        tower.getPlayer().defense += 30;
                        flag = true;
                        break;
                    case "item05_3":
                        showMesLabel.setText("获得武士盾,防御+50");
                        tower.getPlayer().defense += 50;
                        flag = true;
                        break;
                    case "item05_4":
                        showMesLabel.setText("获得圣盾,防御+120");
                        tower.getPlayer().defense += 120;
                        flag = true;
                        break;
                    case "item05_5":
                        showMesLabel.setText("获得圣神盾,防御+190");
                        tower.getPlayer().defense += 190;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item06")) {
                switch (layer2[y][x]) {
                    case "item06_3":
                        showMesLabel.setText("获得圣水瓶,生命值翻倍");
                        tower.getPlayer().hp *= 2;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item07")) {
                switch (layer2[y][x]) {
                    case "item07_1":
                        showMesLabel.setText("获得心之灵杖");
                        flag = true;
                        tower.getPlayer().inventory.put("SpiritStick", 1);
                        if (tower.getPlayer().inventory.containsKey("SunStick")) {
                            if (tower.getPlayer().inventory.get("SunStick").equals(1)) {
                                tower.getDoorMap().get("door04_2").openable = true;
                            }
                        }
                        break;
                    case "item07_3":
                        showMesLabel.setText("获得炎之灵杖");
                        flag = true;
                        tower.getPlayer().inventory.put("SunStick", 1);
                        if (tower.getPlayer().inventory.containsKey("SpiritStick")) {
                            if (tower.getPlayer().inventory.get("SpiritStick").equals(1)) {
                                tower.getDoorMap().get("door04_2").openable = true;
                            }
                        }
                        break;
                }
            } else if (layer2[y][x].contains("item08")) {
                switch (layer2[y][x]) {
                    case "item08_1":
                        showMesLabel.setText("获得小飞羽,等级+1");
                        tower.getPlayer().level++;
                        tower.getPlayer().attack += 7;
                        tower.getPlayer().defense += 7;
                        tower.getPlayer().hp += 1000;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item09")) {
                switch (layer2[y][x]) {
                    case "item09_1":
                        showMesLabel.setText("获得幸运硬币,金币+300");
                        tower.getPlayer().money += 300;
                        flag = true;
                        break;
                    case "item09_4":
                        showMesLabel.setText("获得风之罗盘,可以使用楼层传送");
                        canUseFloorTransfer = true;
                        flag = true;
                        break;
                    case "item09_5":
                        showMesLabel.setText("获得幸运十字架");
                        tower.getPlayer().inventory.put("item09_5", 1);
                        flag = true;
                        break;
                    case "item09_6":
                        showMesLabel.setText("获得圣光徽,可以查看怪物信息");
                        canUseMonsterManual = true;
                        flag = true;
                        break;
                    case "item09_8":
                        showMesLabel.setText("获得星光神锒");
                        tower.getPlayer().inventory.put("LumpHammer", 1);
                        flag = true;
                        break;
                }
            }
            if (flag) {
                Item item = tower.getItemMap().get(layer2[y][x]);
                if (item.msg != null) {
                    canMove = false;
                    musicPlayer.getSpecialItem();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getSpecialItem(item);
                        }
                    }).start();
                } else {
                    musicPlayer.getItem();
                }
            } else {
                try {
                    showMesLabel.setText("获得" + tower.getItemMap().get(layer2[y][x]).getName() + ",嘛事没有");
                } catch (Exception e) {
                    System.err.println("layer2 (x=" + x + ",y=" + y + ") itemId(" + layer2[y][x] + ") 不存在!");
                }
            }
            if (isNormalFloor()) {
                tower.getGameMapList().get(floor).layer2[y][x] = "";
            } else {
                tower.getSpecialMap().get(specialGameMapNo).layer2[y][x] = "";
            }
            return false;
        }
        if (layer1[y][x].contains("monster")) {
            Monster monster = null;
            try {
                monster = tower.getMonsterMap().get(layer1[y][x]);
            } catch (Exception e) {
                System.err.println("layer1 (x=" + x + ",y=" + y + ") monsterId(" + layer1[y][x] + ") 不存在!");
            }
            monster.script_start(tower);
            FightCalc fightCalc = new FightCalc(tower.getPlayer(), monster);
            if (!fightCalc.canAttack) {
                showMesLabel.setText("无法击杀:" + tower.getMonsterMap().get(layer1[y][x]).getName());
                //System.out.println("无法击杀");
                return false;
            }
            int pHP = tower.getPlayer().hp - fightCalc.mDamageTotal;
            if (pHP > 0) {
                musicPlayer.fight();
                showMesLabel.setText("击杀:" + monster.getName() + ",损失" + (tower.getPlayer().hp - pHP) + "HP");
                if (isNormalFloor()) {
                    tower.getGameMapList().get(floor).layer1[y][x] = "";
                } else {
                    tower.getSpecialMap().get(specialGameMapNo).layer1[y][x] = "";
                }
                tower.getPlayer().hp = pHP;
                tower.getPlayer().money += monster.getMoney();
                tower.getPlayer().exp += monster.getExp();
                monster.script_end(this, tower);
                return true;
            } else {
                showMesLabel.setText("无法击杀:" + tower.getMonsterMap().get(layer1[y][x]).getName());
                //System.out.println("无法击杀");
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否在普通法楼层
     *
     * @return 如果在普通楼层, 则返回true;反之,则返回false
     */
    public boolean isNormalFloor() {
        return specialGameMapNo == null || specialGameMapNo.equals("");
    }

    private String secretScript = "";

    /**
     * 与NPC对话
     *
     * @param npcId
     */
    public void meetNpc(String npcId) {
        NPC npc = tower.getNpcMap().get(npcId);
        List<Dialogue> dialogues = npc.dialogues;
        for (int i = 0; i < dialogues.size(); i++) {
            Dialogue dialogue = dialogues.get(i);
            System.out.println(dialogue.name + ":" + dialogue.text);
            dialogBox = new JDialog(mainframe, null, true);
            String s;
            ImageIcon photo;
            JPanel dialogp = new JPanel(null);
            JLabel pict = new JLabel();
            JLabel name;
            JTextArea content = new JTextArea();
            content.setBorder(BorderFactory.createLineBorder(Color.white));
            JLabel tip = new JLabel("Space...");
            if (dialogue.name.contains("player")) {
                pict.setBounds(208, 8, 32, 32);
                name = new JLabel("勇士");
                name.setBounds(176, 16, 32, 16);
                photo = new ImageIcon(tower.getPlayer().getPlayerIcon()[1][0].getImage());
            } else {
                pict.setBounds(13, 8, 32, 32);
                name = new JLabel(npc.getName());
                name.setBounds(48, 16, 120, 16);
                photo = new ImageIcon(tower.getNpcMap().get(npcId).getIcon()[0].getImage());
            }
            pict.setIcon(photo);
            name.setFont(new Font("宋体", Font.BOLD, 13));
            name.setBackground(Color.white);
            name.setForeground(Color.white);
            s = dialogue.text;
            dialogBox.setSize(256, 128);
            dialogBox.setUndecorated(true);
            content.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent arg0) {

                }

                public void keyReleased(KeyEvent arg0) {

                }

                public void keyPressed(KeyEvent arg0) {
                    boolean close = false;
                    switch (arg0.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            close = true;
                            musicPlayer.dialogueSpace();
                            break;
                    }
                    if (npc.getName().equals("奇怪的人")) {
                        switch (arg0.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                secretScript += "8";
                                break;
                            case KeyEvent.VK_DOWN:
                                secretScript += "2";
                                break;
                            case KeyEvent.VK_LEFT:
                                secretScript += "4";
                                break;
                            case KeyEvent.VK_RIGHT:
                                secretScript += "6";
                                break;
                            case KeyEvent.VK_A:
                                secretScript += "A";
                                break;
                            case KeyEvent.VK_B:
                                secretScript += "B";
                                break;
                        }
                        if (secretScript.equals("88224646BABA")) {
                            close = true;
                            tower.getGameMapList().get(0).layer3[10][5] = "stair03_1";
                            tower.getGameMapList().get(1).layer1[9][6] = "";
                        }
                    }
                    if (close) {
                        secretScript = "";
                        escapeDown = true;
                        dialogBox.dispose();
                    }
                }
            });
            dialogp.setSize(256, 128);
            dialogp.setBackground(Color.black);
            content.setText(s);
            content.setLineWrap(true);
            content.setEditable(false);
            content.setBounds(4, 48, 248, 58);
            content.setFont(new Font("宋体", Font.BOLD, 16));
            content.setBackground(Color.black);
            content.setForeground(Color.WHITE);
            tip.setBounds(212, 105, 50, 25);
            tip.setFont(new Font("微软雅黑", Font.BOLD, 11));
            tip.setForeground(Color.white);
            tip.setBackground(Color.white);
            dialogp.add(pict);
            dialogp.add(name);
            dialogp.add(content);
            dialogp.add(tip);
            dialogBox.setLocation(mainframe.getLocation().x + 242, mainframe.getLocation().y + 125);
            dialogBox.add(dialogp);
            dialogBox.setVisible(true);
            while (!escapeDown) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            escapeDown = false;
        }
    }

    byte nowSelected = 0;

    public void meetShop(String shopId) {
        Shop shop = tower.getShopMap().get(shopId);
        dialogBox = new JDialog(mainframe, null, true);
        String s;
        ImageIcon photo;
        JPanel dialogp = new JPanel(null);
        JLabel shopImg = new JLabel();
        JTextArea shopDialogue = new JTextArea();
        //shopDialogue.setBorder(BorderFactory.createLineBorder(Color.white));
        shopImg.setBounds(10, 8, 32, 32);
        JLabel name = new JLabel(shop.getName());
        name.setBounds(50, 12, 200, 25);
        photo = new ImageIcon(shop.getIcon()[0].getImage());
        shopImg.setIcon(photo);
        name.setFont(new Font("微软雅黑", Font.BOLD, 20));
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        JLabel selectLabel = new JLabel();
        selectLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/selected.png")));
        selectLabel.setBounds(10, 100, 30, 30);
        selectLabel.setForeground(Color.white);
        //selectLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        dialogBox.add(selectLabel);
        List<String> sellNameList = shop.sell.name;
        for (int i = 0; i < sellNameList.size(); i++) {
            JLabel label = new JLabel(sellNameList.get(i), JLabel.CENTER);
            label.setBounds(34, 100 + 30 * i, 200, 30);
            label.setForeground(Color.white);
            label.setFont(new Font("微软雅黑", Font.BOLD, 16));
            //label.setOpaque(true);
            //label.setBackground(new Color(255, 255, 255, 100));
            dialogBox.add(label);
        }
        shopDialogue.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                Shop shop = tower.getShopMap().get(shopId);
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        input.clear();
                        if (nowSelected <= 0) {
                            break;
                        }
                        musicPlayer.shopSelect();
                        nowSelected--;
                        selectLabel.setBounds(10, 100 + nowSelected * 30, 30, 30);
                        break;
                    case KeyEvent.VK_DOWN:
                        input.clear();
                        if (nowSelected >= 3) {
                            break;
                        }
                        musicPlayer.shopSelect();
                        nowSelected++;
                        selectLabel.setBounds(10, 100 + nowSelected * 30, 30, 30);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (sellNameList.get(nowSelected).contains("离开")) {
                            musicPlayer.upAndDown();
                            dialogBox.dispose();
                            canMove = true;
                            input.clear();
                            nowSelected = 0;
                            break;
                        }
                        short price;
                        if (shop.price != 0) {
                            price = (short) shop.price;
                        } else {
                            price = Short.valueOf(shop.sell.price.get(nowSelected) + "");
                        }
                        if (shop.need.equals("money")) {
                            if (tower.getPlayer().money >= price) {
                                musicPlayer.shopBuySuc();
                                tower.getPlayer().money -= price;
                                shop.buyNum++;
                                shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
                                List<String> attributeList = shop.sell.attribute;
                                List<Short> valList = shop.sell.val;
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
                            } else {
                                musicPlayer.shopBuyFail();
                            }
                        } else if (shop.need.equals("exp")) {
                            if (tower.getPlayer().exp >= price) {
                                musicPlayer.shopExpBuySuc();
                                tower.getPlayer().exp -= price;
                                shop.buyNum++;
                                shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
                                List<String> attributeList = shop.sell.attribute;
                                List<Short> valList = shop.sell.val;
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
                            } else {
                                musicPlayer.shopBuyFail();
                            }
                        } else if (shop.need.equals("item")) {
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
                                musicPlayer.shopBuySuc();
                            } else {
                                musicPlayer.shopBuyFail();
                            }
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        musicPlayer.upAndDown();
                        dialogBox.dispose();
                        canMove = true;
                        input.clear();
                        nowSelected = 0;
                        break;

                }
            }
        });
        dialogp.setSize(268, 235);
        dialogp.setBackground(Color.black);
        shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
        shopDialogue.setLineWrap(true);
        shopDialogue.setEditable(false);
        shopDialogue.setBounds(4, 48, 260, 40);
        shopDialogue.setFont(new Font("宋体", Font.BOLD, 16));
        shopDialogue.setBackground(Color.black);
        shopDialogue.setForeground(Color.WHITE);
        dialogp.add(shopImg);
        dialogp.add(name);
        dialogp.add(shopDialogue);
        dialogBox.setSize(268, 235);
        dialogBox.setUndecorated(true);
        dialogBox.setLocation(mainframe.getLocation().x + 237, mainframe.getLocation().y + 100);
        dialogBox.add(dialogp);
        dialogBox.setVisible(true);
    }

    public void getSpecialItem(Item item) {
        dialogBox = new JDialog(mainframe, null, true);
        JPanel dialogp = new JPanel(null);
        JLabel pict = new JLabel();
        JLabel name;
        JTextArea content = new JTextArea();
        //content.setBorder(BorderFactory.createLineBorder(Color.white));
        JLabel tip = new JLabel("Space...");
        name = new JLabel(item.getName(), JLabel.CENTER);
        //name.setBorder(BorderFactory.createLineBorder(Color.white));
        name.setBounds(0, 10, 400, 30);
        name.setFont(new Font("微软雅黑", Font.BOLD, 20));
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        dialogBox.setSize(400, 128);
        dialogBox.setUndecorated(true);
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        dialogBox.dispose();
                        input.clear();
                        canMove = true;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        dialogBox.dispose();
                        input.clear();
                        canMove = true;
                        break;
                }
            }
        });
        dialogp.setSize(400, 128);
        dialogp.setBackground(Color.black);
        dialogp.setBorder(BorderFactory.createLineBorder(new Color(228, 122, 0), 3));
        content.setText(item.msg);
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(6, 48, 388, 58);
        content.setFont(new Font("宋体", Font.BOLD, 16));
        content.setBackground(Color.black);
        content.setForeground(Color.WHITE);
        tip.setBounds(354, 104, 50, 25);
        tip.setFont(new Font("微软雅黑", Font.BOLD, 11));
        tip.setForeground(Color.white);
        tip.setBackground(Color.white);
        dialogp.add(pict);
        dialogp.add(name);
        dialogp.add(content);
        dialogp.add(tip);
        dialogBox.setLocation(mainframe.getLocation().x + 171, mainframe.getLocation().y + 125);
        dialogBox.add(dialogp);
        dialogBox.setVisible(true);
    }

    byte nowMonsterManual = 0;

    public void showMonsterManual(List<FightCalc> fightCalcList) {
        if (fightCalcList.size() == 0) {
            return;
        }
        dialogBox = new JDialog(mainframe, null, true);
        JPanel dialogp = new JPanel(null);
        JLabel pict = new JLabel();
        JTextArea content = new JTextArea();
        //content.setBorder(BorderFactory.createLineBorder(Color.white));
        dialogBox.setSize(352, 352);
        dialogBox.setUndecorated(true);
        for (int i = 8 * nowMonsterManual; i < fightCalcList.size() && i < 8 * (nowMonsterManual + 1); i++) {
            Monster monster = fightCalcList.get(i).getMonster();
            JLabel mainLabel = new JLabel();
            mainLabel.setBounds(3, 8 + 42 * (i % 8), 346, 40);
            mainLabel.setForeground(Color.white);
            mainLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

            JLabel picLabel = new JLabel();
            picLabel.setBounds(3, 4, 32, 32);
            picLabel.setIcon(monster.getIcon()[0]);

            ImageIcon background = new ImageIcon(tower.getFloorImage()[0]);
            background.setImage(background.getImage().getScaledInstance(background.getIconWidth(), background.getIconHeight(), Image.SCALE_DEFAULT));

            JLabel backgroundLabel = new JLabel();
            backgroundLabel.setIcon(background);
            backgroundLabel.setBounds(3, 4, 32, 32);

            JLabel nameLabel = new JLabel("名称", JLabel.CENTER);
            nameLabel.setBounds(38, 3, 30, 15);
            nameLabel.setForeground(Color.white);
            nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            //nameLabel.setOpaque(true);
            //nameLabel.setBackground(new Color(255, 255, 255, 100));

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
            dialogBox.add(mainLabel);
        }
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_D:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (nowMonsterManual != 0) {
                            nowMonsterManual--;
                            changeFlag = true;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (nowMonsterManual < fightCalcList.size() / 8.0 - 1) {
                            nowMonsterManual++;
                            changeFlag = true;
                        }
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    dialogBox.dispose();
                    input.clear();
                    canMove = true;
                    nowMonsterManual = 0;
                }
                if (changeFlag) {
                    input.clear();
                    dialogBox.dispose();
                    showMonsterManual(fightCalcList);
                }
            }
        });
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(0, 0, 1, 1);
        content.setBackground(Color.black);
        dialogp.setSize(352, 352);
        dialogp.setBackground(Color.black);
        dialogp.setBorder(BorderFactory.createLineBorder(new Color(228, 122, 0), 3));
        dialogp.add(pict);
        dialogp.add(content);
        dialogBox.setLocation(mainframe.getLocation().x + 195, TITLE_HEIGHT + mainframe.getLocation().y + 32);
        dialogBox.add(dialogp);
        dialogBox.setVisible(true);
    }

    int nowSelectFloor = 0;

    public void showFloorTransfer() {
        if ((nowSelectFloor = floor) < 0) {
            nowSelectFloor = 0;
        }
        dialogBox = new JDialog(mainframe, null, true);
        JPanel dialogp = new JPanel(null);
        JLabel pict = new JLabel();
        JTextArea content = new JTextArea();
        //content.setBorder(BorderFactory.createLineBorder(Color.white));
        dialogBox.setSize(352, 352);
        dialogBox.setUndecorated(true);

        JLabel mainLabel = new JLabel();
        mainLabel.setBounds(50, 135, 250, 80);
        mainLabel.setForeground(Color.white);
        mainLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), 3));

        JLabel floorNoLabel = new JLabel(String.valueOf(nowSelectFloor), JLabel.CENTER);
        floorNoLabel.setBounds(75, 0, 100, 80);
        floorNoLabel.setForeground(Color.white);
        floorNoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 38));

        JLabel floorLabel = new JLabel("F", JLabel.CENTER);
        floorLabel.setBounds(140, 0, 100, 80);
        floorLabel.setForeground(Color.white);
        floorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 38));

        JLabel upPicLabel = new JLabel();
        if (nowSelectFloor + 1 <= tower.getPlayer().maxFloor) {
            upPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/up_1.png")));
        } else {
            upPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/up_2.png")));
        }
        upPicLabel.setBounds(160, 85, 32, 32);
        upPicLabel.setForeground(Color.white);

        JLabel downPicLabel = new JLabel();
        if (nowSelectFloor - 1 >= tower.getPlayer().minFloor) {
            downPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/down_1.png")));
        } else {
            downPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/down_2.png")));
        }
        downPicLabel.setBounds(160, 233, 32, 32);
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
        dialogBox.add(mainLabel);
        dialogBox.add(upPicLabel);
        dialogBox.add(downPicLabel);
        dialogBox.add(enterLabel);
        dialogBox.add(quitLabel);

        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {

            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (nowSelectFloor + 1 > tower.getPlayer().maxFloor) {
                            break;
                        }
                        musicPlayer.floorTransferSelect();
                        nowSelectFloor++;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (nowSelectFloor - 1 < tower.getPlayer().minFloor) {
                            break;
                        }
                        musicPlayer.floorTransferSelect();
                        nowSelectFloor--;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        closeFlag = true;
                        musicPlayer.upAndDown();
                        if (floor < nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(nowSelectFloor).upPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(nowSelectFloor).upPositionY;
                            showMesLabel.setText("魔塔 第" + nowSelectFloor + "层");
                            DIRECTION = DIRECTION_DOWN;
                            musicPlayer.playBackgroundMusic(nowSelectFloor);
                            nowMonsterManual = 0;
                        } else if (floor > nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(nowSelectFloor).downPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(nowSelectFloor).downPositionY;
                            showMesLabel.setText("魔塔 第" + nowSelectFloor + "层");
                            DIRECTION = DIRECTION_DOWN;
                            musicPlayer.playBackgroundMusic(nowSelectFloor);
                            nowMonsterManual = 0;
                        }
                        floor = nowSelectFloor;
                        break;
                    case KeyEvent.VK_F:
                        closeFlag = true;
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    dialogBox.dispose();
                    input.clear();
                    canMove = true;
                }
                if (changeFlag) {
                    floorNoLabel.setText(String.valueOf(nowSelectFloor));
                    if (nowSelectFloor >= tower.getPlayer().maxFloor) {
                        upPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/up_2.png")));
                    } else {
                        upPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/up_1.png")));
                    }
                    if (nowSelectFloor <= tower.getPlayer().minFloor) {
                        downPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/down_2.png")));
                    } else {
                        downPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/down_1.png")));
                    }
                }
            }
        });
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(0, 0, 1, 1);
        content.setBackground(Color.black);
        dialogp.setSize(352, 352);
        dialogp.setBackground(Color.black);
        dialogp.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), 2));
        dialogp.add(pict);
        dialogp.add(content);
        dialogBox.setLocation(mainframe.getLocation().x + 195, TITLE_HEIGHT + mainframe.getLocation().y + 32);
        dialogBox.add(dialogp);
        dialogBox.setVisible(true);
    }

    public void end() {
        ///System.out.println("drawEnd");
        running = false;
        musicPlayer.playEndBackgroundMusic();
        this.remove(playerPicLabel);
        this.remove(lvLabel);
        this.remove(hpPicLabel);
        this.remove(hpLabel);
        this.remove(atkPicLabel);
        this.remove(atkLabel);
        this.remove(defPicLabel);
        this.remove(defLabel);
        this.remove(expPicLabel);
        this.remove(expLabel);
        this.remove(yKeyPicLabel);
        this.remove(yKeyLabel);
        this.remove(bKeyPicLabel);
        this.remove(bKeyLabel);
        this.remove(rKeyPicLabel);
        this.remove(rKeyLabel);
        this.remove(monPicLabel);
        this.remove(monLabel);
        this.remove(showMesLabel);

        for (int i = 0; i <= 0xFF; i++) {
            this.setBackground(new Color(0, 0, 0, i));
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JLabel label = new JLabel();
        label.setForeground(Color.white);
        label.setBounds(50, 100, 300, 288);
        label.setVisible(true);

        int x = 230, y = 440;

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
        endLabel.setBounds(0, 0, 576, 380);
        endLabel.setVisible(true);
        //endLabel.setBorder(BorderFactory.createLineBorder(Color.red));

        this.add(textLabel1);
        this.add(textLabel2);
        this.add(textLabel3);
        this.add(textLabel4);
        this.add(textLabel5);
        this.add(textLabel6);
        this.add(textLabel7);
        this.add(textLabel8);
        this.add(textLabel9);
        this.add(textLabel10);
        this.add(label);
        this.add(endLabel);

        ImageUtil imageUtil = new ImageUtil();
        new Thread(() -> {
            for (int i = 0; i <= 120; i++) {
                //                label.setIcon(new ImageIcon(changeAlpha((int) (80 - Math.abs(1.6 * i - 80)))));
                //                try {
                //                    Thread.sleep(15);
                //                } catch (InterruptedException e) {
                //                    e.printStackTrace();
                //                }
                if (i <= 50) {
                    label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", 2 * i)));
                } else {
                    label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", (int) (120 - 0.4 * i))));
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= y + 240 + 40; i++) {
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
                label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/icon/image_sword.jpg", 72 - i)));
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
            musicPlayer.stopEndBackgroundMusic();
            this.remove(textLabel1);
            this.remove(textLabel2);
            this.remove(textLabel3);
            this.remove(textLabel4);
            this.remove(textLabel5);
            this.remove(textLabel6);
            this.remove(textLabel7);
            this.remove(textLabel8);
            this.remove(textLabel9);
            this.remove(textLabel10);
            this.remove(label);
            this.remove(endLabel);
            //System.exit(0);
            new Thread(() -> {
                imageScript();
            }).start();
            postScript();
        }).run();
    }

    //线程中调用
    private void imageScript() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageUtil imageUtil = new ImageUtil();
        JLabel label = new JLabel();
        label.setForeground(Color.white);
        label.setBounds(10, 120, 226, 162);
        label.setVisible(true);
        this.add(label);
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 80; j++) {
                label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/gameImage/image" + i + ".png", j)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 100; j++) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 80; j++) {
                label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/gameImage/image" + i + ".png", 80 - j)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //线程中调用
    private void postScript() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        musicPlayer.playPostScriptBackgroundMusic();
        int x = 250, y = 440;
        //标题
        JLabel titleLabel = new JLabel("魔塔", JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 50));
        //titleLabel.setBounds(x, y, 300, 50);
        titleLabel.setVisible(true);
        JLabel titleEnglishLabel = new JLabel("Magic Tower", JLabel.CENTER);
        titleEnglishLabel.setForeground(Color.white);
        titleEnglishLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        //titleEnglishLabel.setBounds(x, y + 50, 300, 50);
        titleEnglishLabel.setVisible(true);

        //程序设计
        JLabel programLabel = new JLabel("程序设计(Java):", JLabel.LEFT);
        programLabel.setForeground(Color.white);
        programLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        //programLabel.setBounds(x, y + 200, 300, 30);
        programLabel.setVisible(true);
        JLabel programValLabel = new JLabel("Vip、疯子", JLabel.LEFT);
        programValLabel.setForeground(Color.white);
        programValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        //programNameLabel.setBounds(x + 50, y + 230, 300, 30);
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

        this.add(titleLabel);
        this.add(titleEnglishLabel);
        this.add(programLabel);
        this.add(programValLabel);
        this.add(characterModelLabel);
        this.add(characterModelValLabel);
        this.add(soundEffectLabel);
        this.add(soundEffectValLabel1);
        this.add(soundEffectValLabel2);
        this.add(musicLabel);
        this.add(musicValLabel1);
        this.add(musicValLabel2);
        this.add(musicValLabel3);
        this.add(musicValLabel4);
        this.add(musicValLabel5);
        this.add(musicValLabel6);
        this.add(musicValLabel7);
        this.add(testLabel);
        this.add(testValLabel);
        this.add(thanksLabel);
        this.add(thanksValLabel1);
        this.add(thanksValLabel2);
        this.add(thanksValLabel3);

        for (int i = 0; i <= y + 820 + 40; i++) {
            titleLabel.setBounds(x, y - i, 300, 50);
            titleEnglishLabel.setBounds(x, y + 50 - i, 300, 30);

            programLabel.setBounds(x, y + 120 - i, 300, 30);
            programValLabel.setBounds(x + 40, y + 150 - i, 300, 30);

            characterModelLabel.setBounds(x, y + 200 - i, 300, 30);
            characterModelValLabel.setBounds(x + 40, y + 230 - i, 300, 30);

            soundEffectLabel.setBounds(x, y + 280 - i, 300, 30);
            soundEffectValLabel1.setBounds(x + 40, y + 310 - i, 300, 30);
            soundEffectValLabel2.setBounds(x + 40, y + 340 - i, 300, 30);

            musicLabel.setBounds(x, y + 390 - i, 300, 30);
            musicValLabel1.setBounds(x + 40, y + 420 - i, 300, 30);
            musicValLabel2.setBounds(x + 40, y + 450 - i, 300, 30);
            musicValLabel3.setBounds(x + 40, y + 480 - i, 300, 30);
            musicValLabel4.setBounds(x + 40, y + 510 - i, 300, 30);
            musicValLabel5.setBounds(x + 40, y + 540 - i, 300, 30);
            musicValLabel6.setBounds(x + 40, y + 570 - i, 300, 30);
            musicValLabel7.setBounds(x + 40, y + 600 - i, 300, 30);

            testLabel.setBounds(x, y + 650 - i, 300, 30);
            testValLabel.setBounds(x + 40, y + 680 - i, 300, 30);

            thanksLabel.setBounds(x, y + 730 - i, 300, 30);
            thanksValLabel1.setBounds(x + 40, y + 760 - i, 300, 30);
            thanksValLabel2.setBounds(x + 40, y + 790 - i, 300, 30);
            thanksValLabel3.setBounds(x + 40, y + 820 - i, 300, 30);

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
        emailLabel.setBounds(155, 130, 300, 50);
        JLabel emailValLabel = new JLabel("xuehy1999@qq.com", JLabel.LEFT);
        emailValLabel.setForeground(Color.white);
        emailValLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        emailValLabel.setVisible(true);
        emailValLabel.setBounds(155, 180, 300, 50);
        this.add(emailLabel);
        this.add(emailValLabel);
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
        endLabel.setBounds(0, 0, 576, 380);
        this.add(endLabel);
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
        thankPlayLabel.setBounds(0, 0, 576, 380);
        this.add(thankPlayLabel);
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
