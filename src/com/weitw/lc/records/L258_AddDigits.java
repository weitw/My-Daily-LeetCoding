package com.weitw.lc.records;

/*
给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。

 

示例 1:

输入: num = 38
输出: 2
解释: 各位相加的过程为：
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2
由于 2 是一位数，所以返回 2。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Success;

@Success
public class L258_AddDigits {
    /**
     * 双层循环实现
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num >= 0 && num <= 9) {
            return num;
        }
        int add;
        while (num > 9) {
            add = 0;
            while (num / 10 != 0) {
                add += num % 10;
                num = num / 10;
            }
            add += num % 10;
            num = add;
        }
        return num;
    }

    /**
     * 递归实现
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        if (num >= 0 && num <= 9) {
            return num;
        }
        int add = 0;
        while (num / 10 != 0) {
            add += num % 10;
            num = num / 10;
        }
        add += num % 10;
        num = add;
        return addDigits2(num);
    }

    public static void main(String[] args) {
        L258_AddDigits l_258AddDigits = new L258_AddDigits();
        int num = 8;
        System.out.println(l_258AddDigits.addDigits2(num));
        System.out.println(3%10);
    }
}
