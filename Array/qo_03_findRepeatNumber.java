package Array;

public class qo_03_findRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // Ŀ�꣺�±�i����ŵ���ֵΪi����[0]��2Ų��nums��2��, ���±�[0]<->��nums[2]����, ��ǰ������
            // �����ÿ�α�������j=nums[i]Ų��Ŀ��λ��[j]
            while (nums[i] != i) { // [2,0,1,1]->[0,1,2,1�ظ�]
                int j = nums[i]; // ����ƥ���j�ƶ���nums[j]��������
                swap(nums, i, j); // [1,0��,2��,1] -> [0��,1,2��,1]
                if (nums[i] == nums[j]) { // ��j>=i, ��ǰ��Ų��ͬ���������ظ���
                    return nums[j];
                }

            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

