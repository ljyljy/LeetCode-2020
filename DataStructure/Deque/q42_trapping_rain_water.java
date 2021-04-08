package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class q42_trapping_rain_water {
    // 法2-1：双指针 - 时间O(n)，空间O(1) 【最优解！效率高！】
    public int trap_2v1(int[] height) {
        if (height.length == 0 || height == null) return 0;
        int n = height.length;
        int l_maxH = height[0], r_maxH = height[n-1];
        int res = 0;
        for (int i = 0, j = n-1; i <= j; ) { // 而非i<j！否则会漏算i==j时的列
            if (l_maxH < r_maxH) { // 写法1（获取左右侧最高柱子的较矮者）
                l_maxH = Math.max(l_maxH, height[i]);
                res += l_maxH - height[i++];  // >= 0 (等于0时，左侧比当前左指针矮，无法盛水)
            } else {
                r_maxH = Math.max(r_maxH, height[j]);
                res += r_maxH - height[j--];
            }
        }
        return res;
    }

    // 法2-2【更易想 - 但效率很低！】双指针 - 时间O(n^2)，空间O(1)
    public int trap_2v2(int[] height) {
        int n = height.length;
        if (n == 0 || height == null) return 0;
        int res = 0;
        for (int i = 0; i < n; i++) { // 遍历每一列
            if (i == 0 || i == n-1) continue; // 首尾柱子不接雨水
            int r_maxH = height[i], l_maxH = height[i];  //  左、右侧柱子的最高高度
            for (int r = i+1; r < n; r++) {
                if (height[r] > r_maxH) r_maxH = height[r];
            }
            for (int l = i-1; l>=0; l--) {
                if (height[l] > l_maxH) l_maxH = height[l]; //  l_maxH = Math.max(l_maxH, height[l]);
            }   // 写法2（获取左右侧最高柱子的较矮者） ↓ - 优化见写法1（将其作为最先判断）
            int H_i = Math.min(r_maxH, l_maxH) - height[i];
            if (H_i > 0) res += H_i;
        }
        return res;
    }

    // 法1-2：动态规划 - 时间O(n)，空间O(n) - 效率也挺高！
    public int trap_1v2(int[] height) {
        int n = height.length;
        if (n == 0 || height == null) return 0;
        int res = 0;
        int[] l_maxH_dp = new int[n], r_maxH_dp = new int[n]; // 保存i左/右侧的最高柱子【包括自己i】
        l_maxH_dp[0] = height[0]; r_maxH_dp[n-1] = height[n-1]; // 初始化dp
        for (int i = 1; i < n; i++) {  // 递推, 注意i从1开始
            l_maxH_dp[i] = Math.max(l_maxH_dp[i-1], height[i]); // L→R:取决于i柱高 & i左侧∈[0,i-1]最高柱子
            r_maxH_dp[n-1-i] = Math.max(r_maxH_dp[n-i], height[n-1-i]); // L<-R: 取决于[n-1-i]柱高 & 其右侧∈[n-i, n)最高柱子
        } // ↑ 优化法1-1，合并2个for循环

        for (int i = 0; i < n; i++){
            if (i == 0 || i == n-1) continue;  // 首尾柱子无法接雨水
            res += Math.min(l_maxH_dp[i], r_maxH_dp[i]) - height[i];
        }
        return res;
    }

    // 法1-1：动归v1
    public int trap_1v1(int[] height) {
        int n = height.length;
        if (n == 0 || height == null) return 0;
        int res = 0;
        int[] l_maxH = new int[n], r_maxH = new int[n];
        l_maxH[0] = height[0]; r_maxH[n-1] = height[n-1];
        for (int i = 1; i < n; i++) l_maxH[i] = Math.max(l_maxH[i-1], height[i]);
        for (int i = n-2; i >= 0; i--) r_maxH[i] = Math.max(r_maxH[i+1], height[i]);
        for (int i = 0; i < n; i++) { // 去掉首尾柱子0 & n-1(∵接不了雨水)
            res += Math.max(0, Math.min(l_maxH[i], r_maxH[i]) - height[i]);
        }
        return res;
    }

    // 法3：单调栈 —— 时间O(n), 空间O(n)
    public int trap_3(int[] height) {
        int res = 0;
        int n = height.length;
        if (height == null || n == 0) return res;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // 遍历每一列, ↓维护单调栈（递减,元素为idx）
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop(); // 左右边缘中间【最矮的柱子】
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int width = i - left - 1; // 宽度不包括左(left)/右(i)边缘
                int h = Math.min(height[i], height[left]) - height[top];
                res += width * h;
            }
                stack.push(i);
        }
        return res;
    }

}