package Two_Pointers.simulate;

import java.util.Arrays;

public class q481_magical_string {
    public int magicalString(int n) {
        int[] nums = new int[n + 2];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 2;
        int last = 2; // nums[2]Ϊ2����������j=3��,��Ҫ���棨1��2���棩
        for (int i = 2, j = 3; i < n && j < n; i++) {
            int nxt = last == 2 ? 1 : 2;
            nums[j++] = nxt;
            if (nums[i] == 2) nums[j++] = nxt;
            last = nxt;
        }
        int cntOne = 0;
        for (int i = 0; i < n; i++) { // ֻ����[0,n]֮��
            if (nums[i] == 1) cntOne++;
        }
        return cntOne; // ȥ��nums[0,n]����Ĳ���
    }
}
