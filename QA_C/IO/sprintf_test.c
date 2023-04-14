#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>


int main()
{
    char str[80];
    sprintf(str, "Pi.val = %f", M_PI);
    puts(str); // puts()�������Զ����ַ���������ϻ��з�
    // Pi.val = 3.141593

    // ���ƣ�q38.c
    printf("----CASE 2: append str by 'realloc & sprintf'-----\n");
    char* str2 = (char*)calloc(3, sizeof(char));
    str2[0] = '2', str2[1] = '1'; // str2: "21"
    str2 = (char*)realloc(str2, (strlen(str2) + 3) * sizeof(char)); // ��չ3���ֽ�,�����Զ���ʼ������
    sprintf(str2 + strlen(str2), "%d%c\0", 3, '4'); // ��str2����׷��"34\0"
    printf("str2: %s \n", str2);
    // str2: 2134
    return(0);
}