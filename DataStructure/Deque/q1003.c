#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q1003.c

// ��2��ջ���Ƽ���
bool isValid(char* s) {
    int n = strlen(s);
    if (n < 3) return false;
    if (n == 3) return strcmp(s, "abc") == 0;
    // n > 3
    char stk[n];
    int top = 0;
    for (int i = 0; i < n; i++) {
        stk[top++] = s[i]; // ��ջ: ��"a(top-3) b c NULL(top=4)"
        // �� stk[top - 1] == 'c' && stk[top - 2] == 'b' && stk[top - 3] == 'a'
        if (top - 3 >= 0 && strncmp(&stk[top - 3], "abc", 3) == 0) {
            top -= 3; // ��ջ3��Ԫ��
        }
    }
    return top == 0;
}

// ��1������������
const char* pat = "abc";
int cnt[3] = { 0 };

bool findABC(char* s, int start, int end);
bool subStrEquals(char* s, int start, int end, const char* pat);
bool cntABC(int* cnt);

bool isValid_BF(char* s) {
    int n = strlen(s);
    if (n < 3) return false;
    return findABC(s, 0, n - 1);
}


bool findABC(char* s, int start, int end) {
    int len = end - start + 1; // ����ұ�
    if (len < 3) {
        return false;
    }
    else if (len == 3) {
        return subStrEquals(s, start, end, pat);
    }

    // len > 3
    for (int i = start; i <= end; ) { // ����ұ�
        if (subStrEquals(s, i, i + 2, pat)) {
            i = i + 3;
        }
        else {
            if (s[i] != 'a') return false;
            cnt[s[i] - 'a']++; // ----��"a"
            // �󴮱��Ӵ��ָ�����"a'abc'b'abc'c"
            for (int j = i + 1; j <= end; ) {
                // 1) �����"a"����Ӵ�
                while (j <= end && j + 2 <= end && subStrEquals(s, j, j + 2, pat)) {
                    j = j + 3; //  ����Ӵ�'abc'������"a(i)'a(j)bc''a(j+3)bc'bc"
                }
                // printf("1) i=%d, j=%d\n", i, j);
                // 1-1) ��abc��������F
                if (j > end) return false;// 1-1-1: ȱ��"b"��"a'a(j)bc'"��
                // 1-2) �Ӵ�'abc'ƥ����ϣ��ص���"b(j)+�Ӵ�"/"b(j)c"ƥ��
                if (s[j] != 'b') return false; // 1-1-2: �Ӵ�'abc'ƥ��ʧ�ܣ�"a'abc''a(j)c/bc��'bc"
                cnt[s[j] - 'a']++; // ----��"b"
                // printf("2) cnt['%c' - 'a']=%d\n", s[j], cnt[s[j] - 'a']);
                // 2-1) ��"b(j)c"ƥ��
                if (j + 1 <= end && s[j + 1] == 'c') { // ��ƥ����ϣ�"a'abc'b(j)c"
                    // cnt[s[j] - 'a']++; // ----��"c"
                    memset(cnt, 0, sizeof(cnt)); // ����cnt����������
                    i = j + 2; // ����ƥ���
                    break;
                }
                // 2-2) �����"b"����Ӵ�, "ab'a(j)bc''abc'c"
                j += 1;
                while (j <= end && j + 2 <= end && subStrEquals(s, j, j + 2, pat)) {
                    j = j + 3; //  ����Ӵ�'abc'������"ab'abc''abc'c"
                }
                // printf("2-2) i=%d, j=%d\n", i, j);
                // 3-1) ��abc������(ȱ��"c" �� �Ӵ�'abc'ƥ��ʧ��)��F��
                if (j > end) return false; // ȱ��"c"��"ab'a(j)bc''abc'"��
                // 3-2) �Ӵ�'abc'ƥ����ϣ��ص���"c(j)"ƥ��
                if (s[j] != 'c') return false; // �Ӵ�'abc'ƥ��ʧ�ܣ�"ab'abc''a(j)c/bc��'c"
                cnt[s[j] - 'a']++; // ----��"c"
                if (cnt[0] == cnt[1] == cnt[2] == 1) {
                    memset(cnt, 0, sizeof(cnt)); // ����cnt����������
                    i = j + 1; // ����ƥ���
                    break;
                }
                j++;
            }
        }
    }
    return true;
}

// �Ż����滻Ϊstrncmp(src, pat, len=3);
bool subStrEquals(char* s, int start, int end, const char* pat) {
    return strncmp(s + start, pat, 3) == 0;
    // int len1 = end - start + 1; // ����ұ�
    // // int len2 = strlen(pat); // 3
    // if (len1 != 3) return false;

    // for (int i = start; i <= end; i++) { // ����ұ�
    //     if (s[i] != pat[i - start]) {
    //         return false;
    //     }
    // }
    // return true;
}

bool cntABC(int* cnt) { // n = 3
    for (int i = 0; i < 3; i++) {
        if (cnt[i] != 1) return false;
    }
    return true;
}

int main() {
    char* s = "aabcbc";
    bool ans = isValid_BF(s);
    bool ans2 = isValid(s);
    printf("ans = %s\n", ans ? "true" : "false");
    printf("ans2 = %s\n", ans2 ? "true" : "false");

    s = "abcabcababcc";
    ans = isValid_BF(s);
    ans2 = isValid(s);
    printf("ans = %s\n", ans ? "true" : "false");
    printf("ans2 = %s\n", ans2 ? "true" : "false");

    s = "abccba";
    ans = isValid_BF(s);
    ans2 = isValid(s);
    printf("ans = %s\n", ans ? "true" : "false");
    printf("ans2 = %s\n", ans2 ? "true" : "false");

    return 0;
}