package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图片工具类
 * @author xuehy
 * @since 2020/6/9
 */
public final class ImageUtil {

    /**
     * 调整图片透明度     (0.不透明 ~ 100.全透明)
     * @param path      图片文件路径
     * @param alpha     透明度
     * @return          调整后图片透明度
     */
    public Image changeAlpha(String path, int alpha) {
        //检查透明度是否越界
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 100) {
            alpha = 100;
        }
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            int weight = image.getWidth();
            int height = image.getHeight();
            BufferedImage output = new BufferedImage(weight ,height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            output = g2.getDeviceConfiguration().createCompatibleImage(weight, height, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            //调制透明度
            for (int j1 = output.getMinY(); j1 < output.getHeight(); j1++) {
                for (int j2 = output.getMinX(); j2 < output.getWidth(); j2++) {
                    int rgb = output.getRGB(j2, j1);
                    rgb = ((alpha * 255 / 100) << 24) | (rgb & 0x00ffffff);
                    output.setRGB(j2, j1, rgb);
                }
            }
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(image, 0, 0, weight,height, null);
            g2.dispose();
            return Toolkit.getDefaultToolkit().createImage(output.getSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
