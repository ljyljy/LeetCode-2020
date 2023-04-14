#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/* ֪ʶ��
1���ַ�����չ & ƴ��
  - strcat
  - realloc + sprintf
    - realloc: ��չ�ַ������ȣ���չ�ڴ桾�����Զ���0������
      - ע�⣺��չʱҪ����'\0'�ĳ���
    - sprintf: ����ʽ��������д��ĳ���ַ�����
      - ע�⣺�����滻LC�޷�ʹ�õ�itoa(3) --> sprintf(str2 + strlen(str2), "%d", 3);
2��sprintf


*/
// Q38: Count and Say
// https://leetcode.com/problems/count-and-say/
// ˼·��ģ��
// ʱ�临�Ӷȣ�O(n^2)
// �ռ临�Ӷȣ�O(n)

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
      tmp = (char*)realloc(tmp, (strlen(tmp) + 3) * sizeof(char)); // 3=cnt+char+'\0'(��©��)
      // memset(tmp + strlen(tmp), 0, sizeof(char) * 3); // realloc�������Զ���ʼ����0����

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