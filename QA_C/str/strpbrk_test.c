#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {
    char* str = "9001 leetcode.com ljyljyok";
    char* group = "yjl"; // group�����ַ�Ȩ����ͬ���޲������ȼ�
    char* ans = strpbrk(str, group); // strpbrk �ҵ��׸�ƥ���ַ�(group�������ַ�)������ - ������O(n*m)
    printf("Find any char in group(%s): ans=%s\n", group, ans);
    // Find any char in group(yjl): ans=leetcode.com ljyljyok
    return 0;
}