#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

int func1() {
    int nums[] = { 1,2,3 };
    int* p = &nums[0];// 或 ... = nums;
    int tmp = *p++; // *、++优先级相同，【相当于*(p++)】
    // 先执行 p++，*p=nums[1]=2; 后读取 *p=1(p自加前先使用旧值进行解引用，区别于++p)，赋值给tmp
    printf("nums[0]=%d, tmp=%d, *p=%d\n", nums[0], tmp, *p); // 1, 1, 2
}

int main() {
    func1();
}