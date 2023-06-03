#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// 法1：推荐，贪心
int maxProfit1(int* prices, int n) {
    int curMin = prices[0];
    int curProfit = INT_MIN;
    for (int i = 0; i < n; i++) {
        curMin = fmin(curMin, prices[i]);
        curProfit = fmax(curProfit, prices[i] - curMin);
    }
    return curProfit;
}

// 法2：dp
int maxProfit(int* prices, int n) {
    const int MOD = 2;
    int dp[MOD][2]; // 截至第i天的最大利润, 分情况 -- 0:持有 & 1:不持有
    memset(dp, 0, sizeof(dp));
    dp[0][0] = -prices[0]; // 第0天持有第0个股票(买入)
    dp[0][1] = 0; // 第0天不持有

    for (int i = 1; i < n; i++) {
        // 第i天持有：max(先前就持有, 今天买入(-成本/付出，利润为负))   ↓ 与前一天不持有无关(只可以一次买入，dp[(i-1) % MOD][1]可能是前一天卖出的利润，不为0！)
        dp[i % MOD][0] = fmax(dp[(i - 1) % MOD][0], /* dp[(i-1) % MOD][1] */ -prices[i]); // 只可能买入一次
        // 第i天不持有：max(第i-1天不持有(卖出/利润), 第i-1天持有且今天卖出)
        dp[i % MOD][1] = fmax(dp[(i - 1) % MOD][1], dp[(i - 1) % MOD][0] + prices[i]); // 只可能卖出一次
    }

    return fmax(dp[(n - 1) % MOD][0], dp[(n - 1) % MOD][1]);
}


int main()
{
    int prices[] = { 7, 1, 5, 3, 6, 4 };
    int n = sizeof(prices) / sizeof(int);
    printf("res1 = %d\n", maxProfit(prices, n));
    printf("res2 = %d\n", maxProfit1(prices, n));
    return 0;
}