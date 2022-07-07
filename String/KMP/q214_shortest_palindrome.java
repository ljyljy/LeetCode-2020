package String.KMP;

// ��ȣ�ACW831, Q28, 214, 1044
public class q214_shortest_palindrome {
    // ��2��������KMP - O(n+m)��
    public String shortestPalindrome(String s) {
        // 1) Ԥ����s
        String s0 = s;
        StringBuffer rev_s = new StringBuffer(s).reverse();
//        String rev_s = reverseStr(s);
        s = s + "#" + rev_s.toString();
        String p = s;
        System.out.println("processed: " + s);

        // 2) KMPģ��
        int n = s.length(), m = p.length();
        s = " " + s; p = " " + p; // ��ͷKMP�ڱ���

        char[] ss = s.toCharArray(), pp = p.toCharArray();

        // 2-1) ����next����
        int[] next = new int[m+1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }

//        // 2-2) ƥ�� - ����
//        for (int i = 1, j = 0; i <= n; i++) {
//            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
//            if (ss[i] == pp[j+1]) j++;
//            if (j == m) {
////                System.out.print(i - m); // ƥ��ɹ�����ʼidx
//                j = next[j];
//            }
//        }
        // �Ա�q1044����Ҫ����next���飡
        int maxLen = next[m]; // �����ǰ׺�ĳ���, ����j=m=endΪ�յ���ƥ��ǰ׺����
        System.out.println("maxLen = next[m] = " + next[m]);
//        System.out.println("KMP.maxLen=" + maxLen);
        String fillPrefix = new StringBuilder(s0.substring(maxLen)).reverse().toString(); // �ǻ��ĵĺ�׺.��ת=>��Ϊ�ǻ��ĵ�ǰ׺

        return fillPrefix + s0;

    }

    // ��1������[����] - O(n^2)�� str[0:i) + revStr[n-i:n)
    public String shortestPalindrome_BF(String s) {
        int n = s.length();
        String rev_s = reverseStr(s); // (anana)bc -> cb(anana)
        for (int i = n; i >= 0; i--) { // i--����֤���ȱ�������Ļ���ǰ׺
            // i = n-2ʱ��str[0:n-2) == "anana" == rev_s[2:n)
            if (s.substring(0, i).equals(rev_s.substring(n-i))) {
                return rev_s.substring(0, n-i) + s;
            }
        }
        return "";
    }

    private String reverseStr(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        for (int i = 0, j = n-1; i < j; i++, j--) {
            char tmp = ss[i];
            ss[i] = ss[j];
            ss[j] = tmp;
        }
        return new String(ss);
    }

    private String reverseStr2(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // todo: ��3���ַ�����ϣ
    // https://leetcode-cn.com/problems/shortest-palindrome/solution/by-flix-be4y/
    public String shortestPalindrome_RK(String s) {
        int n = s.length(), pos = -1;
        int base = 131313; // ����
        long pow = 1; // Ϊ�˷�����㵹���ַ����� hash ֵ
        char[] c = s.toCharArray();
        long hash1 = 0, hash2 = 0;
        int mod = (int)1e6;
        String rev_s = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < n; i++) {
            hash1 = (hash1 * base + c[i]) % mod;
            // �����ַ����� hash ֵ, �������ַ�Ҫ�ŵ����λ
            hash2 = (hash2 + c[i] * pow)% mod;
            if (hash1 == hash2) {
                if (s.substring(0, i + 1).equals(rev_s.substring(n - i - 1))) {
                    pos = i;
                }
            }
            pow = (pow * base) % mod;
        }
        return new StringBuilder(s.substring(pos + 1)).reverse() + s;
    }


// WA-bug
//    final int base = 131313; // ����
//    final int mod = (int)1e6;
//    long[] h, p, h2, p2;
//    public String shortestPalindrome(String s) {
//        char[] c = s.toCharArray();
//        String rev_s = new StringBuilder(s).reverse().toString();
//        int n = s.length(), pos = -1;
////        long pow = 1; // Ϊ�˷�����㵹���ַ����� hash ֵ
////        long hash1 = 0, hash2 = 0;
//
//        // �ַ�����ϣ
//        h = new long[n+1]; p = new long[n+1];
//        h[0] = 0; p[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            h[i] = (h[i-1] * base + s.charAt(i-1)) % mod;
//            p[i] = (p[i-1] * base) % mod;
//        }
//
//        h2 = new long[n+1]; p2 = new long[n+1];
//        h2[0] = 0; p2[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            h2[i] = (h2[i-1] * base + s.charAt(n-i)) % mod;
//            p2[i] = (p2[i-1] * base) % mod;
//        }
//
//        for (int i = 0; i < n; i++) {
//            long hash1 = calcHash_ij(h, p,0, i+1); // hash[0,i]
//            long hash2 = calcHash_ij(h2, p2, 0, i+1); // hash[i:-1:0]
//            if (hash1 == hash2) {
//                // ���Ĵ�Ϊ[0, i]
//                if (s.substring(0, i + 1).equals(rev_s.substring(n-(i+1), n))) {
//                    pos = i;
//                }
//            }
//        }
//        System.out.println(pos);
//        System.out.println(s.substring(pos + 1, n));
//        return new StringBuilder(s.substring(pos + 1, n)).reverse() + s;
//    }
//
//    // ����ҿ�str[i,j)
//    private long calcHash_ij(long[] h, long[] p, int i, int j) {
//        // i = i+1; j = j+1;
//        long hash_ij = h[j] - h[i] * p[j-i];
//        return hash_ij % mod;
//    }

    // WA-BUG
//    public String shortestPalindrome(String s) {
//        int n = s.length(), pos = -1;
//        int base = 131313; // ����
////        long pow = 1; // Ϊ�˷�����㵹���ַ����� hash ֵ
//        long[] h = new long[n+1], p = new long[n+1];
//        char[] c = s.toCharArray();
//        long hash1 = 0, hash2 = 0;
//        int mod = (int)1e6;
//        String rev_s = new StringBuilder(s).reverse().toString();
//        for (int i = 0; i < n; i++) {
//            h[i+1] = (h[i] * base + c[i]) % mod;
//            // �����ַ����� hash ֵ, �������ַ�Ҫ�ŵ����λ
//            p[i+1] = (p[i] * base)% mod;
//
//
//            if (hash1 == hash2) {
//                if (s.substring(0, i + 1).equals(rev_s.substring(n - i - 1))) {
//                    pos = i;
//                }
//            }
//            pow = (pow * base) % mod;
//        }
//        return new StringBuilder(s.substring(pos + 1)).reverse() + s;
//    }


    public static void main(String[] args) {
        q214_shortest_palindrome sol = new q214_shortest_palindrome();

        String str = "ananab";
        System.out.println(sol.shortestPalindrome_BF(str));
        System.out.println(sol.shortestPalindrome(str));
        System.out.println("============");

        String str2 = "ananabc";
        System.out.println(sol.shortestPalindrome_BF(str2));
        System.out.println(sol.shortestPalindrome(str2));
        System.out.println("============");

        String str3 = "abcd";
        System.out.println(sol.shortestPalindrome_BF(str3));
        System.out.println(sol.shortestPalindrome(str3));
        System.out.println("============");
    }
}
