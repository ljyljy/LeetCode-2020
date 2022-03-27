package Other.math;

import java.util.Scanner;

public class HJ56_completeNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int cnt = 0;
            // num ∈ [2, N], 1不是完全数，跳过
            for (int num = 2; num <= n; num++) {
//                 Set<Integer> factors = new HashSet<>();
//                 factors.add(1);
                int sum = 1; // 算入因子1
                for (int fac = 2; fac*fac <= num; fac++) {
                    if (num % fac == 0) {
                        sum += fac;
                        if (fac != num / fac) {
                            sum += num / fac;
                        }
//                         factors.add(fac);
//                         factors.add(num / fac);
                    }
                }
                if (sum == num) cnt++;
            }
            System.out.println(cnt == 0? -1: cnt);
        }
    }
}
