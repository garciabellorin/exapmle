package com.coding;

import java.util.HashSet;
import java.util.Set;

public class LC_261_ContaminatedBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
        private Set<Integer> values;
        public LC_261_ContaminatedBST(TreeNode root) {
            values = new HashSet<>();
            cleanUp(root, 0);
        }

        public boolean find(int target) {
            return values.contains(target);
        }

        private void cleanUp(TreeNode node, int val) {
            if(node == null) {
                return;
            }
            node.val = val;
            values.add(val);

            //Go left
            cleanUp(node.left, ((2 * val) + 1));

            //Go right
            cleanUp(node.right, ((2 * val) + 2));
        }

}
