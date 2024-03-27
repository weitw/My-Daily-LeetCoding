package com.weitw.lc.records;


import com.weitw.lc.annotation.Success;

import java.util.Arrays;

/*
给定一个 n x n 的二进制矩阵 image ，先 水平 翻转图像，然后 反转 图像并返回 结果 。

水平翻转图片就是将图片的每一行都进行翻转，即逆序。

例如，水平翻转 [1,1,0] 的结果是 [0,1,1]。
反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。

例如，反转 [0,1,1] 的结果是 [1,0,0]。
 

示例 1：

输入：image = [[1,1,0],[1,0,1],[0,0,0]]
输出：[[1,0,0],[0,1,0],[1,1,1]]
解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
示例 2：

输入：image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 

提示：

n == image.length
n == image[i].length
1 <= n <= 20
images[i][j] == 0 或 1.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flipping-an-image
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

@Success
public class L832_FlipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int[] img : image) {
            int i = 0, j = img.length - 1;
            while (i < j) {
                img[i] = img[i] + img[j];
                img[j] = img[i] - img[j];
                img[i] = img[i] - img[j];
                img[i] = img[i] == 0 ? 1 : 0;
                img[j] = img[j] == 0 ? 1 : 0;
                i++;
                j--;
            }
            if (i == j) {
                img[i] = img[i] == 0 ? 1 : 0;
            }
        }
        return image;
    }

    public static void main(String[] args) {
        int[][] image = new int[3][3];
        int[] img1 = {1,1,0};
        int[] img2 = {1,0,1};
        int[] img3 = {0,0,0};
        image[0] = img1;
        image[1] = img2;
        image[2] = img3;
        L832_FlipAndInvertImage obj = new L832_FlipAndInvertImage();
        System.out.println(Arrays.deepToString(obj.flipAndInvertImage(image)));
    }
}
