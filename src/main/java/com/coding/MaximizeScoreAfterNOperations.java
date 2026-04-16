package com.coding;

import java.math.BigInteger;

public class MaximizeScoreAfterNOperations {

    public static void main(String[] args) {
        /**
         * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
         * In the ith operation (1-indexed), you will:
         * Choose two elements, x and y.
         * Receive a score of i * gcd(x, y).
         * Remove x and y from nums.
         * Return the maximum score you can receive after performing n operations.
         * The function gcd(x, y) is the greatest common divisor of x and y.
         */
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(maxScore(nums) == 14); // expected 14
        nums = new int[]{1, 2};
        System.out.println(maxScore(nums) == 1); // expected 1
        nums = new int[]{3, 4, 6, 8};
        System.out.println(maxScore(nums) == 11); // expected 11

    }


    public static int maxScore(int[] nums) {
        int m = nums.length;
        int[][] gcdArray = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                gcdArray[i][j] = BigInteger.valueOf(nums[i]).gcd(BigInteger.valueOf(nums[j])).intValue();
            }
        }

        int[] maxScore = new int[1 << m];
        for (int operation = 0; operation < 1 << m; ++operation) {
            int counter = Integer.bitCount(operation);
            if (counter % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (((operation >> i) & 1) == 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (((operation >> j) & 1) == 1) {
                                int gcdValue = gcdArray[i][j];
                                int currentMask = operation ^ (1 << i) ^ (1 << j);
                                maxScore[operation] = Math.max(
                                        maxScore[operation], maxScore[currentMask] + counter / 2 * gcdValue);
                            }
                        }
                    }
                }
            }
        }
        return maxScore[(1 << m) - 1];
    }
}
