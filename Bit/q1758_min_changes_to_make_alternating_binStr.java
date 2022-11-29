package Bit;

public class q1758_min_changes_to_make_alternating_binStr {
    public int minOperations(String s) {
        int n = s.length();
        int cnt = 0;
        // 先构造 形如'0101...', 统计变化次数cnt
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != (char) ('0' + i % 2)) {
                cnt++;
            }
        }
        // 则形如'1010...'的变化次数一定为n-cnt
        return Math.min(cnt, n - cnt);
    }
}
