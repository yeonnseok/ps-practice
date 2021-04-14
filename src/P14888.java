import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P14888 {
    
    /**
     * 앞에서 부터 계산하면 된다.
     * N 제한이 11이 때문에 경우의 수는 10! (3628800) 
     * 하지만 연산자의 종류는 총 4개밖에 안되기 때문에 중복인 경우를 제외하면 최대의 경우는 2520개 밖에 안된다.
     * 연산자의 순서를 순열로 돌린다.
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

    static int calc(int[] a, int[] b) {
        int n = a.length;
        int ans = a[0];
        for (int i = 1; i < n; i++) {
            if (b[i-1] == 0) {
                ans = ans + a[i];
            } else if (b[i-1] == 1) {
                ans = ans - a[i];
            } else if (b[i-1] == 2) {
                ans = ans * a[i];
            } else {
                ans = ans / a[i];
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {

        int n = sc.nextInt();
        int[] a = new int[n]; // 숫자
        for (int i = 0; i < n; i ++) {
            a[i] = sc.nextInt();
        }
        int[] b = new int[n-1]; // 연산자 갯수 (핵심 로직)
        int m = 0;
        for (int i = 0; i < 4; i++) {
            int cnt = sc.nextInt();
            for (int k = 0; k < cnt; k++) {
                b[m++] = i;
            }
        } // [0, 0, 1, 1, 1, 2, 3]
        
        Arrays.sort(b);
        ArrayList<Integer> result = new ArrayList<>();
        do {
            int temp = calc(a, b);
            result.add(temp);
        } while(next_permutation(b));
        Collections.sort(result);
        m = result.size();
        System.out.println(result.get(m-1));
        System.out.println(result.get(0));
    }
}
