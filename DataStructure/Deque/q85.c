#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

// q85.c

#define PEEK stk[top-1]
int getCurArea(int* heights, int n);

int maximalRectangle(char** matrix, int m, int* matrixColSize) {
    int n = matrixColSize[0] + 2; // �����ڱ�
    int heights2[n];
    memset(heights2, 0, sizeof(heights2)); // ������

    heights2[0] = heights2[n - 1] = -1;
    int maxArea = 0;
    for (int i = 0; i < m; i++) {
        // printf("%d, ", heights2[0]);
        for (int j = 1; j < n - 1; j++) {// ���ڱ�ƫ��1λ, ���ڱ�����-1 ������
            if (matrix[i][j - 1] == '0') {
                heights2[j] = 0;
            }
            else {
                heights2[j] += matrix[i][j - 1] - '0';
            }
            // printf("%d, ", heights2[j]);
        }
        // printf("%d \n", heights2[n-1]);
        maxArea = fmax(maxArea, getCurArea(heights2, n));
    }
    return maxArea;
}

// �Ƽ�v1�����ڱ�����ǰ��ջ
int getCurArea(int* heights, int n) {
    int stk[n], top = 0;
    memset(stk, 0, sizeof(stk));

    int maxArea = 0;
    for (int r = 0; r < n; r++) { // �����ڱ� �߶�Ϊ-1
        // ��ǰ[r] < [PEEK]�߶ȣ�[PEEK]�����Լ���maxArea�ˣ���ά������ջ��
        while (top > 0 && heights[PEEK] > heights[r]) {
            int maxH = heights[PEEK];
            --top; // ��ջ����¶��߽�(��ջ��PEEK')
            int width = r - 1 - PEEK; // ���=[PEEK'+1, r-1]
            maxArea = fmax(maxArea, maxH * width);
        }
        stk[top++] = r;
    }
    return maxArea;
}


/* ���ڱ���ǰ��ջ������Ҫ��2����
  - 1) stk[top++] = 0;
  - 2) for �� idx=1 ��ʼ����
  - 3) while (top > 1 && ...)
*/
int getCurArea_v2(int* heights, int n) {
    int stk[n], top = 0;
    memset(stk, 0, sizeof(stk));
    stk[top++] = 0; // v2: ���ڱ�(idx=0)��ǰ��ջ�������豣֤ջ��������һ�����ڱ�(top>1)

    int maxArea = 0;
    for (int r = 1; r < n; r++) { // �����ڱ�Ϊ-1
        // ��ǰ[r] < [PEEK]�߶ȣ�[PEEK]�����Լ���maxArea�ˣ���ά������ջ��
        while (top > 1 && heights[PEEK] > heights[r]) {
            int maxH = heights[PEEK];
            --top; // ��ջ����¶��߽�(��ջ��PEEK')
            int width = r - 1 - PEEK; // ���=[PEEK'+1, r-1]
            maxArea = fmax(maxArea, maxH * width);
            // printf("PEEK'+1=%d, r-1=%d, width=%d, curArea=%d, maxArea=%d\n", PEEK+1, r-1, width, maxH * width, maxArea);
        }
        stk[top++] = r;
    }
    return maxArea;
}

int main(int argc, char const* argv[]) {
    int m = 4;
    int matrixColSize[] = { 5, 5, 5, 5 };
    char* matrix[] = {
        "10100",
        "10111",
        "11111",
        "10010"
    };
    int res = maximalRectangle(matrix, m, matrixColSize);
    printf("res = %d\n", res);
    return 0;
}