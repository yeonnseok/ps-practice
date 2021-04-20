import java.util.*;
public class P16928 {
    
    private static Scanner sc = new Scanner(System.in);
    
    // 목표 : 주사위를 굴려서 도착점(100번칸)에 도착하기 위해 굴려야하는 최소 횟수
    public static void main(String[] args) {

        int n = sc.nextInt();
        int m = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        // 1. 맵 세팅
        int[] map = new int[101];
        for (int i = 0; i < 101; i++) {
            map[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int ladder = sc.nextInt();
            int toPos = sc.nextInt();
            map[ladder] = toPos;
        }
        for (int i = 0; i < m; i++) {
            int snake = sc.nextInt();
            int toPos = sc.nextInt();
            map[snake] = toPos;
        }
        
        int[] d = new int[101];
        d[1] = 1;
        q.offer(1);
        while(!q.isEmpty()) {
            int cur = q.poll();
            if (cur == 100) {
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int np = cur + i;
                if (np <= 100 && np >= 1 && d[map[np]] == 0) {
                    q.offer(map[np]);
                    d[map[np]] = d[cur] + 1;
                }
            }
        }
        System.out.println(d[100]-1);
    }
}
