package Array.prefixSum;

public class q1744_eat_favorite_candy_on_favorite_day {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        long[] preSum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + candiesCount[i-1]; // 对应第(i-1)类糖的前缀和
        }
        int nq = queries.length;
        boolean[] ans = new boolean[nq];
        for (int i = 0; i < nq; i++) {
            int fType = queries[i][0]; long fDay = queries[i][1], dCap = queries[i][2];
            long eat_min = 1*(fDay+1), eat_max = dCap*(fDay+1); // fDay天内，可吃糖果数[eat_min, eat_max]
            long ate_min = preSum[fType]+1, ate_max = preSum[fType+1]; // 前提：若要在fDay天吃到fType的糖，至少先吃的糖果数
            ans[i] = !(ate_min > eat_max || eat_min > ate_max); // 没有交集：min > max，再取反
        }
        return ans;
    }
}
