#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>


// q90. �Ӽ�II
int** res;
int* n_cols;
bool* used;
int curCnt, curLen, basicSize;

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

void dfs(int* nums, int n, int idx, int* path, bool* used) {
    // if (curLen > n) return; // �ɲ�д��for(i < n)�������鲻Խ��
    res[curCnt] = (int*)calloc(curLen, sizeof(int));
    memcpy(res[curCnt], path, curLen * sizeof(int)); // path��̬�仯����Ҫmemcpy������ֱ��ָ�븳ֵ
    n_cols[curCnt] = curLen;
    curCnt++;

    if (curCnt == basicSize) {
        basicSize *= 2;
        res = (int**)realloc(res, basicSize * sizeof(int*));
        n_cols = (int*)realloc(n_cols, basicSize * sizeof(int));
    }

    for (int i = idx; i < n; i++) {
        // if (used[i]) continue; // ͬһ��֦�£�ͬһ����ȥ�أ�������֣�i��idx����Զ����ͷ����vsȫ���У���
        if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue; // ͬһ���㣬��ֵͬ����ͬ��.ȥ��
        used[i] = true;
        path[curLen++] = nums[i];
        dfs(nums, n, i + 1, path, used);
        used[i] = false;
        --curLen;
    }
}

int** subsetsWithDup(int* nums, int n, int* returnSize, int** returnColumnSizes) {
    qsort(nums, n, sizeof(*nums), cmp);
    curCnt = 0, curLen = 0, basicSize = 8;
    res = (int**)calloc(basicSize, sizeof(int*));
    n_cols = (int*)calloc(basicSize, sizeof(int));
    int* path = (int*)calloc(n, sizeof(int));
    bool* used = (bool*)calloc(n, sizeof(bool));

    dfs(nums, n, 0, path, used);

    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    int nums[] = { 1, 2, 3, 2 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int returnSize;
    int* returnColumnSizes;
    int** res = subsetsWithDup(nums, n, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        printf("[");
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d,", res[i][j]);
        }
        printf("]\n");
    }
}