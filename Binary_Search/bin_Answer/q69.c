#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q69.c

// ���֣�
// - ����ʹ�� L + R + 1 >> 1
//   - ��sqrt(8)=2.828->ȡ2��Ҳ���ǵ�mid=2ʱ��mid*mid < x, ��mid������ans.
//     - ��������Ҫ��֤start=mid����߽�������mid������start=mid+1��ֱ������3�ˣ��������ȷ��2.
// - �˷��ȼ�תΪ������ʽ������int�����
int mySqrt1(int x) {
    int start = 1, end = x;
    while (start < end) { // ֻ�� [L, mid-1] [mid, R]
        int mid = start + end + 1 >> 1;
        if (mid == x / mid) {
            return mid;
        }
        else if (mid < x / mid) {
            start = mid;
        }
        else {
            end = mid - 1;
        }
    }
    return start;
}

// ��2��for����
// - �˷��ȼ�תΪ������ʽ������int�����
int mySqrt(int x) {
    int lastAns = 0;
    for (int i = 1; i <= x / i; i++) {
        if (i == x / i) {
            return i;
        }
        else if (i < x / i) {
            lastAns = i;
        }
        else break;
    }
    return lastAns;
}

int main() {
    int x = 8;
    int ans = mySqrt(x);
    printf("%d\n", ans);
    ans = mySqrt(x);
    printf("%d\n", ans);
    return 0;
}