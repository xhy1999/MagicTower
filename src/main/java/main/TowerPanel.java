package main;

import com.jogamp.opengl.awt.GLJPanel;
import end.NormalEnd;
import entity.*;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static pane.FloorTransferPane.floorTransferPane;
import static pane.MonsterManualPane.monsterManualPane;
import static pane.MonsterManualPane.showMonsterManual;
import static pane.FloorTransferPane.showFloorTransfer;
import static pane.NpcDialogPane.npcDialogPane;
import static pane.NpcDialogPane.showNpcDialog;
import static pane.ShopPane.shopPane;
import static pane.ShopPane.showShop;
import static pane.SpecialItemPane.showSpecialItem;
import static pane.SpecialItemPane.specialItemPane;

/**
 * @author Xhy
 */
public final class TowerPanel extends JPanel implements Runnable {

    /**
     * 单个图像大小,默认采用CSxCS图形,可根据需要调整比例
     * 当然,应始终和窗体大小比例协调;比如CSxCS的图片,
     * 如果一行设置15个,那么就是480
     */
    public static final byte CS = 32;

    //标题栏高度
    public static int TITLE_HEIGHT = 26;

    //行
    public static final int GAME_ROW = 11;
    //列
    public static final int GAME_COL = 11;

    /**
     * 窗体的宽和高
     */
    public static final int WINDOW_WIDTH = 18 * CS - 10;
    public static final int WINDOW_HEIGHT = 14 * CS - 10 + 16;

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
    static JLabel floorNumLabel;
    JLabel playerPicLabel;
    JLabel floorLabel;
    JLabel playerWindowLine, infoWindowLine, mapWindowLine;
    JLabel lvLabel;
    JLabel hpPicLabel, hpLabel;
    JLabel atkPicLabel, atkLabel;
    JLabel defPicLabel, defLabel;
    JLabel expPicLabel, expLabel;
    //乘号
    JLabel symbol1, symbol2, symbol3, symbol4;
    JLabel yKeyPicLabel, yKeyLabel;
    JLabel bKeyPicLabel, bKeyLabel;
    JLabel rKeyPicLabel, rKeyLabel;
    JLabel monPicLabel, monLabel;

    JLabel showMesLabel;
    JLabel fpsLabel, showFpsLabel;

    /**
     * 帧数(每秒8帧)
     */
    private byte frames = 0;

    public static boolean running = false;
    public static boolean canMove = true;
    public static KeyInputHandler input;
    public static MusicPlayer musicPlayer;
    //TODO 正式版这里要改为 false
    public static boolean canUseFloorTransfer = false;
    public static boolean canUseMonsterManual = true;
    public static String specialGameMapNo;
    public static byte end;
    //TODO 正式版这里要改为 0
    public static int floor = 0;

    public static ExecutorService mainExecutor;
    private List<Tower> gameSave;
    private Tower tower;

    public static JFrame mainframe = new JFrame("魔塔v1.13  (复刻者:Vip、疯子)");

    public TowerPanel(Tower tower) {
        this.add(monsterManualPane);
        this.add(floorTransferPane);
        this.add(specialItemPane);
        this.add(shopPane);
        this.add(npcDialogPane);
        this.tower = tower;
        this.gameSave = new LinkedList<>();
        this.mainExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                1L, TimeUnit.SECONDS, new SynchronousQueue<>());
        this.tower.getPlayer().x = this.tower.getGameMapList().get(floor).upPositionX;
        this.tower.getPlayer().y = this.tower.getGameMapList().get(floor).upPositionY;
        //TODO 正式版这里要改为 0
        this.tower.getPlayer().maxFloor = 0;
        this.tower.getPlayer().minFloor = 0;
        musicPlayer = new MusicPlayer();
        musicPlayer.playBackgroundMusic(floor);
        DIRECTION = DIRECTION_UP;
        input = new KeyInputHandler(this);

        /********************UI部分********************/
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
        mainframe.setLocation((screenUtil.getScreenWidth() - WINDOW_WIDTH) / 2, (screenUtil.getScreenHeight() - WINDOW_HEIGHT - 100) / 2);
        //得到一个Toolkit对象
        Toolkit tool = this.getToolkit();
        Image image = tool.getImage(this.getClass().getResource("/image/icon/mt.png"));
        mainframe.setIconImage(image);
        Container contentPane = mainframe.getContentPane();
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

    @Override
    public void run() {
        TITLE_HEIGHT = (int) (mainframe.getBounds().getSize().getHeight() - this.getSize().getHeight());
        Short fps = 0; //tick = 0
        double fpsTimer = System.currentTimeMillis();
        double nsPerTick = 1000000000.0 / 10;
        double then = System.nanoTime();
        double needTick = 0;
        while (running) {
            double now = System.nanoTime();
            needTick += (now - then) / nsPerTick;
            then = now;
            while (needTick >= 1) {
                //tick++;
                tick();
                needTick--;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            fps++;
            if (System.currentTimeMillis() - fpsTimer > 125) {
                playerPicLabel.setIcon(tower.getPlayer().getPlayerIcon()[1][frames % 4]);
                if (frames == 7) {
                    //System.out.printf("FPS:%d %n", fps);
                    //System.out.printf("FPS:%d tick:%d %n", fps, tick);
                    showFpsLabel.setText(fps.toString());
                    frames = 0;
                    fps = 0;
                    //tick = 0;
                } else {
                    frames++;
                }
                fpsTimer += 125;
            }
        }
        end();
    }

    /**
     * 保存游戏
     */
    private void save() {
        this.tower.canUseFloorTransfer = canUseFloorTransfer;
        this.tower.canUseMonsterManual = canUseMonsterManual;
        this.tower.specialGameMapNo = specialGameMapNo;
        this.tower.floor = floor;
        try {
            gameSave.add(0, this.tower.clone());
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
            this.tower = gameSave.get(0).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        canUseFloorTransfer = this.tower.canUseFloorTransfer;
        canUseMonsterManual = this.tower.canUseMonsterManual;
        specialGameMapNo = this.tower.specialGameMapNo;
        floor = this.tower.floor;
        DIRECTION = DIRECTION_DOWN;
        updateFloorNum();
        musicPlayer.playBackgroundMusic(floor);
        showMesLabel.setText("数据读取成功");
    }

    /*********************************************** 主要逻辑 ***********************************************/

    //玩家上次移动时间
    public long lastMove = System.currentTimeMillis();
    //不动多久后玩家动作停止
    private static final short STOP_TIME = 180;
    //玩家动作帧数计数
    private byte moveNo = 0;
    //当前怪物手册的页数(仅当层数切换时重置为0)
    public static byte nowMonsterManual = 0;
    //当前选择的层数
    public static int nowSelectFloor = 0;

    public void tick() {
        if (!canMove) {
            lastMove = System.currentTimeMillis();
            moveNo = 0;
            return;
        }
        if (isNormalFloor()) {
            String stair = this.tower.getGameMapList().get(floor).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x];
            if (stair.equals("stair01")) {
                musicPlayer.upAndDown();
                floor--;
                this.tower.getPlayer().x = this.tower.getGameMapList().get(floor).downPositionX;
                this.tower.getPlayer().y = this.tower.getGameMapList().get(floor).downPositionY;
                updateFloorNum();
                DIRECTION = DIRECTION_DOWN;
                musicPlayer.playBackgroundMusic(floor);
                nowMonsterManual = 0;
                if (floor < this.tower.getPlayer().minFloor) {
                    this.tower.getPlayer().minFloor = floor;
                }
                return;
            } else if (stair.equals("stair02")) {
                musicPlayer.upAndDown();
                floor++;
                this.tower.getPlayer().x = this.tower.getGameMapList().get(floor).upPositionX;
                this.tower.getPlayer().y = this.tower.getGameMapList().get(floor).upPositionY;
                updateFloorNum();
                DIRECTION = DIRECTION_DOWN;
                musicPlayer.playBackgroundMusic(floor);
                nowMonsterManual = 0;
                if (floor > this.tower.getPlayer().maxFloor) {
                    this.tower.getPlayer().maxFloor = floor;
                }
                return;
            } else if (stair.contains("stair03") || stair.contains("stair04")) {
                this.tower.getStairMap().get(stair).script(this, this.tower, specialGameMapNo);
                updateFloorNum();
                return;
            }
        } else {
            String stair = this.tower.getSpecialMap().get(specialGameMapNo).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x];
            if (stair.contains("stair03") || stair.contains("stair04")) {
                this.tower.getStairMap().get(stair).script(this, this.tower, specialGameMapNo);
                updateFloorNum();
                return;
            }
        }
        if (input.up.down) {
            this.DIRECTION = DIRECTION_UP;
            moveNo = (byte) ((moveNo + 1) % 4);
            if (!canMove(this.tower.getPlayer().x, (byte) (this.tower.getPlayer().y - 1))) {
                return;
            }
            musicPlayer.walk();
            this.tower.getPlayer().y--;
            lastMove = System.currentTimeMillis();
        } else if (input.down.down) {
            this.DIRECTION = DIRECTION_DOWN;
            moveNo = (byte) ((moveNo + 1) % 4);
            if (!canMove(this.tower.getPlayer().x, (byte) (this.tower.getPlayer().y + 1))) {
                return;
            }
            musicPlayer.walk();
            this.tower.getPlayer().y++;
            lastMove = System.currentTimeMillis();
        } else if (input.left.down) {
            this.DIRECTION = DIRECTION_LEFT;
            if (!canMove((byte) (this.tower.getPlayer().x - 1), this.tower.getPlayer().y)) {
                return;
            }
            musicPlayer.walk();
            moveNo = (byte) ((moveNo + 1) % 4);
            this.tower.getPlayer().x--;
            lastMove = System.currentTimeMillis();
        } else if (input.right.down) {
            this.DIRECTION = DIRECTION_RIGHT;
            if (!canMove((byte) (this.tower.getPlayer().x + 1), this.tower.getPlayer().y)) {
                return;
            }
            musicPlayer.walk();
            moveNo = (byte) ((moveNo + 1) % 4);
            this.tower.getPlayer().x++;
            lastMove = System.currentTimeMillis();
        } else if (canUseMonsterManual && input.use_rod.down) {
            mainExecutor.execute(() -> {
                showMonsterManual(this.tower);
            });
        } else if (canUseFloorTransfer && input.use_floor_transfer.down) {
            if (!isNormalFloor()) {
                musicPlayer.fail();
                return;
            }
            canMove = false;
            mainExecutor.execute(() -> {
                showFloorTransfer(this.tower);
            });
        }
        //TODO 正式版这里要去掉
        else if (input.escape.down) {
            running = false;
        } else if (input.save.down) {
            save();
        } else if (input.load.down) {
            load();
        }
        if (System.currentTimeMillis() - lastMove > STOP_TIME) {
            moveNo = 0;
        }
        if (isNormalFloor()) {
            if (this.tower.getGameMapList().get(floor).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x].contains("door") && !this.tower.getGameMapList().get(floor).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x].contains("open")) {
                this.tower.getGameMapList().get(floor).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x] += "open";
                return;
            }
        } else {
            if (this.tower.getSpecialMap().get(specialGameMapNo).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x].contains("door") && !this.tower.getSpecialMap().get(specialGameMapNo).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x].contains("open")) {
                this.tower.getSpecialMap().get(specialGameMapNo).layer3[this.tower.getPlayer().y][this.tower.getPlayer().x] += "open";
                return;
            }
        }
    }

    //开门时间
    private static final byte DOOR_OPEN_TIME = 40;
    //对话中按下空格
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
            gameMap = this.tower.getGameMapList().get(floor);
        } else {
            gameMap = this.tower.getSpecialMap().get(specialGameMapNo);
        }
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        if (x >= GAME_COL || x < 0 || y >= GAME_ROW || y < 0) {
            return false;
        }
        if (layer1[y][x].contains("npc")) {
            showNpcDialog(this.tower, layer1[y][x], x, y);
            return false;
        } else if (layer1[y][x].contains("shop")) {
            mainExecutor.execute(() -> {
                Shop shop;
                try {
                    shop = this.tower.getShopMap().get(layer1[y][x]);
                } catch (Exception e) {
                    System.err.println("layer1 (x=" + x + ",y=" + y + ") shopId(" + layer1[y][x] + ") 不存在!");
                    return;
                }
                if (!shop.canMeet) {
                    return;
                }
                showShop(this.tower, shop.getId());
            });
            return false;
        }
        if (layer3[y][x].contains("wall")) {
            return false;
        } else if (layer3[y][x].contains("door") && !layer3[y][x].contains("open")) {
            boolean open = false;
            switch (layer3[y][x]) {
                case "door01":
                    if (this.tower.getPlayer().yKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        this.tower.getPlayer().yKey--;
                        open = true;
                    }
                    break;
                case "door02":
                    if (this.tower.getPlayer().bKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        this.tower.getPlayer().bKey--;
                        open = true;
                    }
                    break;
                case "door03":
                    if (this.tower.getPlayer().rKey - 1 >= 0) {
                        musicPlayer.openDoor();
                        this.tower.getPlayer().rKey--;
                        open = true;
                    }
                    break;
                default:
                    break;
            }
            if (!open && (layer3[y][x].contains("door04") || layer3[y][x].contains("door05"))) {
                try {
                    if (this.tower.getDoorMap().get(layer3[y][x]).openable) {
                        musicPlayer.openSpecialDoor();
                        open = true;
                    }
                } catch (Exception e) {
                    System.err.println("layer3 (x=" + x + ",y=" + y + ") doorId(" + layer1[y][x] + ") 不存在!");
                    return false;
                }
            }
            if (open) {
                mainExecutor.execute(() -> {
                    if (isNormalFloor()) {
                        if (this.tower.getGameMapList().get(floor).layer3[y][x].equals("") || this.tower.getGameMapList().get(floor).layer3[y][x].contains("open")) {
                            return;
                        }
                        byte f = (byte) floor;
                        for (int i = 1; i < 5; i++) {
                            if (i == 1) {
                                this.tower.getGameMapList().get(f).layer3[y][x] += "open1";
                            } else if (i == 4) {
                                this.tower.getGameMapList().get(f).layer3[y][x] = "";
                            } else {
                                String str = this.tower.getGameMapList().get(f).layer3[y][x];
                                try {
                                    this.tower.getGameMapList().get(f).layer3[y][x] = str.substring(0, str.length() - 1) + i;
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    this.tower.getGameMapList().get(f).layer3[y][x] = "";
                                }
                            }
                            try {
                                Thread.sleep(DOOR_OPEN_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        this.tower.getGameMapList().get(f).layer3[y][x] = "";
                    } else {
                        if (this.tower.getSpecialMap().get(specialGameMapNo).layer3[y][x].equals("") || this.tower.getSpecialMap().get(specialGameMapNo).layer3[y][x].contains("open")) {
                            return;
                        }
                        String f = specialGameMapNo;
                        for (int i = 1; i < 5; i++) {
                            if (i == 1) {
                                this.tower.getSpecialMap().get(f).layer3[y][x] += "open1";
                            } else if (i == 4) {
                                this.tower.getSpecialMap().get(f).layer3[y][x] = "";
                            } else {
                                String str = this.tower.getSpecialMap().get(f).layer3[y][x];
                                try {
                                    this.tower.getSpecialMap().get(f).layer3[y][x] = str.substring(0, str.length() - 1) + i;
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    this.tower.getSpecialMap().get(f).layer3[y][x] = "";
                                }
                            }
                            try {
                                Thread.sleep(DOOR_OPEN_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        this.tower.getSpecialMap().get(f).layer3[y][x] = "";
                    }
                });
            }
            return false;
        }
        if (layer2[y][x].contains("item")) {
            boolean flag = false;
            if (layer2[y][x].contains("item01")) {
                switch (layer2[y][x]) {
                    case "item01_1":
                        showMesLabel.setText("获得1把黄钥匙");
                        this.tower.getPlayer().yKey++;
                        flag = true;
                        break;
                    case "item01_2":
                        showMesLabel.setText("获得1把蓝钥匙");
                        this.tower.getPlayer().bKey++;
                        flag = true;
                        break;
                    case "item01_3":
                        showMesLabel.setText("获得1把红钥匙");
                        this.tower.getPlayer().rKey++;
                        flag = true;
                        break;
                    case "item01_5":
                        showMesLabel.setText("获得万能钥匙,钥匙数量各+1");
                        this.tower.getPlayer().yKey++;
                        this.tower.getPlayer().bKey++;
                        this.tower.getPlayer().rKey++;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item02")) {
                switch (layer2[y][x]) {
                    case "item02_1":
                        showMesLabel.setText("获得红宝石,攻击+3");
                        this.tower.getPlayer().attack += 3;
                        flag = true;
                        break;
                    case "item02_2":
                        showMesLabel.setText("获得蓝宝石,防御+3");
                        this.tower.getPlayer().defense += 3;
                        flag = true;
                        break;
                    case "item02_3":
                        showMesLabel.setText("获得绿宝石,攻防各+3");
                        this.tower.getPlayer().attack += 3;
                        this.tower.getPlayer().defense += 3;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item03")) {
                switch (layer2[y][x]) {
                    case "item03_1":
                        showMesLabel.setText("获得小体力药水,生命+200");
                        this.tower.getPlayer().hp += 200;
                        flag = true;
                        break;
                    case "item03_2":
                        showMesLabel.setText("获得大体力药水,生命+500");
                        this.tower.getPlayer().hp += 500;
                        flag = true;
                        break;
                    case "item03_3":
                        showMesLabel.setText("获得经验药水,经验+10");
                        this.tower.getPlayer().exp += 10;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item04")) {
                switch (layer2[y][x]) {
                    case "item04_1":
                        showMesLabel.setText("获得铁剑,攻击+10");
                        this.tower.getPlayer().attack += 10;
                        flag = true;
                        break;
                    case "item04_2":
                        showMesLabel.setText("获得银剑,攻击+30");
                        this.tower.getPlayer().attack += 30;
                        flag = true;
                        break;
                    case "item04_3":
                        showMesLabel.setText("获得武士剑,攻击+50");
                        this.tower.getPlayer().attack += 50;
                        flag = true;
                        break;
                    case "item04_4":
                        showMesLabel.setText("获得圣剑,攻击+120");
                        this.tower.getPlayer().attack += 120;
                        flag = true;
                        break;
                    case "item04_5":
                        showMesLabel.setText("获得圣神剑,攻击+190");
                        this.tower.getPlayer().attack += 190;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item05")) {
                switch (layer2[y][x]) {
                    case "item05_1":
                        showMesLabel.setText("获得铁盾,防御+10");
                        this.tower.getPlayer().defense += 10;
                        flag = true;
                        break;
                    case "item05_2":
                        showMesLabel.setText("获得银盾,防御+30");
                        this.tower.getPlayer().defense += 30;
                        flag = true;
                        break;
                    case "item05_3":
                        showMesLabel.setText("获得武士盾,防御+50");
                        this.tower.getPlayer().defense += 50;
                        flag = true;
                        break;
                    case "item05_4":
                        showMesLabel.setText("获得圣盾,防御+120");
                        this.tower.getPlayer().defense += 120;
                        flag = true;
                        break;
                    case "item05_5":
                        showMesLabel.setText("获得圣神盾,防御+190");
                        this.tower.getPlayer().defense += 190;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item06")) {
                switch (layer2[y][x]) {
                    case "item06_3":
                        showMesLabel.setText("获得圣水瓶,生命值翻倍");
                        this.tower.getPlayer().hp *= 2;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item07")) {
                switch (layer2[y][x]) {
                    case "item07_1":
                        showMesLabel.setText("获得心之灵杖");
                        flag = true;
                        this.tower.getPlayer().inventory.put("SpiritStick", 1);
                        if (this.tower.getPlayer().inventory.containsKey("SunStick")) {
                            if (this.tower.getPlayer().inventory.get("SunStick").equals(1)) {
                                this.tower.getDoorMap().get("door04_2").openable = true;
                            }
                        }
                        break;
                    case "item07_3":
                        showMesLabel.setText("获得炎之灵杖");
                        flag = true;
                        this.tower.getPlayer().inventory.put("SunStick", 1);
                        if (this.tower.getPlayer().inventory.containsKey("SpiritStick")) {
                            if (this.tower.getPlayer().inventory.get("SpiritStick").equals(1)) {
                                this.tower.getDoorMap().get("door04_2").openable = true;
                            }
                        }
                        break;
                }
            } else if (layer2[y][x].contains("item08")) {
                switch (layer2[y][x]) {
                    case "item08_1":
                        showMesLabel.setText("获得小飞羽,等级+1");
                        this.tower.getPlayer().level++;
                        this.tower.getPlayer().attack += 7;
                        this.tower.getPlayer().defense += 7;
                        this.tower.getPlayer().hp += 1000;
                        flag = true;
                        break;
                }
            } else if (layer2[y][x].contains("item09")) {
                switch (layer2[y][x]) {
                    case "item09_1":
                        showMesLabel.setText("获得幸运硬币,金币+300");
                        this.tower.getPlayer().money += 300;
                        flag = true;
                        break;
                    case "item09_4":
                        showMesLabel.setText("获得风之罗盘,可以使用楼层传送");
                        canUseFloorTransfer = true;
                        flag = true;
                        break;
                    case "item09_5":
                        showMesLabel.setText("获得幸运十字架");
                        this.tower.getPlayer().inventory.put("item09_5", 1);
                        flag = true;
                        break;
                    case "item09_6":
                        showMesLabel.setText("获得圣光徽,可以查看怪物信息");
                        canUseMonsterManual = true;
                        flag = true;
                        break;
                    case "item09_8":
                        showMesLabel.setText("获得星光神锒");
                        this.tower.getPlayer().inventory.put("LumpHammer", 1);
                        flag = true;
                        break;
                }
            }
            if (flag) {
                Item item = this.tower.getItemMap().get(layer2[y][x]);
                if (item.msg != null) {
                    musicPlayer.getSpecialItem();
                    mainExecutor.execute(() -> showSpecialItem(item));
                } else {
                    musicPlayer.getItem();
                }
            } else {
                try {
                    showMesLabel.setText("获得" + this.tower.getItemMap().get(layer2[y][x]).getName() + ",嘛事没有");
                } catch (Exception e) {
                    System.err.println("layer2 (x=" + x + ",y=" + y + ") itemId(" + layer2[y][x] + ") 不存在!");
                }
            }
            if (isNormalFloor()) {
                this.tower.getGameMapList().get(floor).layer2[y][x] = "";
            } else {
                this.tower.getSpecialMap().get(specialGameMapNo).layer2[y][x] = "";
            }
            return false;
        }
        if (layer1[y][x].contains("monster")) {
            Monster monster = null;
            try {
                monster = this.tower.getMonsterMap().get(layer1[y][x]);
            } catch (Exception e) {
                System.err.println("layer1 (x=" + x + ",y=" + y + ") monsterId(" + layer1[y][x] + ") 不存在!");
            }
            monster.script_start(this.tower);
            FightCalc fightCalc = new FightCalc(this.tower.getPlayer(), monster);
            if (!fightCalc.canAttack) {
                showMesLabel.setText("无法击杀:" + this.tower.getMonsterMap().get(layer1[y][x]).getName());
                return false;
            }
            int pHP = this.tower.getPlayer().hp - fightCalc.mDamageTotal;
            if (pHP > 0) {
                musicPlayer.fight();
                showMesLabel.setText("击杀:" + monster.getName() + ",损失" + (this.tower.getPlayer().hp - pHP) + "HP");
                if (isNormalFloor()) {
                    this.tower.getGameMapList().get(floor).layer1[y][x] = "";
                } else {
                    this.tower.getSpecialMap().get(specialGameMapNo).layer1[y][x] = "";
                }
                this.tower.getPlayer().hp = pHP;
                this.tower.getPlayer().money += monster.getMoney();
                this.tower.getPlayer().exp += monster.getExp();
                monster.script_end(this.tower);
                return true;
            } else {
                showMesLabel.setText("无法击杀:" + this.tower.getMonsterMap().get(layer1[y][x]).getName());
                return false;
            }
        }
        return true;
    }

    /*********************************************** 绘制方法 ***********************************************/

    @Override
    public void paintComponent(Graphics g) {// 描绘窗体，此处在默认JPanel基础上构建底层地图
        super.paintComponent(g);
        if (running) {
            drawBackGround(g);
            drawAttribute(g);
            drawMap(g);
            drawPlayer(g);
        }
    }

    /**
     * 创建属性界面
     */
    public void showAttribute() {
        // 各属性的显示
        playerPicLabel = new JLabel();
        playerPicLabel.setBounds(CS + 14, CS + 12, 36, 38);
        //playerPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/role.png")));

        playerWindowLine = new JLabel();
        playerWindowLine.setBounds(CS - 3, CS - 3, CS * 4 + 6, CS * 6 + 6);
        playerWindowLine.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), 3));

        infoWindowLine = new JLabel();
        infoWindowLine.setBounds(CS - 3, CS * 8 - 3, CS * 4 + 6, CS * 4 + 6);
        infoWindowLine.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), 3));

        mapWindowLine = new JLabel();
        mapWindowLine.setBounds(CS * 6 - 3, CS - 3, CS * 11 + 6, CS * 11 + 6);
        mapWindowLine.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), 3));

        floorLabel = new JLabel("魔塔", JLabel.CENTER);
        floorLabel.setBounds(CS * 10 + 10, 0, CS, CS - 3);
        floorLabel.setForeground(Color.white);
        floorLabel.setFont(new Font("宋体", Font.BOLD, 14));

        floorNumLabel = new JLabel(floor + "F", JLabel.CENTER);
        floorNumLabel.setBounds(358, 0, CS + 24, CS - 3);
        floorNumLabel.setForeground(Color.white);
        floorNumLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));


        lvLabel = new JLabel("Lv." + this.tower.getPlayer().level, JLabel.CENTER);
        lvLabel.setBounds(96 - 10, CS + 17, 60, CS);
        lvLabel.setForeground(Color.white);
        lvLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        //lvLabel.setOpaque(true);
        //lvLabel.setBackground(new Color(255, 255, 255, 100));

        hpPicLabel = new JLabel("生命:");
        hpPicLabel.setBounds(CS + 5, 90, 45, CS);
        hpPicLabel.setForeground(Color.white);
        hpPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        hpLabel = new JLabel(String.valueOf(this.tower.getPlayer().hp), JLabel.CENTER);
        hpLabel.setBounds(CS + 5 + 45, 90 + 1, 75, CS);
        hpLabel.setForeground(Color.white);
        hpLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        atkPicLabel = new JLabel("攻击:");
        atkPicLabel.setBounds(CS + 5, 122, 45, CS);
        atkPicLabel.setForeground(Color.white);
        atkPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        atkLabel = new JLabel(String.valueOf(this.tower.getPlayer().attack), JLabel.CENTER);
        atkLabel.setBounds(CS + 5 + 45, 122 + 1, 75, CS);
        atkLabel.setForeground(Color.white);
        atkLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        defPicLabel = new JLabel("防御:");
        defPicLabel.setBounds(CS + 5, 154, 45, CS);
        defPicLabel.setForeground(Color.white);
        defPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        defLabel = new JLabel(String.valueOf(this.tower.getPlayer().defense), JLabel.CENTER);
        defLabel.setBounds(CS + 5 + 45, 154 + 1, 75, CS);
        defLabel.setForeground(Color.white);
        defLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        expPicLabel = new JLabel("经验:");
        expPicLabel.setBounds(CS + 5, 186, 45, CS);
        expPicLabel.setForeground(Color.white);
        expPicLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        expLabel = new JLabel(String.valueOf(this.tower.getPlayer().exp), JLabel.CENTER);
        expLabel.setBounds(CS + 5 + 45, 186 + 1, 75, CS);
        expLabel.setForeground(Color.white);
        expLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        symbol1 = new JLabel("×", JLabel.CENTER);
        symbol1.setBounds(25, 0, CS, 26);
        symbol1.setForeground(Color.white);
        symbol1.setFont(new Font("微软雅黑", Font.PLAIN, CS));

        yKeyPicLabel = new JLabel();
        yKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_1.png")));
        yKeyPicLabel.setBounds(CS, 256, 64, CS);
        yKeyPicLabel.setForeground(Color.white);
        yKeyPicLabel.add(symbol1, BorderLayout.CENTER);

        yKeyLabel = new JLabel(String.valueOf(this.tower.getPlayer().yKey), JLabel.CENTER);
        yKeyLabel.setBounds(96 - 15, 256, 64 + 15, 30);
        yKeyLabel.setForeground(Color.white);
        yKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol2 = new JLabel("×", JLabel.CENTER);
        symbol2.setBounds(25, 0, CS, 26);
        symbol2.setForeground(Color.white);
        symbol2.setFont(new Font("微软雅黑", Font.PLAIN, CS));

        bKeyPicLabel = new JLabel();
        bKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_2.png")));
        bKeyPicLabel.setBounds(CS, 288, 64, CS);
        bKeyPicLabel.setForeground(Color.white);
        bKeyPicLabel.add(symbol2, BorderLayout.CENTER);

        bKeyLabel = new JLabel(String.valueOf(this.tower.getPlayer().bKey), JLabel.CENTER);
        bKeyLabel.setBounds(96 - 15, 288, 64 + 15, 30);
        bKeyLabel.setForeground(Color.white);
        bKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol3 = new JLabel("×", JLabel.CENTER);
        symbol3.setBounds(25, 0, CS, 26);
        symbol3.setForeground(Color.white);
        symbol3.setFont(new Font("微软雅黑", Font.PLAIN, CS));

        rKeyPicLabel = new JLabel();
        rKeyPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/item/item01_3.png")));
        rKeyPicLabel.setBounds(CS, CS * 10, 64, CS);
        rKeyPicLabel.setForeground(Color.white);
        rKeyPicLabel.add(symbol3, BorderLayout.CENTER);

        rKeyLabel = new JLabel(String.valueOf(this.tower.getPlayer().rKey), JLabel.CENTER);
        rKeyLabel.setBounds(96 - 15, CS * 10, 64 + 15, 30);
        rKeyLabel.setForeground(Color.white);
        rKeyLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        symbol4 = new JLabel("×", JLabel.CENTER);
        symbol4.setBounds(25, 0, CS, 26);
        symbol4.setForeground(Color.white);
        symbol4.setFont(new Font("微软雅黑", Font.PLAIN, CS));

        monPicLabel = new JLabel();
        monPicLabel.setIcon(new ImageIcon(getClass().getResource("/image/icon/money.png")));
        monPicLabel.setBounds(CS, 352, 64, CS);
        monPicLabel.setForeground(Color.white);
        monPicLabel.add(symbol4, BorderLayout.CENTER);

        monLabel = new JLabel(String.valueOf(this.tower.getPlayer().money), JLabel.CENTER);
        monLabel.setBounds(96 - 15, 352, 64 + 15, 30);
        monLabel.setForeground(Color.white);
        monLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));

        showMesLabel = new JLabel("魔塔(v1.13)", JLabel.LEFT);
        showMesLabel.setForeground(Color.white);
        showMesLabel.setFont(new Font(null, Font.BOLD, 22));
        showMesLabel.setBounds(16, CS * 13 + 8 - 1, 478, CS);

        fpsLabel = new JLabel("FPS:", JLabel.RIGHT);
        fpsLabel.setBounds(512, 420, CS, 20);
        fpsLabel.setForeground(Color.white);
        fpsLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));

        showFpsLabel = new JLabel("???", JLabel.CENTER);
        showFpsLabel.setBounds(544, 420, 25, 20);
        showFpsLabel.setForeground(Color.white);
        showFpsLabel.setFont(new Font("方正桃体", Font.BOLD, 12));

        this.add(playerPicLabel);
        this.add(playerWindowLine);
        this.add(infoWindowLine);
        this.add(mapWindowLine);
        this.add(floorLabel);
        this.add(floorNumLabel);
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
        this.add(fpsLabel);
        this.add(showFpsLabel);
    }

    private void drawBackGround(Graphics g) {
        //构造背景界面
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 17; j++) {
                if (i == 7 && (j == 1 || j == 2 || j == 3 || j == 4)) {
                    g.drawImage(this.tower.getWallImage()[1], j * CS, i * CS, this);
                    continue;
                }
                if (i == 13 || i == 14) {
                    g.drawImage(this.tower.getFloorImage()[0], j * CS, i * CS, this);
                    continue;
                }
                if (i == 0 || i == 12 || j == 0 || j == 5 || j == 17) {
                    if (i == 0 && (j == 10 || j == 11 || j == 12)) {
                        g.drawImage(this.tower.getFloorImage()[0], j * CS, i * CS, this);
                    } else {
                        g.drawImage(this.tower.getWallImage()[1], j * CS, i * CS, this);
                    }
                } else {
                    g.drawImage(this.tower.getFloorImage()[0], j * CS, i * CS, this);
                }
            }
        }
    }

    private void drawAttribute(Graphics g) {
        //构造属性界面
        if (this.tower.getPlayer().hp > 999999) {
            hpLabel.setText(Math.floor(this.tower.getPlayer().hp / 1000) / 10 + "w");
        } else {
            hpLabel.setText(String.valueOf(this.tower.getPlayer().hp));
        }
        lvLabel.setText("Lv." + this.tower.getPlayer().level);
        atkLabel.setText(String.valueOf(this.tower.getPlayer().attack));
        defLabel.setText(String.valueOf(this.tower.getPlayer().defense));
        expLabel.setText(String.valueOf(this.tower.getPlayer().exp));
        monLabel.setText(String.valueOf(this.tower.getPlayer().money));
        yKeyLabel.setText(String.valueOf(this.tower.getPlayer().yKey));
        bKeyLabel.setText(String.valueOf(this.tower.getPlayer().bKey));
        rKeyLabel.setText(String.valueOf(this.tower.getPlayer().rKey));
    }

    /**
     * 绘制地图
     */
    private static final byte INTERVAL_2 = 2;
    private void drawMap(Graphics g) {
        //System.out.println("构造地图中..." + drawMapNo++);
        GameMap gameMap;
        if (isNormalFloor()) {
            gameMap = this.tower.getGameMapList().get(floor);
        } else {
            gameMap = this.tower.getSpecialMap().get(specialGameMapNo);
        }
        String[][] layer1 = gameMap.layer1;
        String[][] layer2 = gameMap.layer2;
        String[][] layer3 = gameMap.layer3;
        int startX = 6 * CS;
        int startY = 1 * CS;
        for (int i = 0; i < GAME_ROW; i++) {
            for (int j = 0; j < GAME_COL; j++) {
                if (layer3[i][j].contains("wall")) {
                    String wallId = this.tower.getWallMap().get(layer3[i][j]).getId();
                    g.drawImage(getImageFromIcons(this.tower.getWallMap().get(wallId).getIcon(), INTERVAL_2), startX + j * CS, startY + i * CS, CS, CS, this);
                } else if (layer3[i][j].contains("door")) {
                    if (!layer3[i][j].contains("open")) {
                        String doorId = this.tower.getDoorMap().get(layer3[i][j]).getId();
                        g.drawImage(this.tower.getDoorMap().get(doorId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, CS, CS, this);
                    } else {
                        String doorId = this.tower.getDoorMap().get(layer3[i][j].substring(0, layer3[i][j].indexOf("open"))).getId();
                        byte no = Byte.parseByte(layer3[i][j].substring(layer3[i][j].length() - 1));
                        g.drawImage(this.tower.getDoorMap().get(doorId).getIcon()[no].getImage(), startX + j * CS, startY + i * CS, CS, CS, this);
                    }
                } else if (layer3[i][j].contains("stair")) {
                    String stairId = this.tower.getStairMap().get(layer3[i][j]).getId();
                    g.drawImage(getImageFromIcons(this.tower.getStairMap().get(stairId).getIcon(), INTERVAL_2), startX + j * CS, startY + i * CS, CS, CS, this);
                }
                if (layer2[i][j].contains("item")) {
                    String itemId = this.tower.getItemMap().get(layer2[i][j]).getId();
                    g.drawImage(this.tower.getItemMap().get(itemId).getIcon()[0].getImage(), startX + j * CS, startY + i * CS, CS, CS, this);
                }
                if (layer1[i][j].contains("monster")) {
                    String monsterId = this.tower.getMonsterMap().get(layer1[i][j]).getId();
                    g.drawImage(getImageFromIcons(this.tower.getMonsterMap().get(monsterId).getIcon(), INTERVAL_2), startX + j * CS, startY + i * CS, CS, CS, this);
                } else if (layer1[i][j].contains("npc")) {
                    String npcId = this.tower.getNpcMap().get(layer1[i][j]).getId();
                    g.drawImage(getImageFromIcons(this.tower.getNpcMap().get(npcId).getIcon(), INTERVAL_2), startX + j * CS, startY + i * CS, CS, CS, this);
                } else if (layer1[i][j].contains("shop")) {
                    String shopId = this.tower.getShopMap().get(layer1[i][j]).getId();
                    g.drawImage(getImageFromIcons(this.tower.getShopMap().get(shopId).getIcon(), INTERVAL_2), startX + j * CS, startY + i * CS, CS, CS, this);
                }
            }
        }
    }

    /**
     * 绘制玩家
     */
    private void drawPlayer(Graphics g) {
        int startX = 6 * CS;
        int startY = 1 * CS;
        byte x = this.tower.getPlayer().x;
        byte y = this.tower.getPlayer().y;
        g.drawImage(this.tower.getPlayer().getPlayerIcon()[DIRECTION][moveNo].getImage(), startX + x * CS, startY + y * CS, CS, CS, this);
    }

    /*********************************************** 工具方法 ***********************************************/

    /**
     * @param icons
     * @param interval 间隔多少帧切换一次 当间隔大于每秒帧数时,不会改变
     * @return
     */
    private Image getImageFromIcons(ImageIcon[] icons, byte interval) {
        //a << b = a * 2^b
        if (frames <= interval - 1) {
            return icons[0].getImage();
        } else if (frames <= (interval << 1) - 1) {
            return icons[1 % icons.length].getImage();
        } else if (frames <= interval * 3 - 1) {
            return icons[2 % icons.length].getImage();
        } else if (frames <= (interval << 2) - 1) {
            return icons[3 % icons.length].getImage();
        } else if (frames <= interval * 5 - 1) {
            return icons[4 % icons.length].getImage();
        } else if (frames <= interval * 6 - 1) {
            return icons[5 % icons.length].getImage();
        } else if (frames <= interval * 7 - 1) {
            return icons[6 % icons.length].getImage();
        } else if (frames <= (interval << 3) - 1) {
            return icons[7 % icons.length].getImage();
        }
        return null;
    }

    /**
     * 判断是否在普通法楼层
     *
     * @return 如果在普通楼层, 则返回true;反之,则返回false
     */
    public static boolean isNormalFloor() {
        return specialGameMapNo == null || "".equals(specialGameMapNo);
    }

    static public void updateFloorNum() {
        if (floor == -1) {
            if (specialGameMapNo.equals("hell")) {
                floorNumLabel.setText("地下层");
            } else {
                floorNumLabel.setText("神秘层");
            }
        } else {
            floorNumLabel.setText(floor + "F");
        }
    }

    /*********************************************** 结尾字幕 ***********************************************/

    public void end() {
        running = false;
        musicPlayer.playEndBackgroundMusic();
        this.removeAll();
        if (end == 1) {
            NormalEnd.end(this);
        }
        else if (end == 2) {
            NormalEnd.end(this);
        } else {
            NormalEnd.end(this);
        }
    }


}
