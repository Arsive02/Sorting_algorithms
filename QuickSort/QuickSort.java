package com.company;

import java.util.Arrays;
import java.util.Random;
import java.time.Instant;


public class QuickSort {
    public void quickSort(int[] A) {
        quickSort(A, 0, A.length-1);
    }

    private void quickSort(int[] A, int low, int high) {
        if (low < high+1) {
            System.out.println();
            int p = partition(A, low, high);
            quickSort(A, low, p-1);
            quickSort(A, p+1, high);
        }
    }

    private void swap(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

    // returns random pivot index between low and high.
    private int getPivot(int low, int high) {
        Random rand = new Random();
        return rand.nextInt((high - low) + 1) + low;
    }

    /* moves all n < pivot to left of pivot and all n > pivot
        to right of pivot, then returns pivot index. */
    private int partition(int[] A, int low, int high) {
        swap(A, low, getPivot(low, high));
        System.out.println("low: "+low+", high: "+high);
        int border = low + 1;
        for (int i = border; i <= high; i++) {
            if (A[i] < A[low]) {
                swap(A, i, border++);
            }
        }
        swap(A, low, border-1);
        System.out.println(Arrays.toString(A));
        System.out.println("Pivot: "+A[border-1]);
        return border-1;
    }

    public static void main(String[] args) {
        long startTime = Instant.now().toEpochMilli();
        QuickSort qs = new QuickSort();
        int[] A = {9, 1, 3, 4, 2, 8, 7, 6, 5, 0};
        System.out.println("ARRAY BEFORE SORTING: "+Arrays.toString(A));
        System.out.println();
        qs.quickSort(A);
        System.out.println();
        System.out.println("ARRAY AFTER SORTING: "+Arrays.toString(A));
        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}