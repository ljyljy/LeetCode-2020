/*
  2��
  1����άָ��malloc��memset(..., len+1)
  2������ʱ�������char[]��char*������û�����鳤�����ԣ����¶δ��󣡣�
*/

char* convert(char * s, int n){
    int len = strlen(s);
    if (n == 1 || n >= len) return s;

    char** mat = (char**)malloc(sizeof (char*) * n);  // List<StringBuilder>, n*(len+1)
    for (int i = 0; i < n; i++) {// n��sb��ȫ����ʼ��
        // ÿ�ж���֤�ܴ�����󳤶�=len���ַ���
        mat[i] = malloc(sizeof(char) * (len+1)); // ������+1 ('\0')��
        memset(mat[i], 0, sizeof(char) * (len+1));
    }
    int* rows_lastIdx = (int*)malloc(sizeof (int) * n); // ÿ���ϴζ�����idx
    memset(rows_lastIdx, 0, sizeof (int) * n);

    int row_i = 0, flag = -1;
    for (int i = 0; i < len; i++) {
        char c = s[i];
        int curColIdx = rows_lastIdx[row_i]++;// ָ��ȡֵ�󣬺���+1
        mat[row_i][curColIdx] = c;
        // printf("i=%d, j=%d, c=%c\n", i, curColIdx, c);
        if (row_i == 0 || row_i == n-1) {
            flag = -flag;
        }
        row_i += flag;
    }
    // ����s
    int idx = 0;
    for (int i = 0; i < n; i++) {
        int curColIdx = rows_lastIdx[i];
        // printf("curColIdx = %d\n", curColIdx);
        for (int j = 0; j < curColIdx; j++) {
            s[idx++] = mat[i][j];
            // printf("mat[%d][j]=%c, sizeof(mat[i])=%d\n", i, mat[i][j], strlen(mat[i]));
        }
        free(mat[i]);
    }
    // s[idx] = 0;
    free(mat);
    free(rows_lastIdx);
    return s;
}
//leetcode submit region end(Prohibit modification and deletion)

// int main() {
//     char s[] = "PAYPALISHIRING"; // �������ָ�������顿��������Ϊchar* s,��ʧ�����鳤�����ԣ����³��������
//     int numRows = 3;
//     char *res = convert(s, numRows);
//     printf("%s\n", res);
//     return 0;
// }