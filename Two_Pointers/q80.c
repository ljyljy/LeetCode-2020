#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q80.c, ���q26, q27

// ��1��ͨ�ã��Ƽ��������Ƽ���curCnt <= 2
int removeDuplicates(int* nums, int n) {
    int write = 1, read = 1, curCnt = 1;
    while (read < n) {
        if (nums[read] != nums[write - 1]) {
            nums[write++] = nums[read];
            curCnt = 1;
        }
        else if (curCnt < 2) { // ��ͬ�����ظ���<2
            curCnt++;
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

// ������ ������1������ָ�� - ��ʽ˫ָ��
int removeDuplicates1(int* nums, int n) {
    if (n <= 2) return n;
    int write = 2, read = 2; // nums[0], nums[1]�϶����䣬���write��2��
    while (read < n) {
        if (nums[read] != nums[write - 2]) { // ���ϴ�д�� vs ��ǰread
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

int main() {
    int nums[] = { 1, 1, 1, 2, 2, 3 };
    int n = removeDuplicates(nums, 6);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums[i]);
    }
    printf("\nn=%d\n", n);
    return 0;
}