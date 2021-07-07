package util;

/**
 * 字符工具类
 * @author xuehy
 * @since 2020/6/9
 */
public class StringUtil {

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
