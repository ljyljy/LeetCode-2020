#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// ���뽫m��n����arrǰ�棡
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

// ��2������ָ��ĸ�ֵ
void initPtr(int n, int** arr) {
    // int row = sizeof(arr) / sizeof(*arr); // �˻���row=1
    // printf("n=%d, row=%d, col=%d\n", n, row, sizeof(*arr)); // ����col=sizeof(*arr)
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
    const int n = strlen(s); // ������'\0'�ĳ���
    if (n < 2) return s;

    // ��1��dp[n][n] & ��2������ָ��(*dp)[n]
    // http://m.biancheng.net/view/2022.html
    // int (*dp)[n] = malloc(sizeof(bool)*n*n);  // ����ָ�룬��int dp[n][n];
    // initArr(n, dp);

    // ��3������ָ��
    int** dp = (int**)malloc(sizeof(int*) * n); // �״�sizeof(int��*��)��
    for (int i = 0; i < n; i++) {
        dp[i] = (int*)malloc(sizeof(int) * (n));
        // memset(dp[i], 0, sizeof(int) * (n));
        // printf("sizeof(dp[i]) = %d\n", sizeof(dp[i]));
    }
    initPtr(n, dp);

    int maxLen = 1, start = 0; // start���ɶ���Ϊ-1��case:"ac", �����������Ķ���for��

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
    // c��Ƭ�ָ�:
    // ��1)����\0�͵�ַ������ַ��� -- only LC ����AC, IDE�ᱨ��Segmentation fault��
    // printf("maxLen = %d, start = %d\n", maxLen, start);
    // s[start+maxLen] = 0; //'\0';
    // s = &s[start];
    // return s;


    // ��2���ַ�����ȡ ��memcpy������
    // ��memcpy(x,y+3,4);*(x+5)='\0';�������Ͱ��ַ���y�е��±�Ϊ3���ַ���ʼ������4���ַ���������x��Ȼ��*(x+5)='\0';��4���ַ���һ��'\0'�����ַ�����
    char* res = malloc(sizeof(char) * (maxLen + 1)); // ������+1��maxLen��strlen��������'\0'�������������'\0'��λ�ã���
    memcpy(res, s + start, maxLen);
    res[maxLen] = '\0';
    return res;
}

int main() {
    char* str = "babad";
    // str[2] = 0; // �� ����Ϊ"xxx"����ô�������У�
    printf("s = %s\n", str);
    char* ans = longestPalindrome(str);
    // ans[2] = 0; // �� ���ú󷵻ص�ans����ô�����У�
    printf("ans=%s\n", ans);

    str = "accb";
    ans = longestPalindrome(str);
    printf("ans=%s\n", ans);

    str = "ac";
    ans = longestPalindrome(str);
    printf("ans=%s\n", ans);

    return 0;
}