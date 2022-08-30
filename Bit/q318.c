#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#ifndef DIM
#define DIM(x) (sizeof(x)/sizeof(*x))
#endif

int maxProduct(char** words, int n) {
    int* masks = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        char* word = words[i];
        for (int j = 0; j < strlen(word); j++) {
            char ch = word[j];
            masks[i] |= 1 << (ch - 'a');
        }
    }

    int maxProd = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if ((masks[i] & masks[j]) == 0) {
                maxProd = fmax(maxProd, strlen(words[i]) * strlen(words[j]));
            }
        }
    }
    return maxProd;
}

int main() {
    char* words[] = { "abcw","baz","foo","bar","xtfn","abcdef" };
    printf("maxProd=%d\n", maxProduct(words, DIM(words)));
}