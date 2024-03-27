package com.weitw.lc.records;

import com.weitw.lc.annotation.LCSolution;
import com.weitw.lc.annotation.LCName;
import com.weitw.lc.annotation.Success;

import java.util.HashMap;
import java.util.Map;

/*
给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

@Success
@LCName(index = 1, name = "两数之和", date = "2024-03-26")
public class L1_TwoSum {
    /*
    测试用例：
    [2,7,11,15]
    9
     */
    @LCSolution(index = 1, name = "暴力破解", date = "2024-03-26 12", remark = "用两层循环去处理，时间复杂度O(n^2)")
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    @LCSolution(index = 2, name = "一层循环解决", date = "2024-03-26 20", remark = "已经知道两数之和为target，每次遍历的时候把当前值和序号存入map，如果遍历的时候发现target-当前值在map中存在，则直接返回")
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
    }

}
