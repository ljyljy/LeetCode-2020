
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <math.h>
#include <stdbool.h>


// q91_2. ���뷽��
/* ֪ʶ��
�ԡ�
*/
// int curCnt = 0;
int dfs(char* s, int idx, int* memo);
bool isValid(char* s, int idx1, int idx2);

int numDecodings(char* s) {
    int memo[101] = { 0 }; // ���⣺1 <= s.length <= 100����������ϣ
    // curCnt = 0;
    return dfs(s, 0, memo);
}

int dfs(char* s, int idx, int* memo) {
    int n = strlen(s);
    if (memo[idx] != 0) return memo[idx];
    if (idx == n) {
        memo[idx] = 1;
        return 1; // �ߵ��ף�����1���ϲ�Ὣ����������
    }
    if (s[idx] == '0') return 0; // ���ټ��1��ǰ��0�����Ϸ�

    // ���ټ��2��forѭ���У�ֻ����idx��idx+1��ʹ��fmin
    for (int i = idx; i < fmin(n, idx + 2); i++) {
        if (!isValid(s, idx, i)) continue;
        memo[idx] += dfs(s, i + 1, memo);
    }
    return memo[idx];
}

// s[idx1, idx2]�Ƿ�Ϸ�
bool isValid(char* s, int idx1, int idx2) {
    int len = idx2 - idx1 + 1;
    // if (len > 2) return false; // ͬ���2
    // if (s[idx1] == '0') return false; // ͬ���1��ǰ��0�����Ϸ�
    bool res = true;
    if (len == 1) {// ���⣺sֻ�������֣�������������
        res = s[idx1] != '0';
    }
    else {
        res = (s[idx1] == '2' && s[idx2] <= '6') || s[idx1] == '1';
    }
    return res;
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