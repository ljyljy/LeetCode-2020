#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>
#include <ctype.h>


/** ��Ч����
1) ���ַ����� e/E ���зָ�:
  - ������� e/E �������ԡ��������򡸸����������Ҳ�����ǡ�������
  -  ��������� e/E �����ο����ǡ��������򡸸�������
2) check ���������жϡ��������򡸸���������
  - '+/-' ֻ�ܳ�����ͷ��
  - '.'������һ��
  - ���ٴ���һ������
*/
bool isValid(char* s, int start, int end, bool mustInt);

bool isNumber(char* s) {
    int n = strlen(s);

    // 1. �ҵ�e/E��Ψһ�±꣨����Ψһ��ֱ��F��
    int idx_e = -1; // -1˵��Ϊ����
    for (int i = 0; i < n; i++) {
        if (s[i] == 'e' || s[i] == 'E') {
            if (idx_e == -1) {
                idx_e = i;
            }
            else return false; // e/E��Ψһ���Ƿ�
        }
    }

    bool ans = true;
    if (idx_e != -1) { // ��e�ָ�, [0, idx_e-1] 'e' [idx_e+1, n-1]
        ans &= isValid(s, 0, idx_e - 1, false);
        ans &= isValid(s, idx_e + 1, n - 1, true);
    }
    else {
        ans &= isValid(s, 0, n - 1, false);
    }
    return ans;
}

bool isValid(char* s, int start, int end, bool mustInt) {
    int n = end - start + 1;
    if (s[start] == '+' || s[start] == '-') start++; // ����һ��+/-
    bool hasDot = false, hasNum = false;
    for (int i = start; i <= end; i++) {
        if (s[i] == '.') {
            if (hasDot || mustInt) return false; // ֻ�ܳ���һ��'.'
            hasDot = true;
        }
        else if (isdigit(s[i])) { // <ctype.h>���� s[i] >= '0' && s[i] <= '9'
            hasNum = true;
        }
        else return false; // �����ַ�����Ƿ���ĸ�ȣ�
    }
    return hasNum;
}

int main() {
    char* s = "0";
    bool ans = isNumber(s);
    printf("%d", ans);
    return 0;

    char* s2 = "-3e+7";
    bool ans2 = isNumber(s2);
    printf("%d", ans2);

    char* s3 = "a75";
    bool ans3 = isNumber(s3);
    printf("%d", ans3);
}