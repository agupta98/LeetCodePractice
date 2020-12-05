public class Ama1 {
    public static void main(String[] args) {
        System.out.println("1234".compareTo("2345"));
        Ama1 ama1 = new Ama1();
      //  System.out.println(ama1.atMostNGivenDigitSet(new String[]{"1","3","5","7"}, 100));
        System.out.println(ama1.atMostNGivenDigitSet(new String[]{"1","4","9"}, 1000000000));
    }

    int count = 0;
    String num;
    int k;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        num = String.valueOf(n);
        k = num.length();
        for (int i = 1; i < k;i++) {
            count += Math.pow(digits.length, i);
        }
        System.out.println(count);
        dfs (0, 0, digits);
        return count;
    }

    private void dfs(long cur, int pos, String[] digits) {
        if (pos == k) {
            System.out.println("hello");
            count+=1;
            return;
        }
        for (String s: digits)
        {
            if ('0'+Integer.parseInt(s) < num.charAt(pos))
                count += Math.pow( digits.length, k-1-pos);
            else if ('0'+Integer.parseInt(s) == num.charAt(pos))
                dfs(cur*10+Integer.parseInt(s), pos+1, digits);
        }

    }

}


/*
class Solution {
    int count = 0;
    string num;
    int K;

public:
    int atMostNGivenDigitSet(vector<string>& D, int N)
    {
        num = to_string(N);
        K = num.size();

        for (int i=1; i<=K-1; i++)
            count += pow(D.size(), i);

        DFS(0, 0, D);

        return count;
    }

    void DFS(long cur, int pos, vector<string>& D)
    {
        if (pos==K)
        {
            count+=1;
            return;
        }

        for (string s: D)
        {
            if ('0'+stoi(s) < num[pos])
                count += pow( D.size(), K-1-pos);
            else if ('0'+stoi(s) == num[pos])
                DFS(cur*10+stoi(s), pos+1, D);
        }
    }
};
 */
