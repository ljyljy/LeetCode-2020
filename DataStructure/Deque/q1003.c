#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q1003.c

// 法2：栈【推荐】
bool isValid(char* s) {
    int n = strlen(s);
    if (n < 3) return false;
    if (n == 3) return strcmp(s, "abc") == 0;
    // n > 3
    char stk[n];
    int top = 0;
    for (int i = 0; i < n; i++) {
        stk[top++] = s[i]; // 入栈: 如"a(top-3) b c NULL(top=4)"
        // ↓ stk[top - 1] == 'c' && stk[top - 2] == 'b' && stk[top - 3] == 'a'
        if (top - 3 >= 0 && strncmp(&stk[top - 3], "abc", 3) == 0) {
            top -= 3; // 出栈3个元素
        }
    }
    return top == 0;
}

// 法1：暴力、遍历
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
    int len = end - start + 1; // 左闭右闭
    if (len < 3) {
        return false;
    }
    else if (len == 3) {
        return subStrEquals(s, start, end, pat);
    }

    // len > 3
    for (int i = start; i <= end; ) { // 左闭右闭
        if (subStrEquals(s, i, i + 2, pat)) {
            i = i + 3;
        }
        else {
            if (s[i] != 'a') return false;
            cnt[s[i] - 'a']++; // ----大串"a"
            // 大串被子串分隔开："a'abc'b'abc'c"
            for (int j = i + 1; j <= end; ) {
                // 1) 处理大串"a"后的子串
                while (j <= end && j + 2 <= end && subStrEquals(s, j, j + 2, pat)) {
                    j = j + 3; //  多个子串'abc'相连："a(i)'a(j)bc''a(j+3)bc'bc"
                }
                // printf("1) i=%d, j=%d\n", i, j);
                // 1-1) 大串abc不完整，F
                if (j > end) return false;// 1-1-1: 缺大串"b"："a'a(j)bc'"；
                // 1-2) 子串'abc'匹配完毕，回到大串"b(j)+子串"/"b(j)c"匹配
                if (s[j] != 'b') return false; // 1-1-2: 子串'abc'匹配失败："a'abc''a(j)c/bc等'bc"
                cnt[s[j] - 'a']++; // ----大串"b"
                // printf("2) cnt['%c' - 'a']=%d\n", s[j], cnt[s[j] - 'a']);
                // 2-1) 大串"b(j)c"匹配
                if (j + 1 <= end && s[j + 1] == 'c') { // 大串匹配完毕，"a'abc'b(j)c"
                    // cnt[s[j] - 'a']++; // ----大串"c"
                    memset(cnt, 0, sizeof(cnt)); // 重置cnt！【勿忘】
                    i = j + 2; // 继续匹配大串
                    break;
                }
                // 2-2) 处理大串"b"后的子串, "ab'a(j)bc''abc'c"
                j += 1;
                while (j <= end && j + 2 <= end && subStrEquals(s, j, j + 2, pat)) {
                    j = j + 3; //  多个子串'abc'相连："ab'abc''abc'c"
                }
                // printf("2-2) i=%d, j=%d\n", i, j);
                // 3-1) 大串abc不完整(缺大串"c" 或 子串'abc'匹配失败)，F。
                if (j > end) return false; // 缺大串"c"："ab'a(j)bc''abc'"；
                // 3-2) 子串'abc'匹配完毕，回到大串"c(j)"匹配
                if (s[j] != 'c') return false; // 子串'abc'匹配失败："ab'abc''a(j)c/bc等'c"
                cnt[s[j] - 'a']++; // ----大串"c"
                if (cnt[0] == cnt[1] == cnt[2] == 1) {
                    memset(cnt, 0, sizeof(cnt)); // 重置cnt！【勿忘】
                    i = j + 1; // 继续匹配大串
                    break;
                }
                j++;
            }
        }
    }
    return true;
}

// 优化：替换为strncmp(src, pat, len=3);
bool subStrEquals(char* s, int start, int end, const char* pat) {
    return strncmp(s + start, pat, 3) == 0;
    // int len1 = end - start + 1; // 左闭右闭
    // // int len2 = strlen(pat); // 3
    // if (len1 != 3) return false;

    // for (int i = start; i <= end; i++) { // 左闭右闭
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