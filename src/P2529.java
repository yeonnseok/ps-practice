import java.util.Scanner;

public class P2529 {
    /**
     * 모든 경우의 수를 다 해보면 시간 복잡도가 너무 커진다.
     * 어떤 수가 들어가느냐보다, 부등호만 만족시키는 가장 큰 수와 작은 수를 찾으면 되는 것이기 때문에 
     * 가장 큰 수는 9 부터 역순으로 가장 작은 수는 0부터 순열을 돌려서 부등호를 만족하는 수를 찾으면 된다.
     * 시간 복잡도는 (k+1)! * (k+1)로 일반적인 순열 문제의 시간 복잡도가 나온다.
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
        int j = a.length - 1;
        while (a[j] <= a[j-1]) {
            j -= 1;
        }
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp; // swap

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }

    static boolean prev_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] <= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length-1;
        while (a[j] >= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }

    static boolean check(int[] perm, char[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '<' && perm[i] > perm[i+1]) {
                return false;
            }
            if (a[i] == '>' && perm[i] < perm[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int k = sc.nextInt();
        char[] a = new char[k];
        for (int i = 0; i < k; i++) {
            a[i] = sc.next().charAt(0);
        }

        int[] small = new int[k+1];
        int[] big = new int[k+1];
        for (int i = 0; i < k+1; i++) {
            small[i] = i;
            big[i] = 9-i;
        }

        do {
            if (check(small, a)) {
                break;
            }
        } while(next_permutation(small));
        do {
            if (check(big, a)) {
                break;
            }
        } while(prev_permutation(big));

        for (int i = 0; i < big.length; i++) {
            System.out.print(big[i]);
        }
        System.out.println();
        for (int i = 0; i < small.length; i++) {
            System.out.print(small[i]);
        }
        System.out.println();
    }
}
