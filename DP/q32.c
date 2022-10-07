#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(x) sizeof(x)/sizeof(*x)

// C单调栈：top=-1, 则入栈++top，栈顶为top，空为top=-1
int longestValidParentheses(char* s) {
    int n = strlen(s);
    int stk[n + 1], top = -1; // 对比q20：top定义为-1，统一前缀自加/自减
    stk[++top] = -1;
    int ans = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            stk[++top] = i; // deque.push(i);
        }
        else {
            --top; // deque.pop();
            if (top == -1) { // deque.isEmpty()
                stk[++top] = i;
            }
            ans = fmax(ans, i - stk[top]);
        }
    }
    return ans;
}