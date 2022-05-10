package String.KMP;

public class q459_repeated_substring_pattern {
    // ��1������--˫���ַ����� Ӧ�ã�q459,��a471��
    /**
     * ��Ŀ459��˼·�������£�
     * �� t = s + s, ���±�1���ַ���ʼ�����ַ���s�� �ҵ��±�idx��
     * ���磺s="abc", ��"t=a(bc abc)".indexOf("abc", 1)=3=len(s)=idx��˵��s���ظ�
     *       s="abcabc", ��"t=a(bc[abc abc]abc)".indexOf("abcabc", 1)=3<len(s)=6��˵��s�����ظ�
     */
    public boolean repeatedSubstringPattern0(String s) {
        // "a[bb abb]" -> ��[]���ڵ�һ��abb����������idx=len(s)��
        // "a[bc(abc abc)abc]" -> ��[]���ڵ�һ��abcabc����������idx=len(abc)��
        // System.out.println((s + s).indexOf(s, 1));
        return (s + s).indexOf(s, 1) != s.length();
    }

    // ��2��KMP���� - ֻ��next����
    public boolean repeatedSubstringPattern(String s) {
        int m = s.length();
        String p = " " + s;
        int[] next = new int[m+1];

        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j+1)) j = next[j];
            if (p.charAt(i) == p.charAt(j+1)) j++;
            next[i] = j;
        }
        int maxLen = next[m]; // �ǰ��׺�ĳ��ȣ���"abcdabcd abcd" -> next[m] = 8
        System.out.println(maxLen);
        return maxLen != 0 && m % (m - maxLen) == 0;
    }
}
