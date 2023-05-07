#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q1010.c 数组代替哈希，类比两数之和


// 法2：(time[i] + time[j]) % 60 == 0 等价于 [i] % 60 + [j] % 60 == 0（类比两数之和）
int numPairsDivisibleBy60(int* time, int n) {
    int mods[61]; // 数组代替哈希，idx=time[i]%60的值，类比两数之和
    memset(mods, 0, sizeof(mods));
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        int mod = time[i] % 60;
        cnt += mods[(60 - mod) % 60]; // 勿忘%60（边界特判）：避免mod=0时, cnt计数找到mods[60]
        mods[mod]++; // 该句放在cnt更新之后，否则：当60-mod == mod, 会导致cnt加两次
    }
    return cnt;
}

// 法1：暴力（TLE）
int numPairsDivisibleBy60_BF(int* time, int n) {
    int cnt = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if ((time[i] + time[j]) % 60 == 0) {
                cnt++;
            }
        }
    }
    return cnt;
}

int main() {
    int time[] = { 30,20,150,100,40 };
    int n = sizeof(time) / sizeof(time[0]);
    int ans = numPairsDivisibleBy60(time, n);
    printf("%d\n", ans);

    int time2[] = { 60,60,60 };
    int n2 = sizeof(time2) / sizeof(time2[0]);
    int ans2 = numPairsDivisibleBy60(time2, n2);
    printf("%d\n", ans2);
    return 0;
}