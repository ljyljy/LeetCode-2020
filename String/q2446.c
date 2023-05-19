#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q2446.

// ��1��ʱ��תΪ���������ȴ�С
int getTimeInt(char* time);

bool haveConflict_V1(char** event1, int event1Size, char** event2, int event2Size) {
    int startTime1 = getTimeInt(event1[0]);
    int endTime1 = getTimeInt(event1[1]);
    // printf("%d:%d, %d:%d\n", e1_0_hh, e1_0_mm, e1_1_hh, e1_1_mm);

    int startTime2 = getTimeInt(event2[0]);
    int endTime2 = getTimeInt(event2[1]);
    // ���á��غϵ�����������ж�
    // if ((startTime1 <= startTime2 && startTime2 <= endTime1) ||
    //     (startTime2 <= startTime1 && startTime1 <= endTime2)) {
    //     return true;
    // }
    // return false;

    // ���á����غϵ�����������ж�
    return !(endTime1 < startTime2 || endTime2 < startTime1); // ���غϽ�2���Ǹ�������ų���
}

int getTimeInt(char* time) {
    int hh = atoi(time), mm = atoi(time + 3); // "hh:mm"��mmλ��":"/idx=2֮��
    return hh * 60 + mm;
}

// ��2��ֱ��strcmp
bool haveConflict(char** event1, int event1Size, char** event2, int event2Size) {
    return !(strcmp(event1[1], event2[0]) < 0 || strcmp(event2[1], event1[0]) < 0);
}

int main()
{
    char* event1[] = { "12:30", "14:30" };
    char* event2[] = { "13:00", "15:00" };
    printf("%d\n", haveConflict(event1, sizeof(event1) / sizeof(event1[0]),
        event2, sizeof(event2) / sizeof(event2[0]))); // true
    return 0;
}