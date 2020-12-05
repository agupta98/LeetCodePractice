package CodingPractice;

public class MysCloseTotarget {

    public static void main(String[] args) {
      //  key >> (c - 'A') & 1

        System.out.println(1 << 2);
       // System.out.println(7 & ~ (1 << 2));
      //  int[] arr = {9,12,3,7,15};
      //  int target = 5;
       // System.out.println(closestToTarget(arr, target));

    }


    public static int closestToTarget(int[] arr, int t) {
        int m = arr.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int sum = arr[i];
            for (int r = i; r < m; r++) {
                sum = sum & arr[r];
                res = Math.min(res, Math.abs(sum - t));
                System.out.println(res);
                if (sum <= t) break;   // sum is decreasing within inner loop. and now sum < t, res won't be lower.
            }
            if (sum >= t) break; // the future sum won't be smaller than this sum, so res won't be smaller
            //current sum = arr[i] & ... & arr[n -1], which is the smallest, any further sum after this loop,
            // which will be arr[k] & ... & arr[n -1], where k > i, will be larger than current sum;
        }
        return res;
    }
}


// Bit Manipulation

//        Set union A | B
//        Set intersection A & B
//        Set subtraction A & ~B
//        Set negation ALL_BITS ^ A or ~A
//        Set bit A |= 1 << bit
//        Clear bit A &= ~(1 << bit)
//        Test bit (A & 1 << bit) != 0
//        Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))
//        Remove last bit A&(A-1)
//        Get all 1-bits ~0
