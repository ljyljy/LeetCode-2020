#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q1010.c ��������ϣ���������֮��


// ��2��(time[i] + time[j]) % 60 == 0 �ȼ��� [i] % 60 + [j] % 60 == 0���������֮�ͣ�
int numPairsDivisibleBy60(int* time, int n) {
    int mods[61]; // ��������ϣ��idx=time[i]%60��ֵ���������֮��
    memset(mods, 0, sizeof(mods));
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        int mod = time[i] % 60;
        cnt += mods[(60 - mod) % 60]; // ����%60���߽����У�������mod=0ʱ, cnt�����ҵ�mods[60]
        mods[mod]++; // �þ����cnt����֮�󣬷��򣺵�60-mod == mod, �ᵼ��cnt������
    }
    return cnt;
}

// ��1��������TLE��
int numPairsDivisibleBy60_BF(int* time, int n) {
    int cnt = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if ((time[i] + time[j]) % 60 == 0) {
                cnt++;
            }
        }
    }
    return cnt;
}

int main() {
    int time[] = { 30,20,150,100,40 };
    int n = sizeof(time) / sizeof(time[0]);
    int ans = numPairsDivisibleBy60(time, n);
    printf("%d\n", ans);

    int time2[] = { 60,60,60 };
    int n2 = sizeof(time2) / sizeof(time2[0]);
    int ans2 = numPairsDivisibleBy60(time2, n2);
    printf("%d\n", ans2);
    return 0;
}