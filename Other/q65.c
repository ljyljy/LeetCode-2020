#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>
#include <ctype.h>


/** 有效数字
1) 将字符串以 e/E 进行分割:
  - 如果存在 e/E ：左侧可以「整数」或「浮点数」，右侧必须是「整数」
  -  如果不存在 e/E ：整段可以是「整数」或「浮点数」
2) check 函数用于判断「整数」或「浮点数」：
  - '+/-' 只能出现在头部
  - '.'最多出现一次
  - 至少存在一个数字
*/
bool isValid(char* s, int start, int end, bool mustInt);

bool isNumber(char* s) {
    int n = strlen(s);

    // 1. 找到e/E的唯一下标（若不唯一，直接F）
    int idx_e = -1; // -1说明为整数
    for (int i = 0; i < n; i++) {
        if (s[i] == 'e' || s[i] == 'E') {
            if (idx_e == -1) {
                idx_e = i;
            }
            else return false; // e/E不唯一，非法
        }
    }

    bool ans = true;
    if (idx_e != -1) { // 以e分割, [0, idx_e-1] 'e' [idx_e+1, n-1]
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
    if (s[start] == '+' || s[start] == '-') start++; // 至多一个+/-
    bool hasDot = false, hasNum = false;
    for (int i = start; i <= end; i++) {
        if (s[i] == '.') {
            if (hasDot || mustInt) return false; // 只能出现一次'.'
            hasDot = true;
        }
        else if (isdigit(s[i])) { // <ctype.h>，或 s[i] >= '0' && s[i] <= '9'
            hasNum = true;
        }
        else return false; // 其他字符（如非法字母等）
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