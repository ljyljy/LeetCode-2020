package Two_Pointers.simulate;

public class q38_count_and_say {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) { // �ظ�ģ��n-1�Σ�2nd��ʼ��
            StringBuilder sb = new StringBuilder(); // ÿ��ģ��clear
            int curLen = res.length(); // ��:"2 1"->"12 11"
            int start = 0, pos = 0;
            while (pos < curLen) { // cnt+ch
                while (pos < curLen && res.charAt(pos) == res.charAt(start)) {
                    pos++;
                }
                int cnt = pos - start;
                sb.append(cnt + "" + res.charAt(start));

                start = pos;
            }
            res = sb.toString();
        }
        return res;
    }
}
