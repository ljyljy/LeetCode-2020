package DataStructure.Map;

import java.util.ArrayList;
import java.util.List;

public class q442_find_all_duplicates_in_array {
    /*
   ��1) ԭ�ع�ϣ: ʱ�临�Ӷ�O(n) �ռ临�Ӷ�O(1)
   ����һ������nums ,������nums[i]ֵ ��Ӧ��λ�� i-1 �� n
   ע�� ȡ���ǻ�ԭ�䱾����λ�� (nums[i] - 1) % n
   ����nums��ÿ����������һ�� �� ���Ρ�
   ���Գ������ε�����nums[i], ���Ӧ��i���ۼ����Σ�������2*n
   ����λ��i �ϵ��� ��С��2*n

   */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int n = nums.length;
        for (int num: nums) {
            int idx = (num - 1) % n; // num��[1,n], -1��ȡ�࣬��֤<hashIdx,num>��Ч
            nums[idx] += n;
        }
        for (int idx = 0; idx < n; idx++) {
            if (nums[idx] > 2*n) {
                res.add(idx + 1); // num == idx+1
            }
        }
        return res;
    }
}
