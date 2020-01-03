package com.github.dge1992.mongo.excel;

import com.github.dge1992.mongo.excel.ExcelValue;
import com.github.dge1992.mongo.excel.BaseExcelDemo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 董感恩
 * @date 2019-12-29 16:49
 * @desc Excel模型实体
 */
@Data
@ToString
public class ExcelDemo extends BaseExcelDemo {
    @ExcelValue(name = "姓名", length = 3)
    private String name;
    @ExcelValue(name = "年龄", isRequired = true)
    private Integer age;
    @ExcelValue(name = "出生日期")
    private Date birthday;
}
