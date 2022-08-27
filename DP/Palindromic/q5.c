#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// 必须将m，n放在arr前面！
void initArr(int n, int(*arr)[n]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                arr[i][j] = 1;
            }
            else {
                arr[i][j] = 0;
            }
            // printf("%d ", arr[i][j]);
        }
        // printf("\n");
    }
}

// 法2：二级指针的赋值
void initPtr(int n, int** arr) {
    // int row = sizeof(arr) / sizeof(*arr); // 退化：row=1
    // printf("n=%d, row=%d, col=%d\n", n, row, sizeof(*arr)); // 列数col=sizeof(*arr)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                arr[i][j] = 1;
            }
            else {
                arr[i][j] = 0;
            }
            // printf("%d ", arr[i][j]);
        }
        // printf("\n");
    }
}

char* longestPalindrome(char* s) {
    if (!s) return "";
    const int n = strlen(s); // 不包括'\0'的长度
    if (n < 2) return s;

    // 法1：dp[n][n] & 法2：数组指针(*dp)[n]
    // http://m.biancheng.net/view/2022.html
    // int (*dp)[n] = malloc(sizeof(bool)*n*n);  // 数组指针，或int dp[n][n];
    // initArr(n, dp);

    // 法3：二级指针
    int** dp = (int**)malloc(sizeof(int*) * n); // 易错！sizeof(int【*】)！
    for (int i = 0; i < n; i++) {
        dp[i] = (int*)malloc(sizeof(int) * (n));
        // memset(dp[i], 0, sizeof(int) * (n));
        // printf("sizeof(dp[i]) = %d\n", sizeof(dp[i]));
    }
    initPtr(n, dp);

    int maxLen = 1, start = 0; // start不可定义为-1！case:"ac", 不会进入下面的二层for！

    for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
            if (s[i] == s[j] && (j - i <= 1 || dp[i + 1][j - 1])) {
                dp[i][j] = 1;
                int curLen = j - i + 1;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    start = i;
                }
            }
            // printf ("%d ", dp[i][j]);
        }
        // printf("\n");
    }

    // https://leetcode.cn/problems/longest-palindromic-substring/solution/by-frosty-kapitsatia-7z99/
    // c切片分割:
    // 法1)利用\0和地址来输出字符串 -- only LC 可以AC, IDE会报错Segmentation fault！
    // printf("maxLen = %d, start = %d\n", maxLen, start);
    // s[start+maxLen] = 0; //'\0';
    // s = &s[start];
    // return s;


    // 法2：字符串截取 用memcpy函数。
    // 如memcpy(x,y+3,4);*(x+5)='\0';，这样就把字符串y中的下标为3的字符开始的连续4个字符拷贝到了x，然后*(x+5)='\0';在4个字符后补一个'\0'构成字符串。
    char* res = malloc(sizeof(char) * (maxLen + 1)); // 【勿忘+1！maxLen是strlen（不包括'\0'），还需给最后的'\0'留位置！】
    memcpy(res, s + start, maxLen);
    res[maxLen] = '\0';
    return res;
}

int main() {
    char* str = "babad";
    // str[2] = 0; // × 定义为"xxx"后，这么做不可行！
    printf("s = %s\n", str);
    char* ans = longestPalindrome(str);
    // ans[2] = 0; // √ 调用后返回的ans，这么做可行！
    printf("ans=%s\n", ans);

    str = "accb";
    ans = longestPalindrome(str);
    printf("ans=%s\n", ans);

    str = "ac";
    ans = longestPalindrome(str);
    printf("ans=%s\n", ans);

    return 0;
}