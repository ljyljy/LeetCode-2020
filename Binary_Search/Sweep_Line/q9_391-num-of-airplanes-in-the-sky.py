from typing import List

class Interval:
    def __init__(self, start, end):
        self.start = start
        self.end = end

class Solution:
    # 法1：扫描线
    # 将起飞时间和降落时间放到同一个数组中, 标识出是起飞还是降落时间, 然后对数组排序.
    # 遍历数组即可, 碰到起飞计数器加一, 碰到降落计数器减一. 维护最大值作为答案.
    # 注意降落优先于起飞.
    def countOfAirplanes(self, airplanes: Interval):
        points = []
        for airplane in airplanes:
            points.append([airplane.start, 1])
            points.append([airplane.end, -1])

        n_airplane, maxN_airplane = 0, 0
        for _, count_delta in sorted(points):  # 对数组按时间排序
            n_airplane += count_delta
            maxN_airplane = max(maxN_airplane, n_airplane)
        return maxN_airplane

    # 法2：前缀和
    def countOfAirplanes2(self, airplanes: Interval):
        prefix_sum = {}
        for i in airplanes:
            prefix_sum[i.start] = prefix_sum.get(i.start, 0) + 1
            prefix_sum[i.end] = prefix_sum.get(i.end, 0) - 1
        n_airplane, maxN_airplane = 0, 0
        for time_i in sorted(prefix_sum.keys()):
            n_airplane = n_airplane + prefix_sum[time_i]
            maxN_airplane = max(maxN_airplane, n_airplane)
        return maxN_airplane

    # 法3：贪心+小根堆
    # 首先对所有时间段按照起飞时间升序排序.
    # 遍历这个序列, 遍历的过程中把当前的飞机的降落时间添加到小根堆中,
    # 并把堆中所有小于当前飞机起飞时间的元素弹出.
    # 此时的堆中储存的就是当前飞机在天上时, 同时还在天上的飞机.
    # 对堆中元素数量取max得到答案

    ## Java
    # class Solution {
    # public:
    #     priority_queue<int,vector<int>,greater<int> >q;
    #
    #     static bool cmp(const Interval& a, const Interval& b){
    #         return a.start < b.start;
    #     }
    #
    #     /**
    #      * @param intervals: An interval array
    #      * @return: Count of airplanes are in the sky.
    #      */
    #     int countOfAirplanes(vector<Interval> &airplanes) {
    #         sort(airplanes.begin(), airplanes.end(), cmp);
    #         int res = 0;
    #         int n = airplanes.size();
    #         for (int i = 0; i < n; i++) {
    #             while (q.size() > 0 && q.top() <= airplanes[i].start)
    #                 q.pop();
    #             q.push(airplanes[i].end);
    #             res = max(res, (int)q.size());
    #         }
    #         return res;
    #     }
    # };