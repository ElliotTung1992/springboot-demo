package com.github.dge1992.fish.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-27 18:19
 */
public class Test {

    public static void main(String[] args) {

        String d = StrUtil.format("aaa{}bbb{}ccc", "-", null);
        System.out.println(d);
    }
}
