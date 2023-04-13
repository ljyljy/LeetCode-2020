#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// Q38: Count and Say
// https://leetcode.com/problems/count-and-say/
// 思路：模拟
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

char* countAndSay(int n) {
    char* str = (char*)calloc(2, sizeof(char));
    str[0] = '1';

    for (int i = 2; i <= n; ++i) {
        int curLen = strlen(str);
        char* tmp = (char*)calloc(1, sizeof(char));

        int start = 0, end = 0;
        while (end < curLen) {
            while (end < curLen && str[end] == str[start]) {
                ++end;
            }
            tmp = (char*)realloc(tmp, (strlen(tmp) + 2) * sizeof(char)); // 2=cnt+char
            int cnt = end - start;
            sprintf(tmp + strlen(tmp), "%d%c", cnt, str[start]);
            start = end;
        }
        // printf("No.%d, tmp: %s\n", i, tmp);
        free(str);
        str = tmp;
    }
    return str;
}

int main() {
    int n = 5;
    char* str = countAndSay(n);
    printf("res = %s ", str);
    free(str);
    return 0;
}

/* Output:
No.2, tmp: 11
No.3, tmp: 21
No.4, tmp: 1211
No.5, tmp: 111221
res = 111221
*/