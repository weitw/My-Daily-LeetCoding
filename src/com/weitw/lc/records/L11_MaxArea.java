package com.weitw.lc.records;

/*
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。

提示：
n == height.length
2 <= n <= 105
0 <= height[i] <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/container-with-most-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Success;

@Success
public class L11_MaxArea {
    // 暴力求解
    public int maxArea1(int[] height) {
        int max = 0;
        int high;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                high = Math.min(height[i], height[j]);
                max = Math.max(high * (j - i), max);
            }
        }
        return max;
    }

    // 双指正优化
    public int maxArea2(int[] height) {
        int max = 0;
        int i = 0, j = height.length-1;
        while (i < j) {
            if (height[i] < height[j]) {
                max = Math.max(max, height[i] * (j-i));
                i++;
            } else {
                max = Math.max(max, height[j] * (j-i));
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        L11_MaxArea maxArea_11 = new L11_MaxArea();
        System.out.println(maxArea_11.maxArea2(height));
    }
}
