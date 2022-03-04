package DP;

import java.util.Arrays;
import java.util.Scanner;

public class q300_HJ24_Chorus_LIS_Reversed {
//    https://blog.nowcoder.net/n/960f798e727e43889dd69959b4214332

/*���������������
�м���ߣ��������𽥼�С�����Ҳ���У�����Ҫ����ߵ�ͬѧ��������������ȡ�
������ı����Ԫ�ص��Ⱥ�˳��Ҳ����˵��ֻ���޳���������
����������Ҫ���м���ͬѧ��������Ҫ��Ҳ����˵���޳�ĳЩͬѧ��ʣ�µĶ�����Ȼ��Ȼ������Ҫ��

��1�������ÿ��ͬѧ�������м���������������������������Ҫ�󣨰����Լ�����
ʾ����186 186 150 200 160 130 197 200
      1   1   1   2   2   1   3   4
��̬���̣�
��2�������ÿ��ͬѧ�ұ�����м�����������ҵ����������������Ҫ�󣨰����Լ�����
ʾ����186 186 150 200 160 130 197 200
      3   3   2   3   2   1   1   1
��̬���̣�
��3���������������������Ӽ�һ���Լ��������飩���͵õ��Ը���Ϊ����ʱ�����ڶ������������
Ȼ������������еĶ��е����������Ȼ���������������������Сֵ���������޳�������
������-�������ڶ�������=��Ҫ���ӵ�����
*/


public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
//             System.out.println(LIS(nums, n));
        int[] LIS_ordered = LIS(nums, n);
        int[] LIS_reversed = LIS2(nums, n);
        System.out.println(calcMinKick(LIS_ordered, LIS_reversed));
    }
}

    private static int[] LIS(int[] nums, int n) {
        int[] dp = new int[n]; // �ԡ�i����β���LIS
        Arrays.fill(dp, 1);
        //�������������������
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }

    //��������Ҳ����������
    private static int[] LIS2(int[] nums, int n) {
        int[] dp = new int[n]; // �ԡ�i����β���LIS
        Arrays.fill(dp, 1);
        for (int i = n-2; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }

    private static int calcMinKick(int[] LIS1, int[] LIS2) {
        int n = LIS1.length;
        int minKick = n;

        for (int i = 0; i < n; i++) {
            //���������޳����� = n - (lmax[i] + rmax[i] - 1)
            int meetCnt = LIS1[i] + LIS2[i] - 1; // ȥ���Լ��ظ������1��
            minKick = Math.min(minKick, n - meetCnt);
        }
        return minKick;
    }
}

