package CodingPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dikjstra {
    public static void main(String[] args) {
        int[][] maze = {{0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        int[] st = {0,4};
        int[] dt = {4,4};
        Dikjstra dikjstra = new Dikjstra();
        System.out.println(dikjstra.shortestDistance(maze,st,dt));
    }

    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        bfs(maze, start,distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    private void bfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue< int[] > queue = new PriorityQueue <> (Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{start[0], start[1], 0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if (distance[s[0]][s[1]] < s[2]) continue;
            for(int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >=0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x-dir[0]][y-dir[1]]) {
                    distance[x-dir[0]][y-dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x-dir[0], y-dir[1], distance[x-dir[0]][y-dir[1]]});
                }
            }

        }



    }
}
