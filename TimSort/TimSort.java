package com.company;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class TimSort {

    static int RUN = 32;

    public static int minRunLength(int n)
    {
        assert n >= 0;

        // Becomes 1 if any 1 bits are shifted off
        int r = 0;
        while (n >= RUN)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // This function sorts array from left index to right index which is of size at most RUN using insertion sort algorithm.
    public static void insertionSort(int[] arr, int left, int right)
    {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // Merge function merges the sorted runs/elements
    public static void merge(int[] arr, int l, int m, int r)
    {
        // Original array is broken in two parts left and right array
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] = arr[m + x + 1];
        }

        int i = 0;
        int j = 0;
        int k = l;
        // After comparing, we merge those two array in larger sub array
        while (i < len1 && j < len2)
        {
            if (left[i] <= right[j])
            {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < len1)
        {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < len2)
        {
            arr[k] = right[j];
            k++;
            j++;
        }
        System.out.println("Left Array: "+ Arrays.toString(left));
        System.out.println("Right Array: "+ Arrays.toString(right));
    }

    //Tim sort function to sort the array[0...n-1]
    public static void timSort(int[] arr, int n)
    {
        int minRun = minRunLength(RUN);

        // Sort individual sub arrays of size RUN
        for (int i = 0; i < n; i += minRun)
        {
            insertionSort(arr, i, Math.min((i + RUN - 1), (n - 1)));
            System.out.println("Insertion sorting for array of length less than 32: "+Arrays.toString(arr));
            System.out.println();
        }
        // Start merging from size RUN (or 32).
        for (int size = minRun; size < n; size = 2 * size)
        {
            for (int left = 0; left < n; left += 2 * size)
            {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                // Merge sub array arr[left.....mid] & arr[mid+1....right]
                if(mid < right) {
                    merge(arr, left, mid, right);
                    System.out.println("After merging: " + Arrays.toString(arr));
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        long startTime = Instant.now().toEpochMilli();

        int[] A = new int[40];
        for (int i = 0; i < A.length; i++) {
            A[i] = new Random().nextInt(100);
        }

        System.out.println("Initial Array: "+ Arrays.toString(A));

        // Sorted and merged array with print out.
        timSort(A,A.length);
        System.out.println("Sorted Array: "+ Arrays.toString(A));

        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}
