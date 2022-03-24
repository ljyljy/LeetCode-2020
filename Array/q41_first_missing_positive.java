package Array;

public class q41_first_missing_positive {
    // O(n)-ԭ�ع�ϣ�����qo_3��qo_53, q287
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // [3,4,-1,1]->[1, 2ȱ,3, 4]
            // Ŀ�꣺�±�i����ŵ���ֵΪi+1����[0]��3Ų����3-1��, ���±�[0/i]<->��nums[0/i]-1����, ��ǰ������
            // �����ÿ�α�������j=nums[i]Ų��Ŀ��λ��[j-1];
            //      ������������i!=nums[i]-1������Ϊȱʧ������i+1

            // int j = nums[i]-1; // Ŀ��Ų��λ�� ��������while����if��
            while (nums[i]-1 >= 0 && nums[i]-1 < n
                    && nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != nums[i]-1) {
                return i+1;
            }
        }
        return n+1; // ��[1,2,3,4], ȱ5
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
