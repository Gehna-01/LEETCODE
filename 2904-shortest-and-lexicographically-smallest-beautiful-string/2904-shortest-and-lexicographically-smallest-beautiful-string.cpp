class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        int n = s.size();
        int left = 0, ones = 0;
        string ans = "";

        for (int right = 0; right < n; right++) {
            if (s[right] == '1')
                ones++;

            while (ones > k) {
                if (s[left] == '1')
                    ones--;
                left++;
            }

            if (ones == k) {
                // Remove leading zeroes
                int temp = left;
                while (temp <= right && s[temp] == '0')
                    temp++;

                string cur = s.substr(temp, right - temp + 1);

                if (ans == "" ||
                    cur.length() < ans.length() ||
                    (cur.length() == ans.length() && cur < ans)) {
                    ans = cur;
                }
            }
        }

        return ans;
    }
};