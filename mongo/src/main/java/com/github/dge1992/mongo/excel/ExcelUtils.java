package com.github.dge1992.mongo.excel;

import com.github.dge1992.mongo.utils.DateUtils;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.*;

/**
 * @author 董感恩
 * @date 2019-12-30 14:51
 * @desc Excel工具类
 */
public class ExcelUtils<T> {

    private static final String PROPERTY = "property";
    private static final String TYPE = "type";
    private static final String REQUIRED = "required";
    private static final String LENGTH = "length";
    private static final String DATE_FORMAT = "dateFormat";

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    private static final String INTEGER = "java.lang.Integer";
    private static final String DATE = "java.util.Date";
    private static final String DOUBLE = "java.lang.Double";
    private static final String LONG = "java.lang.Long";
    private static final String BOOLEAN = "java.lang.Boolean";

    //生成excel
    public void createExcel(List<T> list, String filePathName) {
        if(list == null || list.size() == 0) return;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        //获取写注解
        List<WriteObject> writeList = getWriteAnnotations(list.get(0).getClass());
        // Sheet样式
        XSSFCellStyle sheetStyle = wb.createCellStyle();
        // 背景色的设定
        XSSFColor myColor = new XSSFColor(Color.LIGHT_GRAY);
        sheetStyle.setFillBackgroundColor(myColor);
        // 前景色的设定
        sheetStyle.setFillForegroundColor(myColor);
        // 填充模式
        sheetStyle.setFillPattern(FillPatternType.FINE_DOTS);
        //创建标题行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;
        for (int i = 0; i < writeList.size(); i++) {
            WriteObject writeObject = writeList.get(i);
            //设置栏宽度
            sheet.setColumnWidth(i, writeObject.getColumnWidth());
            cell = row.createCell((short) i);
            cell.setCellValue(writeObject.getName());
            cell.setCellStyle(sheetStyle);
        }
        //创建数据行
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < writeList.size(); j++) {
                WriteObject writeObject = writeList.get(j);
                Object o = ReflectUtils.reflect(list.get(i)).field(writeObject.fieldName).get();
                if(writeObject.typeName.equals(Date.class.getTypeName())){
                    o = DateUtils.getStringFromDate((Date) o, writeObject.dateFormat);
                }
                String s = o.toString();
                row.createCell((short) j).setCellValue(s);
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePathName);
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析数据
    public ExcelResult analysisData(MultipartFile file, T t){
        if(file == null) throw new RuntimeException("上传文件不能为空！");
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
            Map<String, Map> totalConfig = getReadAnnotations(t.getClass());
            Map property = totalConfig.get(ExcelUtils.PROPERTY);
            Map type = totalConfig.get(ExcelUtils.TYPE);
            Map required = totalConfig.get(ExcelUtils.REQUIRED);
            Map length = totalConfig.get(ExcelUtils.LENGTH);
            //取值，从第二行开始取值
            for (int r = 1; r <= sheet.getLastRowNum(); r++){
                Row row = sheet.getRow(r);
                if(row == null)break;
                Class<?> clazz = t.getClass();
                T obj = (T) clazz.newInstance();
                for (int i = 0; i < totalCells; i++){
                    String excelValue = dataMap.get(i);
                    //System.out.println(excelValue);
                    String typeName = (String) type.get(excelValue);
                    boolean isRequired = (boolean) required.get(excelValue);
                    int len = (int) length.get(excelValue);
                    Object value = null;
                    Cell cell = row.getCell(i);
                    //非空校验
                    //为空，非必输，继续
                    if(checkCellNull(cell) && !isRequired)continue;
                    //为空，必输 记录错误
                    if(checkCellNull(cell) && isRequired){
                        errorList.add("第" + r + "行数据，" + excelValue + "类型数据不能为空！");
                        obj = null;
                        break;
                    }
                    //获取cell类型
                    CellType cellTypeEnum = cell.getCellType();
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue().trim();
                    //根据类型取数据
                    try{
                        switch (typeName){
                            case ExcelUtils.INTEGER:
                                value = new Integer((String)value);
                                break;
                            case ExcelUtils.DATE:
                                if(cellTypeEnum == CellType.NUMERIC){
                                    cell.setCellType(CellType.NUMERIC);
                                    value = cell.getDateCellValue();
                                    break;
                                }
                                Map dateFormat = totalConfig.get(ExcelUtils.DATE_FORMAT);
                                String pattern = (String)dateFormat.get(excelValue);
                                if(org.apache.commons.lang3.StringUtils.isEmpty(pattern))pattern = ExcelUtils.DEFAULT_PATTERN;
                                value = DateUtils.getDateFromStringOfPattern((String)value, pattern);
                                break;
                            case ExcelUtils.DOUBLE:
                                value = new Double((String)value);
                                break;
                            case ExcelUtils.LONG:
                                value = new Long((String)value);
                                break;
                            case ExcelUtils.BOOLEAN:
                                cell.setCellType(CellType.BOOLEAN);
                                value = cell.getBooleanCellValue();
                                break;
                        }
                    }catch (Exception e){
                        errorList.add("第" + r + "行数据，" + excelValue + "类型数据格式错误！！");
                        obj = null;
                        break;
                    }
                    if(null == value){
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
                if(obj != null){
                    if(t instanceof BaseExcelDemo)ReflectUtils.reflect(obj).field("index", r);
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
        if(cell == null || cell.getCellTypeEnum() == CellType.BLANK)return true;
        return false;
    }

    //校验格式并生成Workbook
    public static Workbook checkAndCreate(MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))throw new RuntimeException("上传文件格式不正确");
        Workbook wb = null;
        try {
            InputStream inputStream = file.getInputStream();
            wb = (fileName.matches("^.+\\.(?i)(xlsx)$")) ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    //获取读注解属性
    public static Map<String, Map> getReadAnnotations(Class clazz) {
        Map<String, Map> totalConfig = new HashMap<>();
        //注释-》属性
        Map<String, String> map = new HashMap<>();
        //注释-》属性类型
        Map<String, String> map2 = new HashMap<>();
        //注释-》是否必填
        Map<String, Boolean> map3 = new HashMap<>();
        //注释-》长度
        Map<String, Integer> map4 = new HashMap<>();
        //注释-》日期格式化表达式
        Map<String, String> map5 = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            boolean isPresent = field.isAnnotationPresent(ExcelProperty.class);
            String annotationName = null;
            boolean annotationIsRequired = false;
            int length = 0;
            String dateFormat = "";
            if(isPresent){
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                annotationName = annotation.name();
                annotationIsRequired = annotation.isRequired();
                length = annotation.length();
                dateFormat = annotation.dateFormat();
            }
            String fieldName = field.getName();
            String typeName = field.getType().getTypeName();
            map.put(annotationName, fieldName);
            map2.put(annotationName, typeName);
            map3.put(annotationName, annotationIsRequired);
            map4.put(annotationName, length);
            map5.put(annotationName, dateFormat);
        }
        totalConfig.put(ExcelUtils.PROPERTY, map);
        totalConfig.put(ExcelUtils.TYPE, map2);
        totalConfig.put(ExcelUtils.REQUIRED, map3);
        totalConfig.put(ExcelUtils.LENGTH, map4);
        totalConfig.put(ExcelUtils.DATE_FORMAT, map5);
        return totalConfig;
    }

    //获取写注释属性
    public List<WriteObject> getWriteAnnotations(Class clazz) {
        Map<Integer, WriteObject> writeObjectMap = new TreeMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            boolean isPresent = field.isAnnotationPresent(ExcelProperty.class);
            if(isPresent){
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if(annotation.isWrite()){
                    int index = annotation.index();
                    while (index == 0 || writeObjectMap.get(index) != null){
                        Random rand = new Random();
                        index = rand.nextInt(1000);
                    }
                    String name = annotation.name();
                    String fieldName = field.getName();
                    String typeName = field.getType().getTypeName();
                    int columnWidth = annotation.columnWidth();
                    String dateFormat = annotation.dateFormat();
                    WriteObject writeObject = new WriteObject(name, fieldName, typeName, index, columnWidth, dateFormat);
                    writeObjectMap.put(index, writeObject);
                }
            }
        }
        List<WriteObject> list = new ArrayList();
        for (Map.Entry entry:writeObjectMap.entrySet()) {
            list.add((WriteObject)entry.getValue());
        }
        return list;
    }

    @AllArgsConstructor
    @Data
    class WriteObject{
        String name;
        String fieldName;
        String typeName;
        Integer index;
        Integer columnWidth;
        String dateFormat;
    }

    @Data
    public
    class ExcelResult{
        //记录错误集合
        List<String> errorList = Lists.newArrayList();
        //数据集合
        List<T> dataList = new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(Date.class.getComponentType());
        System.out.println(Date.class.getDeclaredClasses());
        System.out.println(Date.class.getTypeName());
        System.out.println(Double.class.getTypeName());
    }
}
