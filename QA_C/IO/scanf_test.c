#include <stdlib.h>
#include <stdio.h>

int main() {
    printf("-------CASE 1: getchar & putchar------\n");
    long input;
    int ch; // ʹ��int����Χ���󣩣�����char��������getchar�ķ���ֵ��
    while (scanf("%ld", &input) != 1) {
        while ((ch = getchar()) != '\n') {
            putchar(ch); // ��ӡ������Ч���ַ�
        }
        printf(" is not a long number.\n");
    }
    printf("valid input = %d\n", input);
    getchar(); // ����һ��scanf֮ǰ����'�Ե�'�����������Ļس�

    printf("-------CASE 2: ------\n");
    int num;
    while (scanf("%d", &num) != 1) {
        scanf("%*s"); // ��ȡ�������������ַ�
        printf("please input an integer, such as 100.\n");
    }
    printf("The num is: %d \n", num);

}
/**
-------CASE 1: getchar & putchar------
low
low is not a long number.
high102 1038
high102 1038 is not a long number.
102832
valid input = 102832
-------CASE 2: ------
siw92 3012
please input an integer, such as 100.
The num is: 3012

 */