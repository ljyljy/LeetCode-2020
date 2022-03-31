package Other.math;

import java.util.Scanner;

/**
 * ��֤��Ƴ�˹���������κ�һ������m������������д��m����������֮�͡�
 *
 * ���磺
 *
 * 1^3=1
 *
 * 2^3=3+5
 *
 * 3^3=7+9+11
 *
 * 4^3=13+15+17+19
 *
 * ����һ��������m��m��100������m������д��m����������֮�͵���ʽ�����
 * ���ף�ʱ�临�Ӷȣ�O(m) ���ռ临�Ӷȣ�O(1)
 *
 * ���룺
 * 6
 * �����
 * 31+33+35+37+39+41
 */
public class HJ76_cube_3_consecutive_odd_sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            decompose(num);
        }
        sc.close();
    }

    private static void decompose(int n) {
        int[] ans = new int[n];
        int mid = n / 2;
        if (n % 2 == 0) {
            ans[mid] = n * n + 1;
        } else ans[mid] = n * n;

        for (int i = mid; i >= 1; i--) {
            ans[i - 1] = ans[i] - 2;
        }

        for (int i = mid; i + 1 < n; i++) {
            ans[i + 1] = ans[i] + 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int num: ans) sb.append(num + "+");
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
