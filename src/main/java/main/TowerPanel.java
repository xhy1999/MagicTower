package main;

import com.jogamp.opengl.awt.GLJPanel;
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

import static pane.MonsterManualPane.showMonsterManual;
import static pane.FloorTransferPane.showFloorTransfer;

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

    JDialog dialogBox;
    JLabel showMesLabel;
    JLabel fpsLabel, showFpsLabel;

    /**
     * 帧数(每秒8帧)
     */
    private byte frames = 0;

    private boolean running = false;
    public static boolean canMove = true;
    public static KeyInputHandler input;
    public static MusicPlayer musicPlayer;
    //TODO 正式版这里要改为 false
    public static boolean canUseFloorTransfer = true;
    public static boolean canUseMonsterManual = true;
    public static String specialGameMapNo;
    //TODO 正式版这里要改为 0
    public static int floor = 1;

    private ExecutorService mainExecutor;
    private List<Tower> gameSave;
    private Tower tower;

    public static JFrame mainframe = new JFrame("魔塔v1.13  (复刻者:Vip、疯子)");

    public TowerPanel(Tower tower) {
        this.tower = tower;
        this.gameSave = new LinkedList<>();
        this.mainExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                5L, TimeUnit.SECONDS, new SynchronousQueue<>());
        this.tower.getPlayer().x = this.tower.getGameMapList().get(floor).upPositionX;
        this.tower.getPlayer().y = this.tower.getGameMapList().get(floor).upPositionY;
        //TODO 正式版这里要改为 0
        this.tower.getPlayer().maxFloor = 23;
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
        mainframe.setLocation(screenUtil.getScreenWidth() / 3, screenUtil.getScreenWidth() / 6);
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
            end();
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
            canMove = false;
            mainExecutor.execute(() -> {
                NPC npc;
                try {
                    npc = this.tower.getNpcMap().get(layer1[y][x]);
                } catch (Exception e) {
                    System.err.println("layer1 (x=" + y + ",y=" + x + ") npcId(" + layer1[y][x] + ") 不存在!");
                    return;
                }
                npc.script_start(this.tower);
                //重新获取一边,以防npc改变而这里没变
                npc = this.tower.getNpcMap().get(layer1[y][x]);
                if (!npc.canMeet) {
                    canMove = true;
                    return;
                }
                meetNpc(layer1[y][x]);
                if (npc.canRemove) {
                    if (isNormalFloor()) {
                        this.tower.getGameMapList().get(floor).layer1[y][x] = "";
                    } else {
                        this.tower.getSpecialMap().get(specialGameMapNo).layer1[y][x] = "";
                    }
                }
                npc.script_end(this.tower);
                canMove = true;
                input.clear();
            });
            return false;
        } else if (layer1[y][x].contains("shop")) {
            canMove = false;
            mainExecutor.execute(() -> {
                Shop shop;
                try {
                    shop = this.tower.getShopMap().get(layer1[y][x]);
                } catch (Exception e) {
                    System.err.println("layer1 (x=" + x + ",y=" + y + ") shopId(" + layer1[y][x] + ") 不存在!");
                    return;
                }
                if (!shop.canMeet) {
                    canMove = true;
                    return;
                }
                meetShop(shop.getId());
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
                    canMove = false;
                    musicPlayer.getSpecialItem();
                    mainExecutor.execute(() -> getSpecialItem(item));
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
                monster.script_end(this, this.tower);
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

    /*********************************************** 额外窗口 ***********************************************/

    //秘籍彩蛋
    private String secretScript = "";

    /**
     * 与NPC对话
     *
     * @param npcId
     */
    public void meetNpc(String npcId) {
        NPC npc = this.tower.getNpcMap().get(npcId);
        List<Dialogue> dialogues = npc.dialogues;
        for (int i = 0; i < dialogues.size(); i++) {
            Dialogue dialogue = dialogues.get(i);
            dialogBox = new JDialog(mainframe, null, true);
            String s;
            ImageIcon photo;
            JPanel dialog = new JPanel(null);
            JLabel pict = new JLabel();
            JLabel name;
            JTextArea content = new JTextArea();
            content.setBorder(BorderFactory.createLineBorder(Color.white));
            JLabel tip = new JLabel("Space...");
            if (dialogue.name.contains("player")) {
                pict.setBounds(208, 8, CS, CS);
                name = new JLabel("勇士");
                name.setBounds(176, 16, 32, 16);
                photo = new ImageIcon(this.tower.getPlayer().getPlayerIcon()[1][0].getImage());
                System.out.println("勇士:\n" + dialogue.text);
            } else {
                pict.setBounds(13, 8, CS, CS);
                name = new JLabel(npc.getName());
                name.setBounds(48, 16, 120, 16);
                photo = new ImageIcon(this.tower.getNpcMap().get(npcId).getIcon()[0].getImage());
                System.out.println(npc.getName() + ":\n" + dialogue.text);
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
            dialog.setSize(256, 128);
            dialog.setBackground(Color.black);
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
            dialog.add(pict);
            dialog.add(name);
            dialog.add(content);
            dialog.add(tip);
            dialogBox.setLocation(mainframe.getLocation().x + 242, TITLE_HEIGHT + mainframe.getLocation().y + 96);
            dialogBox.add(dialog);
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
        Shop shop = this.tower.getShopMap().get(shopId);
        dialogBox = new JDialog(mainframe, null, true);
        ImageIcon photo;
        JPanel dialog = new JPanel(null);
        JLabel shopImg = new JLabel();
        JTextArea shopDialogue = new JTextArea();
        shopImg.setBounds(10, 8, CS, CS);
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
        dialogBox.add(selectLabel);
        List<String> sellNameList = shop.sell.name;
        for (int i = 0; i < sellNameList.size(); i++) {
            JLabel label = new JLabel(sellNameList.get(i), JLabel.CENTER);
            label.setBounds(34, 100 + 30 * i, 200, 30);
            label.setForeground(Color.white);
            label.setFont(new Font("微软雅黑", Font.BOLD, 16));
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
                            price = Short.valueOf(shop.sell.price.get(nowSelected).toString());
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
                                shop.buyNum++;
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
        dialog.setSize(268, 235);
        dialog.setBackground(Color.black);
        shopDialogue.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
        shopDialogue.setLineWrap(true);
        shopDialogue.setEditable(false);
        shopDialogue.setBounds(4, 48, 260, 40);
        shopDialogue.setFont(new Font("宋体", Font.BOLD, 16));
        shopDialogue.setBackground(Color.black);
        shopDialogue.setForeground(Color.WHITE);
        dialog.add(shopImg);
        dialog.add(name);
        dialog.add(shopDialogue);
        dialogBox.setSize(268, 235);
        dialogBox.setUndecorated(true);
        dialogBox.setLocation(mainframe.getLocation().x + 237, TITLE_HEIGHT + mainframe.getLocation().y + 71);
        dialogBox.add(dialog);
        dialogBox.setVisible(true);
    }

    public void getSpecialItem(Item item) {
        dialogBox = new JDialog(mainframe, null, true);
        JPanel dialog = new JPanel(null);
        JLabel pict = new JLabel();
        JLabel name;
        JTextArea content = new JTextArea();
        JLabel tip = new JLabel("Space...");
        name = new JLabel(item.getName(), JLabel.CENTER);
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
                boolean close = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        close = true;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        close = true;
                        break;
                }
                if (close) {
                    dialogBox.dispose();
                    input.clear();
                    canMove = true;
                }
            }
        });
        dialog.setSize(400, 128);
        dialog.setBackground(Color.black);
        dialog.setBorder(BorderFactory.createLineBorder(new Color(228, 122, 0), 3));
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
        dialog.add(pict);
        dialog.add(name);
        dialog.add(content);
        dialog.add(tip);
        dialogBox.setLocation(mainframe.getLocation().x + 171, TITLE_HEIGHT + mainframe.getLocation().y + 96);
        dialogBox.add(dialog);
        dialogBox.setVisible(true);
    }

    /*********************************************** 结尾字幕 ***********************************************/
    public void end() {
        running = false;
        musicPlayer.playEndBackgroundMusic();
        this.remove(playerWindowLine);
        this.remove(infoWindowLine);
        this.remove(mapWindowLine);
        this.remove(playerPicLabel);
        this.remove(floorLabel);
        this.remove(floorNumLabel);
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
        this.remove(fpsLabel);
        this.remove(showFpsLabel);

        for (int i = 0; i <= 0xFF; i++) {
            this.setBackground(new Color(0, 0, 0, i));
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
        this.add(imgLabel);
        this.add(endLabel);

        ImageUtil imageUtil = new ImageUtil();
        mainExecutor.execute(() -> {
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
            this.remove(imgLabel);
            this.remove(endLabel);
            mainExecutor.execute(() -> imageScript());
            postScript();
        });
    }

    //线程中调用
    private void imageScript() {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageUtil imageUtil = new ImageUtil();
        JLabel label = new JLabel();
        label.setForeground(Color.white);
        label.setBounds(10, 140, 226, 162);
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
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字幕
     * 需要在线程中调用
     */
    private void postScript() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        musicPlayer.playPostScriptBackgroundMusic();
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
        endLabel.setBounds(0, 0, 576, 430);
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
        thankPlayLabel.setBounds(0, 0, 576, 430);
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
