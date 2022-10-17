package Two_Pointers.Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class q904_fruit_into_baskets {
    // 转化：求只包含两种元素的最长连续子序列【滑动窗口】
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int left = 0, right = 0, total = 0;
        Map<Integer, Integer> window = new HashMap<>();
        while (right < n) {
            int cur = fruits[right++];
            window.put(cur, window.getOrDefault(cur, 0)+1);
            while (window.size() > 2) {
                int pop = fruits[left++];
                window.put(pop, window.get(pop) - 1);
                if (window.get(pop) <= 0) {
                    window.remove(pop);
                }
            }
            total = Math.max(total, right - left);
        }
        return total;
    }
}
