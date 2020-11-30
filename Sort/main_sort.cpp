//
//  main.cpp
//  sort_compare
//
//  Created by zzj on 2020/10/27.
//

#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <algorithm>

using namespace std;

int numQuickSwap = 0, numQuickCom = 0;
int numMergeSwap = 0, numMergeCom = 0;
int numHeapSwap = 0, numHeapCom = 0;

void showVec(vector<int> vec) {
    int n = vec.size();
    for (int i = 0; i < n; ++i) {
        cout << vec[i] << " ";
    }
    cout << endl;
}

int myRandom(int i) {
    return rand()%i;
}

void bubbleSort(vector<int>& vec) {
//    vector<int> vec = input;
    int n = vec.size();
    int numSwap = 0, numCom = 0;
    for (int i = 0; i < n - 1; ++i) {
        for (int j = 0; j < n - 1 - i; ++j) {
            ++numCom;
            if (vec[j] > vec[j+1]) {
                ++numSwap;
                int tmp = vec[j];
                vec[j] = vec[j+1];
                vec[j+1] = tmp;
            }
        }
    }
    cout << "conduct " << numCom << " compare" << endl;
    cout << "conduct " << numSwap << " swap" << endl;
//    return vec;
}

void selectSort(vector<int>& vec) {
//    vector<int> vec = input;
    int n = vec.size();
    int minIndex, tmp;
    int numSwap = 0, numCom = 0;
    for (int i = 0; i < n; ++i) {
        minIndex = i;
        for (int j = i; j < n; ++j) {
            ++numCom;
            if (vec[j] < vec[minIndex]) {
                minIndex = j;
            }
        }
        if (minIndex != i) {
            tmp = vec[i];
            vec[i] = vec[minIndex];
            vec[minIndex] = tmp;
            ++numSwap;
        }
        
    }
    cout << "conduct " << numCom << " compare" << endl;
    cout << "conduct " << numSwap << " swap" << endl;
//    return vec;
}

void insertSort(vector<int>& vec) {
//    vector<int> vec = input;
    int numSwap = 0, numCom = 0;
    int n = vec.size();
    for (int i = 1; i < n; ++i) {
        int tmp = vec[i];
        int j = i-1;
        // 如果tmp比较小，相应的vec[j]后移，给tmp留空，直到找到合适的tmp的位置
        while(j>=0 && tmp < vec[j]) {
            numCom++;
            numSwap++;
            vec[j+1] = vec[j];
            --j;
        }
        //numSwap++;
        vec[j+1] = tmp;
    }
    cout << "conduct " << numCom << " compare" << endl;
    cout << "conduct " << numSwap << " swap" << endl;
//    return vec;
}

void quickSort(vector<int>& vec, int start, int end) {
    if (start >= end) return;
    int left = start;
    int right = end;
    int pivot = vec[left];
    while (left < right) {
        while (left < right && vec[right] > pivot) {
            --right;
            ++numQuickCom;
        }
        if (left < right) {
            vec[left++] = vec[right];
            ++numQuickSwap;
        }
        while (left < right && vec[left] < pivot) {
            ++left;
            ++numQuickCom;
        }
        if (left < right) {
            vec[right--] = vec[left];
            ++numQuickSwap;
        }
    }
    vec[left] = pivot;
    quickSort(vec, start, left-1);
    quickSort(vec, left+1, end);
}

// 有一个辅助数组tmp，帮助每次两路向上递归时，找两个部分小的数，先放进去
void mergeSort(vector<int>& vec, vector<int>& tmp, int start, int end) {
    // 归并到底，返回
    if (start >= end) return;
    // 找中点，向下递归
    int mid = start + (end - start) / 2;
    mergeSort(vec, tmp, start, mid);
    mergeSort(vec, tmp, mid+1, end);
    // 辅助数组的下标从start开始，两路归并上来的分别从start和mid+1开始
    int k = start, i = start, j = mid + 1;
    // 找到小的数，放进辅助数组
    while (i <= mid && j <= end) {
        ++numMergeCom;
        ++numMergeSwap;
        tmp[k++] = vec[i] < vec[j] ? vec[i++] : vec[j++];
    }
    // 如果还有剩下来的，那么挨个放进数组
    while (i <= mid) {
        tmp[k++] = vec[i++];
        ++numMergeSwap;
    }
    while (j <= end) {
        tmp[k++] = vec[j++];
        ++numMergeSwap;
    }
    // 辅助数组给原始数组赋值
    for (int i = start; i <= end; ++i) {
        vec[i] = tmp[i];
    }
}

void max_heapify(vector<int>& vec, int start, int end) {
    int dad = start;
    int son = 2 * dad + 1;
    while (son <= end) {
        // 比较两个子节点的值，找出其中大的
        if (son + 1 <= end && vec[son] < vec[son+1]) {
            ++son;
            ++numHeapCom;
        }
        // 父节点大，则说明构建完毕
        if (vec[dad] > vec[son]) {
            ++numHeapCom;
            return;
        }
        // 否则交换之后继续构建下一组父子
        else {
            swap(vec[dad], vec[son]);
            ++numHeapSwap;
            dad = son;
            son = dad * 2 + 1;
        }
    }
}

void HeapSort(vector<int>& vec) {
    int len = vec.size();
    // i从最后一个父节点开始调整
    for (int i = len/2-1; i >= 0; --i) {
        max_heapify(vec, i, len-1);
    }
    // 每次交换最大的到最后之后，将剩下来的重新调整为大顶堆
    for (int i = len - 1; i > 0; --i) {
        swap(vec[0], vec[i]);
        ++numHeapSwap;
        max_heapify(vec, 0, i-1);
    }
}

void shellSrot(vector<int>& vec) {
    int length = vec.size();
    int numCom = 0, numSwap = 0;
    for (int gap = length/2; gap > 0; gap/=2){
        for (int i = gap; i < length; ++i) {
            int j = i;
            while(j - gap >= 0 && vec[j] < vec[j-gap]) {
                swap(vec[j], vec[j-gap]);
                ++numSwap;
                ++numCom;
                j-=gap;
            }
        }
    }
    cout << "conduct " << numCom << " compare" << endl;
    cout << "conduct " << numSwap << " swap" << endl;
}

vector<int> CountSort(vector<int>& vec) {
    int maxNum = INT_MIN;
    int minNum = INT_MAX;
    // 找到最大最小值，作为count数组的长度范围
    for (auto n : vec) {
        maxNum = max(maxNum, n);
        minNum = min(minNum, n);
    }
    // num-minNum 作为下标
    vector<int> count(maxNum-minNum+1, 0);
    for (int num : vec){
        count[num-minNum]++;
    }
    
    for (int i = 1; i < count.size(); ++i) {
        count[i] += count[i-1];
    }
    vector<int> res(vec.size());
    for (int j = 0; j < vec.size(); ++j) {
        res[count[vec[j]-minNum]-1] = vec[j];
        //showVec(res);
        count[vec[j]-minNum]--;
    }
    return res;
    
}

void BucketSort(vector<int>& vec){
    int maxNum = INT_MIN;
    int minNum = INT_MAX;
    int sizeV = vec.size();
    for (auto n : vec) {
        maxNum = max(maxNum, n);
        minNum = min(minNum, n);
    }
    int bucketSize = (maxNum - minNum) / sizeV + 1;
    vector<vector<int>> bucket(bucketSize);
    for (auto n : vec) {
        int tmp = (n - minNum) / sizeV;
        bucket[tmp].push_back(n);
    }
    for (int i = 0; i < bucketSize; ++i) {
        sort(bucket[i].begin(), bucket[i].end());
    }
    int index = 0;
    for (int i = 0; i < bucket.size(); ++i) {
        for (int j = 0; j < bucket[i].size(); ++j) {
            vec[index++] = bucket[i][j];
        }
    }
    
}

vector<int> generateVec(long long n) {
    vector<int> res(n);
    srand((unsigned)time(NULL));
    for(int i=0;i<n;i++)
        res[i]=1+100000.0*(rand()/(RAND_MAX+1.0));
    return res;
}



int main(int argc, const char * argv[]) {
    vector<int> vec = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 4, 11, 34, 56, 234, 2, 45, 3, 65, 457};
    random_shuffle(vec.begin(), vec.end(), myRandom);
    cout << "origin data is: " << endl;
    showVec(vec);
    
    cout << "---------------------------" << endl;
    cout << "after bubblesort: " << endl;
    vector<int> vecBubble = vec;
    bubbleSort(vecBubble);
    showVec(vecBubble);
    
    cout << "---------------------------" << endl;
    cout << "after selectSort: " << endl;
    vector<int> vecSelect = vec;
    selectSort(vecSelect);
    showVec(vecSelect);
    
    cout << "---------------------------" << endl;
    cout << "after insertSort: " << endl;
    vector<int> vecInsert = vec;
    insertSort(vecInsert);
    showVec(vecInsert);
    
    cout << "---------------------------" << endl;
    cout << "after quickSort: " << endl;
    vector<int> vecQuick = vec;
    quickSort(vecQuick, 0, vecQuick.size()-1);
    cout << "conduct " << numQuickCom << " compare" << endl;
    cout << "conduct " << numQuickSwap << " swap" << endl;
    showVec(vecQuick);
    
    cout << "---------------------------" << endl;
    cout << "after MergeSort: " << endl;
    vector<int> vecMerge = vec;
    vector<int> tmp(vecMerge.size());
    mergeSort(vecMerge, tmp, 0, vecMerge.size()-1);
    cout << "conduct " << numMergeCom << " compare" << endl;
    cout << "conduct " << numMergeSwap << " swap" << endl;
    showVec(vecMerge);
    
    cout << "---------------------------" << endl;
    cout << "after HeapSort: " << endl;
    vector<int> vecHeap = vec;
    HeapSort(vecHeap);
    cout << "conduct " << numHeapCom << " compare" << endl;
    cout << "conduct " << numHeapSwap << " swap" << endl;
    showVec(vecHeap);
    
    cout << "---------------------------" << endl;
    cout << "after ShellSort: " << endl;
    vector<int> vecSehll = vec;
    shellSrot(vecSehll);
    showVec(vecSehll);
    
    cout << "---------------------------" << endl;
    cout << "after CountSort: " << endl;
    vector<int> vecCount = CountSort(vec);
    showVec(vecCount);
    
    cout << "---------------------------" << endl;
    cout << "after BucketSort: " << endl;
    vector<int> vecBucket = vec;
    BucketSort(vecBucket);
    showVec(vecBucket);
    
    vector<int> testVec = generateVec(100000);
    
    
    auto start = clock();
    vector<int> vec1 = testVec;
    bubbleSort(vec1);
    auto end = clock();
    auto ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "bubble sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    selectSort(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "select sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    insertSort(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "insert sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    quickSort(vec1, 0, vec1.size()-1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "quick sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    vector<int> tmp2(testVec.size());
    mergeSort(vec1, tmp2, 0, vec1.size()-1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "merge sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    HeapSort(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "heap sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    shellSrot(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "shell sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    CountSort(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "count sort :" << ptime << "s" << endl;
    
    start = clock();
    vec1 = testVec;
    BucketSort(vec1);
    end = clock();
    ptime=(double)(end-start)/CLOCKS_PER_SEC/5;
    cout << "bucket sort :" << ptime << "s" << endl;
    
    
    return 0;
}
