#include <stdio.h>
#include <stdlib.h>
#include <string.h>



#define DIM(x) sizeof(x)/sizeof(*x)

// ��1��˫ָ�� O(n) - �Ƽ�
int* findClosestElements1(int* arr, int n, int k, int x, int* returnSize) {
    // qsort(arr, n, sizeof(int));
    int left = 0, right = n - 1;
    int rmCnt = n - k;
    while (rmCnt) {
        if (x - arr[left] > arr[right] - x) {
            left++;
        }
        else { // ��� <= �Ҳ���ʱ����--��ɾ���Ҳ�Ԫ�أ����ȱ�����/Сidx��
            right--;
        }
        rmCnt--;
    }
    int* res = malloc(sizeof(int) * k);
    // memset(res, 0, sizeof(int)*k);
    memcpy(res, arr + left, sizeof(int) * k); // �ڴ濽��v1
    // for (int i = left; i < left + k; i++) {// �ڴ濽��v2
    //     res[i] = arr[i];
    // }
    *returnSize = k;
    return res;
}

// ��2������ O(nlogn) -> ��� a �� b ���ӽ� x����ô a ������ b ǰ�档���ȡǰk����
int target;

static inline int cmp1(const void *pa, const void *pb) {
    int a = *(int*)pa, b = *(int*)pb;
    return abs(a - target) != abs(b - target)? \
        abs(a - target) - abs(b - target): \
        a - b;  // ��abs(x-target)������ֵ����
}

static inline int cmp2(const void *pa, const void *pb) {
    return *(int*)pa - *(int*)pb;  // ����ֵ����
}

int* findClosestElements(int* arr, int n, int k, int x, int* returnSize) {
    target = x;
    qsort(arr, n, sizeof(int), cmp1); // ���ܵõ�[3,2,4,1](����target����)
    qsort(arr, n, sizeof(int), cmp2);  // ������������������򡿣���
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