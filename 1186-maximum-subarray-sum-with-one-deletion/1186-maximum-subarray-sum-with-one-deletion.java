class Solution {
    public int maximumSum(int[] arr) {
        int nodel=arr[0];
        int onedel=0;
        int ans=arr[0];
        for(int i=1;i<arr.length;i++){
            int oldnodel=nodel;
            int oldonedel=onedel;
            nodel=Math.max(arr[i],arr[i]+oldnodel);
            onedel=Math.max(oldonedel+arr[i],oldnodel);
            ans=Math.max(ans,Math.max(onedel,nodel));


        }
        return ans;
    }
}