package DataStructure.Deque;

import javax.script.*;
import java.util.Scanner;

public class q227_HJ54_eval {
    public static void main_eval(String[] args) throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            Integer res = (Integer)se.eval(str);
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            System.out.println(calculate(line));
        }
        sc.close();
    }

    // 类比q224，227,772, HJ54
    // 法1：分治（子问题：括号内表达式）
    private static int calculate(String s) {
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
                } // ↓ 分治，后面括号内的表达式，不包括括号本身！
                num = calculate(s.substring(prev+1, i));
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

    // 法2：双栈 & 运算符优先级（见q227，略）
}