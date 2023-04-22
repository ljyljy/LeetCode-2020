#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <limits.h>
// #include <string.h>
// #include <stdbool.h>

int main() {
    // Q1: �з��������޷�������ת��
    unsigned int a = 6; // 0x6
    int b = -20; // 0xFFFFFFEC
    int c = a + b; // c = 0xFFFFFFEC + 0x6 = 0xFFFFFFF2
    printf("b = 0x%X\n", b); // 0xFFFFFFEC
    // a+b��ת��Ϊ�޷���������ת�����з�������c����
    printf("a + b = (uint)%u\n", a + b); // 4294967286
    printf("%d\n", c); // -14  (0xFFFFFFF2���Ϊ�з���������������:X.����+1=0x7FFFFFF2����X.����=0x7FFFFFF1����X=(1110)_2=14)

    // Q2. ������չ
    /* ������չ���⡣
    ���޷��ŵ�shortת��Ϊ�з��ŵ�int���ͣ�����չ��Ϊ�޷���������ѭ������չ��.
    ��˴�11111111 11111111��չΪ00000000 00000000 11111111 11111111,���Ϊ65535
    */
    unsigned short i = 65535;
    printf("%d \n", i); // 65535

    /* ������չ���⡣
    -���з��ŵ�shortת��Ϊ�з��ŵ�int���ͣ�����չ��Ϊ�з���������ѭ������λ����չ
    ��˴�11111111 11111111��չΪ11111111 1111111111111111 11111111,���Ϊ-1
    */
    signed short i2 = 65535;
    printf("i2 = 0x%X\n", i2); // 0xFFFFFFFF
    printf("%d \n", i2); // -1 [-X.���� + 1 = 0xFFFFFFFF] ��X.���� = 0xFFFFFFFE ��X = 0x1 �� XΪ���� ��X = -1


    // Q3. �з��������޷�����������
    unsigned int a2 = 20; // 0x14
    int b2 = 13; // 0xD = (1101)_2
    int k = b2 - a2; // k = 0xD - 0x14 = (���) 0xFFFFFFF9, 0x9 = (1001)_2
    unsigned int m = (unsigned int)b2 + a2;
    int m2 = (int)(b2 + a2);
    int m3 = b2 + (int)a2;
    printf("b2 - a2 = 0x%X\n", b2 - a2); // 0xFFFFFFF9 ���Ϊ�з���������������:X.����+1=0x7FFFFFF9����X.����=0x7FFFFFF8����X=(0111)_2=7
    printf("k = %d = 0x%X = (uint)%u\n", k, k, k); // k = -7 = 0xFFFFFFF9 = (uint)4294967289
    printf("(unsigned int)b2 + a2  = %u = 0x%X \n", m, m); // 33��0x21
    printf("(int)(b2+a2)  = %d = 0x%X \n", m2, m2); // 33��0x21
    printf("b2 + (int)a2  = %d = 0x%X \n", m3, m3); // 33��0x21



    static char word[] = 'Turbo\0';
    printf("sizeof(word)=%d \n", sizeof(word));
    return 0;


}