#include <string.h>
#include <stdio.h>

int main() {
    // strspn: ƥ��ǰ׺��
    // CASE 1
    char* str = "Linux was first developed for 386/486-based pcs.";
    // ���� accept
    printf("%d\n", strspn(str, "Linux"));  // 5, "Linux"ƥ��ͨ��
    printf("%d\n", strspn(str, "ABCDLaeiou")); // 2, "Li"ƥ��ͨ��
    printf("%d\n", strspn(str, "/-")); // 0��ǰ׺��
    printf("%d\n", strspn(str, "1234567890")); // 0
    // ���� reject
    printf("%d\n", strcspn(str, "was")); // 6, ǰ׺��was�ĳ���Ϊ6 = "Linux "
    printf("%d\n", strcspn(str, "nux")); // 2, ǰ׺��nux�ĳ���Ϊ2 = "Li"

    printf("---------------\n");

    // CASE 2
    char buffer[] = "  25,142,330,Smith,J,239-4123"; // ��λ2���ո�
    // ���� accept
    printf("%d\n", strspn(buffer, " 0123456789"));  // 4, "  25"ƥ��ͨ��
    printf("%d\n", strspn(buffer, " ,0123456789")); // 13, "  25,142,330,"ƥ��ͨ��
    printf("%d\n", strspn(buffer, "239")); // 0��ǰ׺��
    printf("%d\n", strspn(buffer, "1234567890")); // 0
    printf("%d\n", strspn(buffer, " ")); // 2, ǰ׺"  "ƥ��ͨ��
    // ���� reject
    printf("%d\n", strcspn(buffer, " ")); // 0, ǰ׺��' '�ĳ���Ϊ0
    printf("%d\n", strcspn(buffer, ",")); // 4, ǰ׺��","�ĳ���Ϊ4, ��"  25"

    // CASE 3 ��Ӧ�ã�
    // ��ȡ�ַ����е�1���ǿհ��ַ���λ��
    char* ptr = buffer + strspn(buffer, " \n\r\f\t\v");
    printf("%s\n", ptr); // 25,142,330,Smith,J,239-4123
    return 0;
}