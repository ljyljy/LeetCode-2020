#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(x) sizeof(x)/sizeof(*x)

/* 思路：
- 单调栈
  - 为避免'()()'时栈空，左哨兵')'.idx=[-1] 入栈
  - 若[i]为'(', 则入栈i；
  - 若[i]为')', 则计算&出栈：
    - 先pop栈顶。则次栈顶为[i]的最近无效idx：
    - 非空，则计算有效长度=[peek+1,i]，即i-peek
    - 空, 则入栈[i]作为左哨兵')'，是后续出现的')'的最近无效idx
*/
#define PEEK (stk[top-1])

int longestValidParentheses(char* s) {
    int n = strlen(s);
    // 【易错 ↓】左哨兵')'.idx为[-1]，因此栈容量为(n+1)
    int* stk = (int*)calloc(n + 1, sizeof(int)), top = 0;
    int maxLen = 0;

    stk[top++] = -1; // 左哨兵')'.idx=[-1], 入栈
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            stk[top++] = i;
        }
        else {
            --top; // 出栈
            if (top == 0) {
                stk[top++] = i; // 作为左哨兵[i]=')'
            }
            maxLen = fmax(maxLen, i - PEEK); // 计算有效长度=[peek+1,i]，即i-peek
        }
    }
    return maxLen;
}

// C单调栈v2：top=-1, 则入栈++top，栈顶为top，空为top=-1, 栈顶&弹栈为stk[top--]
#define PEEK2 (stk[top])
int longestValidParentheses2(char* s) {
    int n = strlen(s);
    int stk[n + 1], top = -1; // 对比q20：top定义为-1，统一前缀自加/自减
    stk[++top] = -1;
    int ans = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            stk[++top] = i; // deque.push(i);
        }
        else {
            top--; // deque.pop();
            if (top == -1) { // deque.isEmpty()
                stk[++top] = i;
            }
            ans = fmax(ans, i - PEEK2);
        }
    }
    return ans;
}

int main() {
    char* s = "(()()(())";
    int ans = longestValidParentheses(s);
    printf("%d ", ans); // 8
    return 0;
}