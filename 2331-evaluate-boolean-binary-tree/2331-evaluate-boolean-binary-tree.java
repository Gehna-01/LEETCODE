class Solution {
    public boolean evaluateTree(TreeNode root) {
        
        // Leaf node
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        // OR
        if (root.val == 2) {
            return left || right;
        }

        // AND
        return left && right;
    }
}