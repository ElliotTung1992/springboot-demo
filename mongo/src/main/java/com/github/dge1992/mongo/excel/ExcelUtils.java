package com.github.dge1992.mongo.excel;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 董感恩
 * @date 2019-12-30 14:51
 * @desc
 */
public class ExcelUtils<T> {

    public ExcelResult analysisData(MultipartFile file, T t){
        //记录错误集合
        List<String> errorList = Lists.newArrayList();
        //数据集合
        List<T> dataList = new ArrayList<>();
        try {
            Workbook wb = checkAndCreate(file);
            //获取标题行
            Sheet sheet = wb.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCells = 0;
            if (totalRows >= 1 && sheet.getRow(0) != null) {
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }
            //获取标题头，存储映射关系并过滤空值
            Row titleRow = sheet.getRow(0);
            Map<Integer, String> dataMap = new HashMap<>();
            for (int i = 0; i < totalCells; i++) {
                String stringCellValue = titleRow.getCell(i).getStringCellValue();
                if(StringUtils.isEmpty(stringCellValue)){
                    //重新设置cell的长度，过滤空值
                    totalCells = i;
                    break;
                }
                dataMap.put(i, stringCellValue);
            }
            //获取所有配置
            Map<String, Map> totalConfig = getDemoAnnotations(t.getClass());
            Map property = totalConfig.get("property");
            Map type = totalConfig.get("type");
            Map required = totalConfig.get("required");
            Map length = totalConfig.get("length");
            //取值，从第二行开始取值
            for (int r = 1; r <= sheet.getLastRowNum(); r++){
                Row row = sheet.getRow(r);
                if(row == null)break;
                Class<?> clazz = t.getClass();
                T obj = (T) clazz.newInstance();
                for (int i = 0; i < totalCells; i++){
                    String excelValue = dataMap.get(i);
                    //System.out.println(excelValue);
                    Class<?> typeClazz = (Class<?>) type.get(excelValue);
                    boolean isRequired = (boolean) required.get(excelValue);
                    int len = (int) length.get(excelValue);
                    Object value = null;
                    Cell cell = row.getCell(i);
                    //非空校验
                    //为空，非必输，继续
                    if(checkCellNull(cell) && !isRequired){
                        continue;
                    }
                    //为空，必输 记录错误
                    if(checkCellNull(cell) && isRequired){
                        errorList.add("第" + r + "行数据，" + excelValue + "类型数据不能为空！");
                        obj = null;
                        break;
                    }
                    //根据类型取数据
                    try{
                        switch (typeClazz.getName()){
                            case "java.lang.String":
                                cell.setCellType(CellType.STRING);
                                value = cell.getStringCellValue().trim();
                                break;
                            case "java.lang.Integer":
                                //cell.setCellType(CellType.NUMERIC);
                                value = (int)cell.getNumericCellValue();
                                break;
                            case "java.util.Date":
                                value = cell.getDateCellValue();
                                break;
                        }
                    }catch (Exception e){
                        errorList.add("第" + r + "行数据，" + excelValue + "类型数据格式错误！！");
                        obj = null;
                        break;
                    }
                    //长度校验
                    //大于0的才校验
                    if(len > 0 && value.toString().length()>len){
                        errorList.add("第" + r + "行数据，" + excelValue + "长度不能超过" + len + "!");
                        obj = null;
                        break;
                    }
                    ReflectUtils.reflect(obj).field((String) property.get(excelValue), value);
                }
                //判断是否继承公共类
                if(obj != null && t instanceof BaseExcelDemo){
                    //记录行号
                    ReflectUtils.reflect(obj).field("index", r);
                }
                if(obj != null){
                    dataList.add(obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //errorList.stream().forEach(System.out::println);
        ExcelResult excelResult = new ExcelResult();
        excelResult.setErrorList(errorList);
        excelResult.setDataList(dataList);
        return excelResult;
    }

    //校验cell是否为空
    public static boolean checkCellNull(Cell cell){
        if(cell == null || cell.getCellTypeEnum() == CellType.BLANK){
            return true;
        }
        return false;
    }

    public static Workbook checkAndCreate(MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new RuntimeException("上传文件格式不正确");
        }
        Workbook wb = null;
        try {
            InputStream inputStream = file.getInputStream();
            boolean isExcel2003 = true;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Map<String, Map> getDemoAnnotations(Class clazz) {
        Map<String, Map> totalConfig = new HashMap<>();
        //注释-》属性
        Map<String, String> map = new HashMap<>();
        //注释-》属性类型
        Map<String, Class<?>> map2 = new HashMap<>();
        //注释-》是否必填
        Map<String, Boolean> map3 = new HashMap<>();
        //注释-》长度
        Map<String, Integer> map4 = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            boolean isPresent = field.isAnnotationPresent(ExcelValue.class);
            String annotationName = null;
            boolean annotationIsRequired = false;
            int length = 0;
            if(isPresent){
                ExcelValue annotation = field.getAnnotation(ExcelValue.class);
                annotationName = annotation.name();
                annotationIsRequired = annotation.isRequired();
                length = annotation.length();
            }
            String fieldName = field.getName();
            Class<?> type = field.getType();
            map.put(annotationName, fieldName);
            map2.put(annotationName, type);
            map3.put(annotationName, annotationIsRequired);
            map4.put(annotationName, length);
        }
        totalConfig.put("property", map);
        totalConfig.put("type", map2);
        totalConfig.put("required", map3);
        totalConfig.put("length", map4);
        return totalConfig;
    }

    @Data
    public
    class ExcelResult{
        //记录错误集合
        List<String> errorList = Lists.newArrayList();
        //数据集合
        List<T> dataList = new ArrayList<>();
    }
}
