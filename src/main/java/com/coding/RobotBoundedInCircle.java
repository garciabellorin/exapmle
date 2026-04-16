package com.coding;

public class RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        int[] current = new int[]{0, 0};
        //                                N        E       S       W
        int[][] direction = new int[][]{{0, 1},  {1, 0}, {0, -1}, {-1, 0}};
        int facing = 0;

        for(char c : instructions.toCharArray()) {
            if(c == 'R') {
                facing = (facing + 1) % 4;
            } else if (c == 'L') {
                facing = (facing + 3) % 4;
            } else {
                current[0] += direction[facing][0];
                current[1] += direction[facing][1];
            }
        }
        return (current[0] == 0) && (current[1] == 0) || facing != 0;
    }
}
