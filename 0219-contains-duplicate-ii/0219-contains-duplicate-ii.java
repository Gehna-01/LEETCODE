import java.util.HashSet;

class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            // Duplicate found within current window
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);

            // Keep window size at most k
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }
}