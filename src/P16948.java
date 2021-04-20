import java.util.*;
public class P16948 {
    static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0, 0, 2, 2, -2, -2};
    static int[] dy = {-2, 2, -1, 1, -1, 1};

    private static Scanner sc = new Scanner(System.in);

    // 목표 : 데스나이트가 출발지에서 목적지까지 이동하는 최소 이동 횟수를 구하라 (BFS)
    public static void main(String[] args) {
        int n = sc.nextInt();
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        int[][] d = new int[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = -1;
            }
        }
        d[x1][y1] = 0;
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x1, y1));
        while(!q.isEmpty()) {
            Position cur = q.poll();
            if (cur.x == x2 && cur.y == y2) {
                break;
            }
            for (int k = 0; k < 6; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (0 <= nx && n > nx && 0 <= ny && n > ny) {
                    if (d[nx][ny] == -1) {
                        q.offer(new Position(nx, ny));
                        d[nx][ny] = d[cur.x][cur.y] + 1;
                    }
                }
            }
        }
        System.out.println(d[x2][y2]);
    }
    
}
