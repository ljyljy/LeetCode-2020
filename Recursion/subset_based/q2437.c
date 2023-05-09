#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q2437. 有效时间的数目

int curCnt, n;

void dfs(char* time, int idx);

bool isValid(char* time);

int countTime(char* time) {
    n = strlen(time); // 5, 形如 hh:mm
    curCnt = 0;
    dfs(time, 0);
    return curCnt;
}

void dfs(char* time, int idx) {
    if (idx >= n && isValid(time)) { // time[idx] == '\0'
        if (strstr(time, "?") == NULL) { // 【坑1，勿漏！】
            // v1: strstr(time, "?") == NULL   // 子串查找
            // v2: strpbrk(time, "?") == NULL  // 组内单char匹配
            // v3(错误): strspn(time, "?") == 0   // 前缀匹配"?"的个数 ← 【WA】: "0?:??"前缀非'?'，也会返回0！
            curCnt++;
        }
        return;
    }
    for (int i = idx; i < n; i++) {
        if (time[i] != '?') { // 跳过！【坑1】
            return dfs(time, i + 1); // 勿漏return。
            // ↑ 当i+1>=n时，会导致dfs出口, 出现带有'?'的原始串！事实上非法！不可算在curCnt内！
        }
        for (char c = '0'; c <= '9'; c++) {
            time[i] = c;
            if (!isValid(time)) {
                time[i] = '?';
                break;
            }
            dfs(time, i + 1);
            time[i] = '?';
        }
    }
}

bool isValid(char* time) {
    // `atoi`、`sscanf` 都可以自动过滤'?'
    int hh = atoi(time); // v1, 'hh'
    // sscanf(time, "%d", &hh); // v2
    int mm = atoi(time + 3); // 'mm'
    // printf("time=%s, hh: mm = %d: %d, (hh < 24 && mm < 60)?: %d\n", time, hh, mm, hh < 24 && mm < 60);
    return hh < 24 && mm < 60;
}

int main() {
    char time[] = "?5:00";
    printf("%d\n", countTime(time)); // 2

    char time2[] = "0?:0?";
    printf("%d\n", countTime(time2)); // 100

    char time3[] = "1?:22";
    printf("%d\n", countTime(time3)); // 10

    char time4[] = "??:??";
    printf("%d\n", countTime(time4)); // 1440
}