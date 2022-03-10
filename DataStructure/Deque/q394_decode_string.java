package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q394_decode_string {
    // ���q1190(����һ����)
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        Deque<String> st_res = new ArrayDeque<>();
        Deque<Integer> st_cnt = new ArrayDeque<>();
        for (char ch: s.toCharArray()) {
            if (ch == '[') {
                // �����µ������ţ���֮ǰ��sbѹջ�ݴ棬����ת
                st_res.push(sb.toString());
                st_cnt.push(cnt);
                cnt = 0; // ������һ���µ�
                sb.setLength(0);
            } else if (ch == ']') {  // ?��Ҫ�������ݴ��sb��cnt��ջ
                int tmpCnt = st_cnt.pop();// ?��cnt��pop�����Ǳ��ִ����cnt��
                String tmpRes = sb.toString().repeat(tmpCnt); // ���ֵ�"sb"*tmpCnt��
                sb = new StringBuilder(st_res.pop() + tmpRes);// ?����-ͷ�巨��pop���ı������ǰ����
            } else if (Character.isDigit(ch)) { // ��0��~��9��? ����ch-'0'!
                cnt = cnt * 10 + (ch - '0'); // ?�״�! ��: 12[abc], ����abc�ظ�12��
            } else sb.append(ch); // ��ĸ,�ݴ浽���ֵ�sb // WA: st_res.push(ch + "");
        }
        return sb.toString();
    }
}
