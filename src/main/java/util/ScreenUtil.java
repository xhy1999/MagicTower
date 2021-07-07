package util;

import java.awt.*;

/**
 * 屏幕工具类
 * @author xuehy
 * @since 2020/6/9
 */
public final class ScreenUtil {

    private int screenWidth;
    private int screenHeight;

    public ScreenUtil() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int)screenSize.getWidth();
        this.screenHeight = (int)screenSize.getHeight();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

}
