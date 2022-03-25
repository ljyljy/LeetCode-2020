//Given n non-negative integers representing the histogram's bar height where th
//e width of each bar is 1, find the area of largest rectangle in the histogram.
//
//
//
//
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3
//].
//
//
//
//
//The largest rectangle is shown in the shaded area, which has area = 10 unit.
//
//
//
// Example:
//
//
//Input: [2,1,5,6,2,3]
//Output: 10
//
// Related Topics 栈 数组

// 法1：暴力 —— O(n^3)
// for i -> 0, n-2【需要加哨兵，否则: i∈[0,n)】
//      for j -> i+1, n-1【需要加哨兵，否则: j∈[i,n)】
//          (i, j) -> 最小高度(for遍历), area
//          update maxArea
// 法2：暴力2
// for i -> 0, n-1（遍历棒子高度）【需要加哨兵】
//      找到棒子i的左右边界（分别矮于自己的idx）
//          area = height[i] * (right - left)
//          update maxArea
// 法3：stack
// for i -> 0, n-2
//      for j -> i+1, n-1
//          (i, j) -> 最小高度, area
//          update maxArea
// 法4：分治
// for i -> 0, n-2
//      for j -> i+1, n-1
//          (i, j) -> 最小高度, area
//          update maxArea

package DataStructure.Deque;
import java.util.ArrayDeque;
import java.util.Deque;

public class q84_largest_rectangle_in_histogram_4star {
    // 1. 单调栈（维护栈）【加哨兵！！！】
    public int largestRectangleArea(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体【哨兵】。
        int[] tmp = new int[heights.length + 2];
//        arraycopy(Object src,  int srcPos[被复制起点], Object dest[复制目标], int destPos[复制起点],int length);
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>(); // 双端队列实现stack
        int max = 0;
        for (int i = 0; i < tmp.length; ++i) {// 递增栈❤，与q42接雨水相反！
            // 对栈中柱体(top)来说，栈中的下一个柱体(pop.top)就是其「左边第一矮于自己」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一矮于自己」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()]; // i: 右边界
                max = Math.max(max, (i - stack.peek() - 1) * h); // 弹栈后的栈顶（原-次栈顶）是左边界
                // 宽度要-1：两边的矮柱子不参与面积计算！ 矮高矮--> 面积只算高的部分！
                // 高的弹栈后，矮的以后有机会自会再算一次（此时下标相减就会包括高的）。
            }
            stack.push(i); // 递增栈❤
        }

        return max;
    }

        // 2. 暴力2 —— O(n^2)
    public int largestRectangleArea2(int[] heights) {
        int max = 0, n = heights.length;
        // 遍历每个柱子，以当前柱子的高度作为矩形的高 h，
        // 从当前柱子向左右遍历，找到矩形的宽度 w。
        for (int i = 0; i < n; ++i) {
            int w = 1, h = heights[i], j = i;
            while (--j >= 0 && heights[j] >= h) w++; // 几个>=的等号勿忘！
            j = i;
            while (++j < n && heights[j] >= h) w++;
            max = Math.max(max, w * h);
        }
        return max;
    }


    // 3. 暴力1 —— O(n^3)【TLE】/ O(n^2)【没加哨兵！！！】
    public int largestRectangleArea3(int[] heights) {
        int max = 0;
        // 枚举左边界
        for (int i = 0; i < heights.length; ++i) {
            int minH = Integer.MAX_VALUE;
            // 枚举右边界
            for (int j = i; j < heights.length; ++j){
                // 确定高度
                // 暴力1（TLE）
//                for (int k = i; k <= j; k++) {
//                    minH = Math.min(minH, heights[k]);
//                }
                // 暴力2
                minH = Math.min(minH, heights[j]);
                max = Math.max(max, minH * (j - i + 1));
            }
        }
        return max;
    }

}
