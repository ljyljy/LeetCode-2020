package Greedy;

public class q860_lemonade_change {
    public boolean lemonadeChange(int[] bills) {
        // Arrays.sort(bills); // WA! 要按顺序找零，先来后到

        int five = 0, ten = 0; // 张数
        for (int bill: bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                ten++;
                if (five > 0) {// 找零5元
                    five--;
                } else {
                    return false;
                }
            } else { // bill == 20, 找零15元（5+10、5+5+5）
                if (five >= 1 && ten >= 1) {
                    five -= 1; ten -= 1;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
