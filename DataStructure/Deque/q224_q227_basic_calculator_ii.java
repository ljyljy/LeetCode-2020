package DataStructure.Deque;

import java.util.*;

// 类比q224，227,772, HJ54
public class q224_q227_basic_calculator_ii {
    // 【荐，易】法1：分治（子问题：括号内表达式）
    public int calculate0(String s) {
//        s = s.replaceAll("\\s", "");
        long n = s.length(), curRes = 0, num = 0, res = 0;
        char preOps = '+'; // 初始化为+，将首个num赋值给curRes
        char[] ss = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') { // 找后续与之配对的')'
                int prev = i, cnt = 0;
                for (; i < n; i++) {
                    if (ss[i] == '(') cnt++;
                    if (ss[i] == ')') cnt--;
                    if (cnt == 0) break; // 配对'()'=[prev, i]
                }
                // 利用【分治】，处理后面括号内的表达式，不包括括号本身！
                num = calculate0(s.substring(prev+1, i));
            }                    // 下句不可写else if！↓ ↓ ↓
            if ((c+"").matches("[+\\-*/]") || i == n-1) {
                //勿漏【i==n-1】：最后需要将curRes全部加入res↑
                switch (preOps) {
                    case '+': curRes += num; break;
                    case '-': curRes -= num; break;
                    case '*': curRes *= num; break;
                    case '/': curRes /= num; break;
                }
                // 更新res，重置curRes（乘除法优先级高，暂存为curRes）
                if (c == '+' || c == '-' || i == n-1) {
                    res += curRes;
                    curRes = 0;
                }
                // 重置num、preOps
                num = 0;
                preOps = c;
            }

        }
        return (int)res;
    }



    // 法2：双栈（进阶：补充^/%、运算符优先级）
    Map<Character, Integer> opsMap = new HashMap<>() {{
        put('+', 1);  put('-', 1); // <'运算符', 优先级>
        put('*', 2);  put('/', 2);
        put('^', 3);  put('%', 3);
    }};

    public int calculate(String s) {
        s = s.replaceAll("\\s", "");
        char[] ss = s.toCharArray();
        int n = s.length();
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0); // 哨兵，避免"(-7..." -> "(0-7..."
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            if (c == '(') { // 1) 左括号，压栈，找与之配对的')'
                ops.addLast(c);
            } else if (c == ')') {// 2) 右括号')'，弹栈，计算括号内
                while (!ops.isEmpty()) {
                    // 利用【双栈】，处理括号内部表达式，
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {// 括号内表达式计算完毕，结果处于nums栈顶
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(c)) { // 3) 数字
                    int num = 0, j = i;
                    while (j < n && Character.isDigit(ss[j])) {
                        num = num * 10 + ss[j] - '0';
                        j++;
                    }
                    nums.addLast(num);
                    i = j-1; // i下标更新到num末尾
                } else { // 4) 运算符
//                    if (!(ss[i]+"").matches("\\+\\-*/\\^%")) continue;
                    if (i-1>=0 && (ss[i-1]+"").matches("[+\\-(]")) {
                        nums.addLast(0); // 特判："+7..." -> "0+7..."
                    }
                    // 只计算括号内
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char preOps = ops.peekLast(); // prev=*/  cur=+-
                        if (opsMap.get(preOps) >= opsMap.get(c)) {
                            // 更高优先级先运算，ops弹栈 & 运算结果压栈nums
                            calc(nums, ops);
                        } else break; // 如：当前是+-，需要等待更高优先级先计算
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
        return nums.peekLast();
    }

    // 更高优先级/括号内 先运算，ops弹栈 & 运算结果压栈nums
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
