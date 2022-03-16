package DP;

public class q44_wildcard_matching {
    /*
    ˼·����̬�滮�� �����άdp���飬����dp[i][j]��ʾs��ǰi���ַ���p��ǰj���ַ��Ƿ�ƥ�䣬
        Ϊ�˷����ʼ�������ǽ�s��p�ĳ��Ⱦ�+1
        ���ǵ�P�п��ܳ��������ַ�����ͨ��ĸ(a-z)��'*'������'.', ���䶯̬ת�Ʒ��̷ֱ��ǣ�
        1) ���p[j]Ϊ��ͨ��ĸ��dp[i][j]==dp[i-1][j-1] and s[i]==p[j]
        2) ���p[j]Ϊͨ���'./?'��s=��ab��, p=��a?����, dp[i][j]==dp[i-1][j-1]
        3) ���p[j]Ϊ'*', ������Ƚϸ���, ����������������ۣ�
           A. ��s="c", p="c*"Ϊ������ʱ'*'ƥ��0��(j������,j-1)��dp[i][j]==dp[i][j-1]
           B. ��s="c[abc]", p="c[*]"Ϊ������ʱ'*'ƥ����(j����,i-1)��dp[i][j]==dp[i-1][j]
        �� A+B. ��p[j]==��*��:
              dp[i][j] = dp[i][j-1] or dp[i-1][j];
     */
    char[] ss, pp;
    public boolean isMatch(String s, String p) {
        // Ϊ�˽��s="a", p="[*]a"��*�����p��ͷ0��ƥ������⣬��Ҫ�����ʼ��dp[0][:]��
        // Ϊ�ˣ���sǰ�ӡ� �����Ա���s=" "��p="*"��ƥ��;
        // i>0, j=0ʱ��dp[i][j]=false(��'��p'��������'�ǿ�s'ƥ��,��s="a", p=" ")
        s = " " + s; p = " " + p; // ???
        ss = s.toCharArray(); pp = p.toCharArray();
        int nS = ss.length, nP = pp.length;
        boolean[][] dp = new boolean[nS][nP];// ???
        dp[0][0] = true; // �ٶ�s��p���ӿ��ַ���ʼ
        for (int i = 0; i < nS; i++) {
            for (int j = 1; j < nP; j++) { // dp[>0][j=0]һ����false���������
                if (pp[j] != '*') {
                    dp[i][j] = (i-1 >= 0 && dp[i-1][j-1]) && match(i, j);
                } else {
                    dp[i][j] = dp[i][j-1] || (i-1 >= 0 && dp[i-1][j]);
                }
            }
        }
        return dp[nS-1][nP-1];// ???
    }

    private boolean match(int i, int j) {
        // s��p��Ӧ�ַ���ͬ || p��Ӧλ��Ϊͨ���'./?'(����ƥ��һ���ַ�)
        return ss[i] == pp[j] || pp[j] == '?';
    }
}
