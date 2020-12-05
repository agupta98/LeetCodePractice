package CodingPractice;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int[][] mat = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        System.out.println(trapRainWater(mat));
    }

    public static class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    ", height=" + height +
                    '}';
        }
    }

    public static int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }

        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        System.out.println(queue);
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    System.out.println("The cell row and cell col " + cell.row + " -- " +cell.col);
                    System.out.println("The row and col " + row + " -- " +col);
                    System.out.println(cell.height - heights[row][col]);
                    res += Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
                }
            }
        }

        return res;
    }
}
