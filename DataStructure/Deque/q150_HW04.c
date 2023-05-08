#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// HW04.���ʽ����(q150, �沨�����ʽ)

/*
֪ʶ�㣺
1. �沨�����ʽ -- Deque
  - ��ջʱ��ע���������˳���磺�ȵ�ջ����b����ջ������a��
2. �ַ����ָstrtok
  - ����1�����ǿɱ�char*, �����Ϊconst��������ǿתΪchar*
  - ����2�����˫���ţ�
3. �ַ���ƥ�䣺strspn, strpbrk
  - strspn: �ַ�ǰ׺ƥ�䣬����ƥ�䵽���ַ���
  - strpbrk: �ַ�ƥ�䣨���ڵ�char��������ƥ�䵽���ַ�ָ��
*/
#define BUF_LEN 50000

// �沨�����ʽ
static int Calc(const char* input) {
    int stk[BUF_LEN] = { 0 };
    int top = 0;
    char* p = strtok((char*)input, ",");
    char* oprs = "+-*/";
    while (p != NULL && *p != '\0') {
        // v1: �ַ�ǰ׺ƥ�䣺`strspn(p, oprs) == 1`, ����ƥ�䵽���ַ���
        // v2: �ַ�ƥ�䣨���ڵ�char����`strpbrk(p, oprs) != NULL`������ƥ�䵽���ַ�ָ��
        //   - if (strlen(p) == 1 && strpbrk(p, oprs) != NULL) { // v2
        if (strlen(p) == 1 && strspn(p, oprs) == 1) { // p�ǲ�����(�ų�'-3'�������)
            // ? ע���������˳��
            int b = stk[--top]; // �ȵ�����ʱ���ڶ�����������
            int a = stk[--top]; // ��󵯳�����һ����������
            int c = 0;
            switch (*p) {
            case '+': c = a + b; break;
            case '-': c = a - b; break;
            case '*': c = a * b; break;
            case '/': c = a / b; break;
            }
            stk[top++] = c;
            // printf("%d %c %d = %d\n", a, *p, b, c);
        }
        else {
            // sscanf(p, "%d", &stk[top++]); // ��2����ȡ������p, ��ѹջstk[top++]
            stk[top++] = atoi(p); // ��1
            // printf("push %d\n", stk[top - 1]);
        }
        p = strtok(NULL, ",");
    }
    return stk[top - 1];
}

int main()
{
    static char buf[BUF_LEN];
    // if (gets_s(buf, sizeof(buf)) == NULL) { return -1; }
    if (scanf_s("%s", buf, sizeof(buf)) != 1) { return -1; }

    int ret = Calc(buf);

    printf("res = %d", ret);
    return 0;
}
/*
9,3,5,-,2,*,+
res = 5

3,-3,-,2,/,10,-
res = -7
*/