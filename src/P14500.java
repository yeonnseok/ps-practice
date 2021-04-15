import java.util.Scanner;

public class P14500 {

    /**
     * 테트로미노 하나만 적절히 놓으면 된다.
     * 백트래킹
     * @param args
     */
     static int[][] a;
     static int n, m;
     static boolean[][] c;
     static int[] dx = {0, 0, 1, -1};
     static int[] dy = {1, -1, 0, 0};
     static int answer = 0;
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                solve(i, j, 0, 0); // i, j, cnt, sum // 한획으로 그릴수 있는 테트로미노
                // 밑에는 빠꾸가 안되기때문에 수동으로 합을 구한다.
                if (j + 2 < m) {
                    int temp = a[i][j] + a[i][j+1] + a[i][j+2];
                    if (i-1 >= 0) { // ㅗ
                        int temp2 = temp + a[i-1][j+1];
                        if (answer < temp2) answer = temp2;
                    }
                    if (i+1 < n) { // ㅜ
                        int temp2 = temp + a[i+1][j+1];
                        if (answer < temp2) answer = temp2;
                    }
                }
                if (i+2 < n) { 
                    int temp = a[i][j] + a[i+1][j] + a[i+2][j];
                    if (j+1 < m) { // ㅏ
                        int temp2 = temp + a[i+1][j+1];
                        if (answer < temp2) answer = temp2;
                    }
                    if (j-1 >= 0) {  // ㅓ
                        int temp2 = temp + a[i+1][j-1];
                        if (answer < temp2) answer = temp2;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static void solve(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            if (answer < sum) answer = sum;
            return;
        }

        if (x < 0 || x >= n || y < 0 || y >= m) {
            return ;
        }
        if (c[x][y]) return;
        c[x][y] = true;
        for (int k = 0; k < 4; k++) {
            solve(x+dx[x], y+dy[k], cnt+1, sum+a[x][y]);
        }
        c[x][y] = false;
    }
}