class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < tickets.length; i++) {
            q.offer(new int[]{i, tickets[i]});
        }

        int time = 0;

        while (!q.isEmpty()) {
            int[] person = q.poll();

            person[1]--;
            time++;

            if (person[1] == 0) {
                if (person[0] == k)
                    return time;
            } else {
                q.offer(person);
            }
        }

        return time;
    }
}