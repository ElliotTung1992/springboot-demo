package com.github.dge1992.fish.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-10 3:20 下午
 */
public class StringUtilTest {

    public static void main(String[] args) {
        String join = StringUtils.join(1, 2, 3);
        System.out.println(join);

        String d = StrUtil.format("aaa{}bbb{}ccc", "-", null);
        System.out.println(d);
    }


}
