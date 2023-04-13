#include <string.h>
#include <stdio.h>

int main() {
    // strspn: 匹配前缀！
    // CASE 1
    char* str = "Linux was first developed for 386/486-based pcs.";
    // 过滤 accept
    printf("%d\n", strspn(str, "Linux"));  // 5, "Linux"匹配通过
    printf("%d\n", strspn(str, "ABCDLaeiou")); // 2, "Li"匹配通过
    printf("%d\n", strspn(str, "/-")); // 0，前缀无
    printf("%d\n", strspn(str, "1234567890")); // 0
    // 过滤 reject
    printf("%d\n", strcspn(str, "was")); // 6, 前缀非was的长度为6 = "Linux "
    printf("%d\n", strcspn(str, "nux")); // 2, 前缀非nux的长度为2 = "Li"

    printf("---------------\n");

    // CASE 2
    char buffer[] = "  25,142,330,Smith,J,239-4123"; // 首位2个空格
    // 过滤 accept
    printf("%d\n", strspn(buffer, " 0123456789"));  // 4, "  25"匹配通过
    printf("%d\n", strspn(buffer, " ,0123456789")); // 13, "  25,142,330,"匹配通过
    printf("%d\n", strspn(buffer, "239")); // 0，前缀无
    printf("%d\n", strspn(buffer, "1234567890")); // 0
    printf("%d\n", strspn(buffer, " ")); // 2, 前缀"  "匹配通过
    // 过滤 reject
    printf("%d\n", strcspn(buffer, " ")); // 0, 前缀非' '的长度为0
    printf("%d\n", strcspn(buffer, ",")); // 4, 前缀非","的长度为4, 即"  25"

    // CASE 3 （应用）
    // 获取字符串中第1个非空白字符的位置
    char* ptr = buffer + strspn(buffer, " \n\r\f\t\v");
    printf("%s\n", ptr); // 25,142,330,Smith,J,239-4123
    return 0;
}