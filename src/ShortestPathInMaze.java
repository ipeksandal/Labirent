import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInMaze {
    private static final int[] rowNum = {-1, 0, 0, 1};
    private static final int[] colNum = {0, -1, 1, 0};

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };
        int result = shortestPath(maze);
        if (result != -1) {
            System.out.println("En kısa yol uzunluğu: " + result);
        } else {
            System.out.println("Yol yok.");
        }
    }

    public static int shortestPath(int[][] maze) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        int rows = maze.length;
        int cols = maze[0].length;

        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));  // (row, col, distance)

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int row = current.row;
            int col = current.col;
            int dist = current.dist;

            // Hedefe ulaşıldıysa mesafeyi döndür
            if (row == rows - 1 && col == cols - 1) {
                return dist;
            }

            // Tüm 4 olası komşuyu kontrol et
            for (int i = 0; i < 4; i++) {
                int newRow = row + rowNum[i];
                int newCol = col + colNum[i];

                if (isValid(maze, newRow, newCol, visited)) {
                    visited[newRow][newCol] = true;
                    queue.add(new Node(newRow, newCol, dist + 1));
                }
            }
        }

        // Hedefe ulaşılamadıysa -1 döndür
        return -1;
    }

    private static boolean isValid(int[][] maze, int row, int col, boolean[][] visited) {
        return (row >= 0) && (row < maze.length) && (col >= 0) && (col < maze[0].length) &&
                (maze[row][col] == 0) && (!visited[row][col]);
    }

    static class Node {
        int row, col, dist;
        public Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
