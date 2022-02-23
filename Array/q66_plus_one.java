package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class q66_plus_one {
    // 类比q2、q415、q43、q67
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n-1]++;
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        // 从后往前进位/复制，末位+1
        for (int i = n-1; (i >= 0 || carry != 0); i--) {
            int x = i >= 0? digits[i] + carry: carry;
            res.add(x % 10); // 或头插 list.insert(0, num);
            carry = x / 10;
        }
        Collections.reverse(res);
        int size = res.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
