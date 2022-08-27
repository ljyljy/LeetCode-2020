#include <stdio.h>
#include <stdlib.h>
#include <string.h>



#define DIM(x) sizeof(x)/sizeof(*x)

// 法1：双指针 O(n) - 推荐
int* findClosestElements1(int* arr, int n, int k, int x, int* returnSize) {
    // qsort(arr, n, sizeof(int));
    int left = 0, right = n - 1;
    int rmCnt = n - k;
    while (rmCnt) {
        if (x - arr[left] > arr[right] - x) {
            left++;
        }
        else { // 左差 <= 右差（相等时，右--，删除右侧元素，优先保留左/小idx）
            right--;
        }
        rmCnt--;
    }
    int* res = malloc(sizeof(int) * k);
    // memset(res, 0, sizeof(int)*k);
    memcpy(res, arr + left, sizeof(int) * k); // 内存拷贝v1
    // for (int i = left; i < left + k; i++) {// 内存拷贝v2
    //     res[i] = arr[i];
    // }
    *returnSize = k;
    return res;
}

// 法2：排序 O(nlogn) -> 如果 a 比 b 更接近 x，那么 a 将排在 b 前面。最后取前k个。
int target;

static inline int cmp1(const void *pa, const void *pb) {
    int a = *(int*)pa, b = *(int*)pb;
    return abs(a - target) != abs(b - target)? \
        abs(a - target) - abs(b - target): \
        a - b;  // 按abs(x-target)升序、数值升序
}

static inline int cmp2(const void *pa, const void *pb) {
    return *(int*)pa - *(int*)pb;  // 按数值升序
}

int* findClosestElements(int* arr, int n, int k, int x, int* returnSize) {
    target = x;
    qsort(arr, n, sizeof(int), cmp1); // 可能得到[3,2,4,1](距离target升序)
    qsort(arr, n, sizeof(int), cmp2);  // 勿忘将最后结果【再升序】！！
    int *res = (int*)malloc(sizeof(int)*k);
    memcpy(res, arr, sizeof(int)*k);
    *returnSize = k;
    return res;
}


int main() {
    int arr[] = { 1,2,3,4,5 };
    int k = 4, x = 3;
    int* returnSize = malloc(sizeof(int) * 1);
    int* res = findClosestElements(arr, DIM(arr), k, x, returnSize);
    // printf("returnSize = %d ", *returnSize);
    for (int i = 0; i < *returnSize; i++) {
        printf("%d ", res[i]);
    }
}