#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q75.c

// [推荐] 法3：O(n) - 1次遍历（三指针）
void swap(int* a, int* b);

void sortColors_3pointers(int* nums, int n) {
    int i = 0, left = 0, right = n - 1;
    while (i <= right) {
        if (nums[i] == 1) i++;
        else if (nums[i] < 1) {
            swap(&nums[i], &nums[left]);
            left++;
            i++;
        }
        else if (nums[i] > 1) {
            swap(&nums[i], &nums[right]);
            right--; // i不可++，下轮还需继续判断
        }
    }
}

void swap(int* a, int* b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

// [推荐] 法2：O(n) - 快选v1
int quickSelect(int* nums, int start, int end, int pivot);

void sortColors_quickSelect(int* nums, int n) {
    int cnt_0 = quickSelect(nums, 0, n - 1, 1); // [<1, >=1], 即[0, (1 & 2)]
    quickSelect(nums, cnt_0, n - 1, 2); // [0, (<2, >=2)], 即[0, (1, 2)]
}

int quickSelect(int* nums, int start, int end, int pivot) {
    int i = start, j = end;
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] >= pivot) j--;
        if (i <= j) {
            swap(&nums[i], &nums[j]);
            i++;
            j--;
        }
    }
    return i; // 或j+1
}
// 法2-2：O(n) - 快选v2 略。见Java版

// 法1：O(nlogn) - 快排
void quickSort(int* nums, int start, int end);
int partition(int* nums, int start, int end);

void sortColors_quickSort(int* nums, int n) {
    quickSort(nums, 0, n - 1);
}

void quickSort(int* nums, int start, int end) {
    if (start >= end) return;
    int mid = partition(nums, start, end);
    quickSort(nums, start, mid);
    quickSort(nums, mid + 1, end);
}

int partition(int* nums, int start, int end) {
    if (start >= end) return start;
    int pivot = nums[start];
    int i = start + 1, j = end;
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--; // 严格>, 不带=！?
        if (i <= j) {
            swap(&nums[i], &nums[j]);
            i++;
            j--;
        }
    } // 退出后，[pivot, ..., j, i, ...]
    swap(&nums[start], &nums[j]);
    return j; // 【易错】或i-1, 即分区的mid下标（右闭）
}

int main() {
    int nums[] = { 2, 0, 2, 1, 1, 0 };
    int n = sizeof(nums) / sizeof(nums[0]);
    // sortColors_3pointers(nums, n); // 法3 [推荐]
    sortColors_quickSelect(nums, n);  // 法2 [推荐]
    // sortColors_quickSort(nums, n); // 法1
    for (int i = 0; i < n; i++) printf("%d ", nums[i]);
    return 0;
}
