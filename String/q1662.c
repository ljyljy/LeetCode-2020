#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// q1662_check_if_two_string_arrays_are_equivalent
#define LEN 2000
bool arrayStringsAreEqual(char** word1, int n1, char** word2, int n2) {
    // char* ans1 = (char*)malloc(sizeof(char)*LEN);
    int i = 0, j = 0;
    int ii = 0, jj = 0;
    while (i < n1 && j < n2) {
        char* s1 = word1[i], * s2 = word2[j];
        int len1 = strlen(s1), len2 = strlen(s2);
        // printf("i = %d, j = %d; s1 = %s, s2 = %s\n", i, j, s1, s2);
        while (ii < len1 && jj < len2) {
            if (s1[ii] == s2[jj]) {
                // printf("ii = %d, jj = %d; ch1 = %c, ch2 = %c\n", ii, jj, s1[ii], s2[jj]);
                ii++; jj++;
            }
            else return false;
        }
        if (ii < len1) { // word2[j]到头，需要j++
            j++;
            jj = 0;
            continue;
        }
        if (jj < len2) {
            i++;
            ii = 0;
            continue;
        }
        ii = 0, jj = 0;
        i++; j++;
    }
    return i == n1 && j == n2; // 都得走到头
}