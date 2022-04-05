package Greedy;

public class q860_lemonade_change {
    public boolean lemonadeChange(int[] bills) {
        // Arrays.sort(bills); // WA! Ҫ��˳�����㣬������

        int five = 0, ten = 0; // ����
        for (int bill: bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                ten++;
                if (five > 0) {// ����5Ԫ
                    five--;
                } else {
                    return false;
                }
            } else { // bill == 20, ����15Ԫ��5+10��5+5+5��
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
