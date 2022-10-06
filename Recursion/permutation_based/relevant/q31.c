#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(x) sizeof(x)/sizeof(*x)

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

void swap(int* a, int* b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void nextPermutation(int* nums, int n) {
    for (int i = n - 1; i - 1 >= 0; i--) {
        if (nums[i] > nums[i - 1]) {
            // Arrays.sort(nums, i, n); // …˝–Ú≈≈¡–nums[i:n-1]
            qsort(nums + i, n - i, sizeof(int), cmp);
            for (int j = i; j < n; j++) {
                if (nums[j] > nums[i - 1]) {
                    swap(&nums[j], &nums[i - 1]);
                    return;
                }
            }
        }
    }
    qsort(nums, n, sizeof(int), cmp);
    return;
}

