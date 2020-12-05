package CodingPractice;

import java.util.*;

public class SetIntersection {

    Set<Integer> s1 = new HashSet<>(Arrays.asList(1,3,5,8,9));
    Set<Integer> s2 = new HashSet<>(Arrays.asList(1,89,5,21,10));
    Set<Integer> s3 = new HashSet<>(Arrays.asList(1,2,5,34,73));
    List<Integer> al = Arrays.asList(1,5, 72, 100);
    Set<Integer> finalSet = new HashSet<>();

    public static void main(String[] args) {
        int[] nums = {3,6,7,1,5};
        System.out.println(Arrays.toString(maxArray(nums, 4)));
        System.out.println(maximumSwap(2736));
    }

    public static int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        System.out.println(Arrays.toString(buckets));
        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;
    }

    public static int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < n; ++i) {
            while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
            if (j < k) ans[j++] = nums[i];
        }
        return ans;
    }

}
