#include <stdlib.h>
#include <stdio.h>

char const* keywords[] = {
    "do", "for", "if", "register", "return",
    NULL // 技巧，标记指针数组的结束位置
};


int main() {
    for (char const** p = keywords; *p != NULL; p++) {
        printf("%s\n", *p);
    }

    // 2）数组初始化
    int arr[10] = { 100 }; // 只对第0个元素赋值100，等价于{[0]=100}
    for (int i = 0; i < 10; i++) {
        printf("%d ", arr[i]);
    }
}