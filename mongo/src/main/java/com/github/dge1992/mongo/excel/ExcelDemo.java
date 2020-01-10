package com.github.dge1992.mongo.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 董感恩
 * @date 2019-12-29 16:49
 * @desc Excel模型实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExcelDemo extends BaseExcelDemo {
    @ExcelProperty(name = "姓名", length = 3, isWrite = true, index = 1)
    private String name;
    @ExcelProperty(name = "年龄", isRequired = true, isWrite = true, index = 2)
    private Integer age;
    @ExcelProperty(name = "出生日期", isWrite = true, columnWidth = 5000, index = 3, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @ExcelProperty(name = "身高", isWrite = true, index = 4)
    private Double height;
    @ExcelProperty(name = "手机号码", isWrite = true, index = 5)
    private Long phone;
    @ExcelProperty(name = "男孩", isWrite = true, index = 6)
    private Boolean isMan;
}
