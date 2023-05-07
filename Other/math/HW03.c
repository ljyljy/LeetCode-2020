
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define STR_LEN 33

// HW03. ������תʮ����

// �������Ĳ���ת��Ϊ��Ӧ������ԭ��
char* convert(char* binStr)
{
    int n = strlen(binStr);
    int carry = 1;
    char* ans = (char*)calloc(STR_LEN, sizeof(char)); // �����Ĳ��� = ���� + 1
    ans[0] = 0;
    for (int i = n - 1; i >= 1; i--) {
        int curSum = carry;
        if (binStr[i] - '0' == 1) {
            curSum += 0; // ȡ��
        }
        else curSum += 1;
        ans[i] = curSum % 2 + '0';
        carry = curSum / 2; // �����carry������򲻹���
    }
    /**
    for (int i = 0; i < 32; i++) {
        printf("%c,", ans[i]);
    }
    */
    return ans;
}

// δAC! PASS 90%
int BinaryToDecimal_v1(char* binStr)
{
    int n = strlen(binStr);
    int result = 0;
    int sign = binStr[0] == '1' ? -1 : 1;
    if (sign == -1) {
        binStr = convert(binStr); //�򣺸������� = ��Ӧ����.���� + 1 ����v1��
        n = 32; // ת��Ϊ���Ӧ������ԭ�룬��������*sign
    }
    int bit = 1;

    for (int i = n - 1; i >= 0; i--) {
        result += (binStr[i] - '0') * bit;
        bit <<= 1; // *2^k, k = 0, 1, 2, ...
    }

    return sign * result;
}

// δAC! PASS 90%
int BinaryToDecimal(char* binStr)
{
    int n = strlen(binStr);
    int ans = 0;
    for (int i = 0; i < n; i++) {
        ans = ans * 2 + (binStr[i] - '0');
    }

    if (binStr[0] == '1') {
        // printf("negative: offset= 1<<n = %d\n", 1<<n); // ��ȥ 2^n + 1
        ans = ans - (1 << n) + 1;
    }

    return ans;
}


int main(void)
{
    char binaryString[STR_LEN];
    // if (scanf_s("%s", &binaryString, STR_LEN) != 1) {
    if (scanf("%s", &binaryString) != 1) {
        return -1;
    };
    int output = BinaryToDecimal_v1(binaryString);
    int output2 = BinaryToDecimal(binaryString);
    printf("%d, %d", output, output2);
    return 0;
}

/*
00011
3, 3

11111111111111111111111111111111
-1, -1
*/