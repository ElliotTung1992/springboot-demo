package com.github.dge1992.mongo.controller;

import com.github.dge1992.mongo.excel.ExcelDemo;
import com.github.dge1992.mongo.excel.ExcelUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 董感恩
 * @date 2019-12-29 16:16
 * @desc Excel控制器
 */
@Api("Excel")
@RequestMapping("/excel")
@RestController
public class ExcelController {

    @PostMapping("/exportExcel")
    public Object exportExcel(MultipartFile file){
//        ExcelUtils excelUtils = new ExcelUtils<VesselVoyageExcel>();
//        List list = excelUtils.getList(file, new VesselVoyageExcel());
        ExcelUtils excelUtils = new ExcelUtils<ExcelDemo>();
        //long start = System.currentTimeMillis();
        ExcelUtils.ExcelResult excelResult = excelUtils.analysisData(file, new ExcelDemo());
        //System.out.println(System.currentTimeMillis() - start);
        List<ExcelDemo> dataList = excelResult.getDataList();
        List errorList = excelResult.getErrorList();
        //System.out.println(dataList.size());
        errorList.stream().forEach(e -> System.out.println(e));
        dataList.stream().forEach(e -> System.out.println(e));
        return null;
    }

    @GetMapping("/createExcel/{filePathName}")
    public void createExcel(@PathVariable("filePathName") String filePathName) {
        filePathName = "/Users/apple/Desktop/Members.xlsx";
        ExcelUtils excelUtils = new ExcelUtils<ExcelDemo>();
        List<ExcelDemo> excelDemos = Arrays.asList(new ExcelDemo("dge", 12, new Date(), 1.70, 13523636794l, true), new ExcelDemo("fnn", 23, new Date(), 1.84, 13523636794l, false));
        excelUtils.createExcel(excelDemos, filePathName);
    }
}
