package com.coding;

import java.util.*;

public class MinSubsequentPairs {

    static final long MOD = 1_000_000_007;

    public static long solve(String s) {
        int n = s.length();

        // dp[i][z] = min cost after i chars, with z zeros used
        long[][] dp = new long[n + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE / 4);

        dp[0][0] = 0; // no chars, no cost

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            for (int z = 0; z <= i; z++) {
                if (dp[i][z] >= Long.MAX_VALUE / 4) continue;

                int ones = i - z;

                // Option 1: place '0'
                if (c == '0' || c == '!') {
                    long cost = dp[i][z] + ones; // each previous 1 forms "10"
                    dp[i + 1][z + 1] = Math.min(dp[i + 1][z + 1], cost);
                }

                // Option 2: place '1'
                if (c == '1' || c == '!') {
                    long cost = dp[i][z] + z; // each previous 0 forms "01"
                    dp[i + 1][z] = Math.min(dp[i + 1][z], cost);
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int z = 0; z <= n; z++) {
            ans = Math.min(ans, dp[n][z]);
        }

        return ans % MOD;
    }

    public static void main(String[] args) {
        System.out.println(solve("0!1!")); // expected 3
    }
}

