#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>
#include "uthash.h"

/* ֪ʶ��
1����1��UT_HASH ��ϣ��
  - ����1��ע�⴫���ǡ�һ��ָ�롿��
    - ���TwoSum��Ҫ��ָ��һ����ϣ������������Ϊ��ϣ��
    - ����һ��ָ�봫�Σ������Թ�ϣ���޸ģ��������غ� �޸���Ч
  - �����������[���Ƽ�]��
    - v1 ��Ҫ���Σ�`TwoSum** obj`
    - v2 ʹ��ȫ�ֱ���`g_obj`����������1��ʹ�ã�

  - ����2���������������
    - ������Ŀ������value��[INT_MIN, INT_MAX]�����value �� num�������
    - ��Ҫת����long���ͣ��������
      - `long num2 = (long)value - cur->num; // ����2���������������`

  - ����3����������ȣ������ж�Map.cnt�Ƿ����1

2����2�����ֲ��ң�sort��/ ˫ָ��
  - ��
 */

 // q170. ����֮�� III - ���ݽṹ���
typedef struct {
    // �� ƽ�Ƶ�[0, INT_MAX*2 + 1], ���󣬲����ʣ�ֱ��ʹ��UT_HASH
    // int nums[(long)INT_MAX * 2 + 1];
    int num;
    int cnt;
    UT_hash_handle hh;
} HashMap;

typedef struct {
    HashMap* map;
} TwoSum;


TwoSum* twoSumCreate() {
    TwoSum* obj = (TwoSum*)calloc(1, sizeof(TwoSum));
    obj->map = NULL; // ����ʹ��HASH_ADD_INT����Ԫ�ء�
    return obj;
}

void twoSumAdd(TwoSum* obj, int number) {
    HashMap* pEntry = NULL;
    HASH_FIND_INT(obj->map, &number, pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->num = number;
        pEntry->cnt = 1;
        HASH_ADD_INT(obj->map, num, pEntry);
        // printf("Add: %d, cnt = %d\n", number, pEntry->cnt);
    }
    else {
        pEntry->cnt += 1;
    }
    // long idx = number + (long)INT_MAX;
    // obj->nums[idx] += 1;
}

bool twoSumFind(TwoSum* obj, int value) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, obj->map, cur, tmp) {
        long num2 = (long)value - cur->num;
        HashMap* pEntry = NULL;
        HASH_FIND_INT(obj->map, &num2, pEntry);
        if (pEntry != NULL) {
            // printf("num2=%d, cur->num=%d, pEntry->cnt=%d\n", num2, cur->num, pEntry->cnt);
            // ����1����������ȣ������ж�Map.cnt�Ƿ����1
            if (num2 == cur->num && pEntry->cnt > 1) return true;
            if (num2 != cur->num) return true;
        }
    }
    return false;
}


void twoSumFree(TwoSum* obj) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, obj->map, cur, tmp) {
        HASH_DEL(obj->map, cur);
        free(cur);
    }
}

int main()
{
    TwoSum* obj = twoSumCreate();
    twoSumAdd(obj, 1);
    twoSumAdd(obj, 3);
    twoSumAdd(obj, 5);
    printf("twoSumFind(obj, 4) = %d\n", twoSumFind(obj, 4)); // ���� true
    printf("twoSumFind(obj, 7) = %d\n", twoSumFind(obj, 7)); // ���� false
    printf("twoSumFind(obj, 8) = %d\n", twoSumFind(obj, 8)); // ���� true
    twoSumAdd(obj, 3);
    printf("twoSumFind(obj, 6) = %d\n", twoSumFind(obj, 6)); // ���� true
    twoSumFree(obj);
    return 0;
}