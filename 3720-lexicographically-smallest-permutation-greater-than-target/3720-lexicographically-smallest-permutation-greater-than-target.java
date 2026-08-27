class Solution {
    private int[] freq;
    private StringBuilder ans;

    private boolean dfs(int idx, boolean greater, String target) {
        int n = target.length();

        if (idx == n) {
            return greater;
        }

        if (greater) {
            // Already greater, append the smallest remaining characters.
            for (int c = 0; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--;
                    ans.append((char) ('a' + c));

                    if (dfs(idx + 1, true, target)) {
                        return true;
                    }

                    ans.deleteCharAt(ans.length() - 1);
                    freq[c]++;
                }
            }
            return false;
        }

        int t = target.charAt(idx) - 'a';

        // Try characters from target[idx] upwards.
        for (int c = t; c < 26; c++) {
            if (freq[c] == 0) continue;

            freq[c]--;
            ans.append((char) ('a' + c));

            if (dfs(idx + 1, greater || (c > t), target)) {
                return true;
            }

            ans.deleteCharAt(ans.length() - 1);
            freq[c]++;
        }

        return false;
    }

    public String lexGreaterPermutation(String s, String target) {
        freq = new int[26];
        ans = new StringBuilder();

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        if (dfs(0, false, target)) {
            return ans.toString();
        }

        return "";
    }
}