package Bit;

public class q1758_min_changes_to_make_alternating_binStr {
    public int minOperations(String s) {
        int n = s.length();
        int cnt = 0;
        // �ȹ��� ����'0101...', ͳ�Ʊ仯����cnt
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != (char) ('0' + i % 2)) {
                cnt++;
            }
        }
        // ������'1010...'�ı仯����һ��Ϊn-cnt
        return Math.min(cnt, n - cnt);
    }
}
