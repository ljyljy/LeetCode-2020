#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

int func1() {
    int nums[] = { 1,2,3 };
    int* p = &nums[0];// �� ... = nums;
    int tmp = *p++; // *��++���ȼ���ͬ�����൱��*(p++)��
    // ��ִ�� p++��*p=nums[1]=2; ���ȡ *p=1(p�Լ�ǰ��ʹ�þ�ֵ���н����ã�������++p)����ֵ��tmp
    printf("nums[0]=%d, tmp=%d, *p=%d\n", nums[0], tmp, *p); // 1, 1, 2
}

int main() {
    func1();
}