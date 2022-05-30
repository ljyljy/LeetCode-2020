package Binary_Search;

import java.util.Scanner;

public class acw_790_3sqrt_float_bin_search {
    private static final double EPS = 1e-8;
    private static double binSearch_float(double x, double start, double end) {
        while (start + EPS < end) { // [L, mid] [mid, R]
            double mid = (start + end) / 2.0;
            if (cube(mid) < x) {
                start = mid; // 无需+1！double型也不应该加的是1.0！
            } else end = mid;
        }
        return start;
    }

    private static double cube(double num) {
        return num * num * num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        // # ∵−10000≤n≤10000 ∴3次方根∈[-22, 22]
        double start = -22.0, end = 22.0;
        double ans = binSearch_float(n, start, end);
        System.out.format("%.6f", ans);
    }
}