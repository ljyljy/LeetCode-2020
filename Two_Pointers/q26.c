#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int removeDuplicates(int* nums, int n) {
    if (n <= 1) return n;
    int write = 1, read = 1;// nums[0]�϶����䣬���write��1��
    while (read < n) {
        if (nums[write - 1] != nums[read]) {
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}