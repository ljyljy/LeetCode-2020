// string.c
#include <stdio.h>

int main() {
    const char strA[] = "Hello, geek!"; // 000000000061FDEB
    const char* strB = "Hello, geek!"; // 0000000000404000
    printf("%p\n%p\n", strA, strB);

    // ��չ��read-only string.
    const char strA2[] = "Hello, geek!"; // 000000000061FDDE
    const char* strB2 = "Hello" ", geek!"; // 0000000000404000
    char* strB3 = "Hello, geek!"; // 0000000000404000
    char strD[] = "Hello, geek!"; // 000000000061FDD1
    char strE[] = "Hello" ", geek!"; // ��������ͬ, 000000000061FDC4
    printf("%p %p %p %p %p\n", strA2, strB2, strB3, strD, strE);

    // �����޸� �ַ���ָ��char*��ʧ�ܣ����ַ�����char[]���ɹ���
    // strB2[0] = 'h'; // ʧ�� error: assignment of read-only location '*strB2'
    // printf("%s\n", strB2);
    strD[0] = 'h';
    printf("%s\n", strD); // �ɹ��� hello, geek!


    // �ܽ᣺1��strB��strB2��strB3����char*����������ͬ, ��ַ��Ϊ0000000000404000
    // 2��strA��strA2��strD��strE��������ͬ����ַ��ͬ
    // 3��strA��strA2��strB��strB2��strB3����char*�������޸ģ�����read-only string
    // 4��strD��strE����char[] �ַ����飬���޸�
}