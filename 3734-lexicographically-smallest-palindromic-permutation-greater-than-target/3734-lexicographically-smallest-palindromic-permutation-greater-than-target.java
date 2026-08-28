class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // A palindrome can have at most one odd-frequency character.
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int half = n / 2;

        // Characters available for the left half.
        int[] cnt = new int[26];
        for (int i = 0; i < 26; i++) {
            cnt[i] = freq[i] / 2;
        }

        char[] left = new char[half];

        /*
         * First try to make the left half exactly equal to
         * target's left half.
         */
        boolean prefixPossible = true;

        for (int i = 0; i < half; i++) {
            int x = target.charAt(i) - 'a';

            if (cnt[x] == 0) {
                prefixPossible = false;
                break;
            }

            left[i] = target.charAt(i);
            cnt[x]--;
        }

        if (prefixPossible) {
            String candidate = build(left, middle, n);

            // Exact left half may already produce a larger palindrome.
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Otherwise, find the rightmost position in the left half
         * that can be increased.
         *
         * Restore characters while moving backwards.
         */
        if (prefixPossible) {
            for (int i = half - 1; i >= 0; i--) {
                cnt[left[i] - 'a']++;

                int current = left[i] - 'a';

                // Find the smallest available character > current.
                for (int c = current + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        left[i] = (char) ('a' + c);
                        cnt[c]--;

                        // Fill remaining positions with smallest chars.
                        int pos = i + 1;

                        for (int ch = 0; ch < 26; ch++) {
                            while (cnt[ch] > 0) {
                                left[pos++] = (char) ('a' + ch);
                                cnt[ch]--;
                            }
                        }

                        return build(left, middle, n);
                    }
                }
            }

            return "";
        }

        /*
         * Matching target failed before completing the half.
         * Reconstruct counts for the matched prefix and backtrack.
         */
        for (int i = 0; i < 26; i++) {
            cnt[i] = freq[i] / 2;
        }

        int matched = 0;

        while (matched < half) {
            int x = target.charAt(matched) - 'a';

            if (cnt[x] == 0) {
                break;
            }

            left[matched] = target.charAt(matched);
            cnt[x]--;
            matched++;
        }

        // At the failure position, try a larger character first.
        if (matched < half) {
            int current = target.charAt(matched) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    left[matched] = (char) ('a' + c);
                    cnt[c]--;

                    fillSmallest(left, matched + 1, cnt);

                    return build(left, middle, n);
                }
            }
        }

        // Backtrack through matched positions.
        for (int i = matched - 1; i >= 0; i--) {
            cnt[left[i] - 'a']++;

            int current = left[i] - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    left[i] = (char) ('a' + c);
                    cnt[c]--;

                    fillSmallest(left, i + 1, cnt);

                    return build(left, middle, n);
                }
            }
        }

        return "";
    }

    private void fillSmallest(char[] left, int pos, int[] cnt) {
        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                left[pos++] = (char) ('a' + c);
                cnt[c]--;
            }
        }
    }

    private String build(char[] left, char middle, int n) {
        StringBuilder ans = new StringBuilder();

        for (char c : left) {
            ans.append(c);
        }

        if ((n & 1) == 1) {
            ans.append(middle);
        }

        for (int i = left.length - 1; i >= 0; i--) {
            ans.append(left[i]);
        }

        return ans.toString();
    }
}