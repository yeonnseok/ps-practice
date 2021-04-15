import java.util.Scanner;

public class P1182 {

    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        solve(a, 0, 0, s); // a, index, sum

        if (s == 0) answer -= 1;
        System.out.println(answer); // 
    }

    static void solve(int[] a, int index, int sum, int s) {
        if (index == a.length && sum == s) {
            answer += 1;
            return;
        }

        if (index == a.length && sum != s) {
            return;
        }

        solve(a, index + 1, sum + a[index], s);
        solve(a, index + 1, sum, s);
    }
    
}
