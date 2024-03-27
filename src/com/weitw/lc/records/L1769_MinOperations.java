package com.weitw.lc.records;


import com.weitw.lc.annotation.Success;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。

在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。

返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。

每个 answer[i] 都需要根据盒子的 初始状态 进行计算。

提示：

n == boxes.length
1 <= n <= 2000
boxes[i] 为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

@Success
public class L1769_MinOperations {
    public int[] minOperations(String boxes) {
        int[] arr = new int[boxes.length()];
        int num;
        for (int i = 0; i < boxes.length(); i++) {
            num = 0;
            for (int j = 0; j < boxes.length(); j++) {
                if (boxes.charAt(j) == '1' && i != j) {
                    num += Math.abs(i - j);
                }
            }
            arr[i] = num;
        }
        return arr;
    }

    public int[] minOperations2(String boxes) {
        int[] arr = new int[boxes.length()];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                list.add(i);
            }
        }
        int num;
        for (int i = 0; i < boxes.length(); i++) {
            num = 0;
            for (Integer j : list) {
                if (i != j) {
                    num += Math.abs(i - j);
                }
            }
            arr[i] = num;
        }
        return arr;
    }

    /**
     * 评论区找到的优解
     * @param boxes
     * @return
     */
    public int[] minOperations3(String boxes) {
        int[] answer = new int[boxes.length()];
        int left = 0, right = 0, total = 0;//左边盒子的个数，右边盒子的个数，操作数
        //计算第一个盒子的操作数
        if (boxes.charAt(0) == '1') left ++;
        for (int i = 1 ; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                total += i;
            }
        }
        answer[0] = total;
        //根据前一个盒子的操作数，计算下一个盒子的操作数
        for (int i = 1; i < boxes.length(); i++) {
            total = total + left - right;
            if (boxes.charAt(i) == '1') {
                left ++;
                right --;
            }
            answer[i] = total;
        }
        return answer;
    }

    public static void main(String[] args) {
        String boxes = "110";
        L1769_MinOperations minOperations = new L1769_MinOperations();
        System.out.println(Arrays.toString(minOperations.minOperations2(boxes)));
    }
}
