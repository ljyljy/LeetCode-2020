#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q69.c

// 二分：
// - 必须使用 L + R + 1 >> 1
//   - 如sqrt(8)=2.828->取2，也就是当mid=2时，mid*mid < x, 但mid可能是ans.
//     - 即：必须要保证start=mid（左边界必须包括mid），若start=mid+1就直接搜索3了，错过了正确答案2.
// - 乘法等价转为除法公式，避免int溢出！
int mySqrt1(int x) {
    int start = 1, end = x;
    while (start < end) { // 只能 [L, mid-1] [mid, R]
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

// 法2：for遍历
// - 乘法等价转为除法公式，避免int溢出！
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