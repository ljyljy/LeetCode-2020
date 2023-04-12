#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// ���Q34
// ��1�����д����ұ߽紦
int searchInsert(int* nums, int n, int target) {
    if (nums[n - 1] < target) return n;
    int start = 0, end = n - 1;
    // ������ nums[left..right] ����ҵ� 1 ������߽硿���ڵ��� target ��Ԫ�ص��±�
    while (start < end) {// [L, mid], [mid+1, R]
        int mid = start + end >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            end = mid;
        }
        else start = mid + 1;
    }
    return start; // ��δ�ҵ�target���򷵻���߽磨�� 1 ������߽硿����target���±꣩
}

int main() {
    int nums[] = { 1,3,5,6 }, target = 7;
    int n = sizeof(nums) / sizeof(*nums);
    int res = searchInsert(nums, n, target);
    printf("%d\n", res); // 4

    target = 2;
    res = searchInsert(nums, n, target);
    printf("%d\n", res); // 1
}