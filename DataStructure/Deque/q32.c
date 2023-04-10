#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(x) sizeof(x)/sizeof(*x)

/* ˼·��
- ����ջ
  - Ϊ����'()()'ʱջ�գ����ڱ�')'.idx=[-1] ��ջ
  - ��[i]Ϊ'(', ����ջi��
  - ��[i]Ϊ')', �����&��ջ��
    - ��popջ�������ջ��Ϊ[i]�������Чidx��
    - �ǿգ��������Ч����=[peek+1,i]����i-peek
    - ��, ����ջ[i]��Ϊ���ڱ�')'���Ǻ������ֵ�')'�������Чidx
*/
#define PEEK (stk[top-1])

int longestValidParentheses(char* s) {
    int n = strlen(s);
    // ���״� �������ڱ�')'.idxΪ[-1]�����ջ����Ϊ(n+1)
    int* stk = (int*)calloc(n + 1, sizeof(int)), top = 0;
    int maxLen = 0;

    stk[top++] = -1; // ���ڱ�')'.idx=[-1], ��ջ
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            stk[top++] = i;
        }
        else {
            --top; // ��ջ
            if (top == 0) {
                stk[top++] = i; // ��Ϊ���ڱ�[i]=')'
            }
            maxLen = fmax(maxLen, i - PEEK); // ������Ч����=[peek+1,i]����i-peek
        }
    }
    return maxLen;
}

// C����ջv2��top=-1, ����ջ++top��ջ��Ϊtop����Ϊtop=-1, ջ��&��ջΪstk[top--]
#define PEEK2 (stk[top])
int longestValidParentheses2(char* s) {
    int n = strlen(s);
    int stk[n + 1], top = -1; // �Ա�q20��top����Ϊ-1��ͳһǰ׺�Լ�/�Լ�
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