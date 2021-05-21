package com.company;

import java.time.Instant;
import java.util.Arrays;

class MergeSort {

    // Breaks down the array to single elements in array.
    private static int[] mergeSort(int[] array) {

        if(array.length <= 1) {
            return array;
        }
        int midpoint = array.length / 2;

        // Declare left and right arrays.
        int[] left = new int[midpoint];
        int[] right;

        if(array.length % 2 == 0) { // if array.length is an even number.
            right = new int[midpoint];
        } else {
            right = new int[midpoint + 1];
        }

        // Insert elements in the left and right arrays.
        for(int i=0; i < midpoint; i++) {
            left[i] = array[i];
        }

        for(int j=0; j < right.length; j++) {
            right[j] = array[midpoint+j];
        }

        int[] result;

        // Recursive call for left and right arrays.
        left = mergeSort(left);
        right = mergeSort(right);

        // Get the merged left and right arrays.
        System.out.println("LEFT ARRAY: "+Arrays.toString(left)+", RIGHT ARRAY: "+Arrays.toString(right));
        result = merge(left, right);
        System.out.println("Result after Merging: "+Arrays.toString(result));
        System.out.println();
        // Return the sorted merged array.
        return result;
    }

    // Merges the left and right array in ascending order.
    private static int[] merge(int[] left, int[] right) {

        // Merged result array.
        int[] result = new int[left.length + right.length];

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while(leftPointer < left.length || rightPointer < right.length) {
            // If there are items in BOTH arrays...
            if(leftPointer < left.length && rightPointer < right.length) {
                // If left item is less than right item...
                if(left[leftPointer] < right[rightPointer]) {
                    result[resultPointer++] = left[leftPointer++];
                } else {
                    result[resultPointer++] = right[rightPointer++];
                }
            }

            // If there are only items in the left array...
            else if(leftPointer < left.length) {
                result[resultPointer++] = left[leftPointer++];
            }

            // If there are only items in the right array...
            else if(rightPointer < right.length) {
                result[resultPointer++] = right[rightPointer++];
            }
        }
        return result;
    }

    public static void main(String[] args) {

        long startTime = Instant.now().toEpochMilli();

        int[] A = {9, 1, 3, 4, 2, 8, 7, 6, 5, 0};
        System.out.println("Initial Array: "+ Arrays.toString(A));

        // Sorted and merged array with print out.
        A = mergeSort(A);
        System.out.println("Sorted Array: "+ Arrays.toString(A));

        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}
