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

    // ���q224��227,772, HJ54
    // ��1�����Σ������⣺�����ڱ��ʽ��
    private static int calculate(String s) {
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
                } // �� ���Σ����������ڵı��ʽ�����������ű���
                num = calculate(s.substring(prev+1, i));
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

    // ��2��˫ջ & ��������ȼ�����q227���ԣ�
}