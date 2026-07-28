class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char middle = 0;

        // Build the first half in lexicographical order
        for (int i = 0; i < 26; i++) {
            int count = freq[i] / 2;

            while (count-- > 0) {
                firstHalf.append((char) ('a' + i));
            }

            if (freq[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder(firstHalf);

        if (middle != 0) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(firstHalf).reverse());

        return ans.toString();
    }
}