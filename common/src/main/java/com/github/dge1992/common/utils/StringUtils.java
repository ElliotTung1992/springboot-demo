package com.github.dge1992.common.utils;

import java.util.Random;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/26
 **/
public class StringUtils {

    public static void main(String[] args) {
        String str = "aa1231";
        System.out.println(isAlphaNumeric(str));
        System.out.println(str.toUpperCase());
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
