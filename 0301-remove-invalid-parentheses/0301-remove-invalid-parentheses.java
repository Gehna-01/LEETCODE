class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        int left = 0;
        int right = 0;

        // Count minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right, result);

        return result;
    }

    private void backtrack(String s, int start, int leftRemove,
                            int rightRemove, List<String> result) {

        if (leftRemove == 0 && rightRemove == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {

            // Skip duplicate characters
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Only remove parentheses
            if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                continue;
            }

            // Remove '('
            if (leftRemove > 0 && s.charAt(i) == '(') {
                String next = s.substring(0, i) + s.substring(i + 1);

                backtrack(next, i, leftRemove - 1,
                          rightRemove, result);
            }

            // Remove ')'
            if (rightRemove > 0 && s.charAt(i) == ')') {
                String next = s.substring(0, i) + s.substring(i + 1);

                backtrack(next, i, leftRemove,
                          rightRemove - 1, result);
            }
        }
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}