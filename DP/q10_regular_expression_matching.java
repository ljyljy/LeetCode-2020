package DP;

public class q10_regular_expression_matching {
    /*
    ˼·����̬�滮�� �����άdp���飬����dp[i][j]��ʾs��ǰi���ַ���p��ǰj���ַ��Ƿ�ƥ�䣬
        Ϊ�˷����ʼ�������ǽ�s��p�ĳ��Ⱦ�+1
        ���ǵ�P�п��ܳ��������ַ�����ͨ��ĸ(a-z)��'*'������'.', ���䶯̬ת�Ʒ��̷ֱ��ǣ�
        1) ���p[j]Ϊ��ͨ��ĸ��dp[i][j]==dp[i-1][j-1] and s[i]==p[j]
        2) ���p[j]Ϊ'.'��s=��ab��, p=��a.����, dp[i][j]==dp[i-1][j-1]
        3) ���p[j]Ϊ'*', ������Ƚϸ���, ����������������ۣ�
           A. ��s="c", p="ca*"Ϊ������ʱ'*'ƥ��0�Σ�dp[i][j]==dp[i][j-2]
           B. ��s="caaa", p="ca*"��"c.*"Ϊ������ʱ'*'ƥ���Σ�
              dp[i][j]==dp[i-1][j] and (s[i]==p[j-1] ��(p[j-1]=='.'ͨ���))
        �� A+B. ��p[j]==��*��:
              dp[i][j] = dp[i][j-2] or (dp[i-1][j] and (s[i]==p[j-1] or p[j-1]=='.'))
     */
    char[] ss, pp;
    public boolean isMatch(String s, String p) {
        // Ϊ�˽��s="a", p="[c*]a"��*�����p��ͷ0��ƥ������⣬��Ҫ�����ʼ��dp[0][:]��
        // Ϊ�ˣ���sǰ�ӡ� �����Ա���s=" "��p="c*"��ƥ��;
        // i>0, j=0ʱ��dp[i][j]=false(��'��p'��������'�ǿ�s'ƥ��,��s="a", p=" ")
        s = " " + s; p = " " + p;
        ss = s.toCharArray(); pp = p.toCharArray();
        int nS = ss.length, nP = pp.length;
        boolean[][] dp = new boolean[nS][nP];
        dp[0][0] = true; // �ٶ�s��p���ӿ��ַ���ʼ
        for (int i = 0; i < nS; i++) {
            for (int j = 1; j < nP; j++) { // dp[>0][j=0]һ����false���������
                // if (j+1 <nP && pp[j+1] == '*') continue; // ��������Ӳ���
                if (pp[j] != '*') { // ���1) & 2)
                    dp[i][j] = (i-1>=0 && dp[i-1][j-1]) && matches(i, j);
                } else { // ���3
                    dp[i][j] = dp[i][j-2] || (i-1 >= 0 && dp[i-1][j] && matches(i, j-1));
                }
            }
        }
        return dp[nS-1][nP-1];
    }

    private boolean matches(int i, int j) {
        // s��p��Ӧ�ַ���ͬ || p��Ӧλ��Ϊͨ���'.'(����ƥ��һ���ַ�)
        return ss[i] == pp[j] || pp[j] == '.';
    }
}
