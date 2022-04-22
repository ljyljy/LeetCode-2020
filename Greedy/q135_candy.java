package Greedy;

import java.util.Arrays;

public class q135_candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int res = 0;
        int[] candies = new int[n];
        Arrays.fill(candies, 1); // 至少1颗
        // 对candies[i]的左右两侧(i±1)分别遍历，每次保证单侧符合要求
        // 1.从前向后，保证单边-i大者=左侧小+1
        for (int i = 1; i < n; i++) { // ?【向后遍历，往前比】
            if (ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1; // 累积迭代的过程，需要正序遍历（i依赖i-1）
        }
        // 2.从后向前，保证单边-左侧i大者=max(old值，右侧小+1)
        for (int i = n-2; i >= 0; i--) { // ?【向前遍历，往后比】
            // i依赖[i+1]，需要逆序遍历(否则导致i++后，candies[i+1]又被加1，与candies[i]相等了); 取max：下界临界值
            if (ratings[i] > ratings[i+1])
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
        }
        return Arrays.stream(candies).sum();
    }


    // 写法2
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int res = 0;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = candies[i+1] + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = Math.max(candies[i], candies[i-1] + 1);
            }
        }
        return Arrays.stream(candies).sum();
    }
}
