package CodingPractice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class SlidingPuzzle {
    public static void main(String[] args) {
        int[][] board = {{4,1,2},{5,0,3}};
        SlidingPuzzle s = new SlidingPuzzle();
        System.out.println(s.slidingPuzzle(board));
    }
    int rows, cols;
    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0){
            return 0;
        }

        rows = board.length;
        cols = board[0].length;

        String target = "123450";
        String start = convertToString(board);
        if (start.equals(target)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()){
            step ++;

            int size = queue.size();
            for (int i = 0; i < size; i ++){
                String str = queue.poll();

                // we can only move the space
                int idx = str.indexOf('0');

                int x = idx / cols;   // to convert string to row and column value of x and y
                int y = idx % cols;

                for (int k = 0; k < 4; k++){
                    int newX = x + dx[k];
                    int newY = y + dy[k];

                    // if valid, do a swap
                    if (newX >= 0 && newY >= 0 && newX < rows && newY < cols){
                        String newStr = swap(str, idx, newX * cols + newY);

                        if (newStr.equals(target)){
                            return step;
                        }

                        if (!visited.contains(newStr)){
                            queue.add(newStr);
                            visited.add(newStr);
                        }
                    }
                }
            }
        }

        return -1;

    }

    private String convertToString(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int[] i : board){
            for (int j : i){
                sb.append(j);
            }
        }

        return sb.toString();
    }

    private String swap(String s, int i, int k){
        char[] array = s.toCharArray();
        char tmp = array[i];
        array[i] = array[k];
        array[k] = tmp;
        return new String(array);

    }
}
