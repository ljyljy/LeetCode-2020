
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>


// q91_1. ���뷽��
/* ֪ʶ��
1) dfs+memo����������ϣ
1) dfs+memo����������ϣ
2) �ַ���תint(����)��
- `atoi(str + idx1)`���޷�ֱ��ָ��str��ȡ�ĳ���idx2����ͨ��`strncpy`��[����]
  - ```C
    bool isValid(char* s, int idx1, int idx2) {
        int curLen = idx2 - idx1 + 1;
        char* curNum = (char*)calloc(curLen + 1, sizeof(char)); // +1: "\0"
        strncpy(curNum, s + idx1, curLen);
        int num = atoi(curNum);
        return 0 < num && num <= 26;
    }
    ```

- `sscanf(str, "%d", &num); `��'����'֧��**��ȡstrָ������** [��]
  - ����**��**֧��"**<font color='red'>`%*d`</font>**"��ʽ������ǰָ��char arr[]���滻sscanf�Ĳ���'format'
  - ```C
    char format[] = "%*d";
    format[1] = len + '0';
    sscanf(s + idx1, format, &num); // '*':ָ�������num���ַ����Ϊlen
    ```

- ���charת��Ϊint��Ȼ��������Ϸ��� / `num*10 + ch`
3) �����ַ���תint�����char���(v2)
*/
int curCnt = 0;
int dfs(char* s, int idx, int* memo);
bool isValid_v1(char* s, int idx1, int idx2);
bool isValid_v2(char* s, int idx1, int idx2);
bool isValid_v3(char* s, int idx1, int idx2);

int numDecodings(char* s) {
    int memo[101] = { 0 }; // ���⣺1 <= s.length <= 100����������ϣ
    curCnt = 0; // ����,��91_2.c
    return dfs(s, 0, memo);
}

int dfs(char* s, int idx, int* memo) {
    int n = strlen(s);
    if (memo[idx] != 0) return memo[idx];
    if (idx == n) {
        memo[idx] = ++curCnt; // ��v2����Ϊ1������1�������¼curCnt��
        return memo[idx];
    }

    for (int i = idx; i < n; i++) {
        if (!isValid_v3(s, idx, i)) continue;
        memo[idx] += dfs(s, i + 1, memo);
    }
    return memo[idx];
}

// s[idx1, idx2]�Ƿ�Ϸ�
bool isValid_v3(char* s, int idx1, int idx2) {
    if (s[idx1] == '0') return false; // ǰ��0�����Ϸ�

    int curLen = idx2 - idx1 + 1;
    char* curNum = (char*)calloc(curLen + 1, sizeof(char)); // +1: "\0"
    strncpy(curNum, s + idx1, curLen);
    int num = atoi(curNum);
    return 0 < num && num <= 26;
}

// s[idx1, idx2]�Ƿ�Ϸ�
bool isValid_v2(char* s, int idx1, int idx2) {
    int len = idx2 - idx1 + 1;
    if (len > 2) return false;
    if (s[idx1] == '0') return false; // ǰ��0�����Ϸ�
    bool res = true;
    if (len == 1) {// ���⣺sֻ�������֣�������������
        res &= s[idx1] != '0';
    }
    else {
        res = (s[idx1] == '2' && s[idx2] <= '6') || s[idx1] == '1';
    }
    return res;
}

// s[idx1, idx2]�Ƿ�Ϸ�
bool isValid_v1(char* s, int idx1, int idx2) {
    int len = idx2 - idx1 + 1;
    if (len > 2) return false;
    if (s[idx1] == '0') return false; // ǰ��0�����Ϸ�
    // ��1����������s[idx1, idx2]תΪ����int������[1,26]
    int num = 0;
    // sscanf��֧��"%*d"��ʽ������ǰָ��char arr[]���滻sscanf�Ĳ���'format'
    char format[] = "%*d";
    format[1] = len + '0';
    sscanf(s + idx1, format, &num); // '*':ָ�������num���ַ����Ϊlen
    // printf("len=%d, num=%*d\n", len, len, num);
    return 0 <= num && num <= 26;
}

int main()
{
    char s[] = "226";
    int res = numDecodings(s);
    printf("%d\n", res); // 3

    char s2[] = "27";
    int res2 = numDecodings(s2);
    printf("%d\n", res2); // 1
    return 0;
}