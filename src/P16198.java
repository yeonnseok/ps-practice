import java.util.ArrayList;
import java.util.Scanner;

public class P16198 {

    private static Scanner sc = new Scanner(System.in);
    
    static int solve(ArrayList<Integer> a) {
        int n = a.size();
        if (n == 2) return 0;

        int ans = 0;
        for (int i = 1; i < n-1; i++) {
            int energy = a.get(i-1)* a.get(i+1); // i번째 구슬을 제거하고 얻을 수 있는 에너지
            ArrayList<Integer> b = new ArrayList<>(a);
            b.remove(i); // i번째 구슬을 제거
            energy += solve(b);
            if (ans < energy) {
                ans = energy;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = sc.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        System.out.println(solve(a));
    }
}
