package DP.P1_Optimize;

public class q471_encode_str_with_shortest_len_5star {
    String[][] dp; // dp[i][j=i+len-1] = s[i,j]����̱��봮��������ұա�
    public String encode(String s){
        int n = s.length();
        dp = new String[n][n]; // dp[i][j]: �з���ʼidx=i������=len, j=i+len-1
        int i = -1, j = -1;

        for (int len = 1; len <= n; len++) {
            for ( i = 0; (j = i + len - 1) < n; i++) {
                dp[i][j] = getEncodedStr(s, i, j);
                if (len > 4) { // �з��Ӵ�����>��"aaaa"="4[a]"
                    for (int k = i; k+1 <= j; k++) {
                        String newStr = dp[i][k] + dp[k+1][j];
                        if (dp[i][j].length() > newStr.length()) {
                            dp[i][j] = newStr;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    /**
     * ʹ��q459�ķ����� �ܿ����ҵ������ظ����ַ�����
     * �ҵ������ظ������ַ��������ǲ��ܽ��б���(ѹ��)��
     * q459��˼·�������£�
     * �� t = s + s, ���±�1���ַ���ʼ�����ַ���s�� �ҵ��±�idx��
     *     ���磺s="abc", ��"t=a(bc abc)".indexOf("abc", 1)=3=len(s)=idx��˵��s���ظ�
     *           s="abcabc", ��"t=a(bc[abc abc]abc)".indexOf("abcabc", 1)=3<len(s)=6��˵��s�����ظ�
     *
     * ���±� 1 ���ַ���ʼ��(t=s + s)�в����ַ���s�� �ҵ��±�idx��
     * ���idx != len(s), ���������ظ����Ӵ�, �ظ�����si = s.substring(0, idx), ����Ϊ n / idx
     * ���� �����������ظ����ַ����� �޷����б���
     *
     * ���⣺��aaaa��->4[a], ����ǰ�󳤶���ȣ��ɲ����룬ֻ�Գ���>5�������ظ�������ѹ����
     */
    // ���q459
    private String getEncodedStr(String s, int i, int j) {
        s = s.substring(i, j+1);
        int n = s.length();
        int idx = (s+s).indexOf(s, 1);
        if (n <= 4 || idx == n) { // len<=4 || ���ظ�������ѹ��
            return s;
        } else { // ��ѹ��
            String si = s.substring(0, idx); // �ظ�����
            int cnt = n / si.length();
            return cnt + "[" + dp[i][i+idx-1] + "]";
        }
    }

}
