package com.github.dge1992.application.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 董感恩
 * @date 2020-04-03 17:48
 * @desc Excel案例实体
 */
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    //忽略这个字段
    @ExcelIgnore
    private String ignore;
}
