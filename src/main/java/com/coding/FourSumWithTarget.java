package com.coding;

public class FourSumWithTarget {
    public int getOptions(int[] A, int[] B, int[] C, int[] D, int target) {
        int count = 0;
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    for (int d : D) {
                        if ((a + b + c + d) == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[]{2, 3};
        int[] B = new int[]{4};
        int[] C = new int[]{2, 3};
        int[] D = new int[]{1, 2};
        //[2,2,2][3,3]
        int target = 10;
        FourSumWithTarget obj = new FourSumWithTarget();
        System.out.println(obj.getOptions(A, B, C, D, target));
    }
}
