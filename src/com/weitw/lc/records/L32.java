package com.weitw.lc.records;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "a", t = "a"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t 仅包含小写字母
 */

public class L32 {
    /**
     * 这种适合比较通用的，s和t中不只是包含字母的情况
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (sMap.get(s.charAt(i)) == null) {
                sMap.put(s.charAt(i), 1);
            } else {
                sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            }
        }
        Integer count;
        for (int i = 0; i < t.length(); i++) {
            count = sMap.get(t.charAt(i));
            if (count == null) {
                return false;
            }
            count--;
            if (count < 0) {
                return false;
            }
            sMap.put(t.charAt(i), count);
        }
        for (Character c : sMap.keySet()) {
            if (sMap.get(c) == null || sMap.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 特殊情况，s,t中只有小写字母
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) {
            return false;
        }
        int[] sums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sums[s.charAt(i) - 'a']++;
            sums[t.charAt(i) - 'a']--;
        }
        for (int sum : sums) {
            if (sum != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaran";
//        String s = "a", t = "a";
        System.out.println(isAnagram1(s, t));
        String aaa = "abcz";
        for (int i = 0; i < aaa.length(); i++) {
            System.out.println((int) aaa.charAt(i));
        }
        System.out.println(122-97);
    }
}
