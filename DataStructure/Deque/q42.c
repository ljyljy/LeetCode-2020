#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
#include <math.h>
// #include <limits.h>

// ��1��˫ָ��
int trap_v1(int* height, int n) {
    // ���[0,i]/�Ҳ�[j,n)Ŀǰ��ߵ����� ��
    int l_maxH = height[0], r_maxH = height[n - 1];
    int res = 0;

    for (int i = 0, j = n - 1; i <= j; ) {// ����i<j�������©��i==jʱ����
        if (l_maxH < r_maxH) {// д��1����ȡ���Ҳ�������ӵĽϰ��ߣ�
            l_maxH = fmax(l_maxH, height[i]);
            res += l_maxH - height[i++];
        }
        else {
            r_maxH = fmax(r_maxH, height[j]);
            res += r_maxH - height[j--];
        }
    }

    return res;
}

// ��2������ջ
#define PEEK stk[top-1]
int trap(int* height, int n) {
    int* stk = (int*)calloc(n, sizeof(int)), top = 0;
    int res = 0;

    for (int r = 0; r < n; r++) {// ����i<j�������©��i==jʱ����
        while (top > 0 && height[r] > height[PEEK]) {
            int mid = stk[--top]; // ��ȡջ�� & ��ջ
            if (top > 0) { // �Էǿ�
                int l = PEEK;
                int width = r - l - 1;
                int h = fmin(height[r], height[l]) - height[mid];
                res += width * h;
            }
        }
        stk[top++] = r;
    }
    return res;
}

int main() {
    int height[] = { 0,1,0,2,1,0,1,3,2,1,2,1 };
    int n = sizeof(height) / sizeof(height[0]);
    int res = trap(height, n);
    printf("%d\n", res);

    int height2[] = { 4,2,0,3,2,5 };
    n = sizeof(height2) / sizeof(height2[0]);
    res = trap(height2, n);
    printf("%d\n", res);
    return 0;
}