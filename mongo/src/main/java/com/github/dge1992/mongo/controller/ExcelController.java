package com.github.dge1992.mongo.controller;

import com.github.dge1992.mongo.excel.ExcelDemo;
import com.github.dge1992.mongo.excel.ExcelUtils;
import com.github.dge1992.mongo.excel.ReflectUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        long start = System.currentTimeMillis();
        ExcelUtils.ExcelResult excelResult = excelUtils.analysisData(file, new ExcelDemo());
        System.out.println(System.currentTimeMillis() - start);
        List<ExcelDemo> dataList = excelResult.getDataList();
        System.out.println(dataList.size());
//        dataList.stream().forEach(e -> System.out.println(e.getIndex()));
        return null;
    }

    public static void main(String[] args) {
        //getDemoAnnotations(ExcelDemo.class);
        Object obj = ReflectUtils.reflect(ExcelDemo.class).get();
        System.out.println(obj);
    }



}
