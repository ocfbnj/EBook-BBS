package cn.ocfbnj.ebookbbs.utils;

public class WebUtils {

    public static int parseInt(String strInt, int defaultvalue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {

        }
        return defaultvalue;
    }
}
