package com.weitw.lc.records;

import com.weitw.lc.annotation.LCSolution;
import com.weitw.lc.annotation.LCName;

/**
 * 某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。
 *
 * 要求实现时间复杂度为 O(n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入：sales = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：[4,-1,2,1] 此连续四天的销售总额最高，为 6。
 * 示例 2:
 *
 * 输入：sales = [5,4,-1,7,8]
 * 输出：23
 * 解释：[5,4,-1,7,8] 此连续五天的销售总额最高，为 23。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 */

@LCName(index = 161, name = "连续销售最高总额", date = "2024-03-26")
public class L161 {

    @LCSolution(index = 161, name = "最佳方案", date = "2024-03-26")
    public static int maxSales(int[] sales) {
        if (sales.length == 1) {
            return sales[0];
        }
        int sum = sales[0];
        int max = sum;
        for (int i = 1; i < sales.length; i++) {
            if (sum >= 0) {
                sum += sales[i];
            } else {
                sum = Math.max(sales[i], sum + sales[i]);
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] sales = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] sales = new int[]{5,4,-1,7,8};
        System.out.println(maxSales(sales));
    }
}
