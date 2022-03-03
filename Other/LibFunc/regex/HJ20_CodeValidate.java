package Other.LibFunc.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HJ20_CodeValidate {
    private static final String NG = "NG";
    private static final String OK = "OK";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            int n = s.length();
            // ����1
            if (n <= 8) {
                System.out.println(NG);
                continue;
            }
            // ����2
            int[] types = new int[4]; // ��д��Сд��ĸ�����֡�����
            for (char c: s.toCharArray()) {
                if ((c+"").matches("[A-Z]")) {
                    types[0] = 1;
                } else if ((c+"").matches("[a-z]")) {
                    types[1] = 1;
                } else if (Character.isDigit(c)) { //(c+"").matches("[0-9]")
                    types[2] = 1;
                } else {
                    types[3] = 1;
                }
            }
            int typeCnt = Arrays.stream(types).sum(); // .getAsInt();
            if (typeCnt < 3) {
                System.out.println(NG);
                continue;
            }
            // ����3: q �ָ��idx(������Ϊ3���Ӵ��������ظ���������3������)
            boolean condition3 = true;
            for (int q = 0; q + 3 <= n; q++) {
                String subStr = s.substring(q, q+3); // [0,3), [1,4)...
                String excludeStr = s.substring(q+3); // [3,n), [4,n)...
                if (excludeStr.contains(subStr)) { // ?String.contains
                    System.out.println(NG);
                    condition3 = false;
                    break;
                }
            }
            if (condition3) {
                System.out.println(OK);
                continue;
            }

        }
    }

    // ��2��������ʽ???��������
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            int n = s.length();
            // ����1
            if (n <= 8) {
                System.out.println(NG);
                continue;
            }
            // ����2
            int types = 0; // ��д��Сд��ĸ�����֡�������
            String[] regs = {"[A-Z]", "[a-z]", "[0-9]", "[^a-zA-Z0-9]"}; // ?reg="[xx]": �ַ���-ƥ������һ��
            for (String reg: regs) {
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    types += 1;
                }
            }
            if (types < 3) {
                System.out.println(NG);
                continue;
            }
            // ����3���ų����¹���(.{3,})�����ظ���reg����:(..)��
            String reg2 = ".*(.{3,}).*(?=\\1).*"; // ?"(?=\\1)":����ƥ����1
            Pattern pattern2 = Pattern.compile(reg2);
            Matcher matcher2 = pattern2.matcher(s);
            if (!matcher2.find()) {
                System.out.println(OK);
                continue;
            } else {
                System.out.println(NG);
                continue;
            }
        }
    }


//    https://blog.nowcoder.net/n/fc6993638c2d4ccaab24e8d8df019880
    public static void main2(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bf.readLine())!=null) {
            // ����check, ��ͬ���ȳ�2���Ӵ��ظ�check
            if (s.length() <= 8 ) {
                System.out.println("NG");
                continue;
            }

            // ��Сд��ĸ.����.��������check
            String[] regexes = {"\\d", "[a-z]", "[A-Z]", "[^\\da-zA-Z]"};
            int types = 0;
            for (String re : regexes) {
                Pattern p = Pattern.compile(re);
                Matcher m = p.matcher(s);
                if (m.find()) {
                    types += 1;
                }
            }
            if(types<3){
                System.out.println("NG");
                continue;
            }
            //ƥ�䴮ǰ������3���ַ�һ����
            //public String replaceAll(String replacement)
            //�滻ģʽ������滻�ַ�����ƥ��������д��ڳ��ȴ���2�Ĳ�������Ԫ�ص��Ӵ��ظ� ɾ��һ���ظ��ִ�������Ȼ����� ��ʱ����NG
            // ����(��1)(��2), "?=(��1����)\\1"��
            if(s.replaceAll("(.{3,})(?=.{3,}\\1)", "").length() < s.length()){
                System.out.println("NG");
                continue;
            }
//            д��2��
            // // ����3
//            String reg2 = ".*(.{3,}).*(?=\\1).*"; // ?
//            Pattern pattern2 = Pattern.compile(reg2);
//            Matcher matcher2 = pattern2.matcher("021Abc998nws2Abced21");
//            System.out.println("test: " +  matcher2.find());
            System.out.println("OK");
        }
    }
}
