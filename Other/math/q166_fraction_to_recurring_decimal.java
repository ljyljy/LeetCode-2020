package Other.math;

import java.util.HashMap;
import java.util.Map;

public class q166_fraction_to_recurring_decimal {
    // numerator�����ӣ�denominator����ĸ
    // ģ�� + Map - ʱ����O(M), M=�𰸳���
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator; // ת long ���㣬��ֹ���
        if (a % b == 0) return String.valueOf(a / b); // ������ֱ�ӷ���
        StringBuilder sb = new StringBuilder();
        if (a * b < 0) sb.append("-"); // ���Ϊ��
        a = Math.abs(a); b = Math.abs(b);

        // ����С����ǰ�Ĳ��֣�����������ֵ�� a
        sb.append(String.valueOf(a/b) + ".");
        a %= b; // ����С�����֣�a��Ϊ����
        Map<Long, Integer> map = new HashMap<>(); // <����num, idx>
        while (a != 0) { // ������Ϊ0
            map.put(a, sb.length()); // idx=len(sb),��С����ռ��1λ��idx����-1
            a *= 10; // ����*10���ٳ���b ��
            sb.append(a / b); // ��
            a %= b; // ������
            // �����ǰ����֮ǰ���ֹ����� [����λ��map.get(a) �� ��ǰλ��idx] �Ĳ��ֿٳ�����ѭ��С�����֣�
            if (map.containsKey(a)) {
                int firstIdx = map.get(a);
                return String.format("%s(%s)",
                        sb.substring(0, firstIdx), sb.substring(firstIdx));
            }
        } // �˳�while��˵��С������û��ѭ������������*10������b
        return sb.toString();
    }
}
