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

    // old: StringBuilder()
    public String reverseStr0(String s, int k) {
        StringBuffer sb = new StringBuffer(s);
        StringBuffer newStr = new StringBuffer(sb);
        int j = 0, n = s.length();
        boolean flag = true;

        for (int i = 0; i < n; i += k) { // ❤注意 i不是++，而是+=k！
            int start = j * k, end = (j+1)* k < n? (j+1)* k: n;

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
}
