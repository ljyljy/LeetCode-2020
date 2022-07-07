package DP.P1_Optimize;

public class q471_encode_str_with_shortest_len_5star {
    String[][] dp; // dp[i][j=i+len-1] = s[i,j]的最短编码串，【左闭右闭】
    public String encode(String s){
        int n = s.length();
        dp = new String[n][n]; // dp[i][j]: 切分起始idx=i，长度=len, j=i+len-1
        int i = -1, j = -1;

        for (int len = 1; len <= n; len++) {
            for ( i = 0; (j = i + len - 1) < n; i++) {
                dp[i][j] = getEncodedStr(s, i, j);
                if (len > 4) { // 切分子串长度>如"aaaa"="4[a]"
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
     * 使用q459的方法， 能快速找到连续重复子字符串，
     * 找到连续重复的子字符串，我们才能进行编码(压缩)。
     * q459的思路大致如下：
     * 令 t = s + s, 从下标1的字符开始查找字符串s， 找到下标idx，
     *     例如：s="abc", 则"t=a(bc abc)".indexOf("abc", 1)=3=len(s)=idx，说明s无重复
     *           s="abcabc", 则"t=a(bc[abc abc]abc)".indexOf("abcabc", 1)=3<len(s)=6，说明s存在重复
     *
     * 从下标 1 的字符开始从(t=s + s)中查找字符串s， 找到下标idx，
     * 如果idx != len(s), 存在连续重复的子串, 重复部分si = s.substring(0, idx), 个数为 n / idx
     * 否则， 不存在连续重复子字符串， 无法进行编码
     *
     * 另外：”aaaa”->4[a], 编码前后长度相等，可不编码，只对长度>5的连续重复串编码压缩！
     */
    // 类比q459
    private String getEncodedStr(String s, int i, int j) {
        s = s.substring(i, j+1);
        int n = s.length();
        int idx = (s+s).indexOf(s, 1);
        if (n <= 4 || idx == n) { // len<=4 || 无重复，不可压缩
            return s;
        } else { // 可压缩
            String si = s.substring(0, idx); // 重复部分
            int cnt = n / si.length();
            return cnt + "[" + dp[i][i+idx-1] + "]";
        }
    }

}
