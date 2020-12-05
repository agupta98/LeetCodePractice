package CodingPractice;

public class MaximumAs {

    public static void main(String[] args) {
        System.out.println(maxA(7));
    }

    public static int maxA(int N) {
        if (N <= 6) return N;
        int res = 0;
        for (int b = N - 3; b >= 1; b--) {
            System.out.println(b);
            int curr = (N - b - 1) * maxA(b);
            res = Math.max(res, curr);
        }
        return res;
    }
}
