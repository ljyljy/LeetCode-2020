package Recursion.partition_based;

import java.util.Arrays;

public class q91_decode_ways {
    // 写法1-2
    int n;
    int[] memo;
    public int numDecodings2(String s) {
        n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(0, s, memo);
    }

    private int dfs(int idx, String s, int[] memo) {
        if (idx == n) return 1;
        if (memo[idx] != -1) return memo[idx];
        // 前导‘0’无对应映射 ↓
        if (s.charAt(idx) == '0') return 0;// 模拟切割s：s以【idx为起始点】
        // int num = Integer.valueOf(s);
        // if (1 <= num && num <= 26) return 1; // 【不可】还有别的可能性！
        // if (num > 26) return 0; // 写法1

        int cnt = 0;
        for (int i = idx; i < idx+2 && i < n; i++) {
            String seg = s.substring(idx, i+1);
            if (Integer.valueOf(seg) > 26) continue; // 写法2
            cnt += dfs(i+1, s, memo);  // 【不可传入seg！】否则idx会乱套！
        }

        memo[idx] = cnt;
        return cnt;
    }

    // 法1：DFS【结合q9_680】 + memo
    // private int ans = 0;
    public int numDecodings_dfs(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] chars = s.toCharArray();
//        Map<Integer, Integer> memo = new HashMap<>();
        int[] memo = new int[n]; // 优化：数组代替哈希
        Arrays.fill(memo, -1); // 初始化memo，勿漏！
        return dfs(chars, 0, memo);
    }

    private int dfs(char[] chars, int idx, int[] memo) {
        if (idx == chars.length) return 1; // ❤完成一个匹配，ans+=1
        if (chars[idx] == '0') return 0; // ❤❤❤前导‘0’无对应映射
        if (memo[idx] != -1) return memo[idx];
        int ans = 0;  // 只控制某结点的下一层
        // for (int i = idx; i < idx + 2; i++) { // ❤ for 循环不可取！因为情况2需要额外特判(<=26), 无法与情况1合并为for循环！

        // 情况1: 分割1个字符
        ans += dfs(chars, idx+1, memo);
        // 情况2：分割2个字符（∵需要特判<=26 ∴不可与情况1合并成for循环）
        if (idx + 2 <= chars.length){
            if ((chars[idx] == '2' && chars[idx+1] <= '6')
                    || chars[idx] == '1' )
                ans += dfs(chars, idx+2, memo);
        }
        // }
        memo[idx] = ans;
        return memo[idx];
    }

    // dfs写法2 - 推荐
    private int dfs2(char[] chars, int idx, int[] memo) {
        if (idx == chars.length) return 1;//❤每次达到最后，就是一种解，ans+=1
        if (memo[idx] != -1) return memo[idx];
        if (chars[idx] == '0') return 0; // ❤❤❤去除'前导0'的情况

        int ans = 0; // 只控制本层
        for (int i = idx; i < idx + 2 && i < chars.length; i++) {
            String numStr = new String(chars, idx, i-idx+1);
            //若numStr < 26就符合要求，dfs下层
            if (Integer.parseInt(numStr) > 26) continue;
            ans += dfs2(chars, i+1, memo);
        }
        return memo[idx] = ans;
    }

    // 法2-1：动归
    public int numDecodings_v2_1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n+1];
        dp[0] = 1; // 当没有前导0时，至少有一种分割方法，如: 9->'I'
        for (int i = 1; i <= n; i++) {
            if ('1' <= chars[i-1] && chars[i-1]  <= '9')
                dp[i] += dp[i-1]; // 排除前导0, 前方一位数'b'∈[1,9]
            // 前方两位数'ab'∈[0, 26]，且不包括前导0！
            if (i < 2) continue;
            if ((chars[i-2] == '2' && chars[i-1] <= '6') || chars[i-2] == '1')
                dp[i] += dp[i-2];
        }
        return dp[n];
    }

    // 法2-2：动归
    public int numDecodings_v2_2(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n+1];
        f[0] = 1; // 当没有前导0时，至少有一种分割方法，如: 9->'I'
        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }

    // 法2-3：动归 - 空间优化 O(1)
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length(), mod = 3;
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] dp = new int[mod];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i % mod] = 0;
            int a = cs[i] - '0', b = (cs[i-1] - '0') * 10 + (cs[i] - '0');
            if (1 <= a && a <= 9) dp[i % mod] += dp[(i-1) % mod];
            if (10 <= b && b <= 26) dp[i % mod] += dp[(i-2) % mod];
        }
        return dp[n % mod];
    }


}
