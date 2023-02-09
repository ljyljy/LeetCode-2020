#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

bool isSubsequence(char* substr, char* str) {
    int n1 = strlen(substr), n2 = strlen(str);
    int i = 0, j = 0;
    while (i < n1 && j < n2) {
        if (substr[i] == str[j]) i++;
        j++;
    }
    return i == n1;
}

int main() {
    char* subStr = "abc";
    char* str = "ahbgdc";
    bool res = isSubsequence(subStr, str);
    printf("%d\n", res);
    return 0;
}