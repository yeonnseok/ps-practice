import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P14889 {
    
    /**
     * 스타트와 링크
     * 팀을 절반의 인원으로 나누기 때문에 순열로 풀 수 있다. (0이 몇개 이고, 1이 몇개인지 보장이 되기 때문)
     * 
     */
    private static final Scanner sc = new Scanner(System.in);
    
    static boolean next_permutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1; 
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length -1;
        while(a[j] <= a[i-1]) {
            j-=1;
        }
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length -1;
        while(i < j) {
            temp  = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        int[] b = new int[n];
        for (int i = 0; i < n/2; i++) {
            b[i] = 1; // 나머지는 0이 들어가 있다.
        }
        Arrays.sort(b);
        int ans = 2147483647;
        do {
            ArrayList<Integer> first = new ArrayList<>();
            ArrayList<Integer> second = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (b[i] == 0) {
                    first.add(i);
                } else {
                    second.add(i);
                }
            }
            int one = 0;
            int two = 0;
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n/2; j++) {
                    if (i == j) continue;
                    one += a[first.get(i)][first.get(j)];
                    two += a[second.get(i)][second.get(j)];
                }
            }
            int diff = one - two;
            if (diff < 0) diff = -diff;
            if (ans > diff) ans = diff;
        } while(next_permutation(b));
        System.out.println(ans);
    }
}
