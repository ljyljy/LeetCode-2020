package Two_Pointers;

// ���Ӵ���������ȣ�q9_1870, Q9_1375
public class q9_1870_count_substr_with_all_zeros {
    public int stringCount(String str) {
        // ��ʽ˫ָ�룬ͳ��cnt
        int cnt = 0, sum = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                cnt++;
            } else {
                sum += countSubstr_allZeros(cnt);
                cnt = 0;
            }
        }
        if (cnt > 0) {
            sum += countSubstr_allZeros(cnt);
        }
        return sum;
    }

    // ����˼·�������ÿ��������0�ĸ�����Ȼ����ݵȲ������������Ӵ�������
    //���磺��000����, 0����Ϊcnt=3����3����0����2����00����1����000�����Ӵ�����=3+2+1=�Ȳ�����(1+3)*3/2=6��
    private int countSubstr_allZeros(int cnt) {
        return (1 + cnt) * cnt / 2;
    }
}
