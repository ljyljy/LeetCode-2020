#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// q1342
int numberOfSteps(int num) {
    int step = 0;
    while (num) { // 奇数则额外加1次操作：自减1
        step += ((num & 1) == 1 ? 1 : 0) + (num > 1 ? 1 : 0);
        num >>= 1;
    }
    return step;
}