#include <stdio.h>
#include <stdlib.h>

# define DIM(x) sizeof(x)/sizeof(*x)

int lowbit(int x) {
    return x & (-x);
}

// 类比q260
int* missingTwo(int* nums, int n, int* returnSize) {
    int xorSum = 0;
    for (int i = 0; i < n; i++) {
        xorSum ^= nums[i];
    }
    for (int i = 1; i <= n + 2; i++) {
        xorSum ^= i;
    }

    // xorSum = 1~N间，消失俩数的xor; 其余数成对异或=0（与q260同）
    int num1 = 0;
    int diffNum = lowbit(xorSum);
    for (int i = 0; i < n; i++) {
        if ((nums[i] & diffNum) != 0) {
            num1 ^= nums[i];
        } // else num2 ^= nums[i];
    }
    for (int i = 1; i <= n + 2; i++) {
        if ((i & diffNum) != 0) {
            num1 ^= i;
        } // else num2 ^= i;
    }

    int* res = (int*)calloc(2, sizeof(int));
    res[0] = num1, res[1] = xorSum ^ num1;
    *returnSize = 2;
    return res;
}


int main() {
    int nums[] = { 1, 2, 3, 4, 6, 7, 9, 10 };
    int returnSize = 0;
    int* res = missingTwo(nums, DIM(nums), &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\n");

}