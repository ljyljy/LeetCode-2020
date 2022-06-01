package Greedy;

public class q605_can_place_flowers {
    /**
     * 示例
     * [1,0,0,0,0,1], 2   -> false  // 解决：假设所有0都被1包围！使用 cnt += (curZeros-1) / 2;
     * [1,0,0,0,1,0,0], 2  -> true  // 解决：for结束后还需：统计最后一个区段的curZeros
     * [1,0,0,0,0], 2     -> true  // 解决：最后一个区段还需【最右哨兵’0’】
     * [0,0,1,0,1], 1     -> true  // 解决：加入【最左哨兵‘0’】，即：curZeros预设1
     */
    // 贪心，【坑：哨兵】
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        int curZeros = 1;// 目前连续0的数量，预设1-【左边界哨兵：虚无的0,避免漏掉最左以0起点的样例。】
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                cnt += (curZeros-1) / 2; // 默认连续的0两边都是1，且1为起点【需要设哨兵，否则会漏掉如[0,0,1],1 -> true】
                curZeros = 0; // 若以'1'为起点，则最左哨兵不起作用，覆盖为0
                if (cnt >= n) return true;
            } else {
                curZeros++;
            }
        }

        // 最后一段0区还未结算：
        curZeros++; // 【最右哨兵'0'】最后再预设1个0，因为最后花坛的最右边没有花，可以认为存在一个虚无的0
        cnt += (curZeros-1)/2;

        return cnt >= n;
    }

    public boolean canPlaceFlowers_WA(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0, prev = -1;
        int curZeros = 0;// 目前连续0的数量
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                cnt += (curZeros-1) / 2; // 默认连续的0两边都必须存在1
                curZeros = 0; //
                if (cnt >= n) return true;
            } else {
                curZeros++;
            }
        }

        // 最后一段0区还未结算：
        curZeros++; // 最后再预设1个0，因为最后花坛的最右边没有花，可以认为存在一个虚无的0
        cnt += (curZeros-1)/2;

        return cnt >= n;
    }
}

