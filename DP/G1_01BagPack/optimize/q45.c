#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
#include <math.h>
#include <limits.h>


// q45. ��Ծ��ϷII
// 2. ̰��v2��������ҳ���λ�ã���
//   - ��̰�ġ���ѡ��������һ��λ����Զ���Ǹ�λ��
int jump4(int* nums, int n) {
    int curPos = n - 1;
    int curJump = 0;
    while (curPos > 0) {
        for (int i = 0; i <= curPos; i++) {
            if (i + nums[i] >= curPos) {
                curPos = i; // ���Ի���·������curPos��ǰ�����
                curJump++;
                break; // ֻ�Ҿ���curPos��Զ�ģ���֤curJump��С
            }
        }
    }
    return curJump;
}


// 2. ̰��v1-2��������ң�д��2��
//   - ��̰�ġ���ѡ��ÿ����Ծ��Զ��λ��
int jump3(int* nums, int n) {
    int curJump = 0;
    int maxPos = 0;
    int endPos = 0;
    // ����[0, n-2]��n-1Ϊ�յ㣬������
    for (int start = 0; start < n - 1; start++) {
        maxPos = fmax(maxPos, start + nums[start]); // ��һ����Զ����maxPos
        if (start == endPos) { // endPos: ������Զ����
            endPos = maxPos; // ̰�ģ�ÿ�ֶ�ѡ����Ծ����Զ��λ��
            curJump++;
        }
    }
    return curJump;
}


// 2. ̰��v1-1��������ң�
//   - ��̰�ġ���ѡ��ÿ����Ծ��Զ��λ��
int jump2(int* nums, int n) {
    int curJump = 0;
    int maxPos = 0;
    int start = 0, end = 1;
    while (end <= n - 1) { // ����һ�������յ�n-1���򷵻�curJump һ������С��Ծ����
        maxPos = 0; // ÿ�����¼��㣡
        while (start < end) { // ����ҿ����������ֿ����ߵ�ÿ����
            maxPos = fmax(maxPos, start + nums[start]); // ��֤ÿһ�ֶ�������Զ�ĵط�
            start++;
        }
        curJump++;
        start = end;
        end = maxPos + 1; // ����ҿ�
    }
    return curJump;
}


// 1. dp[i]��ʾ�����j��[0,i]�������յ�i������С��Ծ��
int jump1(int* nums, int n) {
    int dp[n]; // = {INT_MAX}; // curMinJumps[5]
    for (int i = 0; i < n; i++) dp[i] = INT_MAX;

    dp[0] = 0; // ��0��������idx=0, ��Ծ��=0
    for (int i = 0; i < n; i++) { // ��iΪ�յ�
        for (int j = 0; j < i; j++) { // �����ܹ�����Ŀ��i����potential���j
            // if (nums[i] + i >= n - 1) -- ���ƣ���'n-1'�滻Ϊ��̬�յ�i, ��'i'�滻Ϊ�յ�i���Ʋ��ҵ�Ǳ�����j
            if (j + nums[j] >= i) { // �ҵ��������������j
                dp[i] = fmin(dp[i], dp[j] + 1);
            }
        }
    }
    return dp[n - 1];
}

int main() {
    int nums[] = { 2, 3, 1, 1, 4 };
    int n = sizeof(nums) / sizeof(nums[0]);
    printf("%d\n", jump1(nums, n)); // 2
    printf("%d\n", jump2(nums, n)); // 2
    printf("%d\n", jump3(nums, n)); // 2
    printf("%d\n", jump4(nums, n)); // 2
}