import java.util.*;

public class P14502 {

    private static Scanner sc = new Scanner(System.in);

    static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 목표 : 연구소의 안전 영역 크기의 최대값을 구한다.
    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int answer = 0;

        // 연구소 지도 세팅
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }


        // 바이러스는 상하좌우 인접한 빈 칸으로 모두 펴져나간다.
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        // 새로 세울 수 있는 벽의 개수는 3개이다.
        for (int x1 = 0; x1 < n; x1++) {
            for (int y1 = 0; y1 < m; y1++) {
                if (map[x1][y1] != 0) continue;
                for (int x2 = 0; x2 < n; x2++) {
                    for (int y2 = 0; y2 < m; y2++) {
                        if (map[x2][y2] != 0) continue;
                        for (int x3 = 0; x3 < n; x3++) {
                            for (int y3 = 0; y3 < n; y3++) {
                                if (map[x3][y3] != 0) continue;
                                if (x1 == x2 && y1 == y2) continue;
                                if (x1 == x3 && y1 == y3) continue;
                                if (x2 == x3 && y2 == y3) continue;
                                        
                                map[x1][y1] = 1;
                                map[x2][y2] = 1;
                                map[x3][y3] = 1;

                                int[][] check = new int[n][m];
                                Queue<Position> q = new LinkedList<>();
                                for (int i = 0; i < n; i ++) {
                                    for (int j = 0; j < m; j++) {
                                        check[i][j] = map[i][j];
                                        if (check[i][j] == 2) {
                                            q.offer(new Position(i, j));
                                        }
                                    }
                                }

                                while(!q.isEmpty()) {
                                    Position cur = q.poll();
                                    for (int k = 0; k < 4; k++) {
                                        int nx = cur.x + dx[k];
                                        int ny = cur.y + dy[k];

                                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                            if (check[nx][ny] == 0) {
                                                q.offer(new Position(nx, ny));
                                                check[nx][ny] = 2;
                                            }
                                        }
                                    }
                                }

                                int count = 0;
                                for (int i = 0; i < n; i ++) {
                                    for (int j = 0; j < m; j++) {
                                        if (check[i][j] == 0) {
                                            count++;
                                        }
                                    }
                                }
                                answer = Math.max(answer, count);

                                map[x1][y1] = 0;
                                map[x2][y2] = 0;
                                map[x3][y3] = 0;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
    
}
