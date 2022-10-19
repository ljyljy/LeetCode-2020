#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#define DIM(x) sizeof(x)/sizeof(*x)

int countStudents(int* students, int studentsSize, int* sandwiches, int sandwichesSize) {
    int n = studentsSize, m = sandwichesSize;
    int cnts[2] = { 0 }; // 全部初始化为0
    for (int i = 0; i < n; i++) {
        cnts[students[i]]++;
    }
    for (int i = 0; i < m; i++) {
        if (--cnts[sandwiches[i]] < 0) {
            return m - i;
        }
    }
    return 0;
}

int main() {
    int students[] = { 1,1,1,0,0,1 };
    int sandwiches[] = { 1,0,0,0,1,1 };
    int ans = countStudents(students, DIM(students), sandwiches, DIM(sandwiches));
    printf("ans = %d\n", ans);
    return 0;
}