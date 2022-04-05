package DataStructure.Deque;

import java.util.*;

// ���q224��227,772, HJ54
public class q224_q227_basic_calculator_ii {
    // �������ס���1�����Σ������⣺�����ڱ��ʽ��
    public int calculate0(String s) {
//        s = s.replaceAll("\\s", "");
        long n = s.length(), curRes = 0, num = 0, res = 0;
        char preOps = '+'; // ��ʼ��Ϊ+�����׸�num��ֵ��curRes
        char[] ss = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') { // �Һ�����֮��Ե�')'
                int prev = i, cnt = 0;
                for (; i < n; i++) {
                    if (ss[i] == '(') cnt++;
                    if (ss[i] == ')') cnt--;
                    if (cnt == 0) break; // ���'()'=[prev, i]
                }
                // ���á����Ρ���������������ڵı��ʽ�����������ű���
                num = calculate0(s.substring(prev+1, i));
            }                    // �¾䲻��дelse if���� �� ��
            if ((c+"").matches("[+\\-*/]") || i == n-1) {
                //��©��i==n-1���������Ҫ��curResȫ������res��
                switch (preOps) {
                    case '+': curRes += num; break;
                    case '-': curRes -= num; break;
                    case '*': curRes *= num; break;
                    case '/': curRes /= num; break;
                }
                // ����res������curRes���˳������ȼ��ߣ��ݴ�ΪcurRes��
                if (c == '+' || c == '-' || i == n-1) {
                    res += curRes;
                    curRes = 0;
                }
                // ����num��preOps
                num = 0;
                preOps = c;
            }

        }
        return (int)res;
    }



    // ��2��˫ջ�����ף�����^/%����������ȼ���
    Map<Character, Integer> opsMap = new HashMap<>() {{
        put('+', 1);  put('-', 1); // <'�����', ���ȼ�>
        put('*', 2);  put('/', 2);
        put('^', 3);  put('%', 3);
    }};

    public int calculate(String s) {
        s = s.replaceAll("\\s", "");
        char[] ss = s.toCharArray();
        int n = s.length();
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0); // �ڱ�������"(-7..." -> "(0-7..."
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            if (c == '(') { // 1) �����ţ�ѹջ������֮��Ե�')'
                ops.addLast(c);
            } else if (c == ')') {// 2) ������')'����ջ������������
                while (!ops.isEmpty()) {
                    // ���á�˫ջ�������������ڲ����ʽ��
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {// �����ڱ��ʽ������ϣ��������numsջ��
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(c)) { // 3) ����
                    int num = 0, j = i;
                    while (j < n && Character.isDigit(ss[j])) {
                        num = num * 10 + ss[j] - '0';
                        j++;
                    }
                    nums.addLast(num);
                    i = j-1; // i�±���µ�numĩβ
                } else { // 4) �����
//                    if (!(ss[i]+"").matches("\\+\\-*/\\^%")) continue;
                    if (i-1>=0 && (ss[i-1]+"").matches("[+\\-(]")) {
                        nums.addLast(0); // ���У�"+7..." -> "0+7..."
                    }
                    // ֻ����������
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char preOps = ops.peekLast(); // prev=*/  cur=+-
                        if (opsMap.get(preOps) >= opsMap.get(c)) {
                            // �������ȼ������㣬ops��ջ & ������ѹջnums
                            calc(nums, ops);
                        } else break; // �磺��ǰ��+-����Ҫ�ȴ��������ȼ��ȼ���
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
        return nums.peekLast();
    }

    // �������ȼ�/������ �����㣬ops��ջ & ������ѹջnums
    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.removeLast(), a = nums.removeLast();
        char op = ops.removeLast();
        int ans = 0;
        switch (op) {
            case '+': ans = a + b; break;
            case '-': ans = a - b; break;
            case '*': ans = a * b; break;
            case '/': ans = a / b; break;
            case '^': ans = (int)Math.pow(a, b); break;
            case '%': ans = a % b; break;
        }
        nums.addLast(ans);
    }
}
