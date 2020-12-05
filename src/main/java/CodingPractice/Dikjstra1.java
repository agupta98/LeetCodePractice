package CodingPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dikjstra1 {
    public static void main(String[] args) {
        int[][] maze = {{1,2,3},
                {1,4,1},
                {0,2,1}};
        int[] st = {0,0};
        int[] dt = {2,2};
        Dikjstra1 dikjstra1 = new Dikjstra1();
        System.out.println(dikjstra1.shortestDistance(maze,st,dt));
    }

    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);
      distance[start[0]][start[1]] = maze[0][0];
        bfs(maze, start,distance);
        System.out.println(Arrays.deepToString(distance));
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    private void bfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue< int[] > queue = new PriorityQueue <> (Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{start[0], start[1], maze[0][0]});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if (distance[s[0]][s[1]] < s[2]) continue;
            for(int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                if  (x >=0 && y >= 0 && x < maze.length && y < maze[0].length){
                if (distance[s[0]][s[1]]  + maze[x][y] < distance[x][y]) {
                    distance[x][y] = distance[s[0]][s[1]] + maze[x][y];
                    queue.offer(new int[]{x, y, distance[x][y]});
                }
                }
            }

        }



    }
}
