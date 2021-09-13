package String;

public class q541_reverse_string_ii {
    public String reverseStr(String s, int k) {
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
