package DataStructure.Deque;

public class q622_design_circular_queue {
    private int[] queue;
    private int head, tail,cnt, capacity;

    public q622_design_circular_queue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.head = 0;
        this.tail = 0;
        this.cnt = 0;
    }

    public boolean enQueue(int value) {
        // ¶ÓÁĞÂú
        if (this.cnt == this.capacity) return false;
        tail = (head + cnt) % capacity; // (tail+1) % capacity
        queue[tail] = value;
        cnt++;
        return true;
    }

    public boolean deQueue() { // headºóÒÆ
        if (cnt == 0) return false;
        head = (head + 1) % capacity;
        cnt--;
        return true;
    }

    public int Front() {
        if (cnt == 0) return  -1;
        return queue[head];
    }

    public int Rear() {
        if (cnt == 0) return -1;
        tail = (head + cnt - 1) % capacity;
        return queue[tail];
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public boolean isFull() {
        return cnt == capacity;
    }
}

