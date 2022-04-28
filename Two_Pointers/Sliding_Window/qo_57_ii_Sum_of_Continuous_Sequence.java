package Two_Pointers.Sliding_Window;

import java.util.ArrayList;
import java.util.List;

public class qo_57_ii_Sum_of_Continuous_Sequence {
    public int[][] findContinuousSequence(int target) {
        int left = 1, right = 1, curSum = 0;
        // List<List<Integer>> res = new ArrayList<>();
        List<int[]> res = new ArrayList<>();

        while (right < target) {
            curSum += right++;
            while (curSum > target) {
                curSum -= left++;
            }
            if (curSum == target) {
                int[] level = new int[right-left];
                for (int i = left; i < right; i++) { // 左闭右开
                    level[i-left] = i;
                }
                res.add(level);
            }
        }
        return res.toArray(new int[res.size()][]); // ?【List<int[]> 转二维数组int[][]】
    }
}
