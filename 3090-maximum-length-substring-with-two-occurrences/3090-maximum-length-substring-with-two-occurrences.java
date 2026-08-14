class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            freq[ch - 'a']++;

            // If a character appears more than 2 times,
            // move left until it becomes valid again.
            while (freq[ch - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}