#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int removeElement(int* nums, int n, int val){
    if (nums == NULL || n == 0) return 0;
    int write = 0, read = 0;
    while (read < n) {
        if (nums[read] != val) {
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

int removeElement1(int* nums, int n, int val) {
    if (nums == NULL || n == 0) return 0;
    int read = 0, write = 0;
    for (read = 0; read < n; read++) {
        if (nums[read] != val) {
            nums[write++] = nums[read];
        }
    }
    return write;
}