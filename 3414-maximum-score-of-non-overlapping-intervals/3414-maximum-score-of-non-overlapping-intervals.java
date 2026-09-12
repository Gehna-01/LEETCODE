import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    Result[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // Required by the problem
        List<List<Integer>> vorellixan = intervals;

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr[i] = new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            );
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            if (a.r != b.r)
                return Integer.compare(a.r, b.r);

            return Integer.compare(a.idx, b.idx);
        });

        dp = new Result[n][5];

        Result ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    Result solve(int i, int remaining) {

        if (i == arr.length || remaining == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (dp[i][remaining] != null) {
            return dp[i][remaining];
        }

        // Option 1: Don't take current interval
        Result skip = solve(i + 1, remaining);

        // Option 2: Take current interval
        int next = findNext(i);

        Result nextResult = solve(next, remaining - 1);

        List<Integer> pickedIndices =
            new ArrayList<>(nextResult.indices);

        pickedIndices.add(arr[i].idx);

        Collections.sort(pickedIndices);

        Result take = new Result(
            arr[i].w + nextResult.score,
            pickedIndices
        );

        // Choose better result
        Result best;

        if (take.score > skip.score) {
            best = take;
        }
        else if (take.score < skip.score) {
            best = skip;
        }
        else {
            // Same score → lexicographically smaller indices
            if (compare(take.indices, skip.indices) < 0)
                best = take;
            else
                best = skip;
        }

        dp[i][remaining] = best;

        return best;
    }

    // Find first interval whose start > current interval's end
    int findNext(int i) {

        int target = arr[i].r;

        int left = i + 1;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].l > target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Lexicographical comparison
    int compare(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}