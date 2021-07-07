package score;

import util.ScreenUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 最终得分窗体 JPanel对比版
 * @author xuehy
 * @since 2020/6/9
 */
public class ScorePanel extends JPanel {

    private static JFrame scoreFrame = new JFrame("最终得分");
    private static final int SCORE_WINDOW_WIDTH = 400;
    private static final int SCORE_WINDOW_HEIGHT = 350;

    JLabel playerLabel;

    private Image backgroundImage;

    //窗体测试方法
    public static void main(String[] args) {
        new ScorePanel();
    }

    public ScorePanel() {
        /********************UI部分********************/
        this.setLayout(null);
        //设定初始构造时面板大小
        this.setPreferredSize(new Dimension(SCORE_WINDOW_WIDTH, SCORE_WINDOW_HEIGHT));
        this.backgroundImage = new ImageIcon(this.getClass().getResource("/image/score/bg_yellow.png")).getImage();
        this.initUI();
        //设定焦点在本窗体
        this.setFocusable(true);
        //主窗体
        scoreFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scoreFrame.setSize(SCORE_WINDOW_WIDTH, SCORE_WINDOW_HEIGHT);
        ScreenUtil screenUtil = new ScreenUtil();
        scoreFrame.setLocation((screenUtil.getScreenWidth() - SCORE_WINDOW_WIDTH) / 2, (screenUtil.getScreenHeight() - SCORE_WINDOW_HEIGHT - 100) / 2);
        //得到一个Toolkit对象
        Toolkit tool = this.getToolkit();
        Image image = tool.getImage(this.getClass().getResource("/image/icon/mt.png"));
        scoreFrame.setIconImage(image);
        Container contentPane = scoreFrame.getContentPane();
        contentPane.setSize(SCORE_WINDOW_WIDTH, SCORE_WINDOW_HEIGHT);
        contentPane.add(this, BorderLayout.CENTER);
        scoreFrame.pack();
        this.setFocusable(true);
        scoreFrame.setResizable(false);
        scoreFrame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawBackGround(g);
    }

    private void drawBackGround(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        int imageWidth = backgroundImage.getWidth(this);
        int imageHeight = backgroundImage.getHeight(this);
        for(int ix = 0; ix < width; ix += imageWidth) {
            for(int iy = 0; iy < height; iy += imageHeight) {
                g.drawImage(backgroundImage, ix, iy, this);
            }
        }
    }

    private void initUI() {
        playerLabel = new JLabel("", JLabel.CENTER);
        playerLabel.setBounds(100, 0, 200, 80);

        JLabel playerPicLabel = new JLabel("", JLabel.CENTER);
        playerPicLabel.setBounds(0, 20, 40, 40);
        playerPicLabel.setIcon(new ImageIcon(this.getClass().getResource("/image/icon/mt.png")));
        playerPicLabel.setBorder(BorderFactory.createLineBorder(new Color(104, 24, 24), 2));

        JLabel nameLabel = new JLabel("请输入您的名字:", JLabel.LEFT);
        nameLabel.setBounds(50, 20, 160, 20);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));

        TextField field = new TextField("请输入您的名字");
        field.setBounds(50, 40, 160, 20);
        field.setEditable(true); // 设置单行输入框能否编辑


        playerLabel.add(playerPicLabel);
        playerLabel.add(nameLabel);
        playerLabel.add(field);
        this.add(playerLabel);

    }

}
