class Minesweeper {
    int[] dx = new int[]{0, -1, 0, 1, 1, -1, 1, -1};
    int[] dy = new int[]{1, 0, -1, 0, 1, -1, -1, 1};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        int m = board.length, n = board[0].length;
        
        Queue<int[]> queue = new LinkedList();
        boolean[][] visited = new boolean[m][n];
        
        queue.offer(click);
        visited[click[0]][click[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            int mines = getNumOfBombs(board, x, y);
            System.out.println(Arrays.toString(cur) + "---"+ mines);
            if (mines == 0) {
                board[x][y] = 'B';
                for (int i = 0; i < 8; ++i) {
                    int nx = x + dx[i], ny = y + dy[i];
                
                    if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && !visited[nx][ny] && board[nx][ny] == 'E') {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            } else {
                board[x][y] = (char)(mines + '0');
            }
           // System.out.println(Arrays.deepToString(board));
        }
        
        return board;
    }
    
    int getNumOfBombs(char[][] board, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length) {
                count += board[nx][ny] == 'M' ? 1 : 0;
            }
          
        }
        return count;
    }
}
