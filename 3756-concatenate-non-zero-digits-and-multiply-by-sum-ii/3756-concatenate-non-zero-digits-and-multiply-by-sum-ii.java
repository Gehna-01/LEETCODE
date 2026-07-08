import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();

        // Store non-zero digits and their positions
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                posList.add(i);
                digitList.add(digit);
            }
        }

        int m = posList.size();

        int[] pos = new int[m];
        int[] digits = new int[m];

        for (int i = 0; i < m; i++) {
            pos[i] = posList.get(i);
            digits[i] = digitList.get(i);
        }

        long[] prefixSum = new long[m + 1];
        long[] prefixValue = new long[m + 1];
        long[] pow10 = new long[m + 1];

        pow10[0] = 1;

        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < m; i++) {
            prefixSum[i + 1] = prefixSum[i] + digits[i];
            prefixValue[i + 1] = (prefixValue[i] * 10 + digits[i]) % MOD;
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                answer[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long sum = prefixSum[right + 1] - prefixSum[left];

            long x = (prefixValue[right + 1]
                    - (prefixValue[left] * pow10[len]) % MOD
                    + MOD) % MOD;

            answer[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return answer;
    }

    // First index >= target
    private int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    // First index > target
    private int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}