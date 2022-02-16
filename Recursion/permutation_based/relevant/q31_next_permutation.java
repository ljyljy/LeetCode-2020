package Recursion.permutation_based.relevant;

import java.util.Arrays;

public class q31_next_permutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // �Ӻ���ǰ�ҵ���һ�����������
        // [1,2,3,8,(5[i-1/�յ�], 7[i]),6,4]
        for (int i = n-1; i-1 >= 0; i--) {
            if (nums[i] > nums[i-1]) { // �ҵ��յ㡾i-1��
                // ԭ���޸���һ�����У�[6, 457]
                Arrays.sort(nums, i, n); // �յ��[i,end) ����-> [4,6,7]
                for (int j = i; j < n; j++) { // �Ӻ���j=[i,end)�ұȹյ�����С������յ��滻
                    if (nums[j] > nums[i-1]) {
                        swap(nums, i-1, j);
                        return;
                    }
                }
            }
        }
        // ���䣺���nums��������ô��һ������Ϊ���¿�ʼѭ�����������У���
        Arrays.sort(nums);
        return;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
