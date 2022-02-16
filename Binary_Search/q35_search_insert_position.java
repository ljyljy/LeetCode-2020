package Binary_Search;

public class q35_search_insert_position {
    // https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
    // ���Q34
    // ��1�����д����ұ߽紦
    public int searchInsert1(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n-1;
        if (target > nums[end]) return n;
        // ������ nums[left..right] ����ҵ� 1 �����ڵ��� target ��Ԫ�ص��±�
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid+1;
            } else { // ȡ��ʱ���𰸾���mid���������䣨���ڼ�if�ȣ�return mid��
                end = mid;
            }
        }
        return start;
    }

    // ��2�����������ұ߽� - ֱ��end��ʼ��Ϊn
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid+1;
            } else { // ȡ��ʱ���𰸾���mid���������䣨���ڼ�if�ȣ�return mid��
                end = mid;
            }
        }
        return start;
    }
}
