class Solution {
    public int threeSumMulti(int[] arr, int target) {
    Arrays.sort(arr);
    long ans = 0;
    int mod = 1_000_000_007;
     for(int i=0;i<arr.length-2;i++){
        int left=i+1;
        int count=0;
        int right=arr.length-1;
        while(left<right){
            int sum=arr[i]+arr[left]+arr[right];
            if(sum<target){
                left++;
            }
            else if (sum>target){
                right--;
            }
            else{
                if(arr[left]==arr[right]){
                    count+=right-left+1;
                    ans += count * (count - 1) / 2;
                    break;
                }
                else{
                    if (arr[left] != arr[right]) {

                        long leftCount = 1;
                        long rightCount = 1;

                        while (left + 1 < right && arr[left] == arr[left + 1]) {
                            leftCount++;
                            left++;
                        }

                        while (right - 1 > left && arr[right] == arr[right - 1]) {
                            rightCount++;
                            right--;
                        }

                        ans += leftCount * rightCount;

                        left++;
                        right--;
                    
                }


            }
        }
     }
      
    }
    
    return (int)(ans % mod);  
}
}