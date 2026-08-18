class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long total=0;
        for(int x: arr){
            total+=x;
        }
        long max=arr[0];
        long maxsum=arr[0];
        int limit=(k==1)?arr.length:arr.length*2;

        for(int i=1;i<limit;i++){
            int x=arr[i%arr.length];
            max = Math.max(x, max + x);
            maxsum = Math.max(maxsum, max);
        }
        if (k > 2 && total > 0) {
            maxsum += total * (k - 2);
    }
    return (int)(Math.max(0,maxsum % 1000000007));
}
}