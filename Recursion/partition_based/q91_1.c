
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>


// q91_1. 解码方法
/* 知识点
1) dfs+memo，数组代替哈希
1) dfs+memo，数组代替哈希
2) 字符串转int(掌握)：
- `atoi(str + idx1)`（无法直接指定str截取的长度idx2，可通过`strncpy`）[最慢]
  - ```C
    bool isValid(char* s, int idx1, int idx2) {
        int curLen = idx2 - idx1 + 1;
        char* curNum = (char*)calloc(curLen + 1, sizeof(char)); // +1: "\0"
        strncpy(curNum, s + idx1, curLen);
        int num = atoi(curNum);
        return 0 < num && num <= 26;
    }
    ```

- `sscanf(str, "%d", &num); `可'变相'支持**读取str指定长度** [慢]
  - 本身**不**支持"**<font color='red'>`%*d`</font>**"格式，需提前指定char arr[]，替换sscanf的参数'format'
  - ```C
    char format[] = "%*d";
    format[1] = len + '0';
    sscanf(s + idx1, format, &num); // '*':指定输出到num的字符宽度为len
    ```

- 逐个char转换为int，然后逐个检查合法性 / `num*10 + ch`
3) 避免字符串转int：逐个char检查(v2)
*/
int curCnt = 0;
int dfs(char* s, int idx, int* memo);
bool isValid_v1(char* s, int idx1, int idx2);
bool isValid_v2(char* s, int idx1, int idx2);
bool isValid_v3(char* s, int idx1, int idx2);

int numDecodings(char* s) {
    int memo[101] = { 0 }; // 由题：1 <= s.length <= 100，数组代替哈希
    curCnt = 0; // 多余,见91_2.c
    return dfs(s, 0, memo);
}

int dfs(char* s, int idx, int* memo) {
    int n = strlen(s);
    if (memo[idx] != 0) return memo[idx];
    if (idx == n) {
        memo[idx] = ++curCnt; // 或v2：设为1，返回1（无需记录curCnt）
        return memo[idx];
    }

    for (int i = idx; i < n; i++) {
        if (!isValid_v3(s, idx, i)) continue;
        memo[idx] += dfs(s, i + 1, memo);
    }
    return memo[idx];
}

// s[idx1, idx2]是否合法
bool isValid_v3(char* s, int idx1, int idx2) {
    if (s[idx1] == '0') return false; // 前导0，不合法

    int curLen = idx2 - idx1 + 1;
    char* curNum = (char*)calloc(curLen + 1, sizeof(char)); // +1: "\0"
    strncpy(curNum, s + idx1, curLen);
    int num = atoi(curNum);
    return 0 < num && num <= 26;
}

// s[idx1, idx2]是否合法
bool isValid_v2(char* s, int idx1, int idx2) {
    int len = idx2 - idx1 + 1;
    if (len > 2) return false;
    if (s[idx1] == '0') return false; // 前导0，不合法
    bool res = true;
    if (len == 1) {// 由题：s只包含数字，故无需检测其他
        res &= s[idx1] != '0';
    }
    else {
        res = (s[idx1] == '2' && s[idx2] <= '6') || s[idx1] == '1';
    }
    return res;
}

// s[idx1, idx2]是否合法
bool isValid_v1(char* s, int idx1, int idx2) {
    int len = idx2 - idx1 + 1;
    if (len > 2) return false;
    if (s[idx1] == '0') return false; // 前导0，不合法
    // 法1（慢）：将s[idx1, idx2]转为数字int，检查∈[1,26]
    int num = 0;
    // sscanf不支持"%*d"格式，需提前指定char arr[]，替换sscanf的参数'format'
    char format[] = "%*d";
    format[1] = len + '0';
    sscanf(s + idx1, format, &num); // '*':指定输出到num的字符宽度为len
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