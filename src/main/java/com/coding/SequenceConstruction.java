package com.coding;

import java.util.Arrays;

public class SequenceConstruction {


    static int n, target;
    static int[] result;
    static boolean[] used;
    static boolean found = false;

    public static int[] build(int N, int T) {
        n = N;
        target = T;
        result = new int[n];
        used = new boolean[n + 1];

        backtrack(0, 0);
        return result;
    }

    private static void backtrack(int idx, int sum) {
        if (found) return;

        if (idx == n) {
            if (sum == target) found = true;
            return;
        }

        // Try values in lexicographically smallest order:
        // -n, -(n-1), ..., -1, 1, 2, ..., n
        for (int v = -n; v <= n; v++) {
            if (v == 0) continue;
            int abs = Math.abs(v);
            if (abs > n || used[abs]) continue;

            // Feasibility check
            int remaining = n - idx - 1;

            int minPossible = -(remaining * (remaining + 1)) / 2;
            int maxPossible = (remaining * (remaining + 1)) / 2;

            int newSum = sum + v;
            int needed = target - newSum;

            if (needed < minPossible || needed > maxPossible)
                continue;

            // Choose
            used[abs] = true;
            result[idx] = v;

            backtrack(idx + 1, newSum);

            if (found) return;

            // Undo
            used[abs] = false;
        }
    }

    public static void main(String[] args) {
        int[] ans = build(4, -2);
        System.out.println(Arrays.toString(ans));
    }
}


