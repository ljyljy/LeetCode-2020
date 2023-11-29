package String;

public class q541_reverse_string_ii {
    // 最新版本
    public String reverseStr(String s, int k) {
        int n = s.length();
        boolean flag = true; // isReverse
        char[] ss = s.toCharArray();
        for (int i = 0; i < n; i += k) {
            int start = i, end = Math.min((i+k), n)-1;
            if (flag) {
                reverse(ss, start, end);
            }
            flag = !flag;
        }
        return new String(ss);
    }

    private void reverse(char[] ss, int i, int j) {
        while (i < j) {
            char tmp = ss[i];
            ss[i] = ss[j];
            ss[j] = tmp;
            i++;
            j--;
        }
    }

    // 法2
    public String reverseStr02(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        StringBuilder subStr = new StringBuilder();
        int n = s.length();
        boolean flag = true;
        for (int i = 0; i < n; i += k) {
            int j = Math.min(i + k, n);
            if (flag) {
                subStr = new StringBuilder(sb.substring(i, j)).reverse();
                sb.replace(i, j, subStr.toString());
            }
            flag = !flag;
        }
        return sb.toString();
    }

    // old: StringBuilder()
    public String reverseStr0(String s, int k) {
        StringBuffer sb = new StringBuffer(s);
        StringBuffer newStr = new StringBuffer(sb);
        int j = 0, n = s.length();
        boolean flag = true;

        for (int i = 0; i < n; i += k) { // ❤注意 i不是++，而是+=k！
            int start = j * k, end = (j + 1) * k < n ? (j + 1) * k : n;

            if (flag && start <= i && i <= end) {
                // ❤字符串s.substring,  sb.reverse().toString()【字符串无reverse!】
                String subStr = new StringBuffer(s.substring(start, end))
                        .reverse().toString();
                newStr = sb.replace(start, end, subStr);
                j++;
            } else { // !flag
                j++;
            }
            flag = !flag;
        }
        return newStr.toString();
    }

    public static void main(String[] args) {
        q541_reverse_string_ii obj = new q541_reverse_string_ii();
        System.out.println(obj.reverseStr02("abcdefg", 2));
    }
}
