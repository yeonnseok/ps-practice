import java.util.*;
public class P9019 {

    private static Scanner sc = new Scanner(System.in);

    // 목표 : 네 자리 숫자 A와 B가 주어졌을 때 A-> B로 바꾸는 최소 연산 횟수를 구하라. (BFS)
    // 어떻게 
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean[] check = new boolean[10001];
            int[] dist = new int[10001];
            char[] how = new char[10001];
            int[] from = new int[10001];
            check[n] = true;
            dist[n] = 0;
            from[n] = -1;

            Queue<Integer> q = new LinkedList<>();
            q.offer(n);
            while(!q.isEmpty()) {
                int now = q.poll();
                int next = (now*2) % 10000;
                if (!check[next]) {
                    q.offer(next);
                    check[next] = true;
                    dist[next] = dist[now] + 1;
                    from[next] = now;
                    how[next] = 'D';
                }
                next = now-1;
                if (next == -1) next = 9999;
                if (!check[next]) {
                    q.offer(next);
                    check[next] = true;
                    dist[next] = dist[now] + 1;
                    from[next] = now;
                    how[next] = 'S';
                }
                next = (now%1000)*10 + now/1000;
                if (!check[next]){
                    q.offer(next);
                    check[next] = true;
                    dist[next] = dist[now] + 1;
                    from[next] = now;
                    how[next] = 'L';
                }
                next = (now/10) + (now%10)*1000;
                if (!check[next]) {
                    q.offer(next);
                    check[next] = true;
                    dist[next] = dist[next] + 1;
                    from[next] = now;
                    how[next] = 'R';
                }
            }
            StringBuilder ans = new StringBuilder();
            while(m != n) {
                ans.append(how[m]);
                m = from[m];
            }
            System.out.println(ans.reverse());
        }
    }
    
}
