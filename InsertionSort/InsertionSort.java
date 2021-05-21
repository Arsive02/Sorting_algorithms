package com.company;

import java.util.Arrays;
import java.time.Instant;

public class InsertionSort {

    public int[] insertionSort (int[] list) {
        int i, j, key, temp;
        for (i = 1; i < list.length; i++) {
            key = list[i];
            j = i - 1;
            while (j >= 0 && key < list[j]) {
                temp = list[j];
                list[j] = list[j + 1];
                list[j + 1] = temp;
                j--;
            }
            System.out.println("Key: " + key + ", Interation "+ i + ": "+ Arrays.toString(list));
            System.out.println();
        }
        return list;
    }

    public static void main(String[] args) {
        long startTime = Instant.now().toEpochMilli();

        InsertionSort is = new InsertionSort();
        int[] A = {9, 1, 3, 4, 2, 8, 7, 6, 5, 0};
        int[] result = is.insertionSort(A);
        System.out.println("Sorted array using Insertion Sort: "+ Arrays.toString(result));

        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}
