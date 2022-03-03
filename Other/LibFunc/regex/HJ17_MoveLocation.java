package Other.LibFunc.regex;

import java.util.Scanner;

public class HJ17_MoveLocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int x = 0, y = 0;
            String s = sc.nextLine();
            String[] strs = s.split(";");
            for (String str: strs) {
                if (!str.matches("[ADWS][\\d]{1,2}")) { //[x-y]:匹配一个，{m,n}:出现m~n次
                    continue;
                }
                int loc_ = Integer.valueOf(str.substring(1)); // 数字[1,:)
                char dir_ = str.charAt(0);
                switch (dir_) { // switch-case ?
                    case 'A':
                        x -= loc_;
                        break; // 勿忘！
                    case 'D':
                        x += loc_;
                        break;
                    case 'S':
                        y -= loc_;
                        break;
                    case 'W':
                        y += loc_;
                        break;
                    default: // 勿忘！
                        break;
                }
            }
            System.out.println(x + "," + y);

        }
    }
}
