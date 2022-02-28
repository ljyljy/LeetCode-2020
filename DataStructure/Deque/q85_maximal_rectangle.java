package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q85_maximal_rectangle {
//    https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
    private int m, n; // ����m������n
    public int maximalRectangle(char[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        int maxArea = 0;
        // ÿ����һ�У����¼���߶�
        int[] heights_i = new int[n];  // ��һ���ۼӵĹ��̣�����ĳ�и�=0��������Ϊ0��������иߴ�0�ۼ�
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
        // ?���q84����β���ڱ�(�߶�=0) ������+=2
        int[] tmp = new int[n+2];
        System.arraycopy(heights, 0, tmp, 1, n);
        int maxArea_i = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // �ڱ�
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
