#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

/**
 * 1) int转2进制字符串
 *  - itoa(num, buf, 2);
 *  - 自定义函数toBinStr
 *      buf[33] = {0};
 *      buf[i++] = num % 2 + '0', num/=2;
 *      最后整体反转swap！
 */

#define bufSize 33
char chs[bufSize];   // 形参类型uint32最大为32位，因此我这里定义了大小为33的字符串数组存放
int n;

void toBinStr(int num, char* buf, int size);
int dfs(int idx, int prev, bool isLimit, int(*memo)[2]);

int findIntegers(int num) {
    // itoa(num, chs, 2); // v1: 输出二进制数, 从idx=0起
    toBinStr(num, chs, bufSize); // v2

    n = strlen(chs); // char[]也可用strlen
    // printf("num = %d, binStr = %s, n = %d\r\n", num, chs, n);
    int memo[n][2]; // <idx, prev=0/1>

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 2; j++) {
            memo[i][j] = -1;
        }
    }
    return dfs(0, 0, true, memo);
}

int dfs(int idx, int prev, bool isLimit, int(*memo)[2]) {
    if (idx == n) return 1; // cnt++;
    if (!isLimit && memo[idx][prev] != -1) return memo[idx][prev];

    int cnt = 0;
    int top = isLimit ? chs[idx] - '0' : 1; // 二进制只有0~1
    for (int bit = 0; bit <= top; bit++) {
        if (prev == 1 && bit == 1) continue;// 跳过连续的 1
        cnt += dfs(idx + 1, bit, isLimit && bit == top, memo);
    }
    if (!isLimit) memo[idx][prev] = cnt;
    return cnt;
}

// LC无法使用itoa，构造函数
void toBinStr(int num, char* buf, int size) {
    memset(buf, 0, sizeof(char) * size); // char可以直接使用memset赋任意值！
    int idx = 0;
    while (num != 0) {
        buf[idx++] = num % 2 + '0'; // 应该在低位，后续需要反转
        num /= 2;
    }
    for (int i = 0, j = idx - 1; i < j; i++, j--) {
        int tmp = buf[i];
        buf[i] = buf[j];
        buf[j] = tmp;
    }
    // for (int i = 0; i < size; i++) {
    //     printf("%c ", buf[i]);
    // }
}

int main() {
    int num = 5;
    int cnt = findIntegers(num);
    printf("cnt = %d\n", cnt);
}