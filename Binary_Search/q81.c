#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q81.c

int binSearch_onceKO(int* nums, int n, int target, int start, int end) {
    while (start < end) { // [L, mid-1] [mid, R]
        int mid = start + end >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        /*
        ͨ���������У�nums[mid] >= target, ����mid������������
        ��������ת���������Ҫ�ҵ�������������顯�����ֵ��������ԣ�
            - �磺��ע[start ~ target ~ mid]�ĵ��������ԣ�����end = mid-1��������[start, mid-1]������
            - ������ѭ����[start]��[mid]ֵ������ȣ�
        */
        // �ֱ������Ұ�Ρ�L/R vs mid���У��ҳ�����target�������������
        if (nums[start] == nums[mid]) {
            start++;
            continue;
        }
        if (nums[start] < nums[mid]) {
            if (nums[start] <= target && target <= nums[mid]) {
                end = mid - 1;
            }
            else start = mid;
        }
        else { //  if (nums[start] > nums[mid]) ˵��start����ת������mid~end����
            // �����ע[mid ~ target ~ end]�ĵ���������
            if (nums[mid] <= target && target <= nums[end]) {
                start = mid;
            }
            else end = mid - 1;
        }
    }
    if (nums[start] == target) return start;
    return -1;
}

bool search(int* nums, int n, int target) {
    int end = n - 1;
    while (end > 0 && nums[end] == nums[0]) end--;
    return binSearch_onceKO(nums, n, target, 0, end) != -1;
}

int main() {
    int nums[] = { 2,5,6,0,0,1,2 };
    int n = sizeof(nums) / sizeof(int);
    int target = 0;
    printf("%d\n", search(nums, n, target));

    target = 3;
    printf("%d\n", search(nums, n, target));
    return 0;
}