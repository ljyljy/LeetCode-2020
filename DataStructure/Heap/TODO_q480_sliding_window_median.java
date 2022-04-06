package DataStructure.Heap;

import java.util.*;

public class TODO_q480_sliding_window_median {
    // ��1��˫��
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int cnt = n - k + 1;
        double[] ans = new double[cnt];
        // ����������������ڣ��� right �������� left ��һ��
        // [(1,2,3)<-L��, R��->(4,5,6,7)]
        PriorityQueue<Integer> left  = new PriorityQueue<>((a,b)->Integer.compare(b,a)); // �������ڵ���벿�֣��������ѣ�����С��,<mid��
        PriorityQueue<Integer> right = new PriorityQueue<>((a,b)->Integer.compare(a,b)); // �������ڵ��Ұ벿�֣�������С�ѣ��������,>=mid��
        for (int i = 0; i < k; i++) right.add(nums[i]);
        for (int i = 0; i < k / 2; i++) left.add(right.poll());

        ans[0] = getMid(left, right);
        for (int i = k; i < n; i++) {
            // ��Ϊȷ���� right ��� left �࣬��ˣ�ɾ������Ӷ��� right �Ƚϣ�left ����Ϊ�գ�
            int add = nums[i], del = nums[i - k];
            if (add >= right.peek()) {
                right.add(add);
            } else {
                left.add(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            adjust(left, right);
            ans[i - k + 1] = getMid(left, right);
        }
        return ans;
    }
    void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());
        while (right.size() - left.size() > 1) left.add(right.poll());
    }
    double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return (left.peek() / 2.0) + (right.peek() / 2.0);
        } else {
            return right.peek() * 1.0;
        }
    }

    // ��2��treeset
    public double[] medianSlidingWindow_TS(int[] nums, int k) {
        /*
        TreeSet�ⷨ:�ο���ѩ������������˼·
         */
        // ������������Ծ����ӿ���:nums=[0,1,2,3],k=3,��˻�����2��,��len-k+1
        int len = nums.length;
        double[] res = new double[len - k + 1];
        // set�洢����[nums[i],i]��pair
        // TreeSet���������Ϊ:Ԫ����ͬ,����������С������;Ԫ�ز�ͬ,����Ԫ��������
        // ��������Ԫ�ش�С����ֱ��������ܻᵼ�����(MIN_VALUE-MAX_VALUE)->��Integer.compare(a,b)
        Set<int[]> set = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : Integer.compare(a[0], b[0]));
        //װ���׸�������Ԫ��[0,k-1]
        for (int i = 0; i < k; i++) {
            set.add(new int[] {nums[i], i});
        }
        //�׸���λ��
        res[0] = getMid(set);
        // ��ʼ����滬������λ��
        for (int start = 1, end = k; end < len; start++, end++) {
            // ���µ�ǰ���ڵ�Ԫ��:��Ԫ�س�set,��Ԫ����set
            // nums[k]����
            set.add(new int[] {nums[end], end});
            // nums[0]�˳�
            set.remove(new int[]{nums[start - 1], start - 1});
            // ����´��ڵ���λ��������res
            res[start] = getMid(set);
        }
        return res;
    }

    /*
    ���ݻ����������TreeSet��������λ��
     */
    private double getMid(Set<int[]> set) {
        // �����midȡֵ�м���:size=5->mid=2;size=4->mid=1
        int mid = (set.size() - 1) / 2;
        // ��ȡset������
        Iterator<int[]> iterator = set.iterator();
        // �õ������ߵ��м��Ա�����λ��
        // ��:[0,1,2,3,4],size=5->mid=2->ָ��ͣ����Ԫ��1��
        // ��:[0,1,2,3],size=4->mid=1->ָ��ͣ����Ԫ��0��
        // ���÷��Ͻ���������λ�����
        while (mid-- > 0) iterator.next();
        // sizeΪż��->ָ��ͣ����Ԫ��0��->��λ��=(1+2)/2=1.5=(next()+next())/2
        // sizeΪ����->ָ��ͣ����Ԫ��1��->��λ��=2=next()
        return set.size() % 2 == 0 ? (((double)iterator.next()[0] + iterator.next()[0]) / 2.0) : iterator.next()[0];
    }
}
