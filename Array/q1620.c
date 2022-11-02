#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>


// q1620_coordinate_with_maximum_network_quality
int getSquareDist(int x, int y, int* tower);

int* bestCoordinate(int** towers, int n, int* towersColSize, int radius, int* returnSize) {
    int xMax = INT_MIN, yMax = INT_MIN;
    for (int i = 0; i < n; i++) {
        int* tower = towers[i];
        int x = tower[0], y = tower[1];
        xMax = fmax(xMax, x);
        yMax = fmax(yMax, y);
    }

    int cx = 0, cy = 0;
    int maxQuality = 0;
    for (int x = 0; x <= xMax; x++) {
        for (int y = 0; y <= yMax; y++) {
            int quality = 0;
            for (int i = 0; i < n; i++) {
                int squareDist = getSquareDist(x, y, towers[i]);
                if (squareDist <= radius * radius) {
                    quality += floor((double)towers[i][2] / (1 + sqrt(squareDist)));
                }
            }
            if (quality > maxQuality) { // 严格大于 + xy从小遍历 = 字典序
                maxQuality = quality;
                cx = x;
                cy = y;
            }
        }
    }
    int* ans = (int*)malloc(sizeof(int) * 2);
    ans[0] = cx, ans[1] = cy;
    *returnSize = 2;
    return ans;
}

int getSquareDist(int x, int y, int* tower) { // delta_x^2 + delta_y^2
    return (x - tower[0]) * (x - tower[0]) +
        (y - tower[1]) * (y - tower[1]);
}
