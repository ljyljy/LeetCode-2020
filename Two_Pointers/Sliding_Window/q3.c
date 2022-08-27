#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

int lengthOfLongestSubstring(char* s) {
    int n = strlen(s);
    int window[256] = { 0 };
    int left = 0, right = 0;
    int maxLen = INT_MIN;
    while (right < n) {
        char ch2Add = s[right++];
        window[ch2Add]++;

        while (window[ch2Add] > 1) {
            char ch2Del = s[left++];
            window[ch2Del]--;
        }
        maxLen = fmax(maxLen, right - left);
    }
    return maxLen == INT_MIN ? 0 : maxLen;
}

int main() {
    char* s = "abcabcbb";
    printf("maxLen = %d\n", lengthOfLongestSubstring(s));
}