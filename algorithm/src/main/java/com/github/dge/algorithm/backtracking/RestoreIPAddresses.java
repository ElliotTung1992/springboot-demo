package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-02 9:51 上午
 * https://leetcode.com/problems/restore-ip-addresses/description/
 * https://leetcode-cn.com/problems/restore-ip-addresses/description/
 * 复原 IP 地址
 */
public class RestoreIPAddresses {

    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        List<String> strings = restoreIPAddresses.restoreIpAddresses("25525511135");
        System.out.println(strings);
    }



    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }




    public List<String> restoreIpAddresses2(String s) {
        List<String> list = new ArrayList<>();
        backTrack(s, 1, 0, list, new StringBuffer());
        return list;
    }

    private void backTrack(String s, int patch, int startIndex, List<String> list, StringBuffer sb){
        if(startIndex > s.length() - 1){
            return;
        }
        if(patch == 4){
            String lastStr = s.substring(startIndex);
            if(validate(lastStr)){
                sb.append(lastStr);
                list.add(sb.toString());
            }
        }else{
            // 操作
            for (int i = 0; i < 3; i++) {
                if(startIndex + i + 1 > s.length() - 1){
                    return;
                }
                String subStr = s.substring(startIndex, startIndex + i + 1);
                if(!validate(subStr)){
                    continue;
                }
                sb.append(subStr).append(".");
                backTrack(s, patch + 1, startIndex + i + 1, list, sb);
                // 回溯
                sb.delete(sb.lastIndexOf("."), sb.length());
                sb.delete(sb.lastIndexOf(".") + 1, sb.length());
            }
        }
    }

    private boolean validate(String str){
        int length = str.length();
        if(length == 0 || length > 3){
            return false;
        }
        if(length > 1 && str.charAt(0) == '0'){
            return false;
        }
        if(length == 3 && Integer.valueOf(str) > 255){
            return false;
        }
        return true;
    }
}
