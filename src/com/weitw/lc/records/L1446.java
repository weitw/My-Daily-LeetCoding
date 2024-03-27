package com.weitw.lc.records;


/**
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 *
 * 请你返回字符串 s 的 能量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 *
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e'
 * @author weitw
 * @date 2024/3/20 9:34
 */
public class L1446 {
    public static void main(String[] args) {
        String s = "abbcccddddeeeeedcba";
        System.out.println(maxPower(s));
    }

    public static int maxPower(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char before = s.charAt(0);
        int max = 1;
        int temp = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == before) {
                temp++;
                max = Math.max(max, temp);
            } else {
                before = s.charAt(i);
                temp = 1;
            }
        }
        return max;
    }
}
