package DataStructure.Map;

import java.util.HashSet;
import java.util.Set;


public class q1044_longest_duplicate_substring {
    int n;
    Set<Long> set2 = new HashSet<>();
    int P = 131313;
    long[] h, p;
    // ��2--���ִ𰸡��Ӵ����ȡ� + �ַ�����ϣ
    public String longestDupSubstring(String s) {
        n = s.length();
        String res = "";
        h = new long[n+10]; p = new long[n+10];
        p[0] = 1;
        // Ԥ�����ַ�����ϣ������<ǰ׺��>��
        for (int i = 1; i <= n; i++) {
            h[i] = h[i-1] * P + s.charAt(i-1);
            p[i] = p[i-1] * P;
        }

        int start = 0, end = n;// ������n������"aa"���""(AK="a")
        // [L, mid] [mid+1, R]
        while (start < end) {
            int mid = start + end >> 1;
            String tmp = check2(s, mid);
            if (!tmp.isEmpty())  start = mid+1; // �Ӵ����ȿ��ԼӴ�(������)
            else end = mid;
            res = tmp.length() > res.length()? tmp: res; // res��¼��Ӵ�
        }
        return res;
    }

    private String check2(String s, int len) {
        for (int i = 1; i+len-1 <= n; i++) {
            int j = i+len-1;
            String subStr = s.substring(i-1, j); // str[i-1, i-1+len)
            long hash = h[j] - h[i-1] * p[j-i+1];
            if (set2.contains(hash)) return subStr;
            set2.add(hash);
        }
        return "";
    }


    // ��1--TLE: ���ִ𰸡��Ӵ����ȡ� + ��ͨ��ϣ
    Set<String> set = new HashSet<>();
    public String longestDupSubstring_TLE(String s) {
        n = s.length();
        int start = 0, end = n; // ������n������"aa"���""(AK="a")
        String res = "";
        // [L, mid] [mid+1, R]
        while (start < end) {
            int mid = start + end >> 1;
            String tmp = check(s, mid);
            if (!tmp.isEmpty()) start = mid+1; // �Ӵ����ȿ��ԼӴ�(������)
            else end = mid;
            res = tmp.length() > res.length()? tmp: res; // res��¼��Ӵ�
        }
        return res;
    }

    private String check(String s, int len) {
        for (int i = 0; i+len <= n; i++) {
            String subStr = s.substring(i, i+len);
            if (set.contains(subStr)) return subStr;
            set.add(subStr);
        }
        return "";
    }
}
