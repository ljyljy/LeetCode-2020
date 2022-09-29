package String;

public class q01_09_string_rotation_lcci {
    public boolean isFlipedString(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        if (n == 0) return true;
//        boolean isFliped = false;
        int i = 0, j = 0;
        for (i = 0; i < n; i++) { // ����s1�ĸ���/���
            for (j = 0; j < n; j++) { // s1: ��i��ʼ������������j����s2.j��[0,n)
                if (s1.charAt((i+j) % n) != s2.charAt(j)) {
                    break;
                }
            }
            if (j == n) return true;
        }
        return false;
    }

    // ��2��kmp��أ��������ַ���
//   �ַ���s+s ���������� s1����ͨ����ת�����õ����ַ�����ֻ��Ҫ��� s2�Ƿ�Ϊ s + s�����ַ������ɡ�
// Java��return s1.length() == s2.length() && (s1 + s1).contains(s2);
}
