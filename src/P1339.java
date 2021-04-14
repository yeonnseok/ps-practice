import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class P1339 {
    /**
     * 서로 다른 알파벳이 10개 밖에 되지 않기 때문에, 각각의 알파벳에 0부터 9까지 적절히 넣어보면 된다.
     * 최대값을 구하는게 목적이므로 큰 수부터 필요할 알파벳 갯수만큼 사용한다.
     * 문자와 숫자의 값을 alpha라는 배열을 사용하여 아스키코드와 매칭하면 map을 사용하는 것 보다 시간 복잡도를 줄일 수 있다.
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

    static int[] alpha = new int[256];
    static int calc(String[] a, Character[] letters, int[] d) {
        int m = letters.length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            alpha[letters[i]] = d[i];
        }
        for (String s : a) {
            int now = 0;
            for (char x: s.toCharArray()) {
                now = now * 10 + alpha[x];
            }
            sum += now;
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        int n = sc.nextInt();
        String[] a = new String[n];
        HashSet<Character> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
            for (char x : a[i].toCharArray()) {
                s.add(x);
            }
        }
        Character[] letters = s.toArray(new Character[s.size()]);
        int m = letters.length;
        int[] d = new int[m];
        for (int i = 0; i < m; i++) {
            d[i] = 9 - i;
        }
        Arrays.sort(d); // 순열을 돌리기 위한 정렬 작업
        int ans = 0;
        do {
            int now = calc(a, letters, d);
            if (ans < now) {
                ans = now;
            }
        } while(next_permutation(d));
        System.out.println(ans);
    }
}
