package Two_Pointers.Sliding_Window;

public class q1052_grumpy_bookstore_owner {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int curSum = 0, n = customers.length;
        for (int i = 0; i < n; ++i) {
            if (grumpy[i] == 0) { // �ϰ岻����
                curSum += customers[i]; // �˿���������
            }
        }
        int left = 0, right = 0, maxSum = curSum;
        while (right < n) {
            if (grumpy[right] == 1) {// �ϰ����� -> ������
                curSum += customers[right];
            }
            ++right;
            while (right - left >= minutes) { // ������С
                maxSum = Math.max(maxSum, curSum);
                if (grumpy[left] == 1) {// �ϰ����� -> ��������
                    curSum -= customers[left];
                }
                ++left;
            }

        }
        return maxSum;
    }
}
