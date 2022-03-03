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
                if (!str.matches("[ADWS][\\d]{1,2}")) { //[x-y]:ƥ��һ����{m,n}:����m~n��
                    continue;
                }
                int loc_ = Integer.valueOf(str.substring(1)); // ����[1,:)
                char dir_ = str.charAt(0);
                switch (dir_) { // switch-case ?
                    case 'A':
                        x -= loc_;
                        break; // ������
                    case 'D':
                        x += loc_;
                        break;
                    case 'S':
                        y -= loc_;
                        break;
                    case 'W':
                        y += loc_;
                        break;
                    default: // ������
                        break;
                }
            }
            System.out.println(x + "," + y);

        }
    }
}
