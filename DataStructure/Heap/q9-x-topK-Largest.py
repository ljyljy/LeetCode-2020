import heapq


def __init__(self, k):
    self.k = k
    self.heap = []


# O(logk) (控制堆中只有k个数，可以将O(logn)->O(logk))
# @param {int} num an integer
def add(self, num):
    heapq.heappush(self.heap, num)
    if len(self.heap) > self.k:
        heapq.heappop(self.heap)


# O(klogk) (控制堆中只有k个数，可以将O(klogn)->O(klogk))
# @return {int[]} the top k largest numbers in array
def topk(self):
    return sorted(self.heap, reverse=True)
