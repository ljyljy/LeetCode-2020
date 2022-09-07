
#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

#define DIM(x) sizeof(x) / sizeof(*x)

// v1：快排
void quickSort(int* nums, int start, int end);
int partition(int* nums, int start, int end);
void swap(int* nums, int i, int j);

int* sortArray_quickSort(int* nums, int n, int* returnSize) {
    *returnSize = n;
    quickSort(nums, 0, n - 1);
    return nums;
}

void quickSort(int* nums, int start, int end) {
    if (start >= end) return;
    int mid = partition(nums, start, end);
    quickSort(nums, start, mid);
    quickSort(nums, mid + 1, end);
}

int partition(int* nums, int start, int end) {
    if (start >= end) return start;
    int i = start + 1, j = end, n = end - start + 1;

    // 【随机选pivot（否则TLE！！！）】，再与start交换，后面与模板一致！
    int x = rand() % n + start;
    swap(nums, start, x);
    int pivot = nums[start];

    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && pivot < nums[j]) j--;
        if (i <= j) {
            swap(nums, i, j);
            i++; j--;
        }
    }
    swap(nums, start, j);
    return j;
}

void swap(int* nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}

void FREE(void* p) {
    free(p);
    p = NULL;
}


// v2: 归并
void merge(int* nums, int start, int mid, int end);
void mergeSort(int* nums, int start, int end);

int* sortArray(int* nums, int n, int* returnSize) {
    *returnSize = n;
    mergeSort(nums, 0, n - 1);
    return nums;
}

void mergeSort(int* nums, int start, int end) {
    if (start >= end) return;
    int mid = start + end >> 1;
    mergeSort(nums, start, mid);
    mergeSort(nums, mid + 1, end);
    merge(nums, start, mid, end);
}

void merge(int* nums, int start, int mid, int end) {
    int i = start, j = mid + 1, n = end - start + 1;
    int tmp[n], k = 0;
    while (i <= mid && j <= end) {
        if (nums[i] < nums[j]) {
            tmp[k++] = nums[i++];
        }
        else {
            tmp[k++] = nums[j++];
        }
    }
    while (i <= mid) tmp[k++] = nums[i++];
    while (j <= end) tmp[k++] = nums[j++];

    memcpy(nums + start, tmp, sizeof(tmp));

    // for (int kk = 0; kk < n; kk++) {
    //     nums[start + kk] = tmp[kk];
    // }
}

int main() {
    int nums[] = { 5,1,2,0,0,0 };
    int* returnSize = malloc(sizeof(int));
    int* arr = sortArray(nums, DIM(nums), returnSize);
    for (int i = 0; i < *returnSize; i++) {
        printf("%d ", arr[i]);
    }
    FREE(returnSize);
}
