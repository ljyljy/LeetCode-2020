#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

static int g_val;
int val;
int val2;

int func2() {
    static int val = 10; // ������ʼ����̬�ֲ���������Ϊ���ֵ����ȫ�ֱ���val����ͬһ��
    printf("func2_val=%d, g_val=%d\n", val, g_val); // 10, 0
    printf("&func2_val=%d, &g_val=%d\n", &val, &g_val); // 4206608, 4223024

    // extern int val2; // ����������������ָ�����ļ��е�ȫ�ֱ���val2��
    val2++;
    printf("func2_val2=%d, &val2=%d\n", val2, &val2); // 1, 4225396

    { // �������
        // extern int val2; // ����������������ָ�����ļ��е�ȫ�ֱ���val2��
        val2++;
        printf("func2_val2=%d, &val2=%d\n", val2, &val2); // 2, 4225396
    }
}

int main() {
    printf("&func2()=%p\n", func2()); // 000000000000001C
    printf("val=%d\n", val); // 0
}