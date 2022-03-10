package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q394_decode_string {
    // 类比q1190(几乎一样！)
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        Deque<String> st_res = new ArrayDeque<>();
        Deque<Integer> st_cnt = new ArrayDeque<>();
        for (char ch: s.toCharArray()) {
            if (ch == '[') {
                // 遇到新的左括号，将之前的sb压栈暂存，待后翻转
                st_res.push(sb.toString());
                st_cnt.push(cnt);
                cnt = 0; // 计算下一轮新的
                sb.setLength(0);
            } else if (ch == ']') {  // ?需要将本轮暂存的sb、cnt弹栈
                int tmpCnt = st_cnt.pop();// ?非cnt！pop出的是本轮处理的cnt！
                String tmpRes = sb.toString().repeat(tmpCnt); // 本轮的"sb"*tmpCnt次
                sb = new StringBuilder(st_res.pop() + tmpRes);// ?保序-头插法（pop出的本身就是前串）
            } else if (Character.isDigit(ch)) { // ‘0’~‘9’? 勿忘ch-'0'!
                cnt = cnt * 10 + (ch - '0'); // ?易错! 如: 12[abc], 即：abc重复12次
            } else sb.append(ch); // 字母,暂存到本轮的sb // WA: st_res.push(ch + "");
        }
        return sb.toString();
    }
}
