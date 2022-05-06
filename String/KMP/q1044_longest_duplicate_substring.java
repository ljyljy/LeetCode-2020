package String.KMP;

import java.util.HashSet;
import java.util.Set;

// ��ȣ�ACW831, Q28, 214, 1044
public class q1044_longest_duplicate_substring {
    // ��2--���ִ𰸡��Ӵ����ȡ� + �ַ�����ϣ
    int n;
    Set<Long> set2 = new HashSet<>();
    int base = 131313;
    long[] h, p;
    public String longestDupSubstring_strHash(String s) {
        n = s.length();
        String res = "";
        h = new long[n+10]; p = new long[n+10];

        // Ԥ�����ַ�����ϣ������<ǰ׺��>��
        p[0] = 1; h[0] = 0;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i-1] * base + s.charAt(i-1);
            p[i] = p[i-1] * base;
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
            long hash = h[j] - h[i-1] * p[j-(i-1)];
            if (set2.contains(hash)) return subStr;
            set2.add(hash);
        }
        return "";
    }


    // ��1--TLE(��������): ���ִ𰸡��Ӵ����ȡ� + ��ͨ��ϣ
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

    private String check(String s, int len) { // ��ȡ�Ӵ�����==len
        for (int i = 0; i+len <= n; i++) { // ö�ٷָ��i
            String subStr = s.substring(i, i+len);
            if (set.contains(subStr)) return subStr;
            set.add(subStr);
        }
        return "";
    }

    // ��3��KMP - ö��p = s�Ӵ�������next[j]����¼�ǰ��׺����
    public String longestDupSubstring_KMP_TLE(String s) {
        int n = s.length();
        String maxSub = ""; s = " " + s;
        char[] ss = s.toCharArray();
        for (int k = 1; k <= n; k++) {
            String p = s.substring(k, n+1); // ö��pΪs[k:n)
            p = " " + p;
            int m = p.length();
            char[] pp = p.toCharArray();
            // ����next����
            int[] next = new int[m];
            for (int i = 2, j = 0; i <= m-1; i++) {
                while (j > 0 && pp[i] != pp[j+1]) j = next[j];
                if (pp[i] == pp[j+1]) j++;
                next[i] = j;
            }

            // �Ա�q218��ֻ��Ҫȡnext[m-1]��
            // �ӣ�������ֻ��next[m-1]����Ϊ����ֻ������j=m-1Ϊ��β��ǰ��׺��
            // ��Ҫ����p�������Ӵ��У��ǰ��׺���ȣ�������next[]��
            for (int len: next) {
                // ��ȡʱȥ����ͷ���ڱ�   �� (idx=0��)����˽�ȡ��Ҫ�������1λ(+1)
                String tmp = p.substring(1, len+1); // �� p[m-next[m-1]:m)
                //            String tmp = p.substring(m-next[m-1], m);
//                System.out.println(tmp);
                maxSub = tmp.length() > maxSub.length()? tmp: maxSub;
            }
        }
        return maxSub;
    }

    public static void main(String[] args) {
        String str = "nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy"; // "banana";
        q1044_longest_duplicate_substring sol = new q1044_longest_duplicate_substring();
        System.out.println(sol.longestDupSubstring_strHash(str));

        System.out.println(sol.longestDupSubstring_TLE(str));

        System.out.println(sol.longestDupSubstring_KMP_TLE(str));
    }
}
