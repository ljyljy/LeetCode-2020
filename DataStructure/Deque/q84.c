#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

#define PEEK stk[top-1]

int largestRectangleArea(int* heights, int n) {
    int m = n + 2;
    int heights2[m];
    heights2[0] = heights2[m - 1] = -1;
    int maxArea = 0;
    memcpy(&heights2[1], heights, n * sizeof(int));

    int stk[m], top = 0;
    stk[top++] = -1; // ���ڱ���ջ
    memset(stk, 0, sizeof(stk));
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

int main() {
    int heights[] = { 2, 1, 5, 6, 2, 3 };
    int n = sizeof(heights) / sizeof(heights[0]);
    int ans = largestRectangleArea(heights, n);
    printf("%d", ans);
    return 0;
}