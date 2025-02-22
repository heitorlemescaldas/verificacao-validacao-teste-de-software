package br.edu.ifsp.testing.class08;

public class BinarySearch {
    public int binarySearch(int[] arr, int target) {
        if(arr == null || arr.length == 0)
            return -1;

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
