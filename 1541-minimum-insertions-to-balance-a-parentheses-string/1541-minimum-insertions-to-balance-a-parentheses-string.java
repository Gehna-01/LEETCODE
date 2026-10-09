class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // Every '(' must match exactly two consecutive ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to complete the pair
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // Insert one '(' to match this pair
                    insertions++;
                }
            }
        }

        // Each remaining '(' needs two ')'
        return insertions + 2 * open;
    }
}