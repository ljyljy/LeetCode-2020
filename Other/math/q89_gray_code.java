package Other.math;

import java.util.ArrayList;
import java.util.List;

public class q89_gray_code {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>(){{add(0);}};
        int head = 1; // ÿ��<<1(��2)���ӵ�resÿ��Ԫ�ص����ס�
        // ģ�⣺i=1~n
        // ����resÿ�����������j--��
        for (int i = 0; i < n; i++) {
            for (int j = res.size()-1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }
        return res;
    }
}
