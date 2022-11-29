#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int minOperations(char* s) {
    int n = strlen(s);
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] != (char)('0' + i % 2)) {
            cnt++;
        }
    }
    return fmin(cnt, n - cnt);
}