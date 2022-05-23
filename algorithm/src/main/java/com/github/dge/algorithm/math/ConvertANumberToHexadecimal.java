package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-23 3:41 下午
 * https://leetcode.com/problems/convert-a-number-to-hexadecimal/description/
 * https://leetcode.cn/problems/convert-a-number-to-hexadecimal/description/
 * 十六进制
 */
public class ConvertANumberToHexadecimal {

    public static void main(String[] args) {
        ConvertANumberToHexadecimal hexadecimal = new ConvertANumberToHexadecimal();
        String s = hexadecimal.toHex(26);
        System.out.println(s);
    }

    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int temp = (num >> i * 4) & 0xf;
            if(sb.length() > 0 || temp > 0){
                char c =  temp < 10 ? (char)('0' + temp): (char)('a' + temp - 10);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
