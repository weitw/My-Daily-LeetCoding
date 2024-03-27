package com.weitw.lc.records;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class L17 {
    public static void main(String[] args) {
        L17 l17 = new L17();
        System.out.println(l17.letterCombinations("234"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        for (int i = 0; i < digits.length(); i++) {
            list = test(list, map.get(String.valueOf(digits.charAt(i))));
        }
        return list;
    }

    public List<String> letterCombinations1(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        for (int i = 0; i < digits.length(); i++) {
            list = test(list, map.get(String.valueOf(digits.charAt(i))));
        }
        return list;
    }

    public List<String> test(List<String> list, String c2) {
        if (c2.length() == 0) {
            return list;
        }
        if (list.isEmpty()) {
            for (int i = 0; i < c2.length(); i++) {
                list.add(String.valueOf(c2.charAt(i)));
            }
            return list;
        }
        List<String> newList = new ArrayList<>(list.size() * c2.length());
        for (String s : list) {
            for (int i = 0; i < c2.length(); i++) {
                newList.add(s + c2.charAt(i));
            }
        }
        return newList;
    }
}
