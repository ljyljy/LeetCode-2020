#include <stdlib.h>
#include <stdio.h>

char const* keywords[] = {
    "do", "for", "if", "register", "return",
    NULL // ���ɣ����ָ������Ľ���λ��
};


int main() {
    for (char const** p = keywords; *p != NULL; p++) {
        printf("%s\n", *p);
    }

    // 2�������ʼ��
    int arr[10] = { 100 }; // ֻ�Ե�0��Ԫ�ظ�ֵ100���ȼ���{[0]=100}
    for (int i = 0; i < 10; i++) {
        printf("%d ", arr[i]);
    }
}