package Other.math;

public class qo_62_lastRemainingInCircle {
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0; // ���ʣ�µ������±�Ϊ0
        return (lastRemaining(n-1, m) + m) % n;
    }

}
