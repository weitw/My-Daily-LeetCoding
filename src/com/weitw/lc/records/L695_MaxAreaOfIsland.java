package com.weitw.lc.records;

/*
给你一个大小为 m x n 的二进制矩阵 grid 。

岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

岛屿的面积是岛上值为 1 的单元格的数目。

计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] 为 0 或 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/max-area-of-island
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import com.weitw.lc.annotation.Success;

@Success
public class L695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ans = Math.max(ans, dfs(grid, i, j, new int[]{1, 1, 1, 1}));
            }
        }
        return ans;
    }

    /**
     * 解决方法1
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] != 1) {
            return 0;
        }
        int ans = 1;
        grid[i][j] = 0;
        ans += dfs(grid, i-1, j);  // 上
        ans += dfs(grid, i+1, j);  // 下
        ans += dfs(grid, i, j-1);  // 左
        ans += dfs(grid, i, j+1);  // 右
        return ans;
    }

    /**
     * 解决方法2：方法1的优化，解决一些重复扫描的点
     * @param grid
     * @param i
     * @param j
     * @param speed
     * @return
     */
    public static int dfs(int[][] grid, int i, int j, int[] speed) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] != 1) {
            return 0;
        }
        int ans = 1;
        grid[i][j] = 0;
        if (speed[0] == 1) {
            ans += dfs(grid, i-1, j, new int[]{1, 0, 1, 1});  // 上
        }
        if (speed[1] == 1) {
            ans += dfs(grid, i+1, j, new int[]{0, 1, 1, 1});  // 下
        }
        if (speed[2] == 1) {
            ans += dfs(grid, i, j-1, new int[]{1, 1, 1, 0});  // 左
        }
        if (speed[3] == 1) {
            ans += dfs(grid, i, j+1, new int[]{1, 1, 0, 1});  // 右
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        L695_MaxAreaOfIsland maxAreaOfIsland = new L695_MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
