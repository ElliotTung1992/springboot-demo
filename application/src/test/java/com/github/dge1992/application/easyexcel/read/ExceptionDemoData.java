package com.github.dge1992.application.easyexcel.read;

import lombok.Data;

import java.util.Date;

/**
 * @author 董感恩
 * @date 2020-04-07 11:32
 * @desc
 */
@Data
public class ExceptionDemoData {
    /**
     * 用日期去接字符串 肯定报错
     */
    private Date date;
}
