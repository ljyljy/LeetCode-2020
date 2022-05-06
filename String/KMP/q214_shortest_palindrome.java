package String.KMP;

// ��ȣ�ACW831, Q28, 214
public class q214_shortest_palindrome {
    // ��2��KMP - O(n+m)��
    public String shortestPalindrome(String s) {
        String s0 = s;
        StringBuffer rev_s = new StringBuffer(s).reverse();
//        String rev_s = reverseStr(s);
        s = " " + s + "#" + rev_s.toString(); // ��ͷKMP�ڱ���
        String p = s;
        int n = s.length(), m = p.length();
        char[] ss = s.toCharArray(), pp = p.toCharArray();

        // 1) ����next����
        int[] next = new int[m]; // m�������ڱ�������m+1����q28��acw831ͬ����
        for (int i = 2, j = 0; i <= m-1; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }

//        // 2) ƥ�� - ����
//        for (int i = 1, j = 0; i <= n; i++) {
//            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
//            if (ss[i] == pp[j+1]) j++;
//            if (j == m) {
////                System.out.print(i - m); // ƥ��ɹ�����ʼidx
//                j = next[j];
//            }
//        }
        int maxLen = next[m-1]; // �����ǰ׺�ĳ���, ����j=m=endΪ�յ���ƥ��ǰ׺����
        System.out.println("KMP.maxLen=" + maxLen);
        String fillPrefix = new StringBuilder(s0.substring(maxLen)).reverse().toString(); // �ǻ��ĵĺ�׺.��ת=>��Ϊ�ǻ��ĵ�ǰ׺

        return fillPrefix + s0;

    }

    // ��1������[����] - O(n^2)�� str[0:i) + revStr[n-i:n)
    public String shortestPalindrome_BF(String s) {
        int n = s.length();
        String rev_s = reverseStr(s); // (anana)bc -> cb(anana)
        for (int i = n; i >= 0; i--) {
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
