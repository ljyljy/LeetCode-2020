#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

void reverse(char* s, int start, int end) {
    while (start < end) {
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;
        start++; end--;
    }
}

// 双指针
char* reverseWords(char* s) {
    int n = strlen(s);
    int fast = 0, slow = 0; // 快/读，慢/写
    while (s[fast] == ' ') fast++; // 处理前导' '
    while (fast < n - 1) { // 开始快读&慢写
        if (s[fast] == ' ' && s[fast + 1] == ' ') {
            fast++; // 注意：遇到多个' '，只写一次' '
        }
        else { // 若只有单个' '，则保留
            s[slow++] = s[fast++];
        }
    }
    // 至此fast == n-1，slow处【可能】为末尾（[n-1]是否为' '?）
    if (s[n - 1] != ' ') {
        s[slow++] = s[n - 1];
    }
    s[slow] = '\0'; // slow：收尾

    reverse(s, 0, slow - 1); // 1. 整体反转
    // 2. 每个单词需要再颠倒回来
    for (int i = 0; i < slow; i++) {
        int j = i; // 翻转s[i, j-1]
        while (j < slow && s[j] != ' ') j++; // j处为' '
        reverse(s, i, j - 1);
        i = j;
    }
    return s;
}

int main() {
    char s[] = "     a good   example  "; // 不可定义为char* s = "xxx"(字符串常量)
    printf("%s\n", reverseWords(s));
    return 0;
}