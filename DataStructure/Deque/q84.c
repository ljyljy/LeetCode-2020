#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

/*
֪ʶ��

- �����ڱ���ά������ջ
- **���ڱ�**��Ϊ�˱�֤�������Ӷ����Ա����㣬��Ϊ���ڱ������-1�����Ʊ��ܱ�֤���������ǵ����ġ�
  - **���ڱ�**������ǰ��ջ������forѭ���Ｔ��
  - ������ǰ��ջ(`stk[top++] = 0`)�������豣֤��һ�㣺
    - for��r=1��ʼ��������while���豣�� top>1 �������ڱ�һ����ջ��������minVal�������Զ�����ջ
      - ��`while (top > 1 && heights2[PEEK] > heights2[r]) {...}`
- **���ڱ�**��Ϊ�˷�ֹ���������ջ�����޷������㡣��˱���Ҫ�и�С��������ӵ����ڱ�����֤��������ܱ���ջ & ���㣡

*/

// q84.c

#define PEEK stk[top-1]

// �Ƽ�v1�����ڱ�����ǰ��ջ
int largestRectangleArea1(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    memset(stk, 0, sizeof(stk));
    // stk[top++] = 0; // ���ڱ�������ǰ��ջ������forѭ���Ｔ��

    for (int r = 0; r < m; r++) {
        // �������͵�����[r]��ǰ��Ϳ��Լ�����[����ջ?����q42����ˮ�෴��]
        while (top > 0 && heights2[PEEK] > heights2[r]) {
            // �� ���ڵ���ջ�����֮ǰ�����h��Ϊջ��[PEEK]
            int maxH = heights2[PEEK]; // ȡջ��
            --top; // ����ջ
            int curArea = maxH * (r - PEEK - 1); // ���=[PEEK+1, r-1]����Ϊ֮ǰ��ջ�ˣ��������PEEK��+1��
            maxArea = fmax(maxArea, curArea);
        }
        stk[top++] = r;
    }

    return maxArea;
}

/* v2�����ڱ���ǰ��ջ������Ҫ��3����
  - 1) stk[top++] = 0;
  - 2) for �� idx=1 ��ʼ����
  - 3) while (top > 1 && ...)
*/
int largestRectangleArea(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    memset(stk, 0, sizeof(stk));
    stk[top++] = 0; // ���ڱ�(idx=0)��ǰ��ջ�������豣֤ջ��������һ�����ڱ�(top>1)

    for (int r = 1; r < m; r++) {
        // �������͵�����[r]��ǰ��Ϳ��Լ�����[����ջ?����q42����ˮ�෴��]
        while (top > 1 && heights2[PEEK] > heights2[r]) {
            // �� ���ڵ���ջ�����֮ǰ�����h��Ϊջ��[PEEK]
            int maxH = heights2[PEEK]; // ȡջ��
            --top; // ����ջ
            int curArea = maxH * (r - PEEK - 1); // ���=[PEEK+1, r-1]����Ϊ֮ǰ��ջ�ˣ��������PEEK��+1��
            maxArea = fmax(maxArea, curArea);
        }
        stk[top++] = r;
    }

    return maxArea;
}

int main() {
    int heights[] = { 2, 1, 5, 6, 2, 3 };
    int n = sizeof(heights) / sizeof(heights[0]);
    int ans = largestRectangleArea(heights, n);
    printf("%d", ans);
    return 0;
}