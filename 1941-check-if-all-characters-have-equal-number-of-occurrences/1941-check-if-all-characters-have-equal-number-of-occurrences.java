class Solution {
    public boolean areOccurrencesEqual(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int freq = -1;

        for (int count : map.values()) {
            if (freq == -1) {
                freq = count;
            } else if (count != freq) {
                return false;
            }
        }

        return true;
    }
}