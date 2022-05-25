package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-25 9:38 上午
 * https://leetcode.com/problems/excel-sheet-column-title/description/
 * https://leetcode.cn/problems/excel-sheet-column-title/description/
 * Excel表列名称
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        ExcelSheetColumnTitle excelSheetColumnTitle = new ExcelSheetColumnTitle();
        String s = excelSheetColumnTitle.convertToTitle(2147483647);
        System.out.println(s);
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0){
            int i = (columnNumber - 1) % 26 + 1;
            sb.append((char)('A' + i - 1));
            columnNumber = (columnNumber - i) / 26;
        }
        return sb.reverse().toString();
    }
}
