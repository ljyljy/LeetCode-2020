#include <stdio.h>
#include <stdlib.h>
//#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q66.c

int* plusOne(int* digits, int n, int* returnSize) {
    int* res = (int*)calloc(n + 1, sizeof(int));
    int carry = 0;
    *returnSize = n + 1;
    digits[n - 1] += 1; // 先加1; 再进位↓(for循环)
    for (int i = n; i >= 0 || carry; i--) {
        int sum = i - 1 >= 0 ? digits[i - 1] + carry : carry;
        res[i] = sum % 10;
        carry = sum / 10;
    }

    if (res[0] == 0) {
        // printf("res[n]=%d", res[n]);
        // 将res[0]移除，即将res[1:n]共n个int，前移
        memmove(&res[0], &res[1], n * sizeof(int));
        // memmove(res, res + 1, n * sizeof(int)); // v1
        *returnSize = n;
    }
    return res;
}

int main() {
    int nums[] = { 1, 2, 3 };
    int n = sizeof(nums) / sizeof(int);
    int returnSize = 0;
    int* res = plusOne(nums, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}