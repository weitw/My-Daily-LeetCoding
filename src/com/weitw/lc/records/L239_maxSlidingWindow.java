package com.weitw.lc.records;

import java.util.Arrays;

/**
 * 滑动窗口问题
 */
public class L239_maxSlidingWindow {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
//        int[] nums = new int[]{1,-1};
//        int[] nums = new int[]{1,3,-1};
        int k = 3;
//        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
        System.out.println(Arrays.toString(getMaxSlidingWindow(nums, k)));
    }

    /**
     * 计算滑动窗口内的和
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int sum = 0;
        if (nums.length <= k) {
            for (int num : nums) {
                sum += num;
            }
            return new int[]{sum};
        }
        int left = 0;
        int right = k - 1;
        int resultLength = nums.length - k + 1;
        int[] result = new int[resultLength];
        int j = 0;
        int num = 0;
        while (right < nums.length) {
            num = cal(nums, left++, right++, num);
            result[j++] = num;
        }
        return result;
    }

    public static int cal(int[] nums, int left, int right, int num) {
        if (left == 0) {
            for (int i = left; i <= right; i++) {
                num += nums[i];
            }
            return num;
        }
        if (right >= nums.length) {
            return num - nums[left];
        }
        return num + nums[right] - nums[left-1];
    }

    /**
     * 得到滑动窗口内的最大值数组
     */
    public static int[] getMaxSlidingWindow(int[] nums, int k) {
        int[] maxIndexArray = new int[2];
        if (nums.length <= k) {
            getMaxIndex(nums, 0, nums.length - 1, -1, maxIndexArray);
            return new int[]{maxIndexArray[1]};
        }
        int len = nums.length - k + 1, left = 0, right = k - 1, j = 0, maxIndex = -1;
        int[] result = new int[len];
        while (right < nums.length) {
            getMaxIndex(nums, left++, right++, maxIndex, maxIndexArray);
            maxIndex = maxIndexArray[0];
            result[j++] = maxIndexArray[1];
        }
        return result;
    }

    public static void getMaxIndex(int[] nums, int left, int right, int maxIndex, int[] maxIndexArray) {
        if (maxIndex < left) {
            if (maxIndex != -1 && nums[right] >= nums[maxIndex]) {
                maxIndexArray[0] = right;
                maxIndexArray[1] = nums[right];
                return;
            }
            maxIndex = left;
            for (int i = left+1; i <= right; i++) {
                if (nums[i] >= nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            maxIndexArray[0] = maxIndex;
            maxIndexArray[1] = nums[maxIndex];
            return;
        }
        if (nums[right] >= nums[maxIndex]) {
            maxIndex = right;
        }
        maxIndexArray[0] = maxIndex;
        maxIndexArray[1] = nums[maxIndex];
    }
}
