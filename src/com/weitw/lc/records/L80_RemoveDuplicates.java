package com.weitw.lc.records;

import com.weitw.lc.annotation.Difficulty;
import com.weitw.lc.annotation.Fail;
import com.weitw.lc.annotation.LCName;
import com.weitw.lc.annotation.LCSolution;

import java.util.Arrays;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前七个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 */

@LCName(index = 80, name = "删除有序数组中的重复项 II", date = "2024-03-29", difficulty = Difficulty.Medium)
public class L80_RemoveDuplicates {

    @LCSolution(index = 1, name = "循环，O(n)，借用临时数组来实现", date = "2024-03-29", time = "100%", memory = "57.81%", remark = "因为数组是有序的，所以循环判断的时候，" +
            "记录下当前的数，然后往后遍历，如果一致，则count++，如果发现count超过2，则说明这个数不应该放到新数组里，则跳过。最后将新数组的数据拷贝到nums中即可。" +
            "该方法只需要遍历一次，时间复杂度较低，但是借用了一个临时数组，所以内存占用稍微高一点")
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int len = 0;
        int count = 0;
        int temp = 105;
        int[] result = new int[nums.length];
        for (int num : nums) {
            // 与前一个数不相同，则该值直接放到新数组中
            if (num != temp) {
                count = 1;
                result[len++] = num;
                temp = num;
                continue;
            }
            count++;
            // 重复次数小于等于2，则数允许放到新数组中
            if (count <= 2) {
                result[len++] = num;
            }
        }
        System.arraycopy(result, 0, nums, 0, len);
        System.out.println(Arrays.toString(nums));
        return len;
    }

    @Fail
    public static int removeDuplicates2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int left = 0, right = 1;
        int count = 0;
        while (right < nums.length) {
            if (nums[right] != nums[left]) {
                nums[++left] = nums[right++];
                count = 1;
                continue;
            }
            count++;
            if (count <= 2) {
                left++;
                right++;
                continue;
            }
            // nums[left]相同的数已经超过2个了，即nums[right]是多余的
            right++;
            while (right < nums.length) {
                if (nums[right] != nums[left]) {
                    left++;
                    nums[left] = nums[right];
                    break;
                }
                right++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        System.out.println(Arrays.toString(nums));
        System.out.println(removeDuplicates2(nums));
        System.out.println(Arrays.toString(nums));
    }
}



















