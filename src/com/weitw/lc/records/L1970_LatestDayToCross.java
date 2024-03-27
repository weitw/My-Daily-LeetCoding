package com.weitw.lc.records;

/*
给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。同时给你 row 和 col 分别表示矩阵中行和列的数目。

一开始在第 0 天，整个 矩阵都是 陆地 。但每一天都会有一块新陆地被 水 淹没变成水域。给你一个下标从 1 开始的二维数组 cells ，其中 cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1 ）。

你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。你可以从最上面一行的 任意 格子出发，到达最下面一行的 任意 格子。你只能沿着 四个 基本方向移动（也就是上下左右）。

请返回只经过陆地格子能从最 上面 一行走到最 下面 一行的 最后一天 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/last-day-where-you-can-still-cross
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Fail;

@Fail
public class L1970_LatestDayToCross {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] arr = new int[row][col];
        int day = 1;
        int[] temp;
        for (int i = 0; i < arr.length; i++) {
            temp = cells[day - 1];
            arr[temp[0]][temp[1]] = 1;
            for (int j = 0; j < arr[day - 1].length; j++) {
//                boolean cross = loop(arr);
            }
        }
        return 0;
    }

    public static boolean dnf(int[][] arr, int i, int j) {
        if (arr[i][j] == 1) {
            return false;
        }
        if (i == arr.length -1) {
            return true;
        }
        boolean result = false;
        if (i - 1 >= 0) {
            result = dnf(arr, i-1, j);
        }
        if (i + 1 < arr.length) {
            result = result || dnf(arr, i+1, j);
        }
        if (j - 1 >= 0) {
            result = result || dnf(arr, i, j-1);
        }
        if (j + 1 < arr.length) {
            result = result || dnf(arr, i, j+1);
        }
        return result;
    }
}
