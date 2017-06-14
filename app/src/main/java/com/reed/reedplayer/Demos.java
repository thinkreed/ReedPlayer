package com.reed.reedplayer;

/**
 * Created by thinkreed on 2017/6/7.
 */

public class Demos {

    public int find(int a[], int target) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (a[mid] < target) {
                if (a[mid] < a[j]) {
                    i = mid + 1;
                }
            } else if (a[mid] > target) {
                if (a[mid] > a[i]) {
                    j = mid - 1;
                }
            } else {
                return mid;
            }
        }
        return -1;
    }
}
