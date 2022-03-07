package Other.math;

import java.util.Scanner;

public class q118_HJ53_math_triangle {
//    https://www.nowcoder.com/practice/8ef655edf42d4e08b44be4d777edbf43?tpId=37&tqId=21276&rp=1&ru=/ta/huawei&qru=/ta/huawei&difficulty=2&judgeStatus=&tags=/question-ranking
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(getAns(n));
        }
    }

    private static int getAns(int n) {
        if (n <= 2)
            return -1;
        else if (n % 2 == 1)
            return 2;
        else {
            if(n % 4 == 0) return 3;
            else return 4;
        }
    }
}
