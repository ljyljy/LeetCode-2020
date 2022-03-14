package Other.math;


import java.util.*;

public class HJ50_Eval2{
    // ���ڴ��һ�������ŵļ���, ���ڼ򻯴���
    static Set<Character> brace = new HashSet<>();
    public static void main(String ... args){
        Scanner sc = new Scanner(System.in);
        // ��ʼ�������ż���
        brace.add('{');
        brace.add('(');
        brace.add('[');
        while(sc.hasNextLine()){
            // ���ַ�������ʼ������ԭ���ж�:
            // 1����������������-ǰ���λ�ü���һ��0����-4��Ϊ0-4��
            // ϸ�ڣ�ע��-��ͷ�ĵط�ǰ��һ������������[0-9]���߷�����[)}\\]]����9-0,(3-4)-5������ط��ǲ��ܼ�0��
            // ���ĺ�����������ֻ��������ţ���-9=>0-9, -(3*3)=>0-(3*3)
            // 2�������ַ�����������λ�ü�#, ��Ҫ��Ϊ�˷�ֹ���һ�������޷����������
            String exp = sc.nextLine().replaceAll("(?<![0-9)}\\]])(?=-[0-9({\\[])", "0") + "#";
            // �� replaceAll("(?<![0-9)}\\]])-(?=[0-9({\\[])", "0-") + "#";
            System.out.println(calculate(exp));
        }
    }
    private static int calculate(String exp){
        // ��ʼ��ջ
        Stack<Integer> opStack = new Stack<>();
        Stack<Character> otStack = new Stack<>();

        // ������¼��
        String num = "";
        for(int i = 0; i < exp.length(); i++){
            // ��ȡ�ַ�
            char c = exp.charAt(i);
            // ����ַ������֣������������ۼӵ�num����
            if(Character.isDigit(c)){
                num += c;
            }
            // �����������
            else{
                // ������ַ�������¼�����������ջ�������
                if(!num.isEmpty()){
                    int n = Integer.parseInt(num);
                    num = "";
                    opStack.push(n);
                }
                // ����������ս�����˳�
                if(c == '#')
                    break;
                    // ���������+-
                else if(c == '+' || c == '-'){
                    // ��ջ���߲�����ջ�����������ţ�����ջ
                    if(otStack.isEmpty() || brace.contains(otStack.peek())){
                        otStack.push(c);
                    } else {
                        // ����һֱ����ջ���㣬ֱ���ջ�������������Ϊֹ�������ջ
                        while(!otStack.isEmpty() && !brace.contains(otStack.peek()))
                            popAndCal(opStack, otStack);
                        otStack.push(c);
                    }
                }
                // �������*/
                else if(c == '*' || c == '/'){
                    // ��ջ��������������ջ�������ţ������������ȼ��͵������������ջ
                    if(otStack.isEmpty()
                            || brace.contains(otStack.peek())
                            || otStack.peek() == '+' || otStack.peek() == '-'){
                        otStack.push(c);
                    }else{
                        // ��������*��/��һֱ����ջ���㣬ֱ��ջ�������ȼ����Լ��͵ķ��ţ������ջ
                        while(!otStack.isEmpty()
                                && otStack.peek() != '+' && otStack.peek() != '-'
                                && !brace.contains(otStack.peek()))
                            popAndCal(opStack, otStack);
                        otStack.push(c);
                    }
                } else {
                    // ����������ž�ѹջ
                    if(brace.contains(c))
                        otStack.push(c);
                    else{
                        // �����ž�һֱ����ջ���㣬ֱ������������Ϊֹ
                        char r = getBrace(c);
                        while(otStack.peek() != r){
                            popAndCal(opStack, otStack);
                        }
                        // ��󵯳�������
                        otStack.pop();
                    }
                }
            }
        }
        // ��ʣ�µļ����ֱ꣬�������ջΪ��
        while(!otStack.isEmpty())
            popAndCal(opStack, otStack);
        // ���ؽ��
        return opStack.pop();
    }
    private static void popAndCal(Stack<Integer> opStack, Stack<Character> otStack){
        int op2 = opStack.pop();
        int op1 = opStack.pop();
        char ot = otStack.pop();
        int res = 0;
        switch(ot){
            case '+':
                res = op1 + op2;
                break;
            case '-':
                res = op1 - op2;
                break;
            case '*':
                res = op1 * op2;
                break;
            case '/':
                res = op1 / op2;
                break;
        }
        opStack.push(res);
    }
    private static char getBrace(char brace){
        switch(brace){
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
        }
        return '#';
    }
}