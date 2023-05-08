#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// HW04.表达式计算(q150, 逆波兰表达式)

/*
知识点：
1. 逆波兰表达式 -- Deque
  - 弹栈时，注意操作数的顺序（如：先弹栈减数b，后弹栈被减数a）
2. 字符串分割：strtok
  - 参数1必须是可变char*, 若入参为const，必须先强转为char*
  - 参数2必须带双引号！
3. 字符串匹配：strspn, strpbrk
  - strspn: 字符前缀匹配，返回匹配到的字符数
  - strpbrk: 字符匹配（组内单char），返回匹配到的字符指针
*/
#define BUF_LEN 50000

// 逆波兰表达式
static int Calc(const char* input) {
    int stk[BUF_LEN] = { 0 };
    int top = 0;
    char* p = strtok((char*)input, ",");
    char* oprs = "+-*/";
    while (p != NULL && *p != '\0') {
        // v1: 字符前缀匹配：`strspn(p, oprs) == 1`, 返回匹配到的字符数
        // v2: 字符匹配（组内单char）：`strpbrk(p, oprs) != NULL`，返回匹配到的字符指针
        //   - if (strlen(p) == 1 && strpbrk(p, oprs) != NULL) { // v2
        if (strlen(p) == 1 && strspn(p, oprs) == 1) { // p是操作符(排除'-3'负数情况)
            // ? 注意操作数的顺序
            int b = stk[--top]; // 先弹出的时【第二个】操作数
            int a = stk[--top]; // 最后弹出【第一个操作数】
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
            // sscanf(p, "%d", &stk[top++]); // 法2：读取操作数p, 并压栈stk[top++]
            stk[top++] = atoi(p); // 法1
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