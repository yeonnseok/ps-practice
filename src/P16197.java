import java.util.Scanner;

public class P16197 {
    static char[][] a;
    static int n;
    static int m;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        a = new char[n][m];
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        for (int i = 0; i < n; i++) {
            String values = sc.next();
            for (int j = 0; j < m; j++) {
                a[i][j] = values.charAt(j);
                if (a[i][j] == 'o') {
                    if (x1 == -1 && y1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                    a[i][j] = '.';
                }
            }
        }

        int ans = solve(0, x1, y1, x2, y2);
        System.out.println(ans);
    }

    static int solve(int step, int x1, int y1, int x2, int y2) {

        if (step == 11) return -1;
        boolean fall1 = false;
        boolean fall2 = false;
        if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m) fall1 = true;
        if (x2 < 0 || x2 >= n || y2 < 0 || y2 >= m) fall2 = true;
        
        if (fall1 && fall2) return -1;
        if (fall1 || fall2) return step;

        int ans = -1;
        for (int k = 0; k < 4; k++) {
            int nx1 = x1+dx[k];
            int ny1 = y1+dy[k];
            int nx2 = x2+dx[k];
            int ny2 = y2+dy[k];
            if (0 <= nx1 && nx1 < n && 0 <= ny1 && ny1 < m && a[nx1][ny1] == '#') {
                nx1 = x1;
                ny1 = y1;
            }
            if (0 <= nx2 && nx2 < n && 0 <= ny2 && ny2 < m && a[nx2][ny2] == '#') {
                nx2 = x1;
                ny2 = y1;
            }

            int temp = solve(step + 1, nx1, ny1, nx2, ny2);
            if (temp == -1) continue;
            if (ans == -1 || ans > temp) {
                ans = temp;
            }
        }
        return ans;
    }
}
