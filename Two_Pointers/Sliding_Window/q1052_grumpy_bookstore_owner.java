package Two_Pointers.Sliding_Window;

public class q1052_grumpy_bookstore_owner {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int curSum = 0, n = customers.length;
        for (int i = 0; i < n; ++i) {
            if (grumpy[i] == 0) { // 老板不生气
                curSum += customers[i]; // 顾客满意数量
            }
        }
        int left = 0, right = 0, maxSum = curSum;
        while (right < n) {
            if (grumpy[right] == 1) {// 老板生气 -> 不生气
                curSum += customers[right];
            }
            ++right;
            while (right - left >= minutes) { // 窗口缩小
                maxSum = Math.max(maxSum, curSum);
                if (grumpy[left] == 1) {// 老板生气 -> 弹出窗口
                    curSum -= customers[left];
                }
                ++left;
            }

        }
        return maxSum;
    }
}
