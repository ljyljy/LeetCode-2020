//
// Created by ljylj on 2022/8/25.
//


#include <limits.h>
#include <math.h>

int maxArea(int* height, int n){
    int left = 0, right = n - 1;
    int maxArea = INT_MIN;
    for (int i = 0; i < n; ++i) {
        int minH = height[left] < height[right]? height[left++]: height[right--];
        int width = right - left + 1;
        maxArea = fmax(maxArea, width * minH);
    }
    return maxArea;
}

