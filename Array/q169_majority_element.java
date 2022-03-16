package Array;

public class q169_majority_element {
//    ˼·��ʹ��res & cnt��������ͬcnt++��ͬcnt--����󷵻�res�� ��Ϊ��������������һ�룬���Բ��ᱻ����0���¡�
    // ������ͬ�������Ի������������һ��������䵥���Ǹ�
    public int majorityElement(int[] nums) {
        int res = Integer.MIN_VALUE, cnt = 0;
        for (int num: nums) {
            if (cnt == 0) {
                res = num;
                cnt = 1;
            } else if (num == res) {
                cnt++;
            } else cnt--;
        }
        return res;
    }

}
