package com.github.dge.datastructure.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {

    public static void main(String[] args) {
        KthSmallestElementInASortedMatrix aSortedMatrix = new KthSmallestElementInASortedMatrix();
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        // int[][] matrix = {{1,3,5},{6,7,12},{11,14,14}};
        int k = 8;
        int i = aSortedMatrix.kthSmallest(matrix, k);
        System.out.println(i);
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int[] arr = new int[n * n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[count] = matrix[i][j];
                count++;
            }
        }
        Arrays.sort(arr);
        return arr[k - 1];
    }
}
