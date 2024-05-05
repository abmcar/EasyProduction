package top.abmcar.earyproduction.utils;

import java.util.ArrayList;

public class StringUtil {
    public static String registryNameToUnlocalizedName(String name) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder nowString = new StringBuilder();
        for (char it : name.toCharArray()) {
            if (it >= 'A' && it <= 'Z') {
                if (nowString.length() > 0) {
                    list.add(nowString.toString());
                }
                it = (char) (it - ('A' - 'a'));
                nowString = new StringBuilder("" + it);
            } else {
                nowString.append(it);
            }
        }
        if (nowString.length() > 0) {
            list.add(nowString.toString());
        }
        nowString = new StringBuilder();
        for (String s : list) {
            nowString.append("_").append(s);
        }
        nowString.deleteCharAt(0);
        return nowString.toString();
    }
}
