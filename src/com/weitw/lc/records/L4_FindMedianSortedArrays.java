package com.weitw.lc.records;


import com.weitw.lc.annotation.Success;

import java.util.Arrays;

/**
 * 找到两个有序数组的中位数（两个数组都是升序）
 * @author weitw
 * @date 2022/2/2 20:27
 */

@Success
public class L4_FindMedianSortedArrays {
    /**
     * 方法一：合并两个数组后，取中位数即可
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = mergeSortArray(nums1, nums2);
        int i = nums3.length;
        double d;
        int c = i / 2;
        if (i % 2 == 0) {
            d = (nums3[c-1] + nums3[c]) / 2.0;
        } else {
            d = nums3[i / 2];
        }
        return d;
    }

    /**
     * 合并两个有序数组，保证合并的数组也是有序的
     * @param arrayA
     * @param arrayB
     * @return
     */
    public static int[] mergeSortArray(int[] arrayA, int[] arrayB) {
        if (arrayA == null || arrayA.length == 0) {
            return arrayB;
        }
        if (arrayB == null || arrayB.length == 0) {
            return arrayA;
        }
        int leftA = 0, leftB = 0;
        int aLen = arrayA.length;
        int bLen = arrayB.length;
        int[] arrayC = new int[aLen + bLen];
        int index = 0;
        while (leftA < aLen && leftB < bLen) {
            if (arrayA[leftA] < arrayB[leftB]) {
                arrayC[index++] = arrayA[leftA++];
            } else {
                arrayC[index++] = arrayB[leftB++];
            }
        }
        while (leftA < aLen) {
            arrayC[index++] = arrayA[leftA++];
        }
        while (leftB < bLen) {
            arrayC[index++] = arrayB[leftB++];
        }
        return arrayC;

    }

    /**
     * 方法二：不合并数组，找到两个数组中间的值即可
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int aLen = nums1.length;
        int bLen = nums2.length;
        int a = 0, b = 0;
        boolean flag = false;
        int index;
        if ((aLen + bLen) % 2 == 0) {
            flag = true;
            index = (aLen + bLen) / 2 - 1;
        } else {
            index = (aLen + bLen) / 2;
        }
        while (a < aLen && b < bLen) {
            if (nums1[a] < nums2[b]) {
                if ((a + b) == index) {
                    if (flag) {
                        return nums1[a] + nums1[a+1];
                    } else {
                        return nums1[a];
                    }
                }
                a++;
            } else {
                if ((a + b) == index) {
                    if (flag) {
                        return nums2[b] + nums2[b+1];
                    } else {
                        return nums2[b];
                    }
                }
                b++;
            }
        }
        while (a < aLen) {
            if ((a + b) == index) {
                if (flag) {
                    return nums1[a] + nums1[a+1];
                } else {
                    return nums1[a];
                }
            }
            a++;
        }
        while (b < bLen) {
            if ((a + b) == index) {
                if (flag) {
                    return nums2[b] + nums2[b+1];
                } else {
                    return nums2[b];
                }
            }
            b++;
        }
        return 0.0;
    }

    /**
     * 合并两个数组，合并的结果为nums1，nums1的长度为m+n
     * 测试用例：
     * [1,2,3,0,0,0]
     * 3
     * [2,5,6]
     * 3
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j= 0;
        int[] res = new int[m + n];
        while (i < m && j < n) {
            res[i+j] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < m) {
            res[i+j] = nums1[i++];
        }
        while (j < n) {
            res[i+j] = nums2[j++];
        }
        for (int k = 0; k < m+n; k++) {
            nums1[k] = res[k];
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
//        int[] arrA = new int[]{11, 13, 14, 15, 16, 17};
//        int[] arrB = new int[]{9, 10};
//        Double d = findMedianSortedArrays(arrA, arrB);
//        System.out.println(d);
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;
        merge(nums1, m, nums2, n);
    }





















}
