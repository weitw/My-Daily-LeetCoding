package com.weitw.lc.records;

/*
给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

 

示例 1:

输入: a = "11", b = "1"
输出: "100"
示例 2:

输入: a = "1010", b = "1011"
输出: "10101"
 

提示：

每个字符串仅由字符 '0' 或 '1' 组成。
1 <= a.length, b.length <= 10^4
字符串如果不是 "0" ，就都不含前导零。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-binary
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Fail;

@Fail
public class L67_AddBinary {
    public String addBinary(String a, String b) {
        int add = 0;
        String result = "";
        int temp;
        int i = a.length() - 1;
        int j = a.length() - 1;
        while (i >= 0 && j >= 0) {
            temp = a.charAt(i--) + b.charAt(j--) - 96 + add;
            if (temp >= 2) {
                add = 1;
                result = (temp - 2) + result;
            } else {
                add = 0;
                result = temp + result;
            }
        }
        while (i >= 0) {
            temp = a.charAt(i--) + add - 48;
            if (temp >= 2) {
                add = 1;
                result = (temp - 2) + result;
            } else {
                add = 0;
                result = temp + result;
            }
        }
        while (j >= 0) {
            temp = b.charAt(j--) + add - 48;
            if (temp >= 2) {
                add = 1;
                result = (temp - 2) + result;
            } else {
                add = 0;
                result = temp + result;
            }
        }
        if (add == 1) {
            result = "1" + result;
        }
        return result;
    }

    public static void main(String[] args) {
        L67_AddBinary l67_addBinary = new L67_AddBinary();
        System.out.println(l67_addBinary.addBinary("111111", "1000000"));
    }
}
