class Solution {
    public String reverseParentheses(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == ')') {

                StringBuilder temp = new StringBuilder();

                // Take characters until '('
                while (stack.charAt(stack.length() - 1) != '(') {
                    temp.append(stack.charAt(stack.length() - 1));
                    stack.deleteCharAt(stack.length() - 1);
                }

                // Remove '('
                stack.deleteCharAt(stack.length() - 1);

                // Add reversed substring
                stack.append(temp);

            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }
}