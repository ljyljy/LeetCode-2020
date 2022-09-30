#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

#define DIM(arr) sizeof(arr)/sizeof(*arr)

int func1() {
    int nums[] = { 1,2,3 };
    int* p = &nums[0]; // 或 ... = nums; 
    int tmp = *p++; // *、++优先级相同 & 从右向左结合 (D.T.、HW某员工总结)，相当于*(p++)
    // 1）先执行 p++，得到p的一份拷贝；2）p后移，指向2：*p=nums[1]=2;:3）后读取 *p=1(对p的旧值拷贝 进行解引用，区别于++p)，赋值给tmp
    printf("nums[0]=%d, tmp=%d, *p=%d\n", nums[0], tmp, *p); // 1, 1, 2
    printf("&nums[0]=%d, &tmp=%d, &p=%d, %%p=%p\n", &nums[0], &tmp, &p, p);
    // &nums[0]=6421988, &tmp=6421972, &p=6421976, %p=000000000061FDE8
}


int func1_2() {
    char* p = "ace";
    char tmp = *p++; // 相当于*(p++)
    // 先执行 p++，得到p的一份拷贝；2）p后移; 3)将p的拷贝，解引用后 赋值给tmp
    printf("tmp=%c, *p=%c\n", tmp, *p); // tmp=a, *p=c
}

int func1_3() {
    int nums[] = { 0,4,5,6 };
    int* p = nums;
    int tmp = ++ * p; // 相当于++(*p):  *、++优先级相同 & 从右向左结合
    // 1) 先执行*p；2）对(*p)自增=0+1=1,等价于nums[0]++; 3)将自增结果赋值给tmp 
    // 4) p指针一直未变，指向字符串首地址
    printf("tmp=%d, *p=%d, %%p=%d, nums[0]=%d, &nums[0]=%d , nums[1]=%d\n", tmp, *p, p, nums[0], &nums[0], nums[1]);
    // tmp=1, *p=1, nums[0]=1, nums[1]=4 (没变)
}

int func1_4() {
    char* p = "ace";
    // char tmp = ++ * p; // 相当于++(*p):  *、++优先级相同 & 从右向左结合
    // // 1)  (*p) = 'a', +1后为'b'，但【(*p)是'a', 不能作为左值！是【字符串字面量】, 不可修改！故报错！】 
    // printf("tmp=%c, *p=%c\n", tmp, *p); // 【报错！】

    char* p2 = (char*)malloc(sizeof(char) * 4);
    p2[0] = 'a', p2[1] = 'c', p2[2] = 'e', p2[3] = 0;
    char tmp2 = ++ * p2; // *p2=p2[0]='a', 等价于++(*p2)=++p2[0] => p2[0]='b'
    printf("*tmp2=%c, *p2=%c, p2[1]=%c\n", tmp2, *p2, p2[1]);
}

// TEST2 - q264
void swap(int* pa, int* pb) {
    int tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}

typedef struct STest2 {
    int* nums;
    int n;
} STest2;

void initS2(STest2* stest2, int* nums, int n) {
    stest2->n = n;
    stest2->nums = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        stest2->nums[i] = nums[i];
    }
}

void print_STest2(STest2* stest2, int cnt) {
    printf("case %d: ", cnt);
    for (int i = 0; i < stest2->n; ++i) {
        printf("%d ", stest2->nums[i]);
    }
    printf("\n");
}

// int func2() {
//     int nums[] = { 0, 1,2,3,4,5,6,7,8 };
//     int n = DIM(nums);
//     STest2* stest2 = (STest2*)malloc(sizeof(STest2));
//     initS2(stest2, nums, n);

//     swap(&stest2->nums[0], &stest2->nums[5]); // √
//     print_STest2(stest2, 1);

//     int a = &stest2->nums[0], b = &stest2->nums[5];
//     swap(a, b); // √
//     print_STest2(stest2, 2);


//     int a2 = stest2->nums[0], b2 = stest2->nums[5];
//     swap(&a2, &b2); // ×
//     print_STest2(stest2, 3);

// }


int main() {
    // func1();
    // func1_2();
    // func1_3();
    func1_4();
    // func2();
}