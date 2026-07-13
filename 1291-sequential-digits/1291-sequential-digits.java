class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        int minLength = String.valueOf(low).length();
        int maxLength = String.valueOf(high).length();

        for (int len = minLength; len <= maxLength; len++) {

            for (int start = 0; start + len <= 9; start++) {

                int num = Integer.parseInt(digits.substring(start, start + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}