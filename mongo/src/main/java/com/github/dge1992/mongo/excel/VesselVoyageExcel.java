package com.github.dge1992.mongo.excel;

import com.github.dge1992.mongo.excel.ExcelValue;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 董感恩
 * @date 2019-12-27 17:35
 * @desc 船名航次Excel实体
 */
@Data
@ToString
public class VesselVoyageExcel {
    //英文船名
    @ExcelValue(name = "英文船名")
    private String vesselEnname;
    //航次
    @ExcelValue(name = "航次")
    private String voyno;
    //起运港英文名称
    @ExcelValue(name = "起运港英文名称")
    private String portEnname;
    //预配船期
    @ExcelValue(name = "预配船期")
    private Date etd;
    //预计到港
    @ExcelValue(name = "预计到港")
    private Date eta;
    //起运港代码
    @ExcelValue(name = "起运港代码")
    private String portCode;
    //航线代码
    @ExcelValue(name = "航线代码")
    private String routeCode;
    //承运人
    @ExcelValue(name = "承运人")
    private String carrier;
    //中文船名
    @ExcelValue(name = "中文船名")
    private String vesselCnname;
    //进箱开始
    @ExcelValue(name = "进箱开始")
    private Date cyopen;
    //进箱截止
    @ExcelValue(name = "进箱截止")
    private String cyctncut;
    //实际离港
    @ExcelValue(name = "实际离港")
    private Date atd;
    //实际到港
    @ExcelValue(name = "实际到港")
    private Date ata;
    //截关日期
    @ExcelValue(name = "截关时间")
    private Date etc;
    //截单日期
    @ExcelValue(name = "截单时间")
    private Date cutOffBillDate;
    //截VGM时间
    @ExcelValue(name = "截VGM时间")
    private Date cutVgm;
}
