package com.github.dge.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/26
 **/
public class StringUtils {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.contains("aa"));
        System.out.println(set.contains(""));
        System.out.println(set.contains(null));
    }

    /**
     * 获取随机位数的字符串
     *
     * @author fengshuonan
     * @Date 2017/8/24 14:09
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 校验字符串只能是数据或者英文
     * @param str
     * @return boolean
     */
    public static boolean isAlphaNumeric(String str) {
        // 正则表达式只允许数字和英文字母
        String pattern = "^[a-zA-Z0-9]+$";
        return str.matches(pattern);
    }
}
