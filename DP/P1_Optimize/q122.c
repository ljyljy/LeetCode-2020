#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>


// ��1��dp
int maxProfit(int* prices, int n) {
    const int MOD = 2;
    int dp[MOD][2]; // ������i����������, ����� -- 0:���� & 1:������
    memset(dp, 0, sizeof(dp));
    dp[0][0] = -prices[0]; // ��0����е�0����Ʊ(����)
    dp[0][1] = 0; // ��0�첻����

    for (int i = 1; i < n; i++) {
        // ��i����У�max(��ǰ�ͳ���, ��ǰһ�첻���С� & ��������(-�ɱ�/����������Ϊ��))
        dp[i % MOD][0] = fmax(dp[(i - 1) % MOD][0], dp[(i - 1) % MOD][1] - prices[i]); // anytime��ֻ�ܳ���һֻ��Ʊ
        // ��i�첻���У�max(��i-1�첻����(����/����), ��i-1������ҽ�������)
        dp[i % MOD][1] = fmax(dp[(i - 1) % MOD][1], dp[(i - 1) % MOD][0] + prices[i]);
    }

    return fmax(dp[(n - 1) % MOD][0], dp[(n - 1) % MOD][1]);
}

int main()
{
    int prices[] = { 7, 1, 5, 3, 6, 4 };
    int n = sizeof(prices) / sizeof(int);
    printf("res = %d\n", maxProfit(prices, n));
    return 0;
}