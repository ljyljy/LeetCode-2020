package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q85_maximal_rectangle {
//    https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
    private int m, n; // 行数m，列数n
    public int maximalRectangle(char[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        int maxArea = 0;
        // 每遍历一行，重新计算高度
        int[] heights_i = new int[n];  // 有一个累加的过程，但若某行高=0，则重置为0；下面的行高从0累加
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights_i[j] += 1;
                } else {
                    heights_i[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, calcMaxAreaByRow(heights_i));
        }
        return maxArea;
    }

    private int calcMaxAreaByRow(int[] heights) {
        // ?类比q84，首尾是哨兵(高度=0) ∴列数+=2
        int[] tmp = new int[n+2];
        System.arraycopy(heights, 0, tmp, 1, n);
        int maxArea_i = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // 哨兵
        for (int i = 0; i < n+2; i++) {
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea_i = Math.max(maxArea_i, width * h);
            }
            stack.push(i);
        }
        return maxArea_i;
    }
}
