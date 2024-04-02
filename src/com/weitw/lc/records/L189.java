package com.weitw.lc.records;

import com.weitw.lc.annotation.Difficulty;
import com.weitw.lc.annotation.LCName;
import com.weitw.lc.annotation.LCSolution;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */

@LCName(index = 189, name = "轮转数组", date = "2024-03-29", difficulty = Difficulty.Medium)
public class L189 {

    @LCSolution(index = 1, name = "取余，借助新数组", remark = "取余后确认最小移动的位数，然后循环将每个数+k后确认它新的位置，放到新的数组中",
            date = "2024-03-29", time = "61.76%", memory = "83.97%")
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len == 1 || k % len == 0) {
            return;
        }
        k = k % len;
        int[] ks = new int[len];
        int indx;
        for (int i = 0; i < nums.length; i++) {
            indx = i + k;
            indx = indx % len;
            ks[indx] = nums[i];
        }
        System.arraycopy(ks, 0, nums, 0, nums.length);
    }

    @LCSolution(index = 2, name = "取余，借助System.arraycopy方法，减少代码量", remark = "本质上思路还是跟第一个差不多，区别在于是将移动会后超过数组最右边的数据放到新数组中，" +
            "然后再将不会超过最右边的数据移动到新数组的最后",
            date = "2024-03-29", time = "100%", memory = "67.83%")
    public static void rotate1(int[] nums, int k) {
        int len = nums.length;
        if (len == 1 || k % len == 0) {
            return;
        }
        k = k % len;
        int[] ks = new int[k];
        System.arraycopy(nums, len-k, ks, 0, k);
        System.arraycopy(nums, 0, nums, k, len-k);
        System.arraycopy(ks, 0, nums, 0, k);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        // 6,7,8,1,2,3,4,5
        int k = 3;
        rotate1(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
