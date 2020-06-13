package main;

import entity.*;
import load.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

/**
 * @author Xhy
 */
public class TowerPanel extends JPanel implements Runnable, MouseListener {

    private final static Logger logger = LoggerFactory.getLogger(TowerPanel.class);

    /**
     * 单个图像大小,默认采用32x32图形,可根据需要调整比例
     * 当然,应始终和窗体大小比例协调;比如32x32的图片,
     * 如果一行设置15个,那么就是480
     */
    private static final int CS = 32;

    //行
    private static final int GAME_ROW = 11;
    //列
    private static final int GAME_COL = 11;

    /**
     * 窗体的宽和高
     */
    private static final int WINDOW_WIDTH = 18 * 32;
    private static final int WINDOW_HEIGHT = 13 * 32;

    /**
     * 游戏窗体的宽和高
     */
    private static final int GAME_WIDTH = 11 * 32;
    private static final int GAME_HEIGHT = 11 * 32;

    /**
     * 人物方向
     */
    public int DIRECTION;
    private static final int DIRECTION_UP = 0;
    private static final int DIRECTION_DOWN = 1;
    private static final int DIRECTION_LEFT = 2;
    private static final int DIRECTION_RIGHT = 3;

    /**
     * 设定显示图像对象
     */
    JLabel playerPicLabel;
    JLabel lvLabel;
    JLabel hpPicLabel, hpLabel;
    JLabel atkPicLabel, atkLabel;
    JLabel defPicLabel, defLabel;
    JLabel expPicLabel, expLabel;
    /*乘号*/
    JLabel symbol1, symbol2, symbol3, symbol4;
    JLabel yKeyPicLabel, yKeyLabel;
    JLabel bKeyPicLabel, bKeyLabel;
    JLabel rKeyPicLabel, rKeyLabel;
    JLabel monPicLabel, monLabel;

    JDialog dialogBox;
    JLabel showMesLabel = new JLabel("魔塔(测试版)");

    /**
     * 怪物
     */
    private Map<String, Monster> monsterMap;
    private Map<String, Wall> wallMap;
    private Map<String, Door> doorMap;
    private Map<String, Stair> stairMap;
    private Map<String, Item> itemMap;
    private Map<String, NPC> npcMap;
    public int floor = 7;
    /**
     * 帧数(每秒8帧)
     */
    byte frames = 0;

    private boolean running = false;
    public boolean canMove = true;
    private KeyInputHandler input;
    public MusicPlayer musicPlayer;

    JFrame mainframe = new JFrame("MagicTower  (作者:Vip、疯子)");
    Container contentPane;

    Tower tower;

    public TowerPanel(Tower tower) {
        this.tower = tower;
        tower.getPlayer().x = tower.gameMapList.get(floor).upPositionX;
        tower.getPlayer().y = tower.gameMapList.get(floor).upPositionY;
        monsterMap = new LoadMonster().initMonster();
        wallMap = new LoadWall().initWall();
        doorMap = new LoadDoor().initDoor();
        stairMap = new LoadStair().initStair();
        itemMap = new LoadItem().initItem();
        npcMap = new LoadNPC().initNPC();
        musicPlayer = new MusicPlayer();
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
            hpLabel.setText(Math.floor(tower.getPlayer().hp / 1000 ) / 10 + "w");
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
        GameMap gameMap = tower.gameMapList.get(floor);
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
                            String wallId = wallMap.get(layer3[i][j]).getId();
                            g.drawImage(getImageFromIcons(wallMap.get(wallId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer3 (x=" + i + ",y=" + j + ") wallId(" + layer3[i][j] + ") 不存在!");
                        }
                    } else if (layer3[i][j].contains("door")) {
                        if (!layer3[i][j].contains("open")) {
                            try {
                                String doorId = doorMap.get(layer3[i][j]).getId();
                                g.drawImage(doorMap.get(doorId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                            } catch (Exception e) {
                                System.err.println("layer3 (x=" + i + ",y=" + j + ") doorId(" + layer3[i][j] + ") 不存在!");
                            }
                        } else {
                            String doorId = doorMap.get(layer3[i][j].substring(0, layer3[i][j].indexOf("open"))).getId();
                            byte no;
                            try {
                                no = Byte.parseByte(layer3[i][j].substring(layer3[i][j].length() - 1));
                            } catch (Exception e) {
                                no = -1;
                                e.printStackTrace();
                            }
                            try {
                                g.drawImage(doorMap.get(doorId).getIcon()[no].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (layer3[i][j].contains("stair")) {
                        try {
                            String stairId = stairMap.get(layer3[i][j]).getId();
                            g.drawImage(getImageFromIcons(stairMap.get(stairId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer3 (x=" + i + ",y=" + j + ") wallId(" + layer3[i][j] + ") 不存在!");
                        }
                    }
                    if (layer2[i][j].contains("item")) {
                        try {
                            String itemId = itemMap.get(layer2[i][j]).getId();
                            g.drawImage(itemMap.get(itemId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer2 (x=" + i + ",y=" + j + ") itemId(" + layer2[i][j] + ") 不存在!");
                        }
                    }
                    if (layer1[i][j].contains("monster")) {
                        try {
                            String monsterId = monsterMap.get(layer1[i][j]).getId();
                            g.drawImage(getImageFromIcons(monsterMap.get(monsterId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer1 (x=" + i + ",y=" + j + ") monsterId(" + layer1[i][j] + ") 不存在!");
                        }
                    }
                    else if (layer1[i][j].contains("npc")) {
                        try {
                            String npcId = npcMap.get(layer1[i][j]).getId();
                            g.drawImage(getImageFromIcons(npcMap.get(npcId).getIcon(), 2), startX + j * CS, startY + i * CS, 32, 32, this);
                        } catch (Exception e) {
                            System.err.println("layer1 (x=" + i + ",y=" + j + ") npcId(" + layer1[i][j] + ") 不存在!");
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
        drawAttribute(g);
        drawMap(g);
        try {
            drawPlayer(g);
        } catch (Exception e) {
            e.printStackTrace();
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
            //render();
            if (System.currentTimeMillis() - fpsTimer > 125) {
                playerPicLabel.setIcon(tower.getPlayer().getPlayerIcon()[1][frames % 4]);
                //playerPicLabel.setIcon(monsterMap.get("1_12").getIcon()[no % 2]);
                if (frames == 7) {
                    System.out.printf("%d fps, %d tick%n", fps, tick);
                    frames = 0;
                    fps = 0;
                    tick = 0;
                } else {
                    frames++;
                }
                fpsTimer += 125;
            }
        }
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
            return;
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
        }
        if (System.currentTimeMillis() - lastMove > stopTime) {
            moveNo = 0;
        }
        GameMap gameMap = tower.gameMapList.get(floor);
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        if (layer3[tower.getPlayer().y][tower.getPlayer().x].contains("door")) {
            tower.gameMapList.get(floor).layer3[tower.getPlayer().y][tower.getPlayer().x] += "open";
            return;
        }
        if (layer3[tower.getPlayer().y][tower.getPlayer().x].equals("stair01")) {
            musicPlayer.upAndDown();
            floor--;
            tower.getPlayer().x = tower.gameMapList.get(floor).downPositionX;
            tower.getPlayer().y = tower.gameMapList.get(floor).downPositionY;
            showMesLabel.setText("魔塔 第" + floor + "层");
            DIRECTION = DIRECTION_DOWN;
            return;
        }
        if (layer3[tower.getPlayer().y][tower.getPlayer().x].equals("stair02")) {
            musicPlayer.upAndDown();
            floor++;
            tower.getPlayer().x = tower.gameMapList.get(floor).upPositionX;
            tower.getPlayer().y = tower.gameMapList.get(floor).upPositionY;
            showMesLabel.setText("魔塔 第" + floor + "层");
            DIRECTION = DIRECTION_DOWN;
            return;
        }
    }

    /**
     * 开门时间
     */
    private static final byte DOOR_OPEN_TIME = 40;
    /**
     * 玩家伤害临界值
     */
    private short playerCriticalVal = 20;
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
        GameMap gameMap = tower.gameMapList.get(floor);
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        if (x >= GAME_COL || x < 0 || y >= GAME_ROW || y < 0) {
            return false;
        }
        if (layer1[y][x].contains("npc")) {
            canMove = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NPC npc;
                    try {
                        npc = npcMap.get(layer1[y][x]);
                    } catch (Exception e) {
                        System.err.println("layer1 (x=" + y + ",y=" + x + ") npcId(" + layer1[y][x] + ") 不存在!");
                        return;
                    }
                    if (!npc.canMeet) {
                        canMove = true;
                        return;
                    }
                    List<Dialogue> dialogues = npc.dialogues;
                    for (int i = 0; i < dialogues.size(); i++) {
                        Dialogue dialogue = dialogues.get(i);
                        System.out.println(dialogue.name + ":" + dialogue.text);
                        meetNpc(layer1[y][x], dialogue);
                        while (!escapeDown) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        escapeDown = false;
                    }
                    npc.script(tower);
                    if (npc.canRemove) {
                        tower.gameMapList.get(floor).layer1[y][x] = "";
                    }
                    canMove = true;
                    input.up.down = false;
                }
            }).start();
            return false;
        }
        else if (layer1[y][x].contains("shop")) {
            return false;
        }
        if (layer3[y][x].contains("wall")) {
            return false;
        }
        if (layer3[y][x].contains("door")) {
            //openDoor(x, y);
            boolean open = false;
            //System.out.println(layer3[y][x]);
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
            if (open) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tower.gameMapList.get(floor).layer3[y][x].equals("") || tower.gameMapList.get(floor).layer3[y][x].contains("open")) {
                            return;
                        }
                        byte f = (byte)floor;
                        for (int i = 1; i < 5; i++) {
                            if (i == 1) {
                                tower.gameMapList.get(f).layer3[y][x] += "open1";
                            } else if (i == 4) {
                                tower.gameMapList.get(f).layer3[y][x] = "";
                            } else {
                                String str = tower.gameMapList.get(f).layer3[y][x];
                                try {
                                    tower.gameMapList.get(f).layer3[y][x] = str.substring(0, str.length() - 1) + i;
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    tower.gameMapList.get(f).layer3[y][x] = "";
                                }
                            }
                            try {
                                Thread.sleep(DOOR_OPEN_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        tower.gameMapList.get(f).layer3[y][x] = "";
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
            }
            else if (layer2[y][x].contains("item02")) {
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
            }
            else if (layer2[y][x].contains("item03")) {
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
            }
            else if (layer2[y][x].contains("item04")) {
                switch (layer2[y][x]) {
                    case "item04_1":
                        showMesLabel.setText("获得铁剑,攻击+10");
                        tower.getPlayer().attack += 10;
                        flag = true;
                        break;
                    case "item04_2":
                        showMesLabel.setText("获得银剑,攻击+20");
                        tower.getPlayer().attack += 20;
                        flag = true;
                        break;
                    case "item04_3":
                        showMesLabel.setText("获得武士剑,攻击+50");
                        tower.getPlayer().attack += 50;
                        flag = true;
                        break;
                    case "item04_4":
                        showMesLabel.setText("获得圣剑,攻击+100");
                        tower.getPlayer().attack += 100;
                        flag = true;
                        break;
                    case "item04_5":
                        showMesLabel.setText("获得圣神剑,攻击+200");
                        tower.getPlayer().attack += 200;
                        flag = true;
                        break;
                }
            }
            else if (layer2[y][x].contains("item05")) {
                switch (layer2[y][x]) {
                    case "item05_1":
                        showMesLabel.setText("获得铁盾,防御+10");
                        tower.getPlayer().defense += 10;
                        flag = true;
                        break;
                    case "item05_2":
                        showMesLabel.setText("获得银盾,防御+20");
                        tower.getPlayer().defense += 20;
                        flag = true;
                        break;
                    case "item05_3":
                        showMesLabel.setText("获得武士盾,防御+50");
                        tower.getPlayer().defense += 50;
                        flag = true;
                        break;
                    case "item05_4":
                        showMesLabel.setText("获得圣盾,防御+100");
                        tower.getPlayer().defense += 100;
                        flag = true;
                        break;
                    case "item05_5":
                        showMesLabel.setText("获得圣神盾,防御+200");
                        tower.getPlayer().defense += 200;
                        flag = true;
                        break;
                }
            }
            else if (layer2[y][x].contains("item06")) {
                switch (layer2[y][x]) {
                    case "item06_3":
                        showMesLabel.setText("获得圣水,生命值变为1.5倍");
                        tower.getPlayer().hp *= 1.5;
                        flag = true;
                        break;
                }
            }
            else if (layer2[y][x].contains("item07")) {
                switch (layer2[y][x]) {
                    case "item07_1":
                        showMesLabel.setText("获得神秘法杖,可以使用楼层传送");
                        flag = true;
                        break;
                }
            }
            else if (layer2[y][x].contains("item08")) {
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
            }
            else if (layer2[y][x].contains("item09")) {
                switch (layer2[y][x]) {
                    case "item09_1":
                        showMesLabel.setText("获得幸运硬币,金币+200");
                        tower.getPlayer().money += 200;
                        flag = true;
                        break;
                }
            }
            if (flag) {
                musicPlayer.getItem();
            } else {
                try {
                    showMesLabel.setText("获得" + itemMap.get(layer2[y][x]).getName() + ",嘛事没有");
                } catch (Exception e) {
                    System.err.println("layer2 (x=" + x + ",y=" + y + ") itemId(" + layer2[y][x] + ") 不存在!");
                }
            }
            tower.gameMapList.get(floor).layer2[y][x] = "";
            return false;
        }
        if (layer1[y][x].contains("monster")) {
            Monster monster = monsterMap.get(layer1[y][x]);
            if (!monster.hostile) {
                return false;
            }
            int mHP = monster.getHp();
            int pHP = tower.getPlayer().hp;
            int mDamage;
            //魔法师系的怪物攻击玩家为真实伤害
            if (layer1[y][x].contains("monster04")) {
                mDamage = monster.getAttack();
            } else {
                mDamage = monster.getAttack() - tower.getPlayer().defense;
            }
            int pDamage = tower.getPlayer().attack - monster.getDefense();
            //-20为怪物伤害临界值
            if (mDamage <= 0 && mDamage > -20) {
                mDamage = 1;
            } else if (mDamage <= -20) {
                mDamage = 0;
            }
            if (pDamage <= 0 && pDamage > -playerCriticalVal) {
                pDamage = 1;
            } else if (pDamage <= -20) {
                pDamage = 0;
            }
            System.out.println("怪物攻击一次的伤害:" + mDamage);
            System.out.println("玩家攻击一次的伤害:" + pDamage);
            /**
             * 战斗结果计算 默认玩家先攻
             */
            short pAttackNo = 0;
            short attackNo = 0;
            while (mHP > 0 && pHP > 0) {
                if (attackNo % 2 == 0) {
                    pAttackNo++;
                    mHP -= pDamage;
                } else {
                    pHP -= mDamage;
                }
                attackNo++;
            }
            if (mHP <= 0) {
                musicPlayer.fight();
                showMesLabel.setText("击杀:" + monsterMap.get(layer1[y][x]).getName() + ",损失" + (tower.getPlayer().hp - pHP) + "HP");
                tower.gameMapList.get(floor).layer1[y][x] = "";
                tower.getPlayer().hp = pHP;
                tower.getPlayer().money += monster.getMoney();
                tower.getPlayer().exp += monster.getExp();
                System.out.println("玩家攻击次数:" + pAttackNo);
                System.out.println("怪物攻击次数:" + (attackNo - pAttackNo));
                return true;
            } else {
                showMesLabel.setText("无法击杀:" + monsterMap.get(layer1[y][x]).getName());
                System.out.println("无法击杀");
                return false;
            }
        }
        return true;
    }

    /**
     * 与NPC对话
     * @param npcId
     * @param dialogue
     */
    public void meetNpc(String npcId, Dialogue dialogue) {
        NPC npc = npcMap.get(npcId);
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
            pict.setBounds(16, 8, 32, 32);
            name = new JLabel(npc.getName());
            name.setBounds(48, 16, 32, 16);
            photo = new ImageIcon(npcMap.get(npcId).getIcon()[0].getImage());
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
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        musicPlayer.dialogueSpace();
                        escapeDown = true;
                        dialogBox.dispose();
                        break;
                }
            }
        });
        dialogp.setSize(256, 128);
        dialogp.setBackground(Color.black);
        content.setText(s);
        content.setLineWrap(true);
        content.setEditable(false);
        content.setBounds(8, 48, 248, 58);
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
