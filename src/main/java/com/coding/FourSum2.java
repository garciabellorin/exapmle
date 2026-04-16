package com.coding;

import java.util.HashMap;
import java.util.Map;

public class FourSum2 {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        Map<Integer, Integer> sum = new HashMap<>();

        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int plus = n1 + n2;
                sum.put(plus, sum.getOrDefault(plus, 0) + 1);
            }
        }

        for (int n3 : nums3) {
            for (int n4 : nums4) {
                int minus = -(n3 + n4);
                count += sum.getOrDefault(minus, 0);
            }
        }
        return count;
    }
}
