#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/* 知识点
1）字符串扩展 & 拼接
  - strcat
  - realloc + sprintf
    - realloc: 扩展字符串长度（扩展内存【不会自动置0】！）
      - 注意：扩展时要加上'\0'的长度
    - sprintf: 将格式化的数据写入某个字符串中
      - 注意：可以替换LC无法使用的itoa(3) --> sprintf(str2 + strlen(str2), "%d", 3);
2）sprintf


*/
// Q38: Count and Say
// https://leetcode.com/problems/count-and-say/
// 思路：模拟
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

char* countAndSay(int n) {
  char* str = (char*)calloc(2, sizeof(char));
  str[0] = '1'; // str[1] = '\0';

  for (int i = 2; i <= n; ++i) {
    int curLen = strlen(str);
    char* tmp = (char*)calloc(1, sizeof(char)); // '\0'

    int start = 0, end = 0;
    while (end < curLen) {
      while (end < curLen && str[end] == str[start]) {
        ++end;
      }
      tmp = (char*)realloc(tmp, (strlen(tmp) + 3) * sizeof(char)); // 3=cnt+char+'\0'(勿漏！)
      // memset(tmp + strlen(tmp), 0, sizeof(char) * 3); // realloc【不会自动初始化置0！】

      int cnt = end - start;
      sprintf(tmp + strlen(tmp), "%d%c\0", cnt, str[start]);
      // tmp[strlen(tmp)] = '0' + cnt;  //  itoa(cnt, &cnt_c, 10);
      // tmp[strlen(tmp)] = str[start]; // append char
      start = end;
    }
    // printf("No.%d, tmp: %s\n", i, tmp);
    free(str);
    str = tmp;
  }
  return str;
}

int main() {
  int n = 5;
  char* str = countAndSay(n);
  printf("res = %s\n", str);
  free(str);
  return 0;
}

/* Output:
No.2, tmp: 11
No.3, tmp: 21
No.4, tmp: 1211
No.5, tmp: 111221
res = 111221
*/