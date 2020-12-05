package CodingPractice;

class MV {

    public static void main(String[] args) {
        int[] arr = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(arr,4));
    }
    public static double findMaxAverage(int[] nums, int k) {
        double l = Double.MAX_VALUE, r = Double.MIN_VALUE;
        for (int n : nums) {
            l = Math.min(l, n);
            r = Math.max(r, n);
        }
        System.out.println(l + " -- " + r);
        while (l + 1e-5 < r) {
            double mid = l + (r - l) / 2;
            System.out.println("The price is " + mid);
            if (tooSmall(nums, mid, k)) {
               // System.out.println();
                l = mid;
            }
            else
            {
                r =  mid;
            }

        }

        return l;
    }

    private static boolean tooSmall(int[] nums, double mid, int k) {
        // a)add first k element, check if >=0
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i] - mid;
        }
        if (sum >= 0) return true;

        // b)check element after k, track minSum before i-k, if sum-minSum>=0 return true.
        double minSum = 0, prevSum = 0;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prevSum += nums[i-k] - mid;
            minSum = Math.min(minSum, prevSum);

            if (sum - minSum >= 0) return true;
        }

        return false;
    }
}
