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
            // 条件1
            if (n <= 8) {
                System.out.println(NG);
                continue;
            }
            // 条件2
            int[] types = new int[4]; // 大写、小写字母、数字、其他
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
            // 条件3: q 分割的idx(若长度为3的子串，存在重复，则条件3不满足)
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

    // 法2：正则表达式???【荐！】
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            int n = s.length();
            // 条件1
            if (n <= 8) {
                System.out.println(NG);
                continue;
            }
            // 条件2
            int types = 0; // 大写、小写字母、数字、其他；
            String[] regs = {"[A-Z]", "[a-z]", "[0-9]", "[^a-zA-Z0-9]"}; // ?reg="[xx]": 字符集-匹配任意一个
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
            // 条件3：排除以下规则(.{3,})不可重复！reg分组:(..)；
            String reg2 = ".*(.{3,}).*(?=\\1).*"; // ?"(?=\\1)":后面匹配组1
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
            // 长度check, 相同长度超2的子串重复check
            if (s.length() <= 8 ) {
                System.out.println("NG");
                continue;
            }

            // 大小写字母.数字.其它符号check
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
            //匹配串前后连续3个字符一样的
            //public String replaceAll(String replacement)
            //替换模式与给定替换字符串相匹配的输入中存在长度大于2的不含公共元素的子串重复 删除一个重复字串长度显然变短了 这时输入NG
            // 分组(组1)(组2), "?=(组1内容)\\1"，
            if(s.replaceAll("(.{3,})(?=.{3,}\\1)", "").length() < s.length()){
                System.out.println("NG");
                continue;
            }
//            写法2：
            // // 条件3
//            String reg2 = ".*(.{3,}).*(?=\\1).*"; // ?
//            Pattern pattern2 = Pattern.compile(reg2);
//            Matcher matcher2 = pattern2.matcher("021Abc998nws2Abced21");
//            System.out.println("test: " +  matcher2.find());
            System.out.println("OK");
        }
    }
}
