package CodingPractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class ShortestPath {
    public static void main(String[] args) {
       // System.out.println(1<<4);
        int[][] graph = {{1,2,3},{0},{0},{0}};
        ShortestPath s = new ShortestPath();
        System.out.println(s.shortestPathLength(graph));
    }
    public int shortestPathLength(int[][] graph) {
        int[][] dp = new int[graph.length][1<<graph.length];
        Queue<State> queue =  new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][1<<i]=0;
            queue.offer(new State(1<<i, i));
        }
       // System.out.println(Arrays.deepToString(dp));
        while (!queue.isEmpty()) {
            State state = queue.poll();

            for (int next : graph[state.source]) {
                int nextMask = state.mask | 1 << next;    // to mark it visited suppose we have node 1 and 2 1 | 2
                if (dp[next][nextMask] > dp[state.source][state.mask]+1) {
                    dp[next][nextMask] = dp[state.source][state.mask]+1;
                    System.out.println(state.mask + " -- " + nextMask + " -- " + next);
                    queue.offer(new State(nextMask, next));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) {
            res = Math.min(res, dp[i][(1<<graph.length)-1]);
        }
        return res;
    }

    class State {
        public int mask, source;
        public State(int m, int s) {
            mask=m;
            source=s;
        }

        @Override
        public String toString() {
            return "State{" +
                    "mask=" + mask +
                    ", source=" + source +
                    '}';
        }
    }
}