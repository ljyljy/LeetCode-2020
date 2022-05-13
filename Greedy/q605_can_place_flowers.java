package Greedy;

public class q605_can_place_flowers {
    /**
     * ʾ��
     * [1,0,0,0,0,1], 2   -> false  // �������������0����1��Χ��ʹ�� cnt += (curZeros-1) / 2;
     * [1,0,0,0,1,0,0], 2  -> true  // �����for�������裺ͳ�����һ�����ε�curZeros
     * [1,0,0,0,0], 2     -> true  // ��������һ�����λ��衾�����ڱ���0����
     * [0,0,1,0,1], 1     -> true  // ��������롾�����ڱ���0����������curZerosԤ��1
     */
    // ̰�ģ����ӣ��ڱ���
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0, prev = -1;
        int curZeros = 1;// Ŀǰ����0��������Ԥ��1-����߽��ڱ������޵�0,����©��������0������������
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                cnt += (curZeros-1) / 2; // Ĭ��������0���߶���1����1Ϊ��㡾��Ҫ���ڱ��������©����[0,0,1],1 -> true��
                curZeros = 0; // ����'1'Ϊ��㣬�������ڱ��������ã�����Ϊ0
                if (cnt >= n) return true;
            } else {
                curZeros++;
            }
        }

        // ���һ��0����δ���㣺
        curZeros++; // �������ڱ�'0'�������Ԥ��1��0����Ϊ���̳�����ұ�û�л���������Ϊ����һ�����޵�0
        cnt += (curZeros-1)/2;

        return cnt >= n;
    }

    public boolean canPlaceFlowers_WA(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0, prev = -1;
        int curZeros = 0;// Ŀǰ����0������
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                cnt += (curZeros-1) / 2; // Ĭ��������0���߶��������1
                curZeros = 0; //
                if (cnt >= n) return true;
            } else {
                curZeros++;
            }
        }

        // ���һ��0����δ���㣺
        curZeros++; // �����Ԥ��1��0����Ϊ���̳�����ұ�û�л���������Ϊ����һ�����޵�0
        cnt += (curZeros-1)/2;

        return cnt >= n;
    }
}

