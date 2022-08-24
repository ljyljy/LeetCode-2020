#ifndef MIN(A,B)
#define MIN(A,B) ((A)<(B)? (A):(B))
#endif

double getKth(int* A, int i, int n1, int* B, int j, int n2, int k) {
    int len1 = n1 - i, len2 = n2 - j;
    if (len1 > len2) return getKth(B, j, n2, A, i, n1, k);
    if (i == n1) return B[j + k - 1];
    if (k == 1) {
//        printf("%d, %d, min=d\n", A[i], B[j], MIN(A[i], B[j]));
        return MIN(A[i], B[j]);
    }

    int newI = MIN(i + k / 2, n1);
    int newJ = j + k / 2;
    if (A[newI - 1] > B[newJ - 1]) {
        return getKth(A, i, n1, B, newJ, n2, k - k/2);
    } else {
        return getKth(A, newI, n1, B, j, n2, k - (newI - i));
    }
}

double findMedianSortedArrays(int* A, int n1, int* B, int n2) {
    int N = n1 + n2;
    if (N % 2 != 0) {
        return getKth(A, 0, n1, B, 0, n2, N / 2  + 1);
    }
    else {
        double med1 = getKth(A, 0, n1, B, 0, n2, N / 2);
        double med2 = getKth(A, 0, n1, B, 0, n2, N / 2 + 1);
        return (med1 + med2) * 0.5;
    }
}


//#include "src/Array/q4.h"
//int main() {
//    int nums1[] = { 1,2,3 };
//    int nums2[] = { 4,5 };
//    int n1 = sizeof(nums1) / sizeof(int);
//    int n2 = sizeof(nums2) / sizeof(int);
//    printf("n1 = %d, n2 = %d\n", n1, n2);
//    double med = findMedianSortedArrays(nums1, n1, nums2, n2);
//    printf("%lf\n", med);
//    return 0;
//}