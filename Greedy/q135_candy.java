package Greedy;

import java.util.Arrays;

public class q135_candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int res = 0;
        int[] candies = new int[n];
        Arrays.fill(candies, 1); // ����1��
        // ��candies[i]����������(i��1)�ֱ������ÿ�α�֤�������Ҫ��
        // 1.��ǰ��󣬱�֤����-i����=���С+1
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1; // �ۻ������Ĺ��̣���Ҫ���������i����i-1��
        }
        // 2.�Ӻ���ǰ����֤����-���i����=max(oldֵ���Ҳ�С+1)
        for (int i = n-2; i >= 0; i--) {
            // i����[i+1]����Ҫ�������; ?ȡmax���½��ٽ�ֵ
            if (ratings[i] > ratings[i+1])
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
        }
        return Arrays.stream(candies).sum();
    }
}
