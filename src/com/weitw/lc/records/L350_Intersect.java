package com.weitw.lc.records;

/*
给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Success;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Success
public class L350_Intersect {
    /**
     * 先排序，然后用双指针处理
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        int num1Length = nums1.length, num2Length = nums2.length;
        if (num1Length == 0 || num2Length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        int[] nums3 = new int[Math.max(num1Length, num2Length)];
        while (i < num1Length && j < num2Length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] == nums2[j]) {
                nums3[k++] = nums1[i++];
                j++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(nums3, k);
    }

    /**
     * hash表处理
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        int num1Length = nums1.length, num2Length = nums2.length;
        if (num1Length == 0 || num2Length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n1 : nums1) {
            if (map.containsKey(n1)) {
                map.put(n1, map.get(n1) + 1);
            } else {
                map.put(n1, 1);
            }
        }
        int[] nums3 = new int[Math.max(num1Length, num2Length)];
        int i = 0;
        for (int n2 : nums2) {
            if (map.containsKey(n2) && map.get(n2) > 0) {
                nums3[i++] = n2;
                map.put(n2, map.get(n2) - 1);
            }
        }
        return Arrays.copyOf(nums3, i);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        System.out.println(Arrays.toString(intersect2(nums1, nums2)));
    }
}
