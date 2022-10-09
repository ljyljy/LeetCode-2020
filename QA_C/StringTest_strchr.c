#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int main() {
    char* cp = "9001 leetcode.com";
    char* ans = strchr(cp, ' '); // strchr返回第一次出现' '的地址（子串）
    int spaceIdx = strchr(cp, ' ') - cp; // 得到字符串分割处的idx
    // spaceHdr= leetcode.com, spaceIdx=4
    printf("spaceHdr=%s, spaceIdx=%d\n", ans, spaceIdx);
    return 0;
}