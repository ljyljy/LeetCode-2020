#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q2437. ��Чʱ�����Ŀ

int curCnt, n;

void dfs(char* time, int idx);

bool isValid(char* time);

int countTime(char* time) {
    n = strlen(time); // 5, ���� hh:mm
    curCnt = 0;
    dfs(time, 0);
    return curCnt;
}

void dfs(char* time, int idx) {
    if (idx >= n && isValid(time)) { // time[idx] == '\0'
        if (strstr(time, "?") == NULL) { // ����1����©����
            // v1: strstr(time, "?") == NULL   // �Ӵ�����
            // v2: strpbrk(time, "?") == NULL  // ���ڵ�charƥ��
            // v3(����): strspn(time, "?") == 0   // ǰ׺ƥ��"?"�ĸ��� �� ��WA��: "0?:??"ǰ׺��'?'��Ҳ�᷵��0��
            curCnt++;
        }
        return;
    }
    for (int i = idx; i < n; i++) {
        if (time[i] != '?') { // ����������1��
            return dfs(time, i + 1); // ��©return��
            // �� ��i+1>=nʱ���ᵼ��dfs����, ���ִ���'?'��ԭʼ������ʵ�ϷǷ�����������curCnt�ڣ�
        }
        for (char c = '0'; c <= '9'; c++) {
            time[i] = c;
            if (!isValid(time)) {
                time[i] = '?';
                break;
            }
            dfs(time, i + 1);
            time[i] = '?';
        }
    }
}

bool isValid(char* time) {
    // `atoi`��`sscanf` �������Զ�����'?'
    int hh = atoi(time); // v1, 'hh'
    // sscanf(time, "%d", &hh); // v2
    int mm = atoi(time + 3); // 'mm'
    // printf("time=%s, hh: mm = %d: %d, (hh < 24 && mm < 60)?: %d\n", time, hh, mm, hh < 24 && mm < 60);
    return hh < 24 && mm < 60;
}

int main() {
    char time[] = "?5:00";
    printf("%d\n", countTime(time)); // 2

    char time2[] = "0?:0?";
    printf("%d\n", countTime(time2)); // 100

    char time3[] = "1?:22";
    printf("%d\n", countTime(time3)); // 10

    char time4[] = "??:??";
    printf("%d\n", countTime(time4)); // 1440
}