import java.util.Arrays;

class Solution {
    public int maxProduct(int n) {

        String s = String.valueOf(n);
        int[] arr = new int[s.length()];

        int i = 0;
        while (n > 0) {
            arr[i++] = n % 10;
            n /= 10;
        }

        Arrays.sort(arr);

        return arr[arr.length - 1] * arr[arr.length - 2];
    }
}