package com.weitw.lc.records;


import com.weitw.lc.annotation.Difficulty;
import com.weitw.lc.annotation.LCName;
import com.weitw.lc.annotation.LCSolution;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 * @author weitw
 * @date 2024/4/2 9:21
 */

@LCName(index = 55, name = "跳跃游戏", date = "2024-04-02", difficulty = Difficulty.Medium)
public class L55_JumpGame {

    @LCSolution(index = 1, name = "逆推，借用一个新数组", date = "2024-04-02", remark = "逆向遍历nums，借助一个等长的新数组，记录每个位置是否能达到最后的位置，" +
            "如果能，只直接将该位置置为true。如果不能直接到达，那就判断这个位置是否能到下一个为true的位置，因为true就表示这个位置是最终能到达最后一个位置的。" +
            "如果仍然不能达到下一个true，那么该位置就置为false，标识该位置无论如何都无法到达最后。遍历结束后，判断temp[0]是否是true，true就表示最终能到最后的位置。", time = "8.63%", memory = "58.54%")
    public static boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        int step = 0;
        boolean[] temp = new boolean[len];
        for (int i = len - 2; i >= 0; i--) {
            step++;
            if (nums[i] >= step) {
                temp[i] = true;
                continue;
            }
            if (nums[i] == 0) {
                continue;
            }
            boolean is = false;
            for (int j = 1; j <= nums[i]; j++) {
                if (temp[i+j]) {
                    is = true;
                    temp[i] = true;
                    break;
                }
            }
            if (!is) {
                temp[i] = false;
            }
        }
        return temp[0];
    }

    @LCSolution(index = 2, name = "逆推，将数组换成map集合", date = "2024-04-02", remark = "逆向遍历nums，换成map后，理论上在第二步判断该位置能否跳到下一个true的位置时，" +
            "查询效率应该更高，O(1)，数组是O(n)。但是时间更长的原因，预测应该是元素太多，map集合出现了大量的resize，导致时间更长了。\n\n", time = "5.01%", memory = "31.08%")
    public static boolean canJump2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        int step = 0;
        Map<Integer, Boolean> map = new HashMap<>(nums.length);
        for (int i = len - 2; i >= 0; i--) {
            step++;
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] >= step) {
                map.put(i, true);
                continue;
            }
            for (int j = 1; j <= nums[i]; j++) {
                if (map.getOrDefault(i + j, false)) {
                    map.put(i, true);
                    break;
                }
            }
        }
        return map.getOrDefault(0, false);
    }

    @LCSolution(index = 3, name = "逆推，只需要借助一个int变量", date = "2024-04-02", remark = "逆向遍历nums。其实是优化了方案1和2。" +
            "方案1中是逆推时判断当前位置是否能找到下一个true作为跳板，然后会去遍历临时数组。但是实际这个地方可以优化，假如当前下标是3，已知临时数组中" +
            "的5,8位置都是true，现在我只需要知道离当前位置最近的一个跳板即可，即我每次只需要记录最小位置的一个跳板下标即可", time = "93.43%", memory = "90.52%")
    public static boolean canJump3(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        int d = len;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] >= (len - i - 1)) {
                d = i;
                continue;
            }
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] + i >= d) {
                d = i;
            }
        }
        return d == 0;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2,3,1,1,4};
//        int[] nums = new int[]{3,2,1,0,4};
//        int[] nums = new int[]{2,0,0};
//        int[] nums = new int[]{1,0,0};
        int[] nums = new int[]{3,0,8,2,0,0,1};
        System.out.println(canJump3(nums));
    }
}
