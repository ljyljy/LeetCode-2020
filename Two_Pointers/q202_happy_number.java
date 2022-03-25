package Two_Pointers;

public class q202_happy_number {
    // 链表找环，快慢指针
    public boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    private int squareSum(int n) {
        int sum = 0;
        while(n > 0){
            int cur = n % 10;
            sum += cur * cur;
            n /= 10;
        }
        return sum;
    }
}
