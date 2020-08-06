# 数据流滑动窗口平均值 · Moving Average from Data Stream
# Data Stream
# 队列
# 描述
# 给出一串整数流和窗口大小，计算滑动窗口中所有整数的平均值。
#
# 样例
# 样例1 :
#
# MovingAverage m = new MovingAverage(3);
# m.next(1) = 1 // 返回 1.00000
# m.next(10) = (1 + 10) / 2 // 返回 5.50000
# m.next(3) = (1 + 10 + 3) / 3 // 返回 4.66667
# m.next(5) = (10 + 3 + 5) / 3 // 返回 6.00000

from collections import deque


class MovingAverage(object):
    def __init__(self, size):
        self.queue = deque([])
        self.size = size
        self.sum = 0.0

    def next(self, val):
        if len(self.queue) == self.size:
            self.sum -= self.queue.popleft()

        self.sum += val
        self.queue.append(val)
        return self.sum / len(self.queue)


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param = obj.next(val)

if __name__ == "__main__":
    ma = MovingAverage(3)
    print(ma.next(1))
    print(ma.next(10))
    print(ma.next(3))
    print(ma.next(5))
