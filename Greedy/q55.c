#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q55.c�����q45.c


// 1-2. ̰�ģ�������ң�д��2��
//   - ��̰�ġ���ѡ��ÿ����Ծ��Զ��λ�ã��ܷ�����n-1 && ÿ��maxPos�Ƿ��ϸ����
bool canJump2(int* nums, int n) {
    int start = 0, end = 0; // ����ұ�
    int maxPos = 0;
    // ����[0, n-2]��n-1Ϊ�յ㣬������
    for (start = 0; start < n - 1; start++) {
        maxPos = fmax(maxPos, start + nums[start]); // ��һ����Զ���루��գ�
        if (maxPos <= start) {
            return false; // ��: [0,2,3]
        } // �� ע�ⲻ��ÿ���㶼һ���ܴﵽ����Ƚϡ���һ��maxPos�Ƿ�������ӡ��ˣ�
        if (start == end) { // endPos: ������Զ����
            end = maxPos; // ����Ϊ��һ����Զ����
        }
    }
    return maxPos >= n - 1;
}


// 1. ̰�ģ�������ң�д��1��
//   - ��̰�ġ���ѡ��ÿ����Ծ��Զ��λ�ã��ܷ�����n-1 && ÿ��maxPos�Ƿ��ϸ����
bool canJump1(int* nums, int n) {
    int start = 0, end = 1;
    int maxPos = 0, lastMaxPos = 0;
    for (start = 0; start < n; ) { // ��ʼ���[0, n-1] ����
        maxPos = 0;
        while (start < n && start < end) {
            maxPos = fmax(maxPos, start + nums[start]); // ��һ����Զ���루��գ�
            start++;
        }
        if (maxPos == lastMaxPos && maxPos < n - 1) {
            return false; // ��������maxPosû��������������Զ�ﲻ��ĩβn-1��
        }
        // printf("start=%d, end=%d, maxPos=%d\n", start, end, maxPos);
        lastMaxPos = maxPos;
        start = end;
        end = maxPos + 1; // ���״��ҿ���
    }
    return maxPos >= n - 1;
}




// 1-2. ̰�ģ�������ң�д��1��
//   - ��̰�ġ���ѡ��ÿ�־��뱾�����curPos��Զ/��ӽ�0��λ�ã��ܷ�����0 && ÿ��curPos�Ƿ��ϸ�ݼ�
bool canJump(int* nums, int n) {
    int curPos = n - 1, lastPos = n - 1;
    int cnt = n; // ����curPos����һֱ>0�����ѭ����������������n��
    // �յ�curPos�����������㣬���curPos�����԰���������0
    while (curPos > 0 && cnt-- > 0) {
        for (int j = 0; j < curPos; j++) {
            if (j + nums[j] >= curPos) {
                lastPos = curPos;
                curPos = j; // ̰�ĵ���������curPos����Զ���j
                // printf("lastPos = %d, curPos=%d\n", lastPos, curPos);
                if (curPos == 0) return true; // ���Դ�0�������н�
                break;
            }
            // if (j == curPos - 1 && j + nums[j] < curPos) return false; // j��������������ǰ��curPosδ����0������δ�ҵ���������curPos����㡣�޽⡣
        }
        // ���޽⣬����������if
        if (lastPos == curPos && curPos > 0) {
            return false; // curPos���ٵݼ� �� �޷��ߵ�0.
        }
    }
    return curPos <= 0;
}

int main() {
    int nums[] = { 2,3,1,1,4 };
    int n = sizeof(nums) / sizeof(int);
    printf("%d\n", canJump(nums, n)); // true
    printf("%d\n", canJump1(nums, n)); // true
    printf("%d\n", canJump2(nums, n)); // true

    int nums2[] = { 3,2,1,0,4 };
    int n2 = sizeof(nums2) / sizeof(int);
    printf("%d\n", canJump(nums2, n2)); // false
    printf("%d\n", canJump1(nums2, n2)); // false
    printf("%d\n", canJump2(nums2, n2)); // false

    int nums3[] = { 0,2,3 };
    int n3 = sizeof(nums3) / sizeof(int);
    printf("%d\n", canJump(nums3, n3)); // false
    printf("%d\n", canJump1(nums3, n3)); // false
    printf("%d\n", canJump2(nums3, n3)); // false

    int nums4[] = { 2,0,0 };
    int n4 = sizeof(nums4) / sizeof(int);
    printf("%d\n", canJump(nums4, n4)); // true
    printf("%d\n", canJump1(nums4, n4)); // true
    printf("%d\n", canJump2(nums4, n4)); // true

    int nums5[] = { 2,0,1,0,1 };
    int n5 = sizeof(nums5) / sizeof(int);
    printf("%d\n", canJump(nums5, n5)); // false
    printf("%d\n", canJump1(nums5, n5)); // false
    printf("%d\n", canJump2(nums5, n5)); // false

    int nums6[] = { 2,0,1,0,4 };
    int n6 = sizeof(nums6) / sizeof(int);
    printf("%d\n", canJump(nums6, n6)); // false
    printf("%d\n", canJump1(nums6, n6)); // false
    printf("%d\n", canJump2(nums6, n6)); // false

    return 0;
}