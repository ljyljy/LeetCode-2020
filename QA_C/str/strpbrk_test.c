#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {
    char* str = "9001 leetcode.com ljyljyok";
    char* group = "yjl"; // group所有字符权重相同，无查找优先级
    char* ans = strpbrk(str, group); // strpbrk 找到首个匹配字符(group中任意字符)即返回 - 暴力需O(n*m)
    printf("Find any char in group(%s): ans=%s\n", group, ans);
    // Find any char in group(yjl): ans=leetcode.com ljyljyok
    return 0;
}