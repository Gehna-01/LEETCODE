import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers:
        // [weight, index]
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> a[0] != b[0]
                ? Integer.compare(a[0], b[0])
                : Integer.compare(a[1], b[1])
        );

        // Busy servers:
        // [freeTime, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0]
                ? Long.compare(a[0], b[0])
                : a[1] != b[1]
                    ? Long.compare(a[1], b[1])
                    : Long.compare(a[2], b[2])
        );

        for (int i = 0; i < servers.length; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[tasks.length];
        long time = 0;

        for (int i = 0; i < tasks.length; i++) {

            // Task i arrives at time i
            time = Math.max(time, i);

            // Move all servers that have become free
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                available.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // If no server is available,
            // jump to the earliest time a server becomes free
            if (available.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    available.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            // Choose server with smallest weight,
            // then smallest index
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Server will become free after processing task
            busy.offer(new long[]{
                time + tasks[i],
                weight,
                index
            });
        }

        return ans;
    }
}